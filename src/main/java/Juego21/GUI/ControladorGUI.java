package Juego21.GUI;

import Juego21.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ControladorGUI {
    private Stage stage;
    private Juego21 modelo;
    private MenuView menuView;
    private JuegoView juegoView;

    private int jugadorActual;
    public ControladorGUI(Stage stage) {
        this.stage = stage;
    }

    public void mostrarMenu() {
        menuView = new MenuView();
        Scene escena = new Scene(menuView, 1200, 750);
        stage.setScene(escena);
        menuView.getBtnJugar().setOnAction(e -> iniciarJuego());
    }

    private void iniciarJuego() {
        ArrayList<String> nombres =
                menuView.getNombres();

        if (nombres.isEmpty()) {
            return;
        }
        for (String nombre : nombres) {
            if (nombre.isBlank()) {
                return;
            }
        }
        modelo = new Juego21();
        for (String nombre : nombres) {
            modelo.añadirJugadores(nombre);
        }
        modelo.repartirCartasIniciales();
        jugadorActual = 0;
        juegoView = new JuegoView();
        Scene escena = new Scene(juegoView, 1200, 750);

        stage.setScene(escena);
        configurarBotones();
        actualizarVista();
    }

    private void configurarBotones() {

        juegoView.getBtnHit().setOnAction(e -> hit());
        juegoView.getBtnStand().setOnAction(e -> stand());
        juegoView.getBtnMenu().setOnAction(e -> mostrarMenu());
    }

    private void hit() {
        Jugador jugador = obtenerJugadorActual();
        modelo.pedirCartaJugador(jugador);
        actualizarVista();
        if (jugador.getPuntos() >= 21) {
            siguienteJugador();
        }
    }

    private void stand() {
        siguienteJugador();
    }

    private void siguienteJugador() {
        jugadorActual++;
        if (jugadorActual < modelo.getJugadores().size()) {
            actualizarVista();

        } else {
            turnoDealer();
        }
    }

    private void turnoDealer() {

        juegoView.bloquearAcciones();
        modelo.revelarCartasDealer();
        while (modelo.dealerDebePedir()) {
            modelo.pedirCartaDealer();
        }
        modelo.determinarGanadores();
        actualizarVistaFinal();
    }

}