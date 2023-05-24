package it.comprog.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuWindowControl implements Initializable {

    @FXML
    private ComboBox<Difficulty.Difficulties> difficulty;
    private static String chosenDifficulty;

    @FXML
    void gameStarted(ActionEvent event) {
        Difficulty.Difficulties d = difficulty.getSelectionModel().getSelectedItem();
        if (d != null) {
            chosenDifficulty = d.toString();
        } else {
            chosenDifficulty = "EASY";
        }
        System.out.println("Game started! Difficulty: " + chosenDifficulty);
        try {
            Parent gamePageParent = FXMLLoader
                    .load(Objects.requireNonNull(getClass().getResource("GameWindow.fxml")));
            Scene gamePageScene = new Scene(gamePageParent);
            Stage gameStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gameStage.setScene(gamePageScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getChosenDifficulty() {
        return chosenDifficulty;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        difficulty.getItems().setAll(Difficulty.Difficulties.values());
    }
}