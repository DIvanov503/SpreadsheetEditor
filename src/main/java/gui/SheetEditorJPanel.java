package gui;

import javax.swing.*;
import java.awt.*;

public class SheetEditorJPanel extends JPanel {
    SheetEditorJPanel() {
        super(new GridLayout(0,1));
        add(new CellEditorJPanel());
    }
}
