package Juego21.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class MenuView extends BorderPane {

    private ComboBox<Integer> cantidadJugadores;
    private VBox camposNombres;
    private Button btnJugar;
    private ArrayList<TextField> nombres;

    public MenuView() {
        nombres = new ArrayList<>();
        crearVista();
    }

    private void crearVista() {

        Label titulo = new Label("BLACKJACK");

        titulo.setStyle("""
                -fx-font-size: 42px;
                -fx-font-weight: bold;
                -fx-text-fill: white;
                """);

        Label lblCantidad = new Label("Número de jugadores");

        lblCantidad.setStyle("-fx-text-fill: white; -fx-font-size: 16px;"
        );

        cantidadJugadores = new ComboBox<>();
        cantidadJugadores.getItems().addAll(1, 2, 3, 4);

        cantidadJugadores.setValue(1);
        camposNombres = new VBox(10);
        crearCamposJugadores(1);
        cantidadJugadores.setOnAction(e ->
                crearCamposJugadores(
                        cantidadJugadores.getValue()
                )
        );

        btnJugar = new Button("JUGAR");
        btnJugar.setPrefWidth(200);
        btnJugar.setPrefHeight(45);

        VBox contenido = new VBox(
                20,
                titulo,
                lblCantidad,
                cantidadJugadores,
                camposNombres,
                btnJugar
        );

        contenido.setAlignment(Pos.CENTER);
        setCenter(contenido);
        setPadding(new Insets(40));
        setStyle("-fx-background-color: #183d2f;");
    }
    private void crearCamposJugadores(int cantidad) {
        camposNombres.getChildren().clear();
        nombres.clear();

        for (int i = 1; i <= cantidad; i++) {
            TextField campo = new TextField();
            campo.setPromptText("Nombre del jugador " + i);
            campo.setMaxWidth(300);
            nombres.add(campo);
            camposNombres.getChildren().add(campo);
        }
    }

    public Button getBtnJugar() {
        return btnJugar;
    }

    public ArrayList<String> getNombres() {
        ArrayList<String> resultado = new ArrayList<>();

        for (TextField campo : nombres) {
            resultado.add(campo.getText().trim());
        }

        return resultado;
    }

}