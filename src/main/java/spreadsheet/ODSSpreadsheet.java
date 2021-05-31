package spreadsheet;

import org.jopendocument.dom.spreadsheet.*;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ODSSpreadsheet implements ISpreadsheet {
    private File file;
    private SpreadSheet spreadSheet;
    boolean isModified = false;
    String fileName = null;

    @Override
    public void open(String fileName) throws IOException {
        file = new File(fileName);
        spreadSheet = SpreadSheet.createFromFile(file);
        this.fileName = fileName;
        isModified = false;
    }

    @Override
    public void save(String fileName) throws IOException {
        file = new File(fileName);
        save();
        this.fileName = fileName;
        isModified = false;
    }

    @Override
    public void save() throws IOException {
        spreadSheet.saveAs(file);
        isModified = false;
    }

    @Override
    public void empty() {
        spreadSheet = SpreadSheet.createEmpty(new DefaultTableModel());
        spreadSheet.getFirstSheet().setName("Sheet1");
        fileName = null;
        isModified = false;
    }

    @Override
    public int getSheetCount() {
        return spreadSheet.getSheetCount();
    }

    @Override
    public ODSSheet addSheet() {
        isModified = true;
        return new ODSSheet(spreadSheet.addSheet("Sheet" + (getSheetCount() + 1)));
    }

    @Override
    public List<ODSSheet> getSheetList() {
        List<ODSSheet> sheetList = new ArrayList<>();
        for (int i = 0; i < getSheetCount(); ++i) {
            sheetList.add(new ODSSheet(spreadSheet.getSheet(i)));
        }
        return sheetList;
    }

    @Override
    public ODSSheet getSheet(int index) {
        return new ODSSheet(spreadSheet.getSheet(index));
    }

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
