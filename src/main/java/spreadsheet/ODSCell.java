package spreadsheet;

import org.jopendocument.dom.spreadsheet.Cell;

public class ODSCell implements ICell {
    Cell cell;

    ODSCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public Object getValue() {
        return cell.getValue();
    }
}
