
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.*;

public class UpdateYmlFile {
    public void updateYmlFile(String catalog) {
        try {
            // Загружаем XML файл
            File inputFile = new File(catalog);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            // Получаем список всех тегов offers
            NodeList offersList = doc.getElementsByTagName("offer");

            // Изменяем значение узла url в каждом узле offer
            for (int i = 0; i < offersList.getLength(); i++) {
                Node offerNode = offersList.item(i);
                Element offerElement = (Element)offerNode;

                // Получаем значение атрибута id
                String itemId = offerElement.getAttribute("id");

                // Получаем тег url и изменяем его значение
                NodeList urlNodes = offerElement.getElementsByTagName("url");
                Element urlElement = (Element)urlNodes.item(0);
                String newUrl = "http://${URL_HOST()}:3456/castlemock/mock/rest/project/doc9TU/application/vVr2SX/" +
                        "item.html?aid=${QUERY_STRING(query=\"aid\")}&itemid=" + itemId;
                urlElement.setTextContent(newUrl);
            }

            // Записываем изменения в исходный файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(catalog));
            transformer.transform(source, result);

            System.out.println("Файл успешно обновлен!");

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
