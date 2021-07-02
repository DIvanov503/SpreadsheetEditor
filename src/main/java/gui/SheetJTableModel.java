package gui;

import formula.evaluator.CyclicDependencyException;
import spreadsheet.ISheet;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class SheetJTableModel extends AbstractTableModel implements AdjustmentListener {

    private int rowCount;
    private int columnCount;

    ISheet sheet;

    SheetJTableModel(ISheet sheet) {
        this.sheet = sheet;
        rowCount = Math.max(sheet.getRowCount(), 70);
        columnCount = Math.max(sheet.getColumnCount(), 30);
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        return sheet.getCellAt(rowIndex + 1, columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        sheet.setValueAt(aValue, rowIndex + 1, columnIndex);/*
        try {
            MainJFrame.getSpreadsheet().calculate();
        } catch (CyclicDependencyException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        Adjustable scroll = e.getAdjustable();
        if (scroll.getValue() + scroll.getVisibleAmount() >= scroll.getMaximum()) {
            if (scroll.getOrientation() == Adjustable.HORIZONTAL) {
                columnCount *= 1.1;
            } else if (scroll.getOrientation() == Adjustable.VERTICAL) {
                rowCount *= 1.1;
            }
            fireTableStructureChanged();
        }
        fireTableDataChanged();
    }
}
