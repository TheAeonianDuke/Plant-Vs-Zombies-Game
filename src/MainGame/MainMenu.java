package MainGame;

import com.sun.javafx.application.LauncherImpl;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent menuroot = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Parent splashroot = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
        Scene mainmenu= new Scene(menuroot);
        Scene splash=new Scene(splashroot);

        primaryStage.setScene(splash);
        primaryStage.show();
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
        {
            FadeTransition fade = new FadeTransition(Duration.seconds(1),splashroot);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(event1 ->
            {
                primaryStage.setScene(mainmenu);
                primaryStage.show();
            });
            fade.play();
        });
        pause.play();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
