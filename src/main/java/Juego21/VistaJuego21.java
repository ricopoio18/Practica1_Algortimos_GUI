package Juego21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VistaJuego21 {
    private Scanner scanner = new Scanner(System.in);

    public int pedirNumeroJugadores() {
        int cantidad;
        do {
            System.out.print("Número de jugadores (1-5): ");
            cantidad = scanner.nextInt();
            scanner.nextLine();
        } while (cantidad < 1 || cantidad > 5);

        return cantidad;
    }

    public String pedirNombreJugador(int numero) {
        System.out.print("Nombre jugador " + numero + ": ");
        return scanner.nextLine().trim();
    }

    public void mostrarInicioRonda(int ronda) {
        System.out.println("\n========================");
        System.out.println("       RONDA " + ronda);
        System.out.println("========================");
    }

    public void mostrarCartaVisibleDealer(Dealer dealer) {
        System.out.println("\nDealer: " + dealer.getMano());
    }

    public void mostrarEstadoJugador(
            Jugador jugador, int puntos) {

        System.out.println("\n" + jugador.getNombre());
        System.out.println(
                "Cartas: " + jugador.getMano());

        System.out.println(
                "Puntos: " + puntos);
    }

    public boolean pedirDecisionPedirCarta() {

        System.out.print("¿Quieres pedir otra carta? s/n: ");
        return scanner.nextLine().trim().equalsIgnoreCase("s");
    }

    public void mostrarMensajeExcedido(String nombre) {
        System.out.println(
                nombre + " se pasó de 21.");
    }

    public void mostrarInicioTurnoDealer() {
        System.out.println("\n--- Turno del Dealer ---");
    }

    public void mostrarEstadoDealer(Dealer dealer, int puntos) {
        System.out.println("Dealer: " + dealer.getMano() + " | Puntos: " + puntos
        );
    }

    public void mostrarDealerPideCarta() {
        System.out.println("El Dealer pide una carta...");
    }

    public void mostrarDealerSePlanta() {
        System.out.println("El Dealer se planta.");
    }

    public void mostrarResultadosFinales(Dealer dealer, Pila<Jugador> jugadores, HashMap<String, Integer> puntuaciones, HashMap<String, Resultado> resultados) {

        System.out.println("\n--- Resultados ---");
        int puntosDealer = puntuaciones.get(dealer.getNombre());

        System.out.println(
                "Dealer: " + puntosDealer + " puntos " + dealer.getMano()
        );

        boolean dealerGanoContraTodos = true;
        boolean todosEmpataron = true;

        Pila<Jugador> pila = new Pila<Jugador>();
        for(int j = 0; j < jugadores.size(); j++){
            pila.push(jugadores.pop());

        }
        for (int i = 0; i < pila.size(); i++) {
            String nombre = pila.peek().getNombre();
            int puntosJugador = puntuaciones.get(nombre);
            Resultado resultado = resultados.get(nombre);
            System.out.println(
                    nombre + ": " + puntosJugador + " puntos " + pila.peek().getMano() + " -> " + resultado
            );

            if (resultado != Resultado.PERDEDOR) {
                dealerGanoContraTodos = false;
            }

            if (resultado != Resultado.EMPATE) {
                todosEmpataron = false;
            }
        }


        if (dealerGanoContraTodos) {

            System.out.println("\nDealer gana la ronda.");

        } else if (todosEmpataron) {
            System.out.println("\nLa ronda termina en empate.");

        } else if (jugadores.size() == 1) {
            Pila<Jugador> pilaTemporal = new Pila<Jugador>();
            for(int j = 0; j < jugadores.size(); j++){
                pila.push(jugadores.pop());

            }
            Jugador jugador = pilaTemporal.peek();
            Resultado resultado = resultados.get(jugador.getNombre());
            for(int j = 0; j < pilaTemporal.size(); j++){
                jugadores.push(pilaTemporal.pop());

            }
            if (resultado == Resultado.GANADOR) {
                System.out.println("\n" + jugador.getNombre() + " gana la ronda.");

            } else if (resultado == Resultado.EMPATE) {
                System.out.println("\nLa ronda termina en empate.");
            }

        } else {
            System.out.println("\nResultados individuales contra el Dealer.");
        }
    }

    public boolean preguntarOtraRonda() {
        System.out.print("\n¿Quieres jugar otra ronda? s/n: ");
        return scanner.nextLine().trim().equalsIgnoreCase("s");
    }
}