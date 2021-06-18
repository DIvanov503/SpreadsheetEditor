package spreadsheet;

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

    String getFormula();

    void setValue(Object value);
}
