package it.comprog.view;

import it.comprog.view.Difficulty.Difficulties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MenuWindowControl implements Initializable {

    @FXML
    private ComboBox<String> difficulty;
    @FXML
    private ComboBox<String> language;
    private static String chosenDifficulty;
    private ResourceBundle chosenBundle;

    private static final Logger LOGGER = Logger.getLogger(MenuWindowControl.class.getName());

    FileHandler logFile;

    {
        try {
            logFile = new FileHandler("menu_logs.txt");
            LOGGER.addHandler(logFile);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Unable to initialize log file");
        }
    }

    @FXML
    private Label author = new Label();

    @FXML
    void gameStarted(ActionEvent event) {
        String d = difficulty.getSelectionModel().getSelectedItem();
        if (d != null) {
            chosenDifficulty = d;
        } else {
            chosenDifficulty = "Easy";
        }
        LOGGER.log(Level.INFO, "Game started! Difficulty: " + chosenDifficulty);
        SceneManager.showStage("GameWindow.fxml", chosenBundle);
    }

    public static String getChosenDifficulty() {
        return chosenDifficulty;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chosenBundle = resourceBundle;
        difficulty.getItems().addAll(chosenBundle.getString(Difficulties.EASY.getLevel()),
                                     chosenBundle.getString(Difficulties.MEDIUM.getLevel()),
                                     chosenBundle.getString(Difficulties.HARD.getLevel()));

        language.getItems().addAll(chosenBundle.getString("English"), chosenBundle.getString("Polish"));

        ListResourceBundle authorBundle = (ListResourceBundle) ResourceBundle.getBundle("it.comprog.view.Author");
        author.setText(authorBundle.toString());
    }

    @FXML void getChosenLanguage() {
        Locale locale = new Locale("en");
        String choice = language.getValue();

        switch (choice) {
            case "English", "Angielski" -> locale = new Locale("en");
            case "Polish", "Polski" -> locale = new Locale("pl");
            default -> { }
        }
        Locale.setDefault(locale);
        chosenBundle = ResourceBundle.getBundle("it.comprog.view.Language");

        ListResourceBundle authorBundle = (ListResourceBundle) ResourceBundle
                .getBundle("it.comprog.view.Author", locale);

        SceneManager.showStage("MenuWindow.fxml", chosenBundle);
        author.setText(authorBundle.toString());
    }
}