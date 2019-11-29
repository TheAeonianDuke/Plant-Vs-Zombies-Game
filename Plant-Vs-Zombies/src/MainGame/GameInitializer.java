package MainGame;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

public class GameInitializer implements Serializable
{
    public static final long serialVersionUID = 3L;
    private String userName;
    private MainMenuController mainMenuController;
    private ChooseLevelController chooseLevelController;
    private ArrayList<Level> allLevels;
//
//    public GameInitializer()
//    {
//        userName = null;
//        mainMenuController  = new MainMenuController(this);
//        chooseLevelController = new ChooseLevelController(this);
//        allLevels = new ArrayList<>();
//    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

}
