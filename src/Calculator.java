import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;


public class Calculator extends JFrame {

    public Calculator() {
        super("Window name");

        JPanel buttonsPanel = new JPanel(new GridLayout(6, 3));

        for (JButton button : initializeButtons()) {
            buttonsPanel.add(button);
        }

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
            String name = Integer.toString(i);
            buttons.add(new JButton(name));
        }

        // Initialize cancel, zero and backspace

        JButton lBracketButton = new JButton("(");
        JButton zeroButton = new JButton("0");
        JButton rBracketButton = new JButton(")");

        buttons.add(lBracketButton);
        buttons.add(zeroButton);
        buttons.add(rBracketButton);

        // Initialize operators

        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        JButton mulButton = new JButton("*");
        JButton divButton = new JButton("/");

        buttons.add(addButton);
        buttons.add(subButton);
        buttons.add(mulButton);
        buttons.add(divButton);

        JButton resButton = new JButton("=");
        buttons.add(resButton);

        JButton cancelButton = new JButton("C");
        buttons.add(cancelButton);


        return buttons;
    }
}
