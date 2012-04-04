package org.cesg.jsharelinks.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddUI implements Runnable, ActionListener{
    private static final Logger _logger = LoggerFactory.getLogger(AddUI.class);
    private UIHandler handler;
    
    private JFrame frame;
    private JTextField txtComentario;
    private JTextField txtUrl;
    private JButton btnAgregar;


    /**
     * Create the application.
     */
//    private AddUI () {
//    }
    public AddUI(UIHandler handler){
        this.handler = handler;
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize () {
        _logger.debug("# Iniciando los componentes.");
        this.frame = new JFrame();
        this.frame.setBounds(100, 100, 450, 300);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.getContentPane().setLayout(null);
        // ${txtComentario}
        this.txtComentario = new JTextField();
        this.txtComentario.setText("comentario");
        this.txtComentario.setBounds(24, 42, 157, 19);
        this.frame.getContentPane().add(this.txtComentario);
        this.txtComentario.setColumns(10);
        // ${txtUrl}
        this.txtUrl = new JTextField();
        this.txtUrl.setText("url");
        this.txtUrl.setBounds(24, 80, 398, 89);
        this.frame.getContentPane().add(this.txtUrl);
        this.txtUrl.setColumns(10);
        // ${btnAgregar}
        this.btnAgregar = new JButton("Agregar");
        this.btnAgregar.addActionListener(this);
        this.btnAgregar.setBounds(24, 195, 117, 25);
        this.frame.getContentPane().add(this.btnAgregar);
    }

    /**
     * @wbp.parser.entryPoint
     */
    public void run () {
        try {
            initialize();
            this.frame.setVisible(true);
        } catch ( Exception e ) {
            _logger.error("## ERROR al iniciar la UI.",e);
        }        
    }
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.btnAgregar) {
            handler.doAgregar(null);
        }
    }
}
