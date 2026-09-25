package Juego21;

import DeckOfCards.CartaInglesa;

public class Movimiento {

    private Jugador jugador;
    private CartaInglesa carta;

    public Movimiento(Jugador jugador, CartaInglesa carta) {
        this.jugador = jugador;
        this.carta = carta;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public CartaInglesa getCarta() {
        return carta;
    }
}
