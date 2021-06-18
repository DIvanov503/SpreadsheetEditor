package spreadsheet;

import formula.evaluator.DependencyGraph;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Sheet implements ISheet {
    private Map<Integer, Column> columnMap = new HashMap<Integer, Column>();
    private String name = "Sheet1";
    private int rowCount = 0;
    private int columnCount = 0;
    private final Spreadsheet spreadsheet;

    public Sheet(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
    }

    public Sheet(Spreadsheet spreadsheet, String name) {
        this(spreadsheet);
        this.name = name;
    }

    @Override
    public void remove() {

    }

    @Override
    public ICell getCellAt(int row, int col) {
        Column column = getColumnAt(col);
        return column == null ? null : column.getCellAt(row);
    }

    @Override
    public Column getColumnAt(int col) {
        return columnMap.getOrDefault(col, null);
    }

    @Override
    public Object getValueAt(int row, int col) {
        ICell cell = getCellAt(row, col);
        return cell == null ? null : cell.getValue();
    }

    @Override
    public void removeColumn(int index, boolean keepWidth) {
        columnMap.remove(index);
        if (!keepWidth) {
            columnMap.keySet().stream().filter(i -> i > index).sorted()
                    .forEach(i -> {
                        columnMap.get(i).setColNumber(i - 1);
                        columnMap.put(i - 1, columnMap.get(i));
                        columnMap.remove(i);
                    });
        }
    }

    @Override
    public void removeColumns(int first, int last, boolean keepWidth) {
        for (int i = first; i < last; ++i) {
            removeColumn(i, true);
        }
        removeColumn(last, keepWidth);
    }

    @Override
    public void removeRow(int index, boolean keepLength) {

    }

    @Override
    public void removeRows(int first, int last, boolean keepLength) {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Column column = getColumnAt(col);
        if (column == null) {
            column = new Column(this, col);
            columnMap.put(col, column);
        }
        column.setValueAt(value, row);
    }

    public void save(Element sheetElement) {
        sheetElement.setAttribute("name", name);
        columnMap.forEach((colNum, column) -> {
            Element columnElement = sheetElement.getOwnerDocument().createElement("column");
            sheetElement.appendChild(columnElement);
            columnElement.setAttribute("index", colNum.toString());
            column.save(columnElement);
        });
    }

    public static Sheet sheetFromXMLElement(Spreadsheet spreadsheet, Element sheetElement) {
        Sheet result = new Sheet(spreadsheet);
        result.setName(sheetElement.getAttribute("name"));
        NodeList childNodeList = sheetElement.getChildNodes();
        for (int i = 0; i < childNodeList.getLength(); ++i) {
            Node node = childNodeList.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element columnElement = (Element)node;
            result.columnMap.put(Integer.parseInt(columnElement.getAttribute("index")),
                    Column.columnFromXMLElement(result, columnElement));
        }
        return result;
    }

    public void addDependencies(DependencyGraph dependencyGraph){
        columnMap.values().forEach(column -> column.addDependencies(dependencyGraph));
    }
}
