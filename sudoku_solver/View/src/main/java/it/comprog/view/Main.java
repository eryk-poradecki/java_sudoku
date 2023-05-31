package it.comprog.view;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("it.comprog.view.Language");

    @Override
    public void start(Stage stage) {
        Locale.setDefault(new Locale("en"));

        SceneManager.setStage(stage);
        SceneManager.showStage("MenuWindow.fxml", resourceBundle);
    }

    public static void main(String[] args) {
        launch();
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

}