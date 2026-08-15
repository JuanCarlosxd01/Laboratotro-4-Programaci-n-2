/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcados.logica;

import java.util.ArrayList;
import ahorcados.excepciones.LetraInvalidaException;
import ahorcados.excepciones.LetraRepetidaException;
/**
 *
 * @author diego
 */
public class AhorcadoPalabraFija extends BaseAhorcado {

    public AhorcadoPalabraFija(String palabra) {
        establecerPalabraSecreta(palabra);
    }

    @Override
    public void jugar(char letra) throws Exception {

        letra = Character.toUpperCase(letra);

        if (!Character.isLetter(letra)) {
            throw new LetraInvalidaException("Letra ingresad no es valida, intenta nuevamente");
        }

        if (letrasIngresadas.contains(letra)) {
            throw new LetraRepetidaException("Esta letra ya fue elegida, intenta nuevamente");
        }

        letrasIngresadas.add(letra);

        if (verificarLetra(letra)) {
            UpdatePalabraMostrada(letra);
        } else {
            intentosRestantes--;
        }
    }

    @Override
    public boolean verificarLetra(char letra) {
        return palabraSecreta.indexOf(letra) >= 0;
    }

    public void UpdatePalabraMostrada(char letra) {

        String nuevaPalabra = "";

        for (int i = 0; i < palabraSecreta.length(); i++) {

            if (palabraSecreta.charAt(i) == letra) {
                
                nuevaPalabra += letra;
            } else {
                nuevaPalabra += palabraMostrada.charAt(i);
            }
        }

        palabraMostrada = nuevaPalabra;
    }

    @Override
    public boolean verificarVictoria() {
        return palabraMostrada.equals(palabraSecreta);
    }
}