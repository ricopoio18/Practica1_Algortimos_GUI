package Juego21;

import DeckOfCards.CartaInglesa;

import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private ArrayList<CartaInglesa> mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        mano = new ArrayList<>();
    }

    public boolean añadirCartas(CartaInglesa carta) {
        if (EvaluacionPuntos.obtenerTotalPuntos(this) >= 21) {
            return false;
        }

        mano.add(carta);
        return true;
    }

    public void limpiarMano() {
        mano.clear();
    }

    public ArrayList<CartaInglesa> getMano() {
        return mano;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return EvaluacionPuntos.obtenerTotalPuntos(this);
    }
}
