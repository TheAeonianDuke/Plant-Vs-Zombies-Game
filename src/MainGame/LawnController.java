package MainGame;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

    @FXML
    private Pane Lawn;

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
        TranslateTransition move = new TranslateTransition(Duration.seconds(2),Pea);
        move.setToX(Pea.getLayoutX() + 1000);
        move.setCycleCount(10);
        move.play();
    }

    @FXML
    private void FallSun()
    {
        TranslateTransition move = new TranslateTransition(Duration.seconds(6),FallSun);
        move.setToY(FallSun.getLayoutY() + 760);
//        move.setCycleCount(10);
        move.play();
    }

    private void moveZombie()
    {

        TranslateTransition move = new TranslateTransition(Duration.seconds(8),Zombie);
        move.setToX(Zombie.getLayoutX() - 2280);
//        System.out.println(Zombie.getLayoutX()+" "+ LawnMower0.getLayoutX());
        move.setCycleCount(1);
        move.play();
        move.setOnFinished(new EventHandler<ActionEvent>(){

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
        TranslateTransition move = new TranslateTransition(Duration.seconds(3),LawnMower0);
        move.setDelay(Duration.millis(7900));
        move.setToX(LawnMower0.getLayoutX() + 1000);
        move.setCycleCount(1);
        move.play();
    }

    @FXML
    private void MenuBtn(javafx.event.ActionEvent actionEvent) throws IOException
    {
        menu_panel.setVisible(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        moveZombie();
        shootPea();
        FallSun();
        triggerLawnMower();
    }


}
