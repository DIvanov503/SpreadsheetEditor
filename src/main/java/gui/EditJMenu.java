package gui;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class EditJMenu extends JMenu {
    EditJMenu() {
        super("Edit");
        setMnemonic(KeyEvent.VK_E);

        JMenuItem newSpreadsheetMenuItem = new JMenuItem("Undo");
        newSpreadsheetMenuItem.setMnemonic(KeyEvent.VK_U);
        add(newSpreadsheetMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Redo");
        newSpreadsheetMenuItem.setMnemonic(KeyEvent.VK_R);
        add(exitMenuItem);
    }
}
