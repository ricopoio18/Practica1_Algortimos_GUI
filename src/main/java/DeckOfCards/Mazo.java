package DeckOfCards;
/**
 * Write a description of class Mazo here.
 *
 * @author (Cecilia Curlango Rosas)
 * @version (2025-2)
 */
import Juego21.Pila;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private Pila<CartaInglesa> cartas = new Pila<CartaInglesa>(52);

    public Mazo() {
        llenar(); // crea todas las cartas, excluyendo Jokers
        mezclar();
    }

    /**
     * Obtiene todas las cartas del mazo.
     * @return
     */
//    public Pila<CartaInglesa> getCartas() {
//        return cartas;
//    }

    public CartaInglesa obtenerUnaCarta() {
        if (cartas.size() > 0) {
            return cartas.pop();
        }
        return null;
    }
    private void mezclar() {
        ArrayList<CartaInglesa> listaMezclada = new ArrayList<>();
        while (!cartas.pila_vacia()) {
            listaMezclada.add(cartas.pop());
        }
        Collections.shuffle(listaMezclada);
        for (CartaInglesa carta : listaMezclada) {
            cartas.push(carta);
        }
    }
    private void llenar() {
        for (int i = 2; i <= 14 ; i++) {
            for (Palo palo : Palo.values()) {
                CartaInglesa c = new CartaInglesa(i,palo, palo.getColor());
                cartas.push(c);
            }
        }
    }

    public void devolverCarta(CartaInglesa carta) {
        if (carta != null) {
            cartas.push(carta);
            mezclar();
        }
    }

    @Override
    public String toString() {
        return cartas.toString();
    }
}
