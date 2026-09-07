package Juego21;

import DeckOfCards.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Juego21 {
    private ArrayList<Jugador> jugadores;
    private Dealer dealer;
    private HashMap<String,Integer> puntuaciones;
    private HashMap<String, Boolean> ganadores;

    public Juego21() {
        jugadores = new ArrayList<Jugador>();
        dealer = new Dealer();
        puntuaciones = new HashMap<>();
        ganadores = new HashMap<>();
    }

    public boolean añadirJugadores(String nombre){
        if(jugadores.size() < 5){
            jugadores.add(new Jugador(nombre));
            return true;
        }
        return false;
    }

    public void repartirCartasIniciales() {
        for (int i = 0; i < 2; i++) {
            for (Jugador jugador : jugadores) {
                CartaInglesa carta = dealer.repartirCarta();
                if (carta != null) {
                    jugador.añadirCartas(carta);
                    sincronizarPuntos(jugador);
                }
            }
            CartaInglesa cartaDealer = dealer.repartirCarta();
            if (cartaDealer != null) {
                dealer.añadirCartas(cartaDealer);
                sincronizarPuntos(dealer);
            }
        }
    }

    public boolean pedirCartaJugador(Jugador jugador) {
        if (EvaluacionPuntos.estaExcedido(EvaluacionPuntos.obtenerTotalPuntos(jugador))) {
            return false;
        }

        CartaInglesa carta = dealer.repartirCarta();
        if (carta != null) {
            boolean entregada = jugador.añadirCartas(carta);
            sincronizarPuntos(jugador);
            return entregada;
        }
        return false;
    }

    public void jugarTurnoDealer() {
        while (EvaluacionPuntos.obtenerTotalPuntos(dealer) < 17) {
            CartaInglesa carta = dealer.repartirCarta();
            if (carta != null) {
                dealer.añadirCartas(carta);
                sincronizarPuntos(dealer);
            } else {
                break;
            }
        }
    }

    public void determinarGanadores() {
        int puntosDealer = EvaluacionPuntos.obtenerTotalPuntos(dealer);
        puntuaciones.put(dealer.getNombre(), puntosDealer);

        for (Jugador jugador : jugadores) {
            int puntosJugador = EvaluacionPuntos.obtenerTotalPuntos(jugador);
            puntuaciones.put(jugador.getNombre(), puntosJugador);

            boolean victoria = EvaluacionPuntos.esVictoria(puntosJugador, puntosDealer);
            ganadores.put(jugador.getNombre(), victoria);
        }
    }

    public void reiniciarRonda() {
        for (Jugador jugador : jugadores) {
            jugador.limpiarMano();
            sincronizarPuntos(jugador);
        }
        dealer.limpiarMano();
        sincronizarPuntos(dealer);

        puntuaciones.clear();
        ganadores.clear();
    }

    private void sincronizarPuntos(Jugador jugador) {
        int totalCalculado = EvaluacionPuntos.obtenerTotalPuntos(jugador);
        int diferencia = totalCalculado - jugador.getPuntos();
        jugador.sumarPuntos(diferencia);
    }


    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public HashMap<String, Integer> getPuntuaciones() {
        return puntuaciones;
    }

    public HashMap<String, Boolean> getGanadores() {
        return ganadores;
    }
}

