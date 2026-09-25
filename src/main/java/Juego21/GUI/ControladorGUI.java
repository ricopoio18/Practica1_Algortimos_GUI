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
        ArrayList<String> nombres = menuView.getNombres();

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

        juegoView = new JuegoView();
        Scene escena = new Scene(juegoView, 1200, 750);
        stage.setScene(escena);
        configurarBotones();
        iniciarRonda();
    }

    private void iniciarRonda() {
        modelo.reiniciarRonda();
        modelo.repartirCartasIniciales();
        jugadorActual = 0;
        juegoView.ocultarResultados();
        juegoView.habilitarAcciones();
        actualizarVista();
        comprobarTurno();
    }
    private void configurarBotones() {
        juegoView.getBtnHit().setOnAction(e -> hit());
        juegoView.getBtnStand().setOnAction(e -> stand());
        juegoView.getBtnMenu().setOnAction(e -> mostrarMenu());
        juegoView.getBtnNuevaRonda().setOnAction(e -> iniciarRonda());
        juegoView.getBtnVolverMenu().setOnAction(e -> mostrarMenu());
    }

    private void hit() {

        Jugador jugador = obtenerJugadorActual();
        if (jugador == null) {
            return;
        }
        boolean recibioCarta = modelo.pedirCartaJugador(jugador);

        if (!recibioCarta) {
            siguienteJugador();
            return;
        }
        actualizarVista();
        if (jugador.getPuntos() >= 21) {
            siguienteJugador();
        }
    }

    private void stand() {
        siguienteJugador();
    }

    private void comprobarTurno() {
        Jugador jugador = obtenerJugadorActual();

        if (jugador.getPuntos() >= 21) {
            siguienteJugador();
        }
    }

    private void siguienteJugador() {
        jugadorActual++;

        if (jugadorActual < modelo.getJugadores().size()) {
            actualizarVista();
            comprobarTurno();
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

    private Jugador obtenerJugadorActual() {

        Pila<Jugador> auxiliar = new Pila<Jugador>(4);
        Jugador encontrado = null;
        while (!modelo.getJugadores().pila_vacia()) {
            auxiliar.push(modelo.getJugadores().pop());
        }

        int indice = 0;

        while (!auxiliar.pila_vacia()) {
            Jugador jugador = auxiliar.pop();
            if (indice == jugadorActual) {
                encontrado = jugador;
            }
            modelo.getJugadores().push(jugador);
            indice++;
        }
        return encontrado;
    }

    private void actualizarVista() {
        Jugador jugador = obtenerJugadorActual();
        juegoView.setNombreJugador(jugador.getNombre());
        juegoView.getManoJugador().mostrarMano(jugador.getMano());
        juegoView.getManoDealer().mostrarMano(modelo.getDealer().getMano());
        juegoView.getMarcador().actualizar(modelo.getJugadores());
        juegoView.mostrarMensaje("Turno de " + jugador.getNombre());
    }

    private void actualizarVistaFinal() {
        juegoView.getManoDealer().mostrarMano(modelo.getDealer().getMano());
        juegoView.getMarcador().actualizar(modelo.getJugadores());
        juegoView.mostrarMensaje("Ronda terminada");
        mostrarResultado();
    }

    private void mostrarResultado() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("DEALER: ")
                .append(modelo.getDealer().getPuntos())
                .append("\n\n");

        Pila<Jugador> auxiliar = new Pila<Jugador>(4);

        while (!modelo.getJugadores().pila_vacia()) {
            auxiliar.push(modelo.getJugadores().pop());
        }

        while (!auxiliar.pila_vacia()) {
            Jugador jugador = auxiliar.pop();
            Resultado resultado = modelo.getResultados().get(jugador.getNombre());

            mensaje.append(jugador.getNombre())
                    .append("   ")
                    .append(jugador.getPuntos())
                    .append("   ");

            if (resultado == Resultado.GANADOR) {
                mensaje.append("GANADOR");

            } else if (resultado == Resultado.PERDEDOR) {
                mensaje.append("PERDEDOR");

            } else {
                mensaje.append("EMPATE");
            }
            mensaje.append("\n");
            modelo.getJugadores().push(jugador);
        }

        juegoView.mostrarResultados(mensaje.toString());
    }
}