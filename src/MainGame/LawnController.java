package MainGame;

import com.sun.jndi.toolkit.url.UrlUtil;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LawnController implements Initializable {
    TranslateTransition movelawnmower;
    TranslateTransition movezombie;
    TranslateTransition movesun;
    TranslateTransition movepea;

    @FXML
    private Pane Lawn;

    @FXML
    private Button peashooter;

    @FXML
    private ImageView peashooter_anim;

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

    // Tile Panes Init //
    @FXML
    private AnchorPane tile00, tile01,tile02,tile03,tile04,
                        tile10,tile11,tile12,tile13,tile14,
                        tile20,tile21,tile22,tile23,tile24,
                        tile30,tile31,tile32,tile33,tile34,
                        tile40,tile41,tile42,tile43,tile44,
                        tile50,tile51,tile52,tile53,tile54,
                        tile60,tile61,tile62,tile63,tile64,
                        tile70,tile71,tile72,tile73,tile74,
                        tile80,tile81,tile82,tile83,tile84;

    @FXML
    private Pane TilePanes[]= {tile00, tile01,tile02,tile03,tile04,
                                tile10,tile11,tile12,tile13,tile14,
                                tile20,tile21,tile22,tile23,tile24,
                                tile30,tile31,tile32,tile33,tile34,
                                tile40,tile41,tile42,tile43,tile44,
                                tile50,tile51,tile52,tile53,tile54,
                                tile60,tile61,tile62,tile63,tile64,
                                tile70,tile71,tile72,tile73,tile74,
                                tile80,tile81,tile82,tile83,tile84};

    public LawnController() {
    }

    // Pea Shooting Anim //
    @FXML
    private void shootPea() {
        movepea = new TranslateTransition(Duration.seconds(2), Pea);
        movepea.setToX(Pea.getLayoutX() + 1000);
        movepea.setCycleCount(10);
        movepea.play();
    }

    // Sun Falling Anim //
    @FXML
    private void FallSun() {
        movesun = new TranslateTransition(Duration.seconds(6), FallSun);
        movesun.setToY(FallSun.getLayoutY() + 760);
//        move.setCycleCount(10);
        movesun.play();
    }

    // Move Zombie Anim //
    private void moveZombie() {

        movezombie = new TranslateTransition(Duration.seconds(8), Zombie);
        movezombie.setToX(Zombie.getLayoutX() - 2280);
        movezombie.setCycleCount(1);
        movezombie.play();

        movezombie.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                Zombie.setVisible(false);
                triggerLawnMower();
                System.out.println(Zombie.getLayoutX() + " " + LawnMower0.getLayoutX());
            }
        });
    }

    // LawnMower Anim //
    private void triggerLawnMower() {
        movelawnmower = new TranslateTransition(Duration.seconds(3), LawnMower0);
        movelawnmower.setDelay(Duration.millis(7900));
        movelawnmower.setToX(LawnMower0.getLayoutX() + 1000);
        movelawnmower.setCycleCount(1);
        movelawnmower.play();
    }

    // Ingame-Menu Btn //
    @FXML
    private void MenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        menu_panel.setVisible(true);
        movelawnmower.pause();
        movezombie.pause();
        movesun.pause();
        movepea.pause();

    }

    // Drag Elements //
    private void DragElement(Button elem) {
        elem.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                peashooter_anim.setLayoutX(event.getSceneX() - 10);
                peashooter_anim.setLayoutY(event.getSceneY() - 10);
                Pea.setLayoutX(event.getSceneX() - 10);
                Pea.setLayoutY(event.getSceneY() - 10);
                System.out.println("Mouse Pressed Detected!");
                event.consume();
            }
        });

        elem.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                peashooter_anim.setLayoutX(event.getSceneX() - 10);
                peashooter_anim.setLayoutY(event.getSceneY() - 10);
                Pea.setLayoutX(event.getSceneX() - 10);
                Pea.setLayoutY(event.getSceneY() - 10);
                System.out.println("Dragging Element Detected!");

            }
        });
        elem.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                peashooter_anim.startFullDrag();
                peashooter_anim.setMouseTransparent(true);
                Pea.startFullDrag();
                Pea.setMouseTransparent(true);
            }
        });
    }

    // Drag Over Tile Detection //
    private void DragOver(Pane tile) {
        tile.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                if (event.getGestureSource() != tile) {
                    System.out.println("over");
                    tile.setStyle("-fx-background-color: #3cb371; -fx-border-color: #ffff");
                    tile.setOpacity(0.5);
//                    tile.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 20, 0, 0, 0)");
                }
            }
        });

        tile.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                if (event.getGestureSource() != tile) {
                    System.out.println("Released");
                    peashooter_anim.setLayoutX(tile.getLayoutX() + 25);
                    peashooter_anim.setLayoutY(tile.getLayoutY() + 15);
                    Pea.setLayoutX(tile.getLayoutX() + 25);
                    Pea.setLayoutY(tile.getLayoutY() + 15);
                }
            }
        });

        tile.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                tile.setStyle("-fx-background-color: transparent");
            }
        });
    }

    // Exit To Menu //
    public void ExitToMenu(ActionEvent actionEvent) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Lawn.getChildren().setAll(pane);
    }

    // Return To Game //
    public void ReturnGame(ActionEvent actionEvent) {
        menu_panel.setVisible(false);
        movelawnmower.play();
        movezombie.play();
        movesun.play();
        movepea.play();
    }


    // Init //

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moveZombie();
        shootPea();
        FallSun();
        triggerLawnMower();
        for (Pane i: TilePanes) {
            DragOver(i);
            DragElement(peashooter);
        }
    }
}
