
package ahorcados;

import javax.swing.*;
import java.awt.*;


public class VentanaPrincipal extends JFrame{
    private CardLayout transicion;
    private JPanel contenedor;
    
    public VentanaPrincipal(){
        setTitle("Juego del Ahorcado");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        transicion = new CardLayout();
        contenedor = new JPanel(transicion);
        
        PanelMenu menu = new PanelMenu(contenedor, transicion);
        contenedor.add(menu, "MENU");
        
        add(contenedor);
        transicion.show(contenedor, "MENU");
        setVisible(true);
    }
}
