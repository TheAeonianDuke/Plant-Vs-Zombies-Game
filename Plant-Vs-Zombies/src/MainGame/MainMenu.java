package MainGame;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent menuRoot = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Parent splashRoot = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
        Scene mainMenu= new Scene(menuRoot);
        Scene splash=new Scene(splashRoot);

        primaryStage.setScene(splash);
        primaryStage.show();
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
        {
            FadeTransition fade = new FadeTransition(Duration.seconds(1),splashRoot);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setOnFinished(event1 ->
            {
                primaryStage.setScene(mainMenu);
                primaryStage.show();
            });
            fade.play();
        });
        pause.play();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
