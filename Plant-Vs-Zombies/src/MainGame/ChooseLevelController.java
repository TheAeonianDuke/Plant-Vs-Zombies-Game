package MainGame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ChooseLevelController {

    @FXML
    private Pane chooseLevel;

    public void goToLevelOne(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("Lawn.fxml"));
        chooseLevel.getChildren().setAll(pane);
    }

    public void goToLevelTwo(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("Lawn2.fxml"));
        chooseLevel.getChildren().setAll(pane);
    }

    public void goToLevelThree(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("Lawn3.fxml"));
        chooseLevel.getChildren().setAll(pane);
    }

    public void goToLevelFour(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("Lawn4.fxml"));
        chooseLevel.getChildren().setAll(pane);
    }

    public void goToLevelFive(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("Lawn5.fxml"));
        chooseLevel.getChildren().setAll(pane);
    }

    public void goToMainMenu(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        chooseLevel.getChildren().setAll(pane);
    }
}
