package MainGame;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainMenuController {

    @FXML
    public void PlayGameBtnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent lawnroot= FXMLLoader.load(getClass().getResource("Lawn.fxml"));
        Scene lawn= new Scene(lawnroot);
        window.setScene(lawn);
        window.show();

    }


}
