package MainGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.io.Serializable;

public class ChooseLevelController implements Serializable
{
    private GameInitializer gameInitializer;

    @FXML
    private Pane chooseLevel;

    @FXML
    private Button Level1;

    @FXML
    private Button Level2;

    @FXML
    private Button Level3;

    @FXML
    private Button Level4;

    @FXML
    private Button Level5;

    public void setGameInitializer(GameInitializer gameInitializer) {
        this.gameInitializer = gameInitializer;
    }

    public void goToLevelOne(ActionEvent actionEvent)
    {
        System.out.println("Level 1");
    }

    public void goToLevelTwo(ActionEvent actionEvent)
    {
        System.out.println("Level 2");
    }

    public void goToLevelThree(ActionEvent actionEvent)
    {
        System.out.println("Level 3");
    }

    public void goToLevelFour(ActionEvent actionEvent)
    {
        System.out.println("Level 4");
    }

    public void goToLevelFive(ActionEvent actionEvent)
    {
        System.out.println("Level 4");
    }

    public void goToMainMenu(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        chooseLevel.getChildren().setAll(pane);
    }
}
