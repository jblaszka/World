package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class CustomTextArea  extends JTextArea  {
    private static final int AREA_WIDTH = 2;
    private static final int AREA_HEIGHT = 1;

    public CustomTextArea(
        String text,
        int row,
        int col,
        int width,
        int height,
        GridBagConstraints constraints,
        JPanel panel
    ){
        super(text, height, width);
        setFont(new Font("Dialog", Font.BOLD, 12));
        setBackground(CustomColors.TEXT_BACKGROUND_COLOR);
        Border margins = BorderFactory.createEmptyBorder(
                LayoutConstants.TOP_MARGIN,
                LayoutConstants.LEFT_MARGIN,
                0,
                0
        );
        Border border = BorderFactory.createLineBorder(CustomColors.BORDER_COLOR,2);
        setBorder(new CompoundBorder(border,margins));
        constraints.gridx = col;
        constraints.gridy = row;
        constraints.gridwidth = AREA_WIDTH;
        constraints.gridheight = AREA_HEIGHT;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(this, constraints);
    }
}
