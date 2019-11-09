package MainGame;

import java.lang.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController
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

    public void chooseLevel(ActionEvent actionEvent)
    {
        System.out.println("Choose Level");
    }

    public void exitGame(ActionEvent actionEvent)
    {
        System.out.println("Exit Game");
        System.exit(0);
    }
}
