package Juego21;

import DeckOfCards.CartaInglesa;

import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private Pila<CartaInglesa> mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new Pila<CartaInglesa>();
    }

    public boolean añadirCartas(CartaInglesa carta) {
        if (EvaluacionPuntos.obtenerTotalPuntos(this) >= 21) {
            return false;
        }

        mano.push(carta);
        return true;
    }

    public void limpiarMano() {
        for(int i = 0; i < mano.size(); i ++){
            mano.pop();
        }
    }

    public Pila<CartaInglesa> getMano() {
        return mano;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return EvaluacionPuntos.obtenerTotalPuntos(this);
    }
}
