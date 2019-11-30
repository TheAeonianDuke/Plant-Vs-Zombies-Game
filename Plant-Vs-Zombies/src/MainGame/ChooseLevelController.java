package MainGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ChooseLevelController {

    @FXML
    private Pane chooseLevel;

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
