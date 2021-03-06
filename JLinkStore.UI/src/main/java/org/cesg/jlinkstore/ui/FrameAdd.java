package org.cesg.jlinkstore.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.cesg.jlinkstore.ui.models.Link;
import org.cesg.jlinkstore.ui.utils.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrameAdd implements Runnable , ActionListener {

    private static final Logger _logger = LoggerFactory.getLogger(FrameAdd.class);
    private final UIHandler handler;
    private static final String BTN_PEGAR_TIP = "Pega la url desde el porta papeles.";

    private JFrame frmLinksShared;
    private JTextField txtComentario;
    private JButton btnAgregar;
    private JTextArea txtrUrl;
    private JButton btnPegar;
    private JLabel lblComentario;
    private JLabel lblDireccinUrl;

    /**
     * Create the application.
     */
    public FrameAdd ( UIHandler handler) {
        this.handler = handler;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize () {
        _logger.debug("# Iniciando los componentes.");
        this.frmLinksShared = new JFrame();
        this.frmLinksShared.setIconImage(new Const().getFrameImage());
        this.frmLinksShared.setResizable(false);
        this.frmLinksShared.setTitle("ADD LINK");
        this.frmLinksShared.setBounds(100, 100, 450, 286);
        this.frmLinksShared
                .setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.frmLinksShared.getContentPane().setLayout(null);
        this.frmLinksShared.setLocationRelativeTo(null);
        // ${txtComentario}
        this.txtComentario = new JTextField();
        this.txtComentario.setBounds(37, 47, 361, 25);
        this.frmLinksShared.getContentPane().add(this.txtComentario);
        this.txtComentario.setColumns(10);
        // ${btnAgregar}
        this.btnAgregar = new JButton("Agregar");
        this.btnAgregar.addActionListener(this);
        this.btnAgregar.setBounds(37, 219, 117, 25);
        this.frmLinksShared.getContentPane().add(this.btnAgregar);
        // ${txtrUrl}
        this.txtrUrl = new JTextArea();
        this.txtrUrl.setLineWrap(true);
        this.txtrUrl.setBounds(37, 114, 361, 80);
        this.frmLinksShared.getContentPane().add(this.txtrUrl);
        // ${btnPegar}
        this.btnPegar = new JButton("Pegar");
        this.btnPegar.addActionListener(this);
        this.btnPegar.setBounds(310, 195, 88, 25);
        this.btnPegar.setToolTipText(BTN_PEGAR_TIP);
        this.frmLinksShared.getContentPane().add(this.btnPegar);
        // ${lblComentario}
        this.lblComentario = new JLabel("Comentario (150 caracteres)");
        this.lblComentario.setBounds(37, 33, 217, 15);
        this.frmLinksShared.getContentPane().add(this.lblComentario);
        // ${lblDireccinUrl}
        this.lblDireccinUrl = new JLabel("Dirección url");
        this.lblDireccinUrl.setBounds(37, 98, 104, 15);
        this.frmLinksShared.getContentPane().add(this.lblDireccinUrl);
    }

    /**
     * @wbp.parser.entryPoint
     */
    public void run () {
        try {
            initialize();
            this.txtComentario.setFocusable(true);
            this.frmLinksShared.setVisible(true);
        } catch ( final Exception e ) {
            _logger.error("## ERROR al iniciar la UI.", e);
        }
    }

    public void actionPerformed ( final ActionEvent e) {
        if ( e.getSource() == this.btnPegar ) {
            final String portaPapeles = Const.PORTAPAPELES.getClipboard();
            if ( portaPapeles.length() > 0 )
                this.txtrUrl.setText(portaPapeles);
        }
        if ( e.getSource() == this.btnAgregar ) {
            handler.doAgregar(agregarLink());
        }
    }

    private final Boolean verificador ( String comentario , String url) {
        // TODO : Mejorar la verificación
        if ( comentario.length() > 0 && url.length() > 0 ) {
            if ( comentario.length() < 150 )
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Link agregarLink () {
        Link link = null;
        String comentario, url;
        comentario = txtComentario.getText();
        url = txtrUrl.getText();
        if ( verificador(comentario, url) ) {
            link = new Link();
            link.setComentario(comentario);
            link.setUrl(url);
            txtComentario.setText(Const.CADENA_VACIA);
            txtrUrl.setText(Const.CADENA_VACIA);
            JOptionPane.showMessageDialog(frmLinksShared, "Link agregado.");
        }
        return link;

    }
}
