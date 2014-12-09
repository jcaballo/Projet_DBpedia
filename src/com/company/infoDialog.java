package com.company;

import javax.swing.*;
import java.awt.event.*;

public class infoDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField abstractText;
    private JTextField birthText;
    private JTextField lastNameText;
    private JTextField firstNameText;
    private JTextField photoText;
    private JButton buttonCancel;

    public infoDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        dispose();
    }


    public static void main(String[] args) {
        infoDialog dialog = new infoDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
