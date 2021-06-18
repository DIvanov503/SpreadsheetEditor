package spreadsheet;

/**
 * This interface represents a cell.
 */
public interface IColumn {
    ICell getCellAt(int row);

    void setValueAt(Object value, int row);

    void setColNumber(int colNumber);

    int getColNumber();
}
