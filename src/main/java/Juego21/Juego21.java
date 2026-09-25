package Juego21;

import DeckOfCards.CartaInglesa;

import java.util.ArrayList;
import java.util.HashMap;

public class Juego21 {

    private Pila<Jugador> jugadores;
    private Dealer dealer;
    private HashMap<String, Integer> puntuaciones;
    private HashMap<String, Resultado> resultados;

    private Pila<Movimiento> movimientos;

    public Juego21() {
        jugadores = new Pila<Jugador>(4);
        dealer = new Dealer();
        puntuaciones = new HashMap<>();
        resultados = new HashMap<>();

        movimientos = new Pila<Movimiento>(52);


    }

    public boolean añadirJugadores(String nombre) {
        if (jugadores.size() < 4) {
            jugadores.push(new Jugador(nombre));
            return true;
        }

        return false;
    }

    public void repartirCartasIniciales() {

        Pila<Jugador> auxiliar = new Pila<Jugador>(4);
        while (!jugadores.pila_vacia()) {
            auxiliar.push(jugadores.pop());
        }

        while (!auxiliar.pila_vacia()) {
            Jugador jugador = auxiliar.pop();
            for (int i = 0; i < 2; i++) {

                CartaInglesa carta = dealer.repartirCarta();

                if (carta != null) {
                    carta.makeFaceUp();
                    jugador.añadirCartas(carta);
                }
            }
            jugadores.push(jugador);
        }
        for (int i = 0; i < 2; i++) {
            CartaInglesa cartaDealer = dealer.repartirCarta();
            if (cartaDealer != null) {
                if (i == 0) {
                    cartaDealer.makeFaceUp();
                } else {
                    cartaDealer.makeFaceDown();
                }
                dealer.añadirCartas(cartaDealer);
            }
        }
    }

    public boolean pedirCartaJugador(Jugador jugador) {

        if (EvaluacionPuntos.estaExcedido(EvaluacionPuntos.obtenerTotalPuntos(jugador))) {
            return false;
        }

        CartaInglesa carta = dealer.repartirCarta();

        if (carta != null) {
            carta.makeFaceUp();
            boolean agregada = jugador.añadirCartas(carta);
            if (agregada) {
                movimientos.push(new Movimiento(jugador, carta));
            }

            return agregada;
        }

        return false;
    }

    public void revelarCartasDealer() {
        Pila<CartaInglesa> auxiliar = new Pila<CartaInglesa>();
        while (!dealer.getMano().pila_vacia()) {
            auxiliar.push(dealer.getMano().pop());
        }
        while (!auxiliar.pila_vacia()) {
            CartaInglesa carta = auxiliar.pop();
            carta.makeFaceUp();
            dealer.añadirCartas(carta);
        }
    }

    public CartaInglesa pedirCartaDealer() {
        CartaInglesa carta = dealer.repartirCarta();
        if (carta != null) {
            carta.makeFaceUp();
            dealer.añadirCartas(carta);
        }

        return carta;
    }

    public boolean dealerDebePedir() {
        return EvaluacionPuntos.obtenerTotalPuntos(dealer) < 17;
    }

    public void determinarGanadores() {

        int puntosDealer = dealer.getPuntos();
        puntuaciones.put(dealer.getNombre(), puntosDealer);
        Pila<Jugador> auxiliar = new Pila<Jugador>(4);

        while (!jugadores.pila_vacia()) {
            auxiliar.push(jugadores.pop());
        }

        while (!auxiliar.pila_vacia()) {
            Jugador jugador = auxiliar.pop();
            int puntosJugador = jugador.getPuntos();
            puntuaciones.put(jugador.getNombre(), puntosJugador);

            Resultado resultado;

            if (puntosJugador > 21) {
                resultado = Resultado.PERDEDOR;

            } else if (puntosDealer > 21) {
                resultado = Resultado.GANADOR;

            } else if (puntosJugador > puntosDealer) {
                resultado = Resultado.GANADOR;
            } else if (puntosJugador < puntosDealer) {
                resultado = Resultado.PERDEDOR;

            } else {
                resultado = Resultado.EMPATE;
            }

            resultados.put(jugador.getNombre(), resultado);

            jugadores.push(jugador);
        }
    }
    public void reiniciarRonda() {

        Pila<Jugador> auxiliar = new Pila<Jugador>(4);
        int cantidad = jugadores.size();

        for (int i = 0; i < cantidad; i++) {
            auxiliar.push(jugadores.pop());
        }

        cantidad = auxiliar.size();
        for (int i = 0; i < cantidad; i++) {
            Jugador jugador = auxiliar.pop();
            jugador.limpiarMano();
            jugadores.push(jugador);
        }

        dealer.limpiarMano();
        dealer.reiniciarMazo();
        puntuaciones.clear();
        resultados.clear();
    }

    public Pila<Jugador> getJugadores() {
        return jugadores;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public HashMap<String, Integer> getPuntuaciones() {
        return puntuaciones;
    }

    public HashMap<String, Resultado> getResultados() {
        return resultados;
    }

    public boolean deshacerMovimiento(Jugador jugador) {

        if (movimientos.pila_vacia()) {
            return false;
        }
        Movimiento movimiento = movimientos.peek();
        if (movimiento.getJugador() != jugador) {
            return false;
        }
        movimientos.pop();
        jugador.quitarUltimaCarta();

        return true;
    }
}