package it.comprog.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        URL fxmlLocation = getClass().getResource("MenuWindow.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        try {
            stage.setScene(new Scene(
                    loader.load())
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}