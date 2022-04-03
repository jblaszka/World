package gui;

import simulation.SimulationParams;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CustomTextField extends JFormattedTextField {
    private static final int DEFAULT_WIDTH = 1;
    private static CustomNumberFormatter formatter = new CustomNumberFormatter();

    private static class CustomNumberFormatter extends NumberFormatter{
        CustomNumberFormatter(){
            super(NumberFormat.getIntegerInstance());
            setValueClass(Integer.class);
            setAllowsInvalid(false);
            setMaximum(0);
        }
    }

    public CustomTextField(
            String fieldName,
            int value,
            int row,
            int col,
            GridBagConstraints constraints,
            JPanel panel
    ){
    super(new DefaultFormatterFactory(formatter), Integer.valueOf(value));
    constraints.gridwidth = DEFAULT_WIDTH;
    constraints.gridy = row;
    constraints.weightx = 0;
    constraints.weighty = 0;

    JLabel label = new JLabel(fieldName + ":");
    constraints.gridx = col;
    constraints.fill = GridBagConstraints.NONE;
    panel.add(label,constraints);

    setFont(new Font("Dialog", Font.BOLD, 12));
    setBackground(CustomColors.BACKGROUND_COLOR);
    Border margins = BorderFactory.createEmptyBorder(5,5,5,0);
    Border border = BorderFactory.createLineBorder(CustomColors.BORDER_COLOR,2);
    setBorder(new CompoundBorder( border,margins));

    constraints.gridx = col + 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;

    getDocument().addDocumentListener((IDocumentListener) (DocumentEvent e) -> {
        String text = getText();
        if(text != null && !text.equals("")){
            SimulationParams.setField(fieldName, Integer.parseInt(text));
        }
    });

    panel.add(this, constraints);
    }
}
