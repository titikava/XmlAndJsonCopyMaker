//"C:\\Users\\iaros\\OneDrive\\Рабочий стол\\kazan_new.xml"

import org.json.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class YmlToJson {

    public void ymlToJson(String xml, String newJson) {
        try {
            // Загрузка XML-файла
            File inputFile = new File(xml);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            // Нахождение всех offer элементов
            NodeList offersList = doc.getElementsByTagName("offer");

            // Cоздание массива JSON-объектов на основе offer элементов
            JSONArray productsArray = new JSONArray();
            for (int i = 0; i < offersList.getLength(); i++) {
                Node offerNode = offersList.item(i);
                if (offerNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element offerElement = (Element) offerNode;

                    // Получение значений из тегов
                    String productId = offerElement.getAttribute("id");
                    String name = offerElement.getElementsByTagName("name").item(0).getTextContent();
                    int price = Integer.parseInt(offerElement.getElementsByTagName("price").item(0).getTextContent());
                    String desc = offerElement.getElementsByTagName("description").item(0).getTextContent();
                    String img = offerElement.getElementsByTagName("picture").item(0).getTextContent();

                    // Cоздание JSON-объекта
                    JSONObject productObject = new JSONObject();
                    productObject.put("id", productId);
                    productObject.put("name", name);
                    productObject.put("price", price);
                    productObject.put("desc", desc);
                    productObject.put("img", img);

                    // Добавление объекта к массиву
                    productsArray.put(productObject);
                }
            }

            try {
                // Создание FileWriter объекта
                FileWriter fileWriter = new FileWriter(newJson);

                // Запись в файл JSON-массива
                fileWriter.write(productsArray.toString());

                // Закрытие FileWriter
                fileWriter.close();

                System.out.println("Json успешно создан");

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка при создании json");
            }

        } catch (ParserConfigurationException | SAXException | IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
