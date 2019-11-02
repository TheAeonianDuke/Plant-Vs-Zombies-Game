package MainGame;

import com.sun.javafx.application.LauncherImpl;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MainMenu extends Application {

    private Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window=primaryStage;
        Parent menuroot = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Parent splashroot = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
        Scene mainmenu= new Scene(menuroot);
        Scene splash=new Scene(splashroot);

        window.setScene(splash);
        window.show();
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
        {
            FadeTransition fade = new FadeTransition(Duration.seconds(1),splashroot);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(event1 ->
            {
                window.setScene(mainmenu);
                window.show();
            });
            fade.play();
        });
        pause.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void PlayGameBtnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent lawnroot= FXMLLoader.load(getClass().getResource("Lawn.fxml"));
        Scene lawn= new Scene(lawnroot);
        window.setScene(lawn);
        window.show();

    }
}
