
package ahorcados;

import javax.swing.*;
import java.awt.*;
import ahorcados.logica.*;
import ahorcados.excepciones.*;

public class PanelMenu extends JPanel{
    
    private CardLayout transicion;
    private JPanel contenedor;
    private JButton btnPalabraFija;
    private JButton btnPalabraAzar;
    private Image fondo = new ImageIcon(getClass().getResource("/ahorcados/FondoAhorcado2.png")).getImage();
    private GestorDePalabras gestor;
    
    public PanelMenu(JPanel contenedor, CardLayout transicion){
        this.contenedor = contenedor;
        this.transicion = transicion;
        gestor = new GestorDePalabras();
        cargarPalabras();
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
        panelOpciones.add(lblTitulo);
        panelOpciones.add(Box.createVerticalStrut(150));
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
        boton.setMaximumSize(new Dimension(150,50));
        boton.setPreferredSize(new Dimension(150, 50));
        return boton;
        
    }
    
    private void pedirPalabra(){
        btnPalabraFija.addActionListener(e ->{
            String palabra =JOptionPane.showInputDialog(this, "Ingrese la palabra secreta");
            if(palabra == null){
                return;
            }
            palabra = palabra.trim();
            if(palabra.isEmpty()){
                JOptionPane.showMessageDialog(this,"Debe ingresar una palabra");
                return;
            }
            BaseAhorcado juego =new AhorcadoPalabraFija(palabra);
            abrirJuego(juego);
        });


        btnPalabraAzar.addActionListener(e ->{
            BaseAhorcado juego = new AhorcadoPalabraRandom(gestor );
            abrirJuego(juego);
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo , 0, 0, getWidth(), getHeight(),this );
    }
    
    private void cargarPalabras(){
        try{
            gestor.agregarPalabra("HOLA");
            gestor.agregarPalabra("UNITEC");
            gestor.agregarPalabra("COMPUTADORA");
            gestor.agregarPalabra("EMBOLIA");
            gestor.agregarPalabra("INTERNET");
            gestor.agregarPalabra("ZANCUDO");
            gestor.agregarPalabra("CIEMPIES");
            gestor.agregarPalabra("TIBURON");
            gestor.agregarPalabra("SIAMESES");
        }catch(PalabraDuplicadaException e){
            JOptionPane.showMessageDialog(
                this,
                e.getMessage()
            );
        }
    }
    
    private void abrirJuego(BaseAhorcado juego){
        PanelJuego panelJuego =new PanelJuego(contenedor, transicion, juego);
        contenedor.add( panelJuego, "JUEGO" );
        transicion.show(contenedor, "JUEGO");
    }
  
}
