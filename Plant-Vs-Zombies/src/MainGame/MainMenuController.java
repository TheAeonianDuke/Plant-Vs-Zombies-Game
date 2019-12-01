package MainGame;

import java.io.File;
import java.lang.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable
{
    @FXML
    private Pane mainMenu;

    @FXML
    private Button playButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button loadButton;

    @FXML
    private Button chooseButton;

    @FXML
    private TextField username;

    MediaPlayer mediaPlayer;

    public void playGame(javafx.event.ActionEvent actionEvent) throws IOException
    {
        if(! username.getText().trim().isEmpty())
        {
            Pane pane = FXMLLoader.load(getClass().getResource("Lawn.fxml"));
            mainMenu.getChildren().setAll(pane);
        }
    }

    public void loadGame(ActionEvent actionEvent)
    {
        System.out.println("Load Game");
    }

    public void chooseLevel(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("ChooseLevel.fxml"));
        mainMenu.getChildren().setAll(pane);
    }

    public void exitGame(ActionEvent actionEvent)
    {
        System.out.println("Exit Game");
        System.exit(0);
    }

    public void play_music1()
    {
        Media media = new Media(Paths.get("src/main/resources/pvz.mp3").toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setAutoPlay(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        play_music1();
    }
}
