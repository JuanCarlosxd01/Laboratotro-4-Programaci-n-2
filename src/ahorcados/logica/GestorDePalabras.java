/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcados.logica;//Jhoveth Moncada
import java.util.Random;

import java.util.ArrayList;

import ahorcados.excepciones.PalabraDuplicadaException;

public class GestorDePalabras {

    private ArrayList<String> palabras;
    private Random random;

    public GestorDePalabras() {
        palabras = new ArrayList<>();
        random = new Random();
    }

    public void agregarPalabra(String palabra) throws PalabraDuplicadaException {

        if (palabra == null || palabra.isBlank()) {
            return;
        }

        palabra = palabra.toUpperCase();

        if (palabras.contains(palabra)) {
            throw new PalabraDuplicadaException("La palabra que desea ingresar, ya existe estimado");
        }

        palabras.add(palabra);
    }

    public String obtenerPalabra() {

        if (palabras.isEmpty()) {
            return null;
        }

        int posicion = random.nextInt(palabras.size());

        return palabras.get(posicion);
    }

    public int cantidadPalabras() {
        return palabras.size();
    }
}
    

