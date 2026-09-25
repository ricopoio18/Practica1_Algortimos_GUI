package Juego21.GUI;

import DeckOfCards.CartaInglesa;
import Juego21.Pila;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ManoView extends HBox {

    public ManoView() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
    }

    public void mostrarMano(Pila<CartaInglesa> mano) {
        getChildren().clear();
        Pila<CartaInglesa> auxiliar = new Pila<CartaInglesa>();
        while (!mano.pila_vacia()) {
            auxiliar.push(mano.pop());
        }
        while (!auxiliar.pila_vacia()) {
            CartaInglesa carta = auxiliar.pop();
            CartaView cartaView = new CartaView(carta);
            getChildren().add(cartaView);
            mano.push(carta);
        }
    }
    public void limpiar() {
        getChildren().clear();
    }
}