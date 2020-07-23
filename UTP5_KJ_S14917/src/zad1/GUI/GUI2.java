package zad1.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;;

public class GUI2 {

    private JFrame frmScript;
    private JTextArea textArea;

    public GUI2() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmScript = new JFrame();
        frmScript.setType(Type.UTILITY);
        frmScript.setResizable(false);
        frmScript.setTitle("Script");
        frmScript.setBounds(100, 100, 315, 300);
        frmScript.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmScript.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        frmScript.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        panel.add(textArea, BorderLayout.CENTER);

        JPanel panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.SOUTH);

        JButton btnNewButton = new JButton("Ok");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String test = textArea.getText();
                if(!test.isEmpty()){
                    GUI.ctl.runScript(test);
                    GUI.setTable();
                    frmScript.setVisible(false);
                    frmScript.dispose();
                }
            }
        });
        panel_1.add(btnNewButton);
        JButton btnNewButton_1 = new JButton("Cancel");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frmScript.setVisible(false);
                frmScript.dispose();
            }
        });
        panel_1.add(btnNewButton_1);
        frmScript.setVisible(true);
    }


    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

}
