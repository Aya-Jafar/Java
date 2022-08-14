package edu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LambdaFrame extends JFrame {
    JButton myButton = new JButton("I'M THE 1ST BUTTON");
    JButton myButton2 = new JButton("I'M THE 2ND BUTTON");

    LambdaFrame() {

        myButton.setBounds(100, 100, 200, 100);
        myButton.addActionListener((ActionListener) new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Y" + "OU CLICKED THE BUTTON USING ANONYMOUS INNER CLASS".toLowerCase());
            }
        });
        
        myButton2.setBounds(100, 200, 200, 100);
        myButton2.addActionListener((e) -> { //e is the argument for the action listener method
            JOptionPane.showMessageDialog(null, "Y" + "OU CLICKED THE BUTTON USING LAMBDA EXPRESSION :D".toLowerCase());
        });
        
        this.add(myButton);
        this.add(myButton2);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(420, 420);
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        new LambdaFrame();
    }
}
