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

        // Initialize numbers 1..9

        for (int i = 1; i <= 9; ++i) {
            final String name = Integer.toString(i);
            JButton button = new JButton(name);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addTextToResultField(name);
                }
            });

            buttons.add(button);
        }

        // Initialize cancel, zero and backspace

        JButton lBracketButton = new JButton("(");
        lBracketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTextToResultField("(");
            }
        });
        buttons.add(lBracketButton);

        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTextToResultField("0");
            }
        });
        buttons.add(zeroButton);

        JButton rBracketButton = new JButton(")");
        rBracketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTextToResultField(")");
            }
        });
        buttons.add(rBracketButton);

        // Initialize operators

        JButton addButton = new JButton("+");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTextToResultField("+");
            }
        });
        buttons.add(addButton);

        JButton subButton = new JButton("-");
        subButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTextToResultField("-");
            }
        });
        buttons.add(subButton);

        JButton mulButton = new JButton("*");
        mulButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTextToResultField("*");
            }
        });
        buttons.add(mulButton);

        JButton divButton = new JButton("/");
        divButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTextToResultField("/");
            }
        });
        buttons.add(divButton);


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
}
