
package ahorcados;

import javax.swing.*;
import java.awt.*;
import ahorcados.logica.*;

public class PanelJuego extends JPanel{
    private JLabel lblAhorcado;
    private JLabel lblPalabra;
    private JLabel lblIntentos;
    private JLabel lblCorrectas;
    private JLabel lblIncorrectas;
    private JTextField txtLetra;
    private JButton btnAdivinar;
    private JButton btnVolver;
    private BaseAhorcado juego;

    private CardLayout transicion;
    private JPanel contenedor;
    private Image fondo = new ImageIcon(getClass().getResource("/ahorcados/FondoAhorcado2.png")).getImage();
    
    public PanelJuego(JPanel contenedor, CardLayout transicion, BaseAhorcado juego){
        this.juego = juego;
        this.contenedor = contenedor;
        this.transicion = transicion;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        crearPanelArriba();
        crearPanelMedio();
        crearPanelAbajo(); 
        
        actualizarInterfaz();
        configurarBoton();
    }
    
    
    private void crearPanelArriba(){
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setOpaque(false);
        JLabel lblTitulo = new JLabel("Ahorcado", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
        
        lblIntentos = new JLabel("Intentos restantes: 6");
        lblIntentos.setForeground(Color.WHITE);
        lblIntentos.setFont(new Font("Arial", Font.BOLD, 18));
        panelSuperior.add(lblTitulo, BorderLayout.CENTER);
        panelSuperior.add(lblIntentos, BorderLayout.EAST);
        add(panelSuperior, BorderLayout.NORTH);
    }
    
    private void crearPanelMedio(){
        JPanel panelCentro = new JPanel(new GridLayout(1, 2, 20, 20));
        panelCentro.setOpaque(false);
        JPanel panelDibujo = new JPanel(new GridBagLayout());
        panelDibujo.setOpaque(false);
        lblAhorcado = new JLabel();
        actualizarAhorcado(0);
        panelDibujo.add(lblAhorcado);
        
        JPanel panelInformacion = new JPanel();
        panelInformacion.setOpaque(false);
        panelInformacion.setLayout(new BoxLayout(panelInformacion, BoxLayout.Y_AXIS));
        
        lblPalabra = new JLabel("_ _ _ _ _");
        lblPalabra.setForeground(Color.WHITE);
        lblPalabra.setFont(new Font("Arial", Font.BOLD, 35));
        lblPalabra.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblIngresar = new JLabel("Ingresa una letra");
        lblIngresar.setFont(new Font("Arial", Font.PLAIN, 18));
        lblIngresar.setForeground(Color.WHITE);
        
        lblIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtLetra = new JTextField();
        txtLetra.setMaximumSize(new Dimension(100, 40));
        txtLetra.setHorizontalAlignment(JTextField.CENTER);
        txtLetra.setFont(new Font("Arial", Font.BOLD, 20));
        
        btnAdivinar = new JButton("ADIVINAR");
        btnAdivinar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblCorrectas = new JLabel("Correctas: ");
        lblCorrectas.setForeground(Color.WHITE);
        
        lblCorrectas.setFont(new Font("Arial", Font.PLAIN, 18));
        lblIncorrectas = new JLabel("Incorrectas: ");
        lblIncorrectas.setForeground(Color.WHITE);
        lblIncorrectas.setFont(new Font("Arial", Font.PLAIN, 18));
        
        panelInformacion.add(Box.createVerticalGlue());
        panelInformacion.add(lblPalabra);
        panelInformacion.add(Box.createVerticalStrut(40));
        panelInformacion.add(lblIngresar);
        panelInformacion.add(Box.createVerticalStrut(10));
        panelInformacion.add(txtLetra);
        panelInformacion.add(Box.createVerticalStrut(15));
        panelInformacion.add(btnAdivinar);
        panelInformacion.add(Box.createVerticalStrut(40));
        panelInformacion.add(lblCorrectas);
        panelInformacion.add(Box.createVerticalStrut(10));
        panelInformacion.add(lblIncorrectas);
        panelInformacion.add(Box.createVerticalGlue());
        
        panelCentro.add(panelDibujo);
        panelCentro.add(panelInformacion);
        add(panelCentro, BorderLayout.CENTER);
    }
    
    private void crearPanelAbajo(){
        JPanel panelInferior = new JPanel();
        panelInferior.setOpaque(false);
        btnVolver = new JButton("Volver al Menu");
        btnVolver.addActionListener(e ->{
            transicion.show(contenedor, "MENU");
        });
        panelInferior.add(btnVolver);
        panelInferior.add(Box.createVerticalStrut(100));
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private void actualizarAhorcado(int errores) {
        ImageIcon imagen =new ImageIcon(getClass().getResource("/ahorcados/FondoAhorcado.png") );

        Image escalada =imagen.getImage().getScaledInstance(300,350,Image.SCALE_SMOOTH);

        lblAhorcado.setIcon(new ImageIcon(escalada));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo , 0, 0, getWidth(), getHeight(),this );
    }
    
    private void configurarBoton(){
        btnAdivinar.addActionListener(e ->{
            String texto = txtLetra.getText().trim();
            if(texto.length() != 1){
                JOptionPane.showMessageDialog(this,"Ingrese una sola letra");
                return;
            }
            char letra = texto.charAt(0);
            try{
                juego.jugar(letra);
                actualizarInterfaz();
                if(juego.verificarVictoria()){
                    JOptionPane.showMessageDialog(this, "¡Ganaste! La palabra era: " + juego.getPalabraSecreta());
                    btnAdivinar.setEnabled(false);
                    txtLetra.setEnabled(false);
                }

                else if(juego.verificarDerrota()){
                    JOptionPane.showMessageDialog(this,"Perdiste. La palabra era: "+ juego.getPalabraSecreta());
                    btnAdivinar.setEnabled(false);
                    txtLetra.setEnabled(false);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this,ex.getMessage());
            }
            txtLetra.setText("");
        });
    }
    
    private void actualizarInterfaz(){
        actualizarPalabra();
        lblIntentos.setText( "Intentos restantes: " + juego.getIntentosRestantes());
        actualizarLetras();
        int errores = 6 - juego.getIntentosRestantes();
        actualizarAhorcado(errores);
    }
    
    private void actualizarPalabra(){
        String palabra = juego.getPalabraMostrada();
        String mostrar = "";
        for(int i = 0; i < palabra.length(); i++){
            mostrar += palabra.charAt(i) + " ";
        }
        lblPalabra.setText(mostrar);
    }
    
    private void actualizarLetras(){
        String correctas = "";
        String incorrectas = "";
        for(char letra : juego.getLetrasIngresadas()){
            if(juego.verificarLetra(letra)){
                correctas += letra + " ";
            }
            else{
                incorrectas += letra + " ";
            }
        }
        lblCorrectas.setText("Correctas: " + correctas);
        lblIncorrectas.setText("Incorrectas: " + incorrectas);
    }

}
