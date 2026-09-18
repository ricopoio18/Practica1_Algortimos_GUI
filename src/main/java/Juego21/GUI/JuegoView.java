package Juego21.GUI;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class JuegoView extends BorderPane {

    private Label nombreDealer;
    private Label nombreJugador;
    private Label mensaje;
    private ManoView manoDealer;
    private ManoView manoJugador;
    private MarcadorView marcador;
    private MazoView mazoView;
    private Button btnStand;
    private Button btnHit;
    private Button btnMenu;

    public JuegoView() {
        crearVista();
    }

    private void crearVista() {
        setPadding(new Insets(20));
        setStyle("-fx-background-color: #12613d;");
        nombreDealer = crearTitulo("DEALER");
        manoDealer = new ManoView();
        VBox dealerBox = new VBox(
                10,
                nombreDealer,
                manoDealer
        );

        dealerBox.setAlignment(Pos.CENTER);

        nombreJugador = crearTitulo("");
        manoJugador = new ManoView();
        VBox jugadorBox = new VBox(10, nombreJugador, manoJugador
        );
        jugadorBox.setAlignment(Pos.CENTER);

        mensaje = new Label();
        mensaje.setStyle("""
                -fx-text-fill: #ffd54f;
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                """);
        VBox centro = new VBox(35, dealerBox, mensaje, jugadorBox
        );

        centro.setAlignment(Pos.CENTER);
        setCenter(centro);
        marcador = new MarcadorView();
        BorderPane.setMargin(
                marcador,
                new Insets(20)
        );

        setLeft(marcador);
        mazoView = new MazoView();
        BorderPane.setMargin(mazoView, new Insets(20));
        setRight(mazoView);
        btnStand = crearBoton("STAND");
        btnHit = crearBoton("HIT");
        btnMenu = crearBoton("MENÚ");

        HBox botones = new HBox(30, btnStand, btnHit, btnMenu
        );

        botones.setAlignment(Pos.CENTER);
        botones.setPadding(new Insets(20));
        setBottom(botones);
    }
    private Label crearTitulo(String texto) {
        Label label = new Label(texto);
        label.setStyle("""
                -fx-font-size: 22px;
                -fx-font-weight: bold;
                -fx-text-fill: white;
                """);

        return label;
    }

    private Button crearBoton(String texto) {

        Button boton = new Button(texto);
        boton.setPrefWidth(140);
        boton.setPrefHeight(45);
        boton.setStyle("""
                -fx-font-size: 16px;
                -fx-font-weight: bold;
                """);

        return boton;
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

    public void setNombreJugador(String nombre) {
        nombreJugador.setText(nombre);
    }

    public void mostrarMensaje(String texto) {
        mensaje.setText(texto);
    }

    public void bloquearAcciones() {
        btnHit.setDisable(true);
        btnStand.setDisable(true);
    }

    public void habilitarAcciones() {
        btnHit.setDisable(false);
        btnStand.setDisable(false);
    }

}