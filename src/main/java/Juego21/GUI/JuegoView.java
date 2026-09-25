package Juego21.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class JuegoView extends BorderPane {
    private ManoView manoDealer;
    private ManoView manoJugador;
    private MarcadorView marcador;
    private MazoView mazoView;
    private Label lblNombreJugador;
    private Label lblMensaje;
    private Button btnStand;
    private Button btnHit;
    private Button btnMenu;
    private VBox panelResultados;
    private Label lblResultados;
    private Button btnNuevaRonda;
    private Button btnVolverMenu;

    private Button btnUndo;


    public JuegoView() {
        crearVista();
    }

    private void crearVista() {
        setPadding(new Insets(20));
        setStyle("-fx-background-color: linear-gradient(to bottom, #10291f, #1d513d);");

        marcador = new MarcadorView();
        setLeft(marcador);
        BorderPane.setMargin(marcador, new Insets(20));

        mazoView = new MazoView();
        VBox zonaMazo = new VBox(10, mazoView);
        zonaMazo.setAlignment(Pos.CENTER);
        zonaMazo.setPadding(new Insets(20));
        setRight(zonaMazo);

        Label lblDealer = new Label("DEALER");
        lblDealer.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        manoDealer = new ManoView();
        VBox dealerBox = new VBox(10, lblDealer, manoDealer);
        dealerBox.setAlignment(Pos.CENTER);

        lblNombreJugador = new Label("JUGADOR");
        lblNombreJugador.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white;");

        manoJugador = new ManoView();
        lblMensaje = new Label("");
        lblMensaje.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #e8c66a;");

        VBox jugadorBox = new VBox(10, lblNombreJugador, manoJugador, lblMensaje);
        jugadorBox.setAlignment(Pos.CENTER);

        VBox mesa = new VBox(55, dealerBox, jugadorBox);
        mesa.setAlignment(Pos.CENTER);

        crearPanelResultados();

        StackPane centro = new StackPane();
        centro.getChildren().addAll(mesa, panelResultados);
        StackPane.setAlignment(panelResultados, Pos.CENTER);
        setCenter(centro);

        btnStand = crearBoton("STAND");
        btnHit = crearBoton("HIT");
        btnMenu = crearBoton("MENÚ");
        btnUndo = new Button("UNDO");

        HBox botones = new HBox(20, btnStand, btnHit, btnMenu, btnUndo);
        botones.setAlignment(Pos.CENTER);
        botones.setPadding(new Insets(20, 0, 5, 0));
        setBottom(botones);
    }

    private void crearPanelResultados() {
        Label titulo = new Label("RESULTADOS");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #e8c66a;");

        lblResultados = new Label();
        lblResultados.setStyle("-fx-font-size: 17px; -fx-text-fill: white;");
        lblResultados.setWrapText(true);

        btnNuevaRonda = crearBoton("NUEVA RONDA");
        btnVolverMenu = crearBoton("MENÚ");

        HBox botones = new HBox(15, btnNuevaRonda, btnVolverMenu);
        botones.setAlignment(Pos.CENTER);

        panelResultados = new VBox(20, titulo, lblResultados, botones);
        panelResultados.setAlignment(Pos.CENTER);
        panelResultados.setPadding(new Insets(30));
        panelResultados.setMaxWidth(430);
        panelResultados.setMaxHeight(350);
        panelResultados.setStyle(
                "-fx-background-color: rgba(8, 27, 20, 0.97);" +
                        "-fx-background-radius: 18;" +
                        "-fx-border-color: #e8c66a;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-radius: 18;"
        );

        panelResultados.setVisible(false);
        panelResultados.setManaged(false);
    }

    private Button crearBoton(String texto) {
        Button boton = new Button(texto);
        boton.setPrefWidth(150);
        boton.setPrefHeight(42);
        boton.setStyle(
                "-fx-background-color: #d5b34c;" +
                        "-fx-text-fill: #14271f;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
        );
        return boton;
    }

    public void setNombreJugador(String nombre) {
        lblNombreJugador.setText(nombre.toUpperCase());
    }

    public void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    public void mostrarResultados(String resultados) {
        lblResultados.setText(resultados);
        panelResultados.setManaged(true);
        panelResultados.setVisible(true);
        panelResultados.toFront();
    }

    public void ocultarResultados() {
        panelResultados.setVisible(false);
        panelResultados.setManaged(false);
    }

    public void bloquearAcciones() {
        btnHit.setDisable(true);
        btnStand.setDisable(true);
    }

    public void habilitarAcciones() {
        btnHit.setDisable(false);
        btnStand.setDisable(false);
    }

    public ManoView getManoDealer() {
        return manoDealer;
    }

    public ManoView getManoJugador() {
        return manoJugador;
    }

    public MarcadorView getMarcador() {
        return marcador;
    }

    public Button getBtnStand() {
        return btnStand;
    }

    public Button getBtnHit() {
        return btnHit;
    }

    public Button getBtnMenu() {
        return btnMenu;
    }

    public Button getBtnNuevaRonda() {
        return btnNuevaRonda;
    }

    public Button getBtnVolverMenu() {
        return btnVolverMenu;
    }

    public Button getBtnUndo() {
        return btnUndo;
    }
}