package Juego21.GUI;

import Juego21.Jugador;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MarcadorView extends VBox {

    public MarcadorView() {

        setSpacing(15);
        setPadding(new Insets(20));
        setPrefWidth(220);
        setStyle("""
                -fx-background-color: rgba(0,0,0,0.20);
                -fx-background-radius: 15;
                """);
    }
    public void actualizar(ArrayList<Jugador> jugadores) {
        getChildren().clear();
        Label titulo = new Label("PUNTUACIONES");

        titulo.setStyle("""
                -fx-text-fill: white;
                -fx-font-size: 20px;
                -fx-font-weight: bold;
                """);

        getChildren().add(titulo);
        for (Jugador jugador : jugadores) {
            Label puntuacion = new Label(jugador.getNombre() + ": " + jugador.getPuntos());

            puntuacion.setStyle("""
                    -fx-text-fill: white;
                    -fx-font-size: 16px;
                    """);

            getChildren().add(puntuacion);
        }
    }

}