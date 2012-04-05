package org.cesg.jsharelinks.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.cesg.jsharelinks.models.Link;
import org.cesg.jsharelinks.services.LinkManager;
import org.cesg.jsharelinks.services.PoolLinkManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UI principal para el manejo de los links.<br>
 * Windows Builder
 * 
 * @author kristian
 * @version 05.04.2012
 */
public class MainUI implements ActionListener , Runnable , MouseListener ,
        ListSelectionListener {

    private final static Logger _logger = LoggerFactory.getLogger(MainUI.class);
    private final LinkManager linkManager;
    private UIHandler handler;
    private DefaultListModel<Link> listModel;

    private JFrame frmSharedLinks;
    public JButton btnIr;
    private JButton btnBorrar;
    private JButton btnAgregar;
    private JList<Link> list;
    private JTextArea txtrUrl;
    private JScrollPane scrollPane;

    /**
     * Create the application.
     */

    public MainUI ( UIHandler handler) {
        this.handler = handler;
        this.linkManager = new PoolLinkManager();
        this.listModel = new DefaultListModel<Link>();

    }

    private void llenarLista () {
        List<Link> listaLinks = this.linkManager.selectAllLink();
        for ( Link link : listaLinks) {
            this.listModel.addElement(link);
        }
        if ( listModel.size() > 0 )
            list.setSelectedIndex(0);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize () {
        _logger.debug("# Iniciando los componentes.");
        this.frmSharedLinks = new JFrame();
        this.frmSharedLinks.setResizable(false);
        this.frmSharedLinks.setTitle("SHARED LINKS");
        this.frmSharedLinks.setBounds(100, 100, 627, 391);
        this.frmSharedLinks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frmSharedLinks.getContentPane().setLayout(null);
        // ${btnIr}
        this.btnIr = new JButton("IR");
        this.btnIr.addActionListener(this);
        this.btnIr.setBounds(524, 314, 87, 25);
        this.frmSharedLinks.getContentPane().add(this.btnIr);
        // ${btnBorrar}
        this.btnBorrar = new JButton("Borrar");
        this.btnBorrar.addActionListener(this);
        this.btnBorrar.setBounds(32, 328, 117, 25);
        this.frmSharedLinks.getContentPane().add(this.btnBorrar);
        // ${btnAgregar}
        this.btnAgregar = new JButton("Agregar");
        this.btnAgregar.addActionListener(this);
        this.btnAgregar.setBounds(32, 299, 117, 25);
        this.frmSharedLinks.getContentPane().add(this.btnAgregar);
        // ${component_name}
        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(32, 58, 189, 228);
        this.frmSharedLinks.getContentPane().add(this.scrollPane);
        // ${list}
        this.list = new JList<Link>();
        this.scrollPane.setViewportView(this.list);
        this.list.addMouseListener(this);
        this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.list.addListSelectionListener(this);
        this.list.setModel(listModel);
        // ${txtrUrl}
        this.txtrUrl = new JTextArea();
        this.txtrUrl.setText("url");
        this.txtrUrl.setBounds(233, 59, 378, 122);
        this.frmSharedLinks.getContentPane().add(this.txtrUrl);
    }

    public void actionPerformed ( final ActionEvent e) {
        if ( e.getSource() == this.btnBorrar ) {
            if ( list.getModel().getSize() > 0 )
                handler.doBorrarLink(list.getSelectedValue().getId());
        }
        if ( e.getSource() == this.btnAgregar ) {
            handler.doShowAddLink();
        }
        if ( e.getSource() == this.btnIr ) {
            if ( list.getModel().getSize() > 0 ) {
                handler.doIr(list.getSelectedValue().getUrl());
                llenarLista();
            }
        }
    }

    public void valueChanged ( final ListSelectionEvent e) {
        if ( e.getSource() == this.list ) {
            txtrUrl.setText(list.getSelectedValue().getUrl());
        }
    }

    /**
     * @wbp.parser.entryPoint
     */
    public void run () {
        try {
            initialize();
            this.frmSharedLinks.setVisible(true);
            llenarLista();
        } catch ( Exception e ) {
            _logger.error("## ERROR al iniciar la UI.", e);
        }

    }

    // REGION :: Metodos no usados.
    public void mouseEntered ( final MouseEvent e) {
    }

    public void mouseExited ( final MouseEvent e) {
    }

    public void mouseClicked ( final MouseEvent e) {
    }

    public void mousePressed ( final MouseEvent e) {
    }

    public void mouseReleased ( final MouseEvent e) {
    }
    // END REGION :: Metodos no usados.

}
