package MainGame;

import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;

public class LawnController implements Initializable {
    TranslateTransition movelawnmower;
    TranslateTransition movezombie;
    TranslateTransition movesun;
    TranslateTransition movepea;
    TranslateTransition collectsun;
    Random get_random = new Random();

    @FXML
    private Pane Lawn;

    @FXML
    private Button peashooter_btn, sunflower_btn, walnut_btn, potato_btn;

    @FXML
    private ImageView peashooter_anim, sunflower,walnut, potato;

    @FXML
    private Pane menu_panel;

    @FXML
    private ImageView Zombie1, Zombie2, Zombie3, Zombie4, Zombie5;

    @FXML
    private ImageView FallSun;

    @FXML
    private ImageView LawnMower0, LawnMower1,LawnMower2,LawnMower3,LawnMower4;

    @FXML
    private Text SunCounter;

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
    private AnchorPane sidemenu;

    private AnchorPane TilePanes[];

    // Zombies Img Arraylist //
    ArrayList<Double> Zombieimg = new ArrayList<>();

    // Peashooter ArrayList //
    ArrayList<Plants> Plants_List=new ArrayList<>();

    // Zombies ArrayList //
    ArrayList<Default_Zombie> Zombies_List=new ArrayList<>();

    // LawnMower ArrayList //
    ArrayList<ImageView> Lawnmower_List=new ArrayList<>();

    public LawnController() {}

    // Pea Shooting Anim //
    @FXML
    private void shootPea(ImageView Pea, ImageView Peashooter) {
        Pea.setVisible(true);
        movepea = new TranslateTransition(Duration.seconds(2), Pea);
        movepea.setFromX(Peashooter.getX());
        movepea.setToX(1280);
        movepea.setCycleCount(1000);
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

    // Add Lawnmower in List //
    private void createLawnmower()
    {
        Lawnmower_List.add(LawnMower0);
        Lawnmower_List.add(LawnMower1);
        Lawnmower_List.add(LawnMower2);
        Lawnmower_List.add(LawnMower3);
        Lawnmower_List.add(LawnMower4);
    }

    // Move Zombie Anim //
    private TranslateTransition moveZombie(ImageView zombie_img) {
        movezombie = new TranslateTransition(Duration.seconds(15), zombie_img);
        movezombie.setToX(-1280);
        movezombie.setCycleCount(1);
        movezombie.play();
        return movezombie;
//        movezombie.setOnFinished(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent arg0) {
//
//            }
//        });
    }

    // Create Default Zombies //
    private void createZombie()
    {

        Zombieimg.add(Zombie1.getLayoutY());
        Zombieimg.add(Zombie2.getLayoutY());
        Zombieimg.add(Zombie3.getLayoutY());
        Zombieimg.add(Zombie4.getLayoutY());
        Zombieimg.add(Zombie5.getLayoutY());

        Default_Zombie new_zombie=new Default_Zombie(Zombieimg.get(get_random.nextInt(Zombieimg.size())));
        Zombies_List.add(new_zombie);
        Lawn.getChildren().add(new_zombie.getZombie_img());

        TranslateTransition zombieanim=moveZombie(new_zombie.getZombie_img());
        createLawnmower();
        for(ImageView img: Lawnmower_List)
        {
            for(Default_Zombie zombie : Zombies_List)
            {
                collision(zombie.getZombie_img(), img);
            }
        }

    }

    private void createPlants()
    {
        double peashooter_initX=peashooter_anim.getLayoutX();
        double peashooter_initY=peashooter_anim.getLayoutY();

        peashooter_btn.setOnMousePressed(event -> {
//            System.out.println("lol pressed");
            for (int i =0 ; i<45; i++) {
                DragOver(peashooter_btn,TilePanes[i], peashooter_initX,peashooter_initY, sidemenu);
            }
        });

        sunflower_btn.setOnMousePressed(event -> {
            for (int i =0 ; i<45; i++) {
                DragOver(sunflower_btn,TilePanes[i], peashooter_initX,peashooter_initY, sidemenu);
            }
        });

        walnut_btn.setOnMousePressed(event -> {
            for (int i =0 ; i<45; i++) {
                DragOver(walnut_btn,TilePanes[i], peashooter_initX,peashooter_initY, sidemenu);
            }
        });

        potato_btn.setOnMousePressed(event -> {
            for (int i =0 ; i<45; i++) {
                DragOver(potato_btn,TilePanes[i], peashooter_initX,peashooter_initY, sidemenu);
            }
        });

        System.out.println(Plants_List.size());
    }

    private void collision(ImageView obj1, ImageView obj2)
    {
        ObservableBooleanValue collision= Bindings.createBooleanBinding(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return obj1.getBoundsInParent().intersects(obj2.getBoundsInParent());
            }
        }, obj1.boundsInParentProperty(),obj2.boundsInParentProperty());

        collision.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    obj1.setVisible(false);
                    triggerLawnMower(obj2);
                    System.out.println(obj2.getLayoutX() + " " + obj1.getLayoutX());
                }
                else{
                    System.out.println("not colliding");
                }
            }
        });
    }

    private void collision(ImageView obj1, ImageView obj2, double init_x, double init_y)
    {
        ObservableBooleanValue collision= Bindings.createBooleanBinding(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return obj1.getBoundsInParent().intersects(obj2.getBoundsInParent());
            }
        }, obj1.boundsInParentProperty(),obj2.boundsInParentProperty());

        collision.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    System.out.println("collision pea");
                    movepea.stop();
                    obj2.setVisible(false);
                    movepea.playFromStart();

                }
                else{
                    obj2.setVisible(true);
                    System.out.println("not colliding");
                }
            }
        });
    }

    // LawnMower Anim //
    private void triggerLawnMower(ImageView lawnmower) {
        movelawnmower = new TranslateTransition(Duration.seconds(3), lawnmower);
//        movelawnmower.setDelay(Duration.millis(7900));
        movelawnmower.setToX(LawnMower0.getLayoutX() + 1000);
        movelawnmower.setCycleCount(1);
        movelawnmower.play();
    }

    // In-game Menu Btn //
    @FXML
    private void MenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        menu_panel.setVisible(true);
        movelawnmower.pause();
        movezombie.pause();
        movesun.pause();
        movepea.pause();

    }

    // Drag Over Tile Detection //
    private void DragOver( Button elem, AnchorPane tile, double peashooter_initX, double peashooter_initY, AnchorPane exception_tiles) {

        Plants plant=null;
        if(elem.getId().equals("peashooter_btn"))
        {
//            System.out.println("lmao");
            plant=new PeaShooter(100,100,20,false,null,5);
            Lawn.getChildren().add(plant.get_main_img());
            Lawn.getChildren().add(plant.get_other_img());
        }

        else if(elem.getId().equals("sunflower_btn"))
        {
            plant=new SunFlower(100,100,20,false,null,5);
            Lawn.getChildren().add(plant.get_main_img());
            Lawn.getChildren().add(plant.get_other_img());
        }

        else if(elem.getId().equals("walnut_btn"))
        {
            plant=new Wallnut(100,100,20,false,null,5);
            Lawn.getChildren().add(plant.get_main_img());
//            Lawn.getChildren().add(plant.get_other_img());
        }

        else if(elem.getId().equals("potato_btn"))
        {
            plant=new PotatoMine(100,100,20,false,null,5);
            Lawn.getChildren().add(plant.get_main_img());
//            Lawn.getChildren().add(plant.get_other_img());
        }
        ImageView peashooter_img=plant.get_main_img();
        ImageView pea_img=plant.get_other_img();

        peashooter_img.setVisible(false);
//        Lawn.getChildren().add(pea_img);
        if(pea_img!=null) {
            pea_img.setVisible(false);
        }
        elem.setOnMousePressed(event -> {
            peashooter_img.setVisible(true);
            peashooter_img.setLayoutX(event.getSceneX() - 10);
            peashooter_img.setLayoutY(event.getSceneY() - 10);
            if(pea_img!=null) {
                pea_img.setLayoutX(event.getSceneX() - 10);
                pea_img.setLayoutY(event.getSceneY() - 10);
            }
//                System.out.println("Mouse Pressed Detected!");
            event.consume();
        });

        elem.setOnMouseDragged(event -> {
            peashooter_img.setVisible(true);
            peashooter_img.setLayoutX(event.getSceneX() - 10);
            peashooter_img.setLayoutY(event.getSceneY() - 10);
            if(pea_img!=null) {
                pea_img.setLayoutX(event.getSceneX() - 10);
                pea_img.setLayoutY(event.getSceneY() - 10);
            }
//                System.out.println("Dragging Element Detected!");
        });

        elem.setOnDragDetected(event -> {
//                new_peashooter=new ImageView("resources/pea_shooter.gif");
            peashooter_img.setVisible(true);
            peashooter_img.startFullDrag();
            peashooter_img.setMouseTransparent(true);
//                peashooter_anim.startFullDrag();
//                peashooter_anim.setMouseTransparent(true);
            if(pea_img!=null) {
                pea_img.startFullDrag();
                pea_img.setMouseTransparent(true);
            }
        });

        elem.setOnMouseDragReleased(event -> peashooter_img.setVisible(false));

        elem.setOnDragDone(event -> peashooter_img.setVisible(false));

        elem.setOnDragDropped(event -> peashooter_img.setVisible(false));

        elem.setOnMouseReleased(event -> peashooter_img.setVisible(false));

        elem.setOnMouseDragExited(event -> peashooter_img.setVisible(false));

        tile.setOnMouseDragOver(event -> {
            if (event.getGestureSource() != tile && event.getGestureSource()!=exception_tiles  ) {
//                    System.out.println("over");
                tile.setStyle("-fx-background-color: #3cb371; -fx-border-color: #ffff");
                tile.setOpacity(0.5);
//                    tile.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 20, 0, 0, 0)");
            }
        });

        exception_tiles.setOnMouseDragReleased((EventHandler<MouseEvent>) event -> {
            peashooter_img.setVisible(true);
            peashooter_img.setLayoutX(peashooter_initX);
            peashooter_img.setLayoutY(peashooter_initY);
        });

        Plants finalPlant = plant;
        tile.setOnMouseDragReleased(event -> {
            System.out.println(event.getSceneX());
            if (event.getGestureSource() != tile && event.getGestureSource()!=exception_tiles && event.getSceneX()>0) {
                peashooter_img.setVisible(true);
                finalPlant.setTilePlaced(tile);
                Plants_List.add(finalPlant);
                System.out.println(Plants_List.size());
                peashooter_img.setLayoutX(tile.getLayoutX() + 25);
                peashooter_img.setLayoutY(tile.getLayoutY() + 15);

                if(pea_img!=null)
                    {
                    pea_img.setLayoutX(tile.getLayoutX() + 30);
                    pea_img.setLayoutY(tile.getLayoutY() + 20);
                    double init_x=pea_img.getLayoutX();
                    double init_y=pea_img.getLayoutY();
                    shootPea(pea_img,peashooter_img);
                        for(Plants plant_img: Plants_List)
                        {
                            if(pea_img!=null || plant_img.get_other_img()!=null)
                            {
                                for(Default_Zombie zombie : Zombies_List)
                                {
                                    System.out.println("entereed loop");

                                    collision(zombie.getZombie_img(), pea_img,pea_img.getLayoutX(),pea_img.getLayoutY());
                                }
                            }
                        }
                    }

                tile.setOpacity(1);
                tile.setStyle("-fx-background-color: transparent");
            }
        });

        tile.setOnMouseDragExited(event -> {
            tile.setOpacity(1);
            tile.setStyle("-fx-background-color: transparent");
        });

        Lawn.setOnMouseDragReleased(event -> {
//                System.out.println("X"+event.getSceneX()+"Y"+event.getSceneY());
            if( event.getSceneX()<306 || event.getSceneX()>1180 || event.getSceneY()>688 || event.getSceneY()<90 && event.getGestureSource()!=exception_tiles)
            {
                peashooter_img.setVisible(true);
                peashooter_img.setLayoutX(peashooter_initX);
                peashooter_img.setLayoutY(peashooter_initY);
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

    public void CollectSun(MouseEvent actionEvent)
    {
        collectsun = new TranslateTransition(Duration.seconds(4),FallSun);
        collectsun.setToX(-304);
        collectsun.setToY(50);
        collectsun.play();
        collectsun.setOnFinished(e ->
        {
            FallSun.setVisible(false);
            SunCounter.setText(String.valueOf(Integer.parseInt(SunCounter.getText()) + 50));
        });
    }

    // Init //

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createZombie();
        createZombie();
        createPlants();
        FallSun();
//        triggerLawnMower();


        TilePanes = new AnchorPane[]{tile00, tile01, tile02, tile03, tile04,
                                    tile10, tile11, tile12, tile13, tile14,
                                    tile20, tile21, tile22, tile23, tile24,
                                    tile30, tile31, tile32, tile33, tile34,
                                    tile40, tile41, tile42, tile43, tile44,
                                    tile50, tile51, tile52, tile53, tile54,
                                    tile60, tile61, tile62, tile63, tile64,
                                    tile70, tile71, tile72, tile73, tile74,
                                    tile80, tile81, tile82, tile83, tile84};



    }
}
