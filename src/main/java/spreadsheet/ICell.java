package spreadsheet;

import formula.AST.Formula;

/**
 * This interface represents a cell.
 */
public interface ICell {

    /**
     * Get cell value.
     *
     * @return value object
     */
    Object getValue();

    String getFormulaString();

    Formula getFormula();

    ISheet getSheet();

    void setValue(Object value);

    void updateValue(Object value);
}
