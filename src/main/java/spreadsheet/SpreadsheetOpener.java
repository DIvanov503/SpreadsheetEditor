package spreadsheet;

import java.io.File;
import java.io.IOException;

public class SpreadsheetOpener {
    public static ISpreadsheet open(String fileName) throws IOException, UnsupportedFileExtensionException {
        ISpreadsheet answer;
        if (fileName.toLowerCase().matches(".*\\.ods$")) {
            answer = new ODSSpreadsheet();
            answer.open(fileName);
        } else {
            throw new UnsupportedFileExtensionException("Unsupported file extension!");
        }
        return answer;
    }

    public static ISpreadsheet open(File file) throws IOException, UnsupportedFileExtensionException {
        return open(file.getName());
    }

    public static class UnsupportedFileExtensionException extends Exception {
        UnsupportedFileExtensionException(String message) {
            super(message);
        }
    }
}
