package it.comprog.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;


public class SceneManager {
    private static Stage stage;

    public static void setStage(Stage stage) {
        SceneManager.stage = stage;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void showStage(String filePath, ResourceBundle resourceBundle) {
        try {
            stage.setScene(new Scene(
                    new FXMLLoader(SceneManager.class.getResource(filePath), resourceBundle).load()
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.sizeToScene();
        stage.show();
    }

}
