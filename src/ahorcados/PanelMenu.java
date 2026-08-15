
package ahorcados;

import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel{
    
    private CardLayout transicion;
    private JPanel contenedor;
    private JButton btnPalabraFija;
    private JButton btnPalabraAzar;
    private Image fondo = new ImageIcon(getClass().getResource("/ahorcados/FondoAhorcado2.png")).getImage();
    
    public PanelMenu(JPanel contenedor, CardLayout transicion){
        this.contenedor = contenedor;
        this.transicion = transicion;
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        JPanel panelOpciones = new JPanel();
        panelOpciones.setOpaque(false);
        BoxLayout columna = new BoxLayout(panelOpciones, BoxLayout.Y_AXIS);
        panelOpciones.setLayout(columna);
        JLabel lblTitulo = new JLabel("Juego del Ahorcado");
        lblTitulo.setForeground(Color.white);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPalabraFija = crearBoton("Palabra Fija");
        btnPalabraAzar = crearBoton("Palabra al Azar");
        panelOpciones.add(Box.createVerticalStrut(50));
        panelOpciones.add(btnPalabraFija);
        panelOpciones.add(Box.createVerticalStrut(50));
        panelOpciones.add(btnPalabraAzar);
        panelOpciones.add(Box.createVerticalStrut(20));
        add(panelOpciones);
        pedirPalabra();    
    }
    
    private JButton crearBoton(String texto){
        JButton boton = new JButton(texto);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(250,200));
        return boton;
        
    }
    
    private void pedirPalabra(){
        btnPalabraFija.addActionListener(e ->{
            
        });
        
        btnPalabraAzar.addActionListener(e ->{
            PanelJuego juego = new PanelJuego(contenedor, transicion);
            contenedor.add(juego, "JUEGO");
            transicion.show(contenedor, "JUEGO");
        });
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo , 0, 0, getWidth(), getHeight(),this );
    }
  
}
