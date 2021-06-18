package spreadsheet;

import formula.AST.Formula;
import formula.parser.FormulaParser;
import formula.Type;
import formula.evaluator.DependencyGraph;
import formula.evaluator.DependencyVisitor;
import org.w3c.dom.Element;

public class Cell implements ICell {

    private Formula formula;
    private String formulaRaw;
    private Object value;
    private Type type = Type.Undef;
    int row;
    final Column column;

    Cell(Column column, int row) {
        this.row = row;
        this.column = column;
    }

    Cell(Column column, int row, Object value) {
        this(column, row);
        setValue(value);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String getFormula() {
        return null;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof String) {
            String strValue = (String) value;
            try {
                int intValue = Integer.parseInt(strValue);
                this.value = intValue;
                type = Type.Int;
                formula = null;
                formulaRaw = null;
                return;
            } catch (NumberFormatException e) { }
            try {
                double doubleValue = Integer.parseInt(strValue);
                this.value = doubleValue;
                type = Type.Double;
                formula = null;
                formulaRaw = null;
                return;
            } catch (NumberFormatException e) { }
            if (strValue.matches("(true|TRUE|false|FALSE)")) {
                boolean boolValue = Boolean.parseBoolean(strValue);
                this.value = boolValue;
                type = Type.Bool;
                formula = null;
                formulaRaw = null;
                return;
            }
            if (strValue.matches("\"[^\"]\"")) {
                this.value = strValue;
                type = Type.String;
                formula = null;
                formulaRaw = null;
                return;
            }
            /* Just to try out the parser
             * TODO handle formula */
            if (strValue.matches("^=.*")) {
                FormulaParser parser = FormulaParser.getParser();
                Formula formula = parser.parse(strValue.substring(1));
                if (formula != null) {
                    this.formula = formula;
                    this.formulaRaw = strValue;
                }
            } else {
                formulaRaw = null;
            }
        }
        this.value = value;
    }

    public void save(Element cellElement) {
        cellElement.setAttribute("type", type.name());
        cellElement.setAttribute("value", value.toString());
        cellElement.setAttribute("formula", formulaRaw);
    }

    public static Cell cellFromXMLElement(Element cellElement, Column column) {
        Cell result = new Cell(column, Integer.parseInt(cellElement.getAttribute("index")));
        String formula = cellElement.getAttribute("formula");
        if (formula == null || formula.length() == 0){
            result.setValue(cellElement.getAttribute("value"));
        } else {
            result.setValue(formula);
        }
        return result;
    }

    public void addDependencies(DependencyGraph dependencyGraph){
        if (formula == null) {
            return;
        }
        DependencyVisitor.getDependencyVisitor().addDependencies(dependencyGraph, formula, getCellReference());
    }

    public String getCellReference() {
        return column.sheet.getName() + "." + ColumnLabelConverter.toLabel(column.getColNumber()) + row;
    }
}
