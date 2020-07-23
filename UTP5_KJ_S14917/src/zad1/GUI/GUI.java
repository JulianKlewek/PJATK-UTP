package zad1.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import zad1.Controller;

import java.awt.Color;
import java.awt.Component;

import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.DefaultListModel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class GUI {

    private JFrame frmModelling;
    private static JTable table;
    private static DefaultTableModel dtm;
    public static Controller ctl;
    private static final String dataPath = System.getProperty("user.home") + "/Modeling/data/";
    private static final String scriptPath = System.getProperty("user.home") + "/Modeling/scripts/";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frmModelling.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    static void setTable() {
        Scanner scanner = new Scanner(ctl.getResultsAsTsv());
        boolean fl = true;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] elements = line.split("\\s+");
            if (fl) {
                elements[0] = "";
                dtm = new DefaultTableModel(elements, 0);
                table.setModel(dtm);
                table.setFillsViewportHeight(true);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    TableColumn column1 = table.getTableHeader().getColumnModel().getColumn(i);
                    column1.setHeaderValue(elements[i]);
                }
                fl = false;
            } else {
                dtm.addRow(elements);
            }
        }
        scanner.close();
    }

    private void initialize() {
        frmModelling = new JFrame();
        frmModelling.setTitle("Modelling framework sample");
        frmModelling.setBounds(100, 100, 750, 400);
        frmModelling.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmModelling.getContentPane().setLayout(new BorderLayout(0, 0));
        frmModelling.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBackground(new Color(240, 240, 240));
        frmModelling.getContentPane().add(panel, BorderLayout.WEST);
        panel.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel = new JLabel("Select models and data                    ");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel, BorderLayout.NORTH);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));

        DefaultListModel<String> dlm = new DefaultListModel<>();
        dlm.addElement("Model1");

        JSplitPane splitPane = new JSplitPane();
        panel_2.add(splitPane, BorderLayout.CENTER);
        splitPane.setResizeWeight(0.5d);
        JList modelList = new JList(dlm);
        splitPane.setLeftComponent(modelList);

        DefaultListModel<String> dlmData = new DefaultListModel<>();
        File dir = new File(dataPath);
        for (File file : dir.listFiles()) {
            if (file.getName().endsWith((".txt"))) {
                dlmData.addElement(file.getName());
            }
        }

        JList dataList = new JList(dlmData);
        splitPane.setRightComponent(dataList);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_2.add(panel_3, BorderLayout.PAGE_END);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        frmModelling.getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new BorderLayout(0, 0));

        JPanel panel_5 = new JPanel();
        panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.add(panel_5, BorderLayout.CENTER);
        panel_5.setLayout(new BorderLayout(0, 0));

        table = new JTable() {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component returnComp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(230, 230, 230);
                Color whiteColor = Color.WHITE;
                if (!returnComp.getBackground().equals(getSelectionBackground())) {
                    Color bg = (row % 2 == 0 ? whiteColor : alternateColor);
                    returnComp.setBackground(bg);
                    bg = null;
                }
                return returnComp;
            }
        };
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        panel_5.add(scrollPane, BorderLayout.CENTER);
        // panel_5.add(table, BorderLayout.CENTER);

        JPanel panel_6 = new JPanel();
        panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_5.add(panel_6, BorderLayout.SOUTH);

        JButton btnNewButton_1 = new JButton("Run script from file");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Select Script File");
                fc.setFileFilter(new FileNameExtensionFilter("Groovy files", "groovy"));
                fc.setCurrentDirectory(new File(scriptPath));
                if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    ctl.runScriptFromFile(fc.getSelectedFile().toString());
                    setTable();
                }
            }
        });
        panel_6.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Create and run ad hoc script");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (!modelList.isSelectionEmpty() && !dataList.isSelectionEmpty() && dtm!= null) {
                    GUI2 window = new GUI2();

                }
            }
        });
        panel_6.add(btnNewButton_2);

        JButton btnNewButton = new JButton("Run models");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (!modelList.isSelectionEmpty() && !dataList.isSelectionEmpty()) {
                    String model = modelList.getSelectedValue().toString();
                    String data = dataList.getSelectedValue().toString();
                    ctl = new Controller(model);
                    ctl.readDataFrom(dataPath + data).runModel();
                    setTable();
                }
            }
        });
        panel_3.add(btnNewButton);
    }

}
