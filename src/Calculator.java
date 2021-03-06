import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;


class Calculator extends JFrame {

    protected JTextField resultField;

    public Calculator() {
        super("Window name");

        JPanel buttonsPanel = new JPanel(new GridLayout(6, 3));

        resultField = new JTextField(20);


        for (JButton button : initializeButtons()) {
            buttonsPanel.add(button);
        }

        add(resultField, BorderLayout.NORTH);
        add(buttonsPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                String expression = resultField.getText();
                Parser parser = new Parser(expression);

                try {
                    double result = parser.calculate();
                    addTextToResultField("=" + result);
                }
                catch (Exception exception) {
                    clearResultField();
                    addTextToResultField("ERROR: " + exception.getMessage());
                }
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
        String expression = resultField.getText() + text;
        resultField.setText(expression);
    }


    protected void clearResultField() {
        resultField.setText("");
    }


    private ActionListener addToResultField(final String text) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTextToResultField(text);
            }
        };
    }
}
