package Juego21;

import DeckOfCards.*;

import java.util.ArrayList;

public class EvaluacionPuntos {

    public static int obtenerTotalPuntos(ArrayList<CartaInglesa> conjuntoCartas) {
        int acumulado = 0;
        int contadorAses = 0;

        for (CartaInglesa carta : conjuntoCartas) {
            int valorCarta = carta.getValor();

            if (valorCarta >= 2 && valorCarta <= 10) {
                acumulado += valorCarta;
            } else if (valorCarta >= 11 && valorCarta <= 13) {
                acumulado += 10;
            } else if (valorCarta == 14) {
                acumulado += 11;
                contadorAses++;
            }
        }

        while (acumulado > 21 && contadorAses > 0) {
            acumulado -= 10;
            contadorAses--;
        }

        return acumulado;
    }


    public static int obtenerTotalPuntos(Jugador participante) {
        return obtenerTotalPuntos(participante.getMano());
    }

    public static boolean estaExcedido(int puntajeTotal) {
        return puntajeTotal > 21;
    }


    public static boolean esVictoria(int puntosParticipante, int puntosBanca) {
        if (puntosParticipante > 21) {
            return false;
        }
        if (puntosBanca > 21) {
            return true;
        }
        return puntosParticipante > puntosBanca;
    }
}