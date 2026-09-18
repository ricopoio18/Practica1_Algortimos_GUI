package Juego21.GUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Aplicacion21 extends Application {

    @Override
    public void start(Stage stage) {

        ControladorGUI controlador = new ControladorGUI(stage);
        controlador.mostrarMenu();
        stage.setTitle("Juego 21");
        stage.setWidth(1200);
        stage.setHeight(750);
        stage.setMinWidth(1000);
        stage.setMinHeight(650);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}