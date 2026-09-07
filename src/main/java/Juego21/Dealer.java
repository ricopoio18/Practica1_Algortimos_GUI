package Juego21;
import DeckOfCards.*;

public class Dealer extends Jugador{
    private Mazo mazo;
    public Dealer() {
        super("Dealer");
        mazo = new Mazo();
    }


    public CartaInglesa repartirCarta(){
        return mazo.obtenerUnaCarta();
    }







}
