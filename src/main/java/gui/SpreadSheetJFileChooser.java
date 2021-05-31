package gui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SpreadSheetJFileChooser extends JFileChooser {

    private static FileFilter fileFilters[] = {
            new FileNameExtensionFilter("OpenDocument spreadsheet (*.ods)", "ods")
    };

    private static SpreadSheetJFileChooser spreadSheetJFileChooser;

    private SpreadSheetJFileChooser() {
        for (FileFilter ff : this.fileFilters) {
            addChoosableFileFilter(ff);
        }
    }

    public static SpreadSheetJFileChooser getSpreadSheetJFileChooser() {
        if (spreadSheetJFileChooser == null) {
            spreadSheetJFileChooser = new SpreadSheetJFileChooser();
        }
        return spreadSheetJFileChooser;
    }
}
