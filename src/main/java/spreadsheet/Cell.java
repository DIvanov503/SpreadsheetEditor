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
    public String getFormulaString() {
        return formulaRaw;
    }

    @Override
    public Formula getFormula() {
        return formula;
    }

    @Override
    public ISheet getSheet() {
        return column.getSheet();
    }

    @Override
    public void setValue(Object value) {
        if (formula != null) {
            getSheet().getSpreadsheet().getDependencyGraph().removeDependenciesFrom(this);
        }
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
            if (strValue.matches("^=.*")) {
                FormulaParser parser = FormulaParser.getParser();
                Formula formula = parser.parse(strValue.substring(1));
                if (formula != null) {
                    this.formula = formula;
                    this.formulaRaw = strValue;
                    DependencyVisitor.getDependencyVisitor().addDependencies(
                            getSheet().getSpreadsheet().getDependencyGraph(),
                            formula,
                            this);
                }
            } else {
                formulaRaw = null;
            }
        }
        this.value = value;
    }

    @Override
    public void updateValue(Object value) {
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
        DependencyVisitor.getDependencyVisitor().addDependencies(dependencyGraph, formula, this);
    }

    public String getCellReference() {
        return column.getSheet().getName() + "." + ColumnLabelConverter.toLabel(column.getColNumber()) + row;
    }
}
