package gui;

import spreadsheet.ISheet;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class SheetJTable extends JTable {
    private JTable rowTableHeader = new JTable(new AbstractTableModel() {
        @Override
        public int getRowCount() {
            return SheetJTable.this.getRowCount();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return rowIndex;
        }
    });


    SheetJTable(ISheet sheet) {
        super(new SheetJTableModel(sheet));
        setColumnSelectionAllowed(false);
        setRowSelectionAllowed(false);
        setAutoResizeMode(AUTO_RESIZE_OFF);
        getTableHeader().setReorderingAllowed(false);
        rowTableHeader.setFocusable(false);
        rowTableHeader.getTableHeader().setReorderingAllowed(false);
        rowTableHeader.getTableHeader().setReorderingAllowed(false);
        rowTableHeader.setDefaultRenderer(Object.class, rowTableHeader.getTableHeader().getDefaultRenderer());
        rowTableHeader.getTableHeader().getColumnModel().getColumn(0).setHeaderValue(" ");
        rowTableHeader.getPreferredScrollableViewportSize().width = rowTableHeader.getColumnModel().getColumn(0).getPreferredWidth();
    }

    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        return CellRenderer.getRenderer();
    }

    public JTable getRowTableHeader() {
        return rowTableHeader;
    }
}
