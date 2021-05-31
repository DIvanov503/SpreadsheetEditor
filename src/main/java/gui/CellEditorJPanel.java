package gui;

import javax.swing.*;
import java.awt.*;

public class CellEditorJPanel extends JPanel {
    CellEditorJPanel() {
        super(new GridLayout(1,0));
        Label coordinateLabel = new Label("A1");
        add(coordinateLabel);
    }
}
