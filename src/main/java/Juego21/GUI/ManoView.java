package Juego21.GUI;

import DeckOfCards.CartaInglesa;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class ManoView extends HBox {

    public ManoView() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
    }

    public void mostrarMano(ArrayList<CartaInglesa> mano) {
        getChildren().clear();

        for (CartaInglesa carta : mano) {
            CartaView cartaView = new CartaView(carta);
            getChildren().add(cartaView);
        }
    }

    public void limpiar() {
        getChildren().clear();
    }
}