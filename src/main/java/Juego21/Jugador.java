package Juego21;

import DeckOfCards.*;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private ArrayList<CartaInglesa> mano;
    private int puntuacion;

    public Jugador(String nombre){
        this.nombre = nombre;
        puntuacion = 0;
        mano = new ArrayList<CartaInglesa>();
    }

    public boolean añadirCartas(CartaInglesa carta){
        if(puntuacion > 21){
            return false;
        }
        mano.add(carta);
        return true;
    }

    public void sumarPuntos(int puntos){
        puntuacion += puntos;
    }

    public int getPuntos(){
        return puntuacion;
    }
    public void limpiarMano(){
        mano.clear();
    }

    public ArrayList<CartaInglesa> getMano(){
        return mano;
    }

    public String getNombre(){
        return nombre;
    }



}
