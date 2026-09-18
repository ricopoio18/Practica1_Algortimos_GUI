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
        setStyle("-fx-background-color: linear-gradient(to bottom, #10291f, #1d513d);");

        Label titulo = new Label("BLACKJACK");
        titulo.setStyle("-fx-font-size: 52px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label lblCantidad = new Label("Número de jugadores");
        lblCantidad.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

        cantidadJugadores = new ComboBox<>();
        cantidadJugadores.getItems().addAll(1, 2, 3, 4);
        cantidadJugadores.setValue(1);
        cantidadJugadores.setPrefWidth(280);
        cantidadJugadores.setPrefHeight(38);

        camposNombres = new VBox(12);
        camposNombres.setAlignment(Pos.CENTER);
        crearCamposJugadores(1);

        cantidadJugadores.setOnAction(e ->
                crearCamposJugadores(cantidadJugadores.getValue()));

        btnJugar = new Button("JUGAR");
        btnJugar.setPrefSize(280, 48);
        btnJugar.setStyle(
                "-fx-background-color: #d5b34c;" +
                        "-fx-text-fill: #14271f;" +
                        "-fx-font-size: 17px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
        );

        VBox tarjeta = new VBox(15);
        tarjeta.setAlignment(Pos.CENTER);
        tarjeta.setMaxWidth(380);
        tarjeta.setPadding(new Insets(30));
        tarjeta.setStyle(
                "-fx-background-color: rgba(0,0,0,0.25);" +
                        "-fx-background-radius: 18;" +
                        "-fx-border-color: rgba(255,255,255,0.15);" +
                        "-fx-border-radius: 18;"
        );

        tarjeta.getChildren().addAll(
                lblCantidad,
                cantidadJugadores,
                camposNombres,
                btnJugar
        );

        VBox contenido = new VBox(12);
        contenido.setAlignment(Pos.CENTER);
        contenido.getChildren().addAll(titulo, tarjeta);

        setCenter(contenido);
        setPadding(new Insets(40));
    }

    private void crearCamposJugadores(int cantidad) {
        camposNombres.getChildren().clear();
        nombres.clear();

        for (int i = 1; i <= cantidad; i++) {
            TextField campo = new TextField();
            campo.setPromptText("Nombre del jugador " + i);
            campo.setPrefWidth(280);
            campo.setMaxWidth(280);
            campo.setPrefHeight(38);
            campo.setStyle(
                    "-fx-background-radius: 7;" +
                            "-fx-font-size: 14px;" +
                            "-fx-padding: 8 12;"
            );

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
