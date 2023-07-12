import org.dom4j.DocumentException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws DocumentException, IOException {
        YmlToJson yml = new YmlToJson();
        CopyCatalogOffer catalogOffer = new CopyCatalogOffer();
        UpdateYmlFile updateYmlFile = new UpdateYmlFile();
        catalogOffer.copyCatalog(2000, "C:\\Users\\iaros\\OneDrive\\Рабочий стол\\kazan.xml",
                "C:\\Users\\iaros\\OneDrive\\Рабочий стол\\testXml.xml");
        yml.ymlToJson("C:\\Users\\iaros\\OneDrive\\Рабочий стол\\testXml.xml",
                "C:\\Users\\iaros\\OneDrive\\Рабочий стол\\testJson.json");
        updateYmlFile.updateYmlFile("C:\\Users\\iaros\\OneDrive\\Рабочий стол\\testXml.xml");
    }
}
