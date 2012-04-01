package org.cesg.jsharelinks.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainUI {

    private final static Logger _logger = LoggerFactory.getLogger(MainUI.class);

    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu mnOpciones;
    private JTable table;
    private JScrollPane scrollPane;

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
                    _logger.error("## ERROR al iniciar la UI.", e);
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
        this.menuBar.setBounds(0, 0, 446, 21);
        this.frame.getContentPane().add(this.menuBar);
        
        this.mnOpciones = new JMenu("Opciones");
        this.menuBar.add(this.mnOpciones);
        
        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(31, 53, 380, 127);
        this.frame.getContentPane().add(this.scrollPane);

        this.table = new JTable();
        this.scrollPane.setViewportView(this.table);
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
            @Override
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            boolean[] columnEditables = new boolean[] {
                false, false, false
            };
            @Override
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
    }
}
