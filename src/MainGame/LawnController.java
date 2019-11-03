package MainGame;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

public class LawnController implements Initializable
{
    TranslateTransition movelawnmower;
    TranslateTransition movezombie;
    TranslateTransition movesun;
    TranslateTransition movepea;

    @FXML
    private Pane Lawn;

    @FXML
    private Button exit_to_menu;

    @FXML
    private Button return_game;

    @FXML
    private Pane menu_panel;

    @FXML
    private ImageView Zombie;

    @FXML
    private ImageView FallSun;

    @FXML
    private ImageView Pea;

    @FXML
    private ImageView LawnMower0;

    @FXML
    private void shootPea()
    {
        movepea = new TranslateTransition(Duration.seconds(2),Pea);
        movepea.setToX(Pea.getLayoutX() + 1000);
        movepea.setCycleCount(10);
        movepea.play();
    }

    @FXML
    private void FallSun()
    {
        movesun = new TranslateTransition(Duration.seconds(6),FallSun);
        movesun.setToY(FallSun.getLayoutY() + 760);
//        move.setCycleCount(10);
        movesun.play();
    }

    private void moveZombie()
    {

        movezombie = new TranslateTransition(Duration.seconds(8),Zombie);
        movezombie.setToX(Zombie.getLayoutX() - 2280);
//        System.out.println(Zombie.getLayoutX()+" "+ LawnMower0.getLayoutX());
        movezombie.setCycleCount(1);
        movezombie.play();
        movezombie.setOnFinished(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent arg0) {
                Zombie.setVisible(false);
                triggerLawnMower();
            }
        });
//

    }

    private void triggerLawnMower()
    {
        movelawnmower = new TranslateTransition(Duration.seconds(3),LawnMower0);
        movelawnmower.setDelay(Duration.millis(7900));
        movelawnmower.setToX(LawnMower0.getLayoutX() + 1000);
        movelawnmower.setCycleCount(1);
        movelawnmower.play();
    }

    @FXML
    private void MenuBtn(javafx.event.ActionEvent actionEvent) throws IOException
    {
        menu_panel.setVisible(true);
        movelawnmower.pause();
        movezombie.pause();
        movesun.pause();
        movepea.pause();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        moveZombie();
        shootPea();
        FallSun();
        triggerLawnMower();
    }


    public void ExitToMenu(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Lawn.getChildren().setAll(pane);
    }

    public void ReturnGame(ActionEvent actionEvent)
    {
        menu_panel.setVisible(false);
        movelawnmower.play();
        movezombie.play();
        movesun.play();
        movepea.play();
    }
}
