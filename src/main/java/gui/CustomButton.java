package gui;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    private static final int DEAFULT_WIDTH = 2;
    private static final int DEAFULT_HEIGH = 1;

    public CustomButton(
            String text,
            int row,
            int col,
            boolean enabled,
            JPanel panel,
            GridBagConstraints constraints
    ) {
        super(text);
        constraints.gridy = row;
        constraints.gridx = col;
        constraints.gridwidth = DEAFULT_WIDTH;
        constraints.gridheight = DEAFULT_HEIGH;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        setEnabled(enabled);
        panel.add(this, constraints);
    }
}