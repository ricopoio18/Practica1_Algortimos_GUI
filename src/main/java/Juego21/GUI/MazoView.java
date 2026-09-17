package Juego21.GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MazoView extends VBox {

    public MazoView() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        Label titulo = new Label("MAZO");
        titulo.setStyle("""
                -fx-font-size: 20px;
                -fx-font-weight: bold;
                -fx-text-fill: white;
                """);

        Image imagen = new Image(getClass().getResourceAsStream("/Cartas/Carta_Volteada.png"));
        ImageView mazo = new ImageView(imagen);
        mazo.setFitWidth(100);
        mazo.setFitHeight(145);
        mazo.setPreserveRatio(true);
        getChildren().addAll(titulo, mazo);
    }
}