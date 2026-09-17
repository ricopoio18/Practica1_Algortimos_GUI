package Juego21;

public class ControladorJuego21 {
    private Juego21 modelo;
    private VistaJuego21 vista;

    public ControladorJuego21(Juego21 modelo, VistaJuego21 vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciarJuego() {
        int numJugadores = vista.pedirNumeroJugadores();

        for (int i = 1; i <= numJugadores; i++) {
            String nombre = vista.pedirNombreJugador(i);
            modelo.añadirJugadores(nombre);
        }

        int ronda = 1;
        boolean seguirJugando;

        do {
            vista.mostrarInicioRonda(ronda);
            modelo.repartirCartasIniciales();
            vista.mostrarCartaVisibleDealer(modelo.getDealer());

            for (Jugador jugador : modelo.getJugadores()) {
                ejecutarTurno(jugador);
            }

            ejecutarTurnoDealer();

            modelo.determinarGanadores();
            vista.mostrarResultadosFinales(
                    modelo.getDealer(),
                    modelo.getJugadores(),
                    modelo.getPuntuaciones(),
                    modelo.getResultados()
            );

            seguirJugando = vista.preguntarOtraRonda();

            if (seguirJugando) {
                modelo.reiniciarRonda();
                ronda++;
            }

        } while (seguirJugando);
    }

    private void ejecutarTurno(Jugador jugador) {
        while (true) {

            int puntos = jugador.getPuntos();

            vista.mostrarEstadoJugador(jugador, puntos);
            if (puntos >= 21) {
                if (puntos > 21) {
                    vista.mostrarMensajeExcedido(
                            jugador.getNombre()
                    );
                }
                break;
            }

            if (vista.pedirDecisionPedirCarta()) {
                modelo.pedirCartaJugador(jugador);
            } else {
                break;
            }
        }
    }

    private void ejecutarTurnoDealer() {

        vista.mostrarInicioTurnoDealer();
        modelo.revelarCartasDealer();
        vista.mostrarEstadoDealer(
                modelo.getDealer(),
                modelo.getDealer().getPuntos()
        );

        while (modelo.dealerDebePedir()) {
            vista.mostrarDealerPideCarta();
            modelo.pedirCartaDealer();
            vista.mostrarEstadoDealer(
                    modelo.getDealer(),
                    modelo.getDealer().getPuntos()
            );
        }

        if (modelo.getDealer().getPuntos() <= 21) {
            vista.mostrarDealerSePlanta();
        } else {
            vista.mostrarMensajeExcedido(
                    modelo.getDealer().getNombre()
            );
        }
    }

    public static void main(String[] args) {

        Juego21 modelo = new Juego21();
        VistaJuego21 vista = new VistaJuego21();
        ControladorJuego21 controlador = new ControladorJuego21(modelo, vista);
        controlador.iniciarJuego();
    }
}