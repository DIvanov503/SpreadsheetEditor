package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.nio.file.ClosedWatchServiceException;

public class CellRenderer extends DefaultTableCellRenderer {

    private static Color defaultForegroundColor = Color.BLACK;
    private static Color defaultBackgroundColor = Color.WHITE;
    private static Color headerForegroundColor = defaultForegroundColor;
    private static Color headerBackgroundColor = Color.LIGHT_GRAY;
    private static Color dropCellForegroundColor = defaultForegroundColor;
    private static Color dropCellBackgroundColor = Color.PINK;
    private static Color focusCellForegroundColor = defaultForegroundColor;
    private static Color focusCellBackgroundColor = Color.CYAN;
    private static Color selectionForegroundColor = Color.CYAN;
    private static Color selectionBackgroundColor = Color.BLACK;

    private static CellRenderer renderer;

    private CellRenderer() {
        //setBackground(Color.LIGHT_GRAY);
    }

    public static CellRenderer getRenderer() {
        if (renderer == null) {
            renderer = new CellRenderer();
        }
        return renderer;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return super.getTableCellRendererComponent(table, value, table.isRowSelected(row) && table.isColumnSelected(column), hasFocus, row, column);
    }
}
