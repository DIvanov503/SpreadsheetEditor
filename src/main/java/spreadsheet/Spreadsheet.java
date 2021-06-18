package spreadsheet;

import formula.evaluator.CyclicDependencyException;
import formula.evaluator.DependencyGraph;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Spreadsheet implements ISpreadsheet {
    private File file;
    //private Map<Integer, Column> columnMap = new HashMap<Integer, Column>();
    private final List<Sheet> sheetList = new ArrayList<>();
    boolean isModified = false;
    String fileName = null;
    DependencyGraph dependencyGraph;

    @Override
    public void open(String fileName) throws IOException, ParserConfigurationException, SAXException {
        file = new File(fileName);
        sheetList.clear();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(fileName));
        Element root = doc.getDocumentElement();
        NodeList childNodeList = root.getChildNodes();
        for (int i = 0; i < childNodeList.getLength(); ++i) {
            Node node = childNodeList.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element sheetElement = (Element)node;
            sheetList.add(Sheet.sheetFromXMLElement(this, sheetElement));
        }
        this.fileName = fileName;
        isModified = false;
    }

    @Override
    public void save(String fileName) throws IOException, TransformerConfigurationException, ParserConfigurationException {
        file = new File(fileName);
        save();
        this.fileName = fileName;
        isModified = false;
    }

    @Override
    public void save() throws IOException, ParserConfigurationException, TransformerConfigurationException {
        FileWriter writer = new FileWriter(file);
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("spreadsheet");
        document.appendChild(root);
        for (Sheet sheet : sheetList) {
            Element sheetElement = document.createElement("sheet");
            root.appendChild(sheetElement);
            sheet.save(sheetElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(System.out);//(new File(xmlFilePath));

        try {
            transformer.transform(domSource, streamResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        System.out.println("Done creating XML File");
        writer.flush();
        isModified = false;
    }

    @Override
    public void empty() {
        sheetList.clear();
        sheetList.add(new Sheet(this));
        fileName = null;
        isModified = false;
    }

    @Override
    public int getSheetCount() {
        return sheetList.size();
    }

    @Override
    public Sheet addSheet() {
        isModified = true;
        return new Sheet(this, "Sheet" + (getSheetCount() + 1));
    }

    @Override
    public List<Sheet> getSheetList() {
        return sheetList;
    }

    @Override
    public Sheet getSheet(int index) {
        return sheetList.get(index);
    }

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void calculate() throws CyclicDependencyException {
        if (dependencyGraph == null) {
            dependencyGraph = new DependencyGraph();
        }
        sheetList.forEach(s -> s.addDependencies(dependencyGraph));
        dependencyGraph.topologicalSort();

    }
}
