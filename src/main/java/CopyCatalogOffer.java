
//"C:\\Users\\iaros\\OneDrive\\Рабочий стол\\kazan.xml"
//"C:\\Users\\iaros\\OneDrive\\Рабочий стол\\kazan_new.xml"

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CopyCatalogOffer {

    public void copyCatalog(int needOffers, String oldCatalog, String newCatalogue) throws DocumentException, IOException {
        // Открываем файл catalog.xml и создаем Document
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(oldCatalog));
        // Получаем корневой элемент каталога
        Element catalog = document.getRootElement();

// Выбираем нужные элементы с помощью XPath запросов
        List<Element> categorys = catalog.selectNodes("//yml_catalog/shop/categories/category[position()]");
        List<Element> items = catalog.selectNodes("//yml_catalog/shop/offers/offer[position() <= " + needOffers + "]");
        // Создаем новый Document
        Document newDocument = DocumentHelper.createDocument();

// Создаем корневой элемент каталога и добавляем его в Document
        Element newCatalog = newDocument.addElement("yml_catalog");
        newCatalog.addAttribute("date", "2023-02-07 12:58");

// Создаем подэлементы каталога и добавляем их в новый каталог
        Element newShop = newCatalog.addElement("shop");
        Element name = newShop.addElement("name");
        name.addText("Мегастрой");
        Element company = newShop.addElement("company");
        company.addText("Мегастрой");
        Element url = newShop.addElement("url");
        url.addText("https://kazan.megastroy.com/");
        Element currencies = newShop.addElement("currencies");
        Element currency = currencies.addElement("currency");
        currency.addAttribute("id", "RUR").addAttribute("rate", "1");
        Element categories = newShop.addElement("categories");
        Element newOffers = newShop.addElement("offers");


        for (Element category : categorys) {
            categories.add((Element) category.clone());
        }
        for (Element item : items) {
            newOffers.add((Element) item.clone());
        }

// Записываем новый файл
        try {
            XMLWriter writer = new XMLWriter(new FileWriter(new File(newCatalogue)));
            writer.write(newDocument);
            writer.close();
            System.out.println("XML успешно скопирован и создан");
        } catch (IOException e) {
            System.out.println("Ошибка при создании документа");
        }
    }
}