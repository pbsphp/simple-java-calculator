import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;


public class Calculator extends JFrame {

    protected String resultText;
    protected JTextField resultField;

    public Calculator() {
        super("Window name");

        resultText = new String("");

        JPanel buttonsPanel = new JPanel(new GridLayout(6, 3));

        resultField = new JTextField(20);


        for (JButton button : initializeButtons()) {
            buttonsPanel.add(button);
        }

        resultField.setText(resultText);

        add(resultField, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);
    }


    public static void main(String[] args) {
        Calculator app = new Calculator();
        app.setVisible(true);
        app.pack();
    }


    ArrayList<JButton> initializeButtons() {
        ArrayList<JButton> buttons = new ArrayList<JButton>();

        // Add some buttons

        String[] neededButtons = {
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "(", "0", ")",
            "+", "-", "*",
            "/", /* ... */
        };

        for (String buttonName : neededButtons) {
            JButton button = new JButton(buttonName);
            button.addActionListener(addToResultField(buttonName));
            buttons.add(button);
        }

        // Add `=' and `C' buttons

        JButton resButton = new JButton("=");
        resButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Parser parser = new Parser(resultText);
                double result = parser.calculate();
                addTextToResultField("=" + result);
            }
        });
        buttons.add(resButton);

        JButton cancelButton = new JButton("C");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearResultField();
            }
        });
        buttons.add(cancelButton);

        return buttons;
    }


    protected void addTextToResultField(String text) {
        resultText += text;
        resultField.setText(resultText);
    }


    protected void clearResultField() {
        resultText = "";
        resultField.setText(resultText);
    }


    private ActionListener addToResultField(final String text) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTextToResultField(text);
            }
        };
    }
}
