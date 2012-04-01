package org.cesg.jsharelinks.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class MainUI {

    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu mnOpciones;
    private JTable table;

//    /**
//     * Launch the application.
//     */
//    public static void main ( String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run () {
//                try {
//                    MainUI window = new MainUI();
//                    window.frame.setVisible(true);
//                } catch ( Exception e ) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public final void show(){
        EventQueue.invokeLater(new Runnable() {
            public void run () {
                try {
                    MainUI window = new MainUI();
                    window.frame.setVisible(true);
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the application.
     */
    private MainUI () {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize () {
        this.frame = new JFrame();
        this.frame.setBounds(100, 100, 450, 300);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setLayout(null);
        
        this.menuBar = new JMenuBar();
        this.menuBar.setBounds(0, 0, 434, 21);
        this.frame.getContentPane().add(this.menuBar);
        
        this.mnOpciones = new JMenu("Opciones");
        this.menuBar.add(this.mnOpciones);
        
        this.table = new JTable();
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.table.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "id", "url", "comentario"
            }
        ) {
            Class[] columnTypes = new Class[] {
                Integer.class, String.class, String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            boolean[] columnEditables = new boolean[] {
                false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        this.table.setBounds(90, 86, 235, 127);
        this.frame.getContentPane().add(this.table);
    }
}
