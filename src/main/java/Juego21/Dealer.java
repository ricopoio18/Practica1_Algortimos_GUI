package Juego21;

import DeckOfCards.CartaInglesa;
import DeckOfCards.Mazo;

public class Dealer extends Jugador {
    private Mazo mazo;


    public Dealer() {
        super("Dealer");
        mazo = new Mazo();
    }

    public CartaInglesa repartirCarta() {
        return mazo.obtenerUnaCarta();
    }

    public void reiniciarMazo() {
        mazo = new Mazo();
    }

    public void devolverCarta(CartaInglesa carta) {
        mazo.devolverCarta(carta);
    }
}