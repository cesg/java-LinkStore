package org.cesg.jsharelinks.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.cesg.jsharelinks.models.Link;
import org.cesg.jsharelinks.services.LinkManager;
import org.cesg.jsharelinks.services.PoolLinkManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UI principal para el manejo de los links.<br>
 * Windows Builder 
 * @author kristian
 * @version 04.04.2012
 */
public class MainUI implements ActionListener {

    private final static Logger _logger = LoggerFactory.getLogger(MainUI.class);
    private final LinkManager linkManager;
    private UIHandler handler;

    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu mnOpciones;
    private final DefaultTableModel mod;
    private JTable table;
    private JScrollPane scrollPane;
    public JButton btnIr;
    private JButton btnBorrar;
    private JButton btnAgregar;

    /**
     * Inicia una nueva instancia de la ventana.
     */
    public static final void show () {
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

    private void llenarTabla () {

        List<Link> allLinks = linkManager.selectAllLink();
        if ( allLinks.size() == 0 )
            return;

        for ( Link link : allLinks) {
            Object[] newRow = { link.getId(), link.getUrl(),
                    link.getComentario() };
            mod.addRow(newRow);
        }
    }

    /**
     * Create the application.
     */
    @SuppressWarnings("serial")
    public MainUI () {
        this.linkManager = new PoolLinkManager();
        this.mod = new DefaultTableModel() {
            @Override
            public boolean isCellEditable ( int row , int column) {
                return Boolean.FALSE;
            }
        };

        mod.addColumn("id");
        mod.addColumn("url");
        mod.addColumn("comentario");
        mod.setColumnCount(3);

        initialize();
        llenarTabla();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize () {
        this.frame = new JFrame();
        this.frame.setBounds(100, 100, 450, 322);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setLayout(null);

        this.menuBar = new JMenuBar();
        this.menuBar.setBounds(0, 0, 446, 21);
        this.frame.getContentPane().add(this.menuBar);

        this.mnOpciones = new JMenu("Opciones");
        this.menuBar.add(this.mnOpciones);

        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(30, 47, 404, 132);
        this.frame.getContentPane().add(this.scrollPane);

        this.table = new JTable();
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.scrollPane.setViewportView(this.table);
        this.table.setModel(mod);

        this.btnIr = new JButton("IR");
        this.btnIr.addActionListener(this);
        this.btnIr.setBounds(30, 208, 87, 25);
        this.frame.getContentPane().add(this.btnIr);

        this.btnBorrar = new JButton("BORRAR");
        this.btnBorrar.addActionListener(this);
        this.btnBorrar.setBounds(273, 208, 117, 25);
        this.frame.getContentPane().add(this.btnBorrar);
        // ${component_name}
        this.btnAgregar = new JButton("Agregar");
        this.btnAgregar.addActionListener(this);
        this.btnAgregar.setBounds(273, 245, 117, 25);
        this.frame.getContentPane().add(this.btnAgregar);
    }

    public void actionPerformed ( final ActionEvent e) {
        if (e.getSource() == this.btnBorrar) {
            handler.doBorrarLink(null);
        }
        if (e.getSource() == this.btnAgregar) {
            handler.doAgregar(null);
        }
        if ( e.getSource() == this.btnIr ) {
            handler.doIr("");
        }
    }
}
