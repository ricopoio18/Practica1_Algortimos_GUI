package Juego21.GUI;

import DeckOfCards.CartaInglesa;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CartaView extends ImageView {

    private double ANCHO = 90;
    private double ALTO = 130;

    public CartaView(CartaInglesa carta) {
        setFitWidth(ANCHO);
        setFitHeight(ALTO);
        setPreserveRatio(true);
        actualizar(carta);
    }

    public void actualizar(CartaInglesa carta) {
        String ruta;

        System.out.println("Valor: " + carta.getValor());
        System.out.println("Palo: " + carta.getPalo());
        System.out.println("FaceUp: " + carta.isFaceup());

        if (!carta.isFaceup()) {
            ruta = "/Cartas/Carta_Volteada.png";
        } else {
            ruta = obtenerRutaCarta(carta);
        }

        System.out.println("Buscando imagen: " + ruta);
        System.out.println("Encontrada: " + getClass().getResource(ruta));

        Image imagen = new Image(getClass().getResourceAsStream(ruta));
        setImage(imagen);
    }

    private String obtenerRutaCarta(CartaInglesa carta) {
        int valor = carta.getValor();
        String palo = carta.getPalo().toString().toUpperCase();
        return "/Cartas/" + valor + "_" + palo + ".png";
    }
}