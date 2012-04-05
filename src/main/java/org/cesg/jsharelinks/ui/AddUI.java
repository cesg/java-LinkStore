package org.cesg.jsharelinks.ui;

import static org.cesg.jsharelinks.utilidades.Utilidades.tryGetTextFromClipBoard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.cesg.jsharelinks.models.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddUI implements Runnable , ActionListener , MouseListener {
    private static final Logger _logger = LoggerFactory.getLogger(AddUI.class);
    private UIHandler handler;
    private static final String CADENA_VACIA = "";
    private static final String COMENTARIO_DEFAULT = "Comentario";
    private static final String URL_DEFAULT = "Url";

    private JFrame frame;
    private JTextField txtComentario;
    private JButton btnAgregar;
    private JTextArea txtrUrl;
    private JButton btnPegar;

    /**
     * Create the application.
     */
    public AddUI ( UIHandler handler) {
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
        this.txtComentario.addMouseListener(this);
        this.txtComentario.setText("comentario");
        this.txtComentario.setBounds(24, 42, 316, 19);
        this.frame.getContentPane().add(this.txtComentario);
        this.txtComentario.setColumns(10);
        // ${btnAgregar}
        this.btnAgregar = new JButton("Agregar");
        this.btnAgregar.addActionListener(this);
        this.btnAgregar.setBounds(24, 195, 117, 25);
        this.frame.getContentPane().add(this.btnAgregar);
        // ${txtrUrl}
        this.txtrUrl = new JTextArea();
        this.txtrUrl.setText("url");
        this.txtrUrl.setBounds(24, 77, 316, 80);
        this.frame.getContentPane().add(this.txtrUrl);
        // ${component_name}
        this.btnPegar = new JButton("Pegar");
        this.btnPegar.addActionListener(this);
        this.btnPegar.setBounds(252, 169, 88, 25);
        this.frame.getContentPane().add(this.btnPegar);
    }

    /**
     * @wbp.parser.entryPoint
     */
    public void run () {
        try {
            initialize();
            this.frame.setVisible(true);
        } catch ( Exception e ) {
            _logger.error("## ERROR al iniciar la UI.", e);
        }
    }

    public void actionPerformed ( final ActionEvent e) {
        if (e.getSource() == this.btnPegar) {
            String portaPapeles = tryGetTextFromClipBoard();
            if(portaPapeles.length() > 0)
                this.txtrUrl.setText(portaPapeles);
        }
        if ( e.getSource() == this.btnAgregar ) {
            handler.doAgregar(agregarLink());
        }
    }

    public void mouseEntered ( final MouseEvent e) {
        if ( e.getSource() == this.txtComentario
                && COMENTARIO_DEFAULT.equals(txtComentario.getText()) ) {
            this.txtComentario.setText(CADENA_VACIA);
        }
    }


    private final Boolean verificador ( String comentario , String url) {
        if ( comentario.length() > 0 && url.length() > 0 ) {
            if ( !COMENTARIO_DEFAULT.equals(comentario)
                    && !URL_DEFAULT.equals(url) )
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
            _logger.debug("Insertando un nuevo link: {}",link.toString());
        }
        return link;

    }
    
// ## REGION :: METODOS NO USADOS.
    public void mouseClicked ( MouseEvent e) {}

    public void mousePressed ( MouseEvent e) {}

    public void mouseReleased ( MouseEvent e) {}
    
    public void mouseExited ( final MouseEvent e) {}
// ## END REGION :: METODOS NO USADOS.
    
}
