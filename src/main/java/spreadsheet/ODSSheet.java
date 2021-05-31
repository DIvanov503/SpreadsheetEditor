package spreadsheet;

import formula.parser.ODSFormulaLexer;
import formula.parser.ODSFormulaParserLexer;
import formula.parser.ODSFormulaParserParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jopendocument.dom.spreadsheet.Sheet;

public class ODSSheet implements ISheet {
    Sheet sheet;

    ODSSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public void remove() {
        sheet.detach();
    }

    @Override
    public ODSCell getCellAt(int row, int col) {
        return new ODSCell(sheet.getCellAt(col, row));
    }

    @Override
    public ODSCell getCellAt(String ref) {
        return new ODSCell(sheet.getCellAt(ref));
    }

    @Override
    public Object getValueAt(int row, int col) {
        return sheet.getRowCount() <= row || sheet.getColumnCount() <= col ? null : sheet.getValueAt(col, row);
    }

    @Override
    public Object getValueAt(String ref) {
        return sheet.getValueAt(ref);
    }

    @Override
    public void removeColumn(int index, boolean keepWidth) {
        sheet.removeColumn(index, keepWidth);
    }

    @Override
    public void removeColumns(int first, int last, boolean keepWidth) {
        sheet.removeColumn(first, last, keepWidth);
    }

    @Override
    public void removeRow(int index, boolean keepLength) {
        sheet.removeRow(index);
    }

    @Override
    public void removeRows(int first, int last, boolean keepLength) {
    }

    @Override
    public String getName() {
        return sheet.getName();
    }

    @Override
    public void setName(String name) {
        sheet.setName(name);
    }

    @Override
    public void setCellAt(ICell cell, int row, int col) {
        sheet.getCellAt(col, row).setValue(cell.getValue());
    }

    @Override
    public int getRowCount() {
        return sheet.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return sheet.getColumnCount();
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        sheet.ensureColumnCount(col+1);
        sheet.ensureRowCount(row+1);
        if (value instanceof String) {
            /* Just to try out the parser
            * TODO handle formula */
            String strValue = (String) value;
            if (strValue.matches("^=")) {
                ODSFormulaLexer lexer = new ODSFormulaLexer(CharStreams.fromString(strValue));
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                ODSFormulaParserParser parser = new ODSFormulaParserParser(tokens);
                ParseTree tree = parser.formula();
                tree.toStringTree(parser);
                System.out.println(tree.toStringTree());
            }
        }
        sheet.setValueAt(value, col, row);
    }
}
