/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcados.logica;

/**
 *
 * @author diego
 */
public interface AhorcadoJuego {

    void establecerPalabraSecreta(String palabra);

    void jugar(char letra) throws Exception;
    
}