/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahorcados.logica;

import java.util.ArrayList;

/**
 *
 * @author diego
 */
    public abstract class BaseAhorcado implements AhorcadoJuego {

    protected String palabraSecreta;
    
    protected String palabraMostrada;
    
    protected ArrayList <Character> letrasIngresadas;
    protected int intentosRestantes;
    protected final int maximoIntentos = 6;
    
    protected ArrayList <String> dibujoAhorcado;

    public BaseAhorcado() {
        intentosRestantes = maximoIntentos;
        
        letrasIngresadas = new ArrayList<>();
        
        dibujoAhorcado = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            dibujoAhorcado.add(" ");
        }
    }

    @Override
    public void establecerPalabraSecreta(String palabra) {
        palabraSecreta = palabra.toUpperCase();

        palabraMostrada = "";

        for (int i = 0; i < palabraSecreta.length(); i++) {
            palabraMostrada += "_";
        }
    }

    public abstract void UpdatePalabraMostrada(char letra);

    public abstract boolean verificarLetra(char letra);
    

    public abstract boolean verificarVictoria();

    public boolean verificarDerrota() {
        return intentosRestantes <= 0;
    }

    public String getPalabraMostrada() {
        return palabraMostrada;
    }

    public int getIntentosRestantes() {
        
        return intentosRestantes;
        
    }

    public ArrayList<Character> getLetrasIngresadas() {
        
        return letrasIngresadas;
    }

    public String getPalabraSecreta() {
        
        return palabraSecreta;
    }
    
    
    }
