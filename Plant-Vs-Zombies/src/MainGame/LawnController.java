package MainGame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
    TranslateTransition movesun;
    Random get_random = new Random();
    private int[] buttonSeed = new int[4];

    @FXML
    private Pane Lawn;

    @FXML
    private Button peashooter_btn, sunflower_btn, walnut_btn, potato_btn;

    @FXML
    private ImageView peashooter_anim, sunflower,walnut, potato,suncounter;

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
    volatile ArrayList<Plants> Plants_List=new ArrayList<>();

    // Zombies ArrayList //
//    volatile ArrayList<Default_Zombie> Zombies_List=new ArrayList<>();

    // Zombies ArrayList //
    volatile ArrayList<Default_Zombie> Zombies_Wave_1=new ArrayList<>();
    volatile ArrayList<Default_Zombie> Zombies_Wave_2=new ArrayList<>();
    volatile ArrayList<Default_Zombie> Zombies_Wave_3=new ArrayList<>();


    // LawnMower ArrayList //
    volatile ArrayList<ImageView> Lawnmower_List=new ArrayList<>();

    // Collisions ArrayList //
    volatile ArrayList<ObservableBooleanValue> collisions_list=new ArrayList<>();

    public LawnController() {}

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

    // Create Default Zombies //
//    private void createZombie()
//    {
//        Zombieimg.add(Zombie1.getLayoutY());
//        Zombieimg.add(Zombie2.getLayoutY());
//        Zombieimg.add(Zombie3.getLayoutY());
//        Zombieimg.add(Zombie4.getLayoutY());
//        Zombieimg.add(Zombie5.getLayoutY());
//
//        Default_Zombie new_zombie=new Default_Zombie(Zombieimg.get(get_random.nextInt(Zombieimg.size())));
//        new_zombie.moveZombie();
//        Zombies_List.add(new_zombie);
//        Lawn.getChildren().add(new_zombie.getZombie_img());
//
//
//        for(ImageView img: Lawnmower_List)
//        {
//            for(Default_Zombie zombie : Zombies_List)
//            {
//                collisionLawnMowerZombie(img,zombie);
//            }
//        }
//    }

    public void createWaves(int wave_1 , int wave_2 , int wave_3)
    {
        Zombieimg.add(Zombie1.getLayoutY());
        Zombieimg.add(Zombie2.getLayoutY());
        Zombieimg.add(Zombie3.getLayoutY());
        Zombieimg.add(Zombie4.getLayoutY());
        Zombieimg.add(Zombie5.getLayoutY());

        for (int i = 0; i < wave_1 ; i++) {
            Default_Zombie zombie = new Default_Zombie(Zombieimg.get(get_random.nextInt(Zombieimg.size())));
            Zombies_Wave_1.add(zombie);
            Lawn.getChildren().add(zombie.getZombie_img());
        }

        for (int i = 0; i < wave_2 ; i++) {
            Default_Zombie zombie = new Default_Zombie(Zombieimg.get(get_random.nextInt(Zombieimg.size())));
            Zombies_Wave_2.add(zombie);
            Lawn.getChildren().add(zombie.getZombie_img());
        }

        for (int i = 0; i < wave_3 ; i++) {
            Default_Zombie zombie = new Default_Zombie(Zombieimg.get(get_random.nextInt(Zombieimg.size())));
            Zombies_Wave_3.add(zombie);
            Lawn.getChildren().add(zombie.getZombie_img());
        }
    }

    public void releaseWave_1() {
        if (Zombies_Wave_1.size() != 0) {
            for (ImageView img : Lawnmower_List) {
                for (Default_Zombie zombie : Zombies_Wave_1) {
                    collisionLawnMowerZombie(img, zombie);
                }
            }
            for (Default_Zombie zombie : Zombies_Wave_1) {
                zombie.moveZombie(get_random.nextInt(25)+ 15 , 1280 + get_random.nextInt(20));
            }
        }
    }
    public void releaseWave_2() {

        if (Zombies_Wave_2.size() != 0) {
            for (ImageView img : Lawnmower_List) {
                for (Default_Zombie zombie : Zombies_Wave_2) {
                    collisionLawnMowerZombie(img, zombie);
                }
            }
            for (Default_Zombie zombie : Zombies_Wave_2) {
                zombie.moveZombie(get_random.nextInt(25)+ 15 , 1280 + get_random.nextInt(20));
            }
        }
    }
    public void releaseWave_3() {
        if (Zombies_Wave_3.size() != 0) {
            for (ImageView img : Lawnmower_List) {
                for (Default_Zombie zombie : Zombies_Wave_3) {
                    collisionLawnMowerZombie(img, zombie);
                }
            }
            for (Default_Zombie zombie : Zombies_Wave_3) {
                zombie.moveZombie(get_random.nextInt(25)+ 15 , 1280 + get_random.nextInt(20));
            }
        }
    }


    // Attach Mechanism //
    private synchronized void attack(Plants plants , Default_Zombie zombie)
    {
        if(!zombie.isDead()) {
            zombie.setHealth(zombie.getHealth() - plants.getAttack_power());
            System.out.println(zombie.getHealth());
            if(zombie.getHealth() <= 0){
                System.out.println("Zombie Dead");
                zombie.setHealth(0);
                zombie.setDead(true);
//                movezombie.stop();
//                zombie.getZombie_img().setVisible(false);
                Lawn.getChildren().remove(zombie.getZombie_img());
                zombie.getMovezombie().stop();
                zombie.getZombie_img().setY(-1000);
                System.out.println(Zombies_Wave_1.size()+" "+Zombies_Wave_2.size()+" "+Zombies_Wave_3.size());
                if(Zombies_Wave_1.contains(zombie))
                {
                    Zombies_Wave_1.remove(zombie);
                    if(Zombies_Wave_1.size()==0)
                    {
                        releaseWave_2();
                    }
                }
                else if(Zombies_Wave_2.contains(zombie))
                {
                    Zombies_Wave_2.remove(zombie);
                    if(Zombies_Wave_2.size()==0)
                    {
                        releaseWave_3();
                    }
                }
                else if(Zombies_Wave_3.contains(zombie))
                {
                    Zombies_Wave_3.remove(zombie);
                    if(Zombies_Wave_3.size()==0)
                    {
                        return; // Throws LevelClearedException
                    }
                }


//                Iterator<Default_Zombie> i = Zombies_List.iterator();
//                while (i.hasNext()){
//                    Default_Zombie z=i.next();
//                    if(z.getId()==zombie.getId())
//                    {
//                        zombie.getMovezombie().stop();
//                        Zombies_List.remove(z);
//                    }
//                }

            }
        }
    }

    private void PvZFightTimer(Plants plant, Default_Zombie zombie)
    {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> PvZFight(plant,zombie)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    private void PvZFight(Plants plants, Default_Zombie zombie)
    {
        if(!plants.isDead()) {
            plants.setHealth(plants.getHealth() - zombie.getAttack());
            if(plants.getHealth() <= 0){
                plants.setHealth(0);
                plants.setDead(true);
                plants.getTilePlaced().getChildren().removeAll();
                zombie.getMovezombie().play();
            }
            else{
                zombie.setHealth(0);
                zombie.setDead(true);
                zombie.getMovezombie().stop();
                zombie.getZombie_img().setY(-1000);
            }
        }
    }

    private void createPlants()
    {
        double peashooter_initX=peashooter_anim.getLayoutX();
        double peashooter_initY=peashooter_anim.getLayoutY();

        peashooter_btn.setOnMousePressed(event -> {
            if(Integer.parseInt(SunCounter.getText()) > 100) {
                for (int i = 0; i < 45; i++) {
                    DragOver(peashooter_btn, TilePanes[i], peashooter_initX, peashooter_initY, sidemenu);
                }
            }
        });

        sunflower_btn.setOnMousePressed(event -> {
            if(Integer.parseInt(SunCounter.getText()) > 50) {
                for (int i = 0; i < 45; i++) {
                    DragOver(sunflower_btn, TilePanes[i], peashooter_initX, peashooter_initY, sidemenu);
                }
            }
        });

        walnut_btn.setOnMousePressed(event -> {
            if(Integer.parseInt(SunCounter.getText()) > 50) {
                for (int i = 0; i < 45; i++) {
                    DragOver(walnut_btn, TilePanes[i], peashooter_initX, peashooter_initY, sidemenu);
                }
            }
        });

        potato_btn.setOnMousePressed(event -> {
            if(Integer.parseInt(SunCounter.getText()) > 25) {
                for (int i = 0; i < 45; i++) {
                    DragOver(potato_btn, TilePanes[i], peashooter_initX, peashooter_initY, sidemenu);
                }
            }
        });

        System.out.println(Plants_List.size());
    }

    private void collisionPeaZombie(Plants obj1, Default_Zombie obj2)
    {
        ObservableBooleanValue collision= Bindings.createBooleanBinding(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    return obj1.get_other_img().getBoundsInParent().intersects(obj2.getZombie_img().getBoundsInParent());

                }catch (NullPointerException e){return null;}
            }
        }, obj1.get_main_img().boundsInParentProperty(),obj2.getZombie_img().boundsInParentProperty());
        collisions_list.add(collision);
        collision.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    System.out.println(obj1.getId()+" collision pea");
                    if(obj1 instanceof PeaShooter)
                    {
                        ((PeaShooter) obj1).getMovepea().stop();
                        obj1.get_other_img().setVisible(false);
                        attack(obj1,obj2);
                        ((PeaShooter) obj1).getMovepea().play();
                    }

                    else if(obj1 instanceof SunFlower)
                    {
                        ((SunFlower) obj1).getSunanim().stop();
                        obj1.get_other_img().setVisible(false);
                        attack(obj1,obj2);
                        ((SunFlower) obj1).getSunanim().play();
                    }
                }
                else{
                    obj1.get_other_img().setVisible(true);
//                    System.out.println("not colliding");
                }
            }
        });
    }

    private void collisionPlantZombie(Plants obj1, Default_Zombie obj2)
    {
        ObservableBooleanValue collision= Bindings.createBooleanBinding(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    return obj1.get_main_img().getBoundsInParent().intersects(obj2.getZombie_img().getBoundsInParent());
                }catch (NullPointerException e){return null;}
            }
        }, obj1.get_main_img().boundsInParentProperty(),obj2.getZombie_img().boundsInParentProperty());
        collisions_list.add(collision);
        collision.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    System.out.println("collision pea");
//                    obj1.getMovepea().stop();
                    obj1.get_main_img().setVisible(false);
//                    obj1.getMovepea().playFromStart();
                }
                else{
                    obj1.get_main_img().setVisible(true);
                    System.out.println("not colliding");
                }
            }
        });
    }

    private void collisionLawnMowerZombie(ImageView obj1, Default_Zombie obj2)
    {
        ObservableBooleanValue collision= Bindings.createBooleanBinding(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    return obj1.getBoundsInParent().intersects(obj2.getZombie_img().getBoundsInParent());
                }catch (NullPointerException e){return null;}
            }
        }, obj1.boundsInParentProperty(),obj2.getZombie_img().boundsInParentProperty());
        collisions_list.add(collision);
        collision.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    System.out.println("collision LawnMower");
                    obj2.getZombie_img().setVisible(false);
                    triggerLawnMower(obj1);
                }
                else{
//                    obj1.setVisible(true);
//                    System.out.println("not colliding");
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
//        movezombie.pause();
        movesun.pause();
//        movepea.pause();

    }

    // Drag Over Tile Detection //
    private void DragOver( Button elem, AnchorPane tile, double peashooter_initX, double peashooter_initY, AnchorPane exception_tiles) {

        Plants plant=null;
        if(elem.getId().equals("peashooter_btn"))
        {
//            System.out.println("lmao");
            plant=new PeaShooter(300,100,50,false,null,5);
            Lawn.getChildren().add(plant.get_main_img());
            Lawn.getChildren().add(plant.get_other_img());
//            if(Integer.parseInt(SunCounter.getText()) < plant.getSun_Cost())
//                return;
        }

        else if(elem.getId().equals("sunflower_btn"))
        {
            plant=new SunFlower(300,50,0,false,null,5);
            Lawn.getChildren().add(plant.get_main_img());
            Lawn.getChildren().add(plant.get_other_img());
//            if(Integer.parseInt(SunCounter.getText()) < plant.getSun_Cost())
//                return;
        }

        else if(elem.getId().equals("walnut_btn"))
        {
            plant=new Wallnut(600,50,0,false,null,5);
            Lawn.getChildren().add(plant.get_main_img());
//            Lawn.getChildren().add(plant.get_other_img());
//            if(Integer.parseInt(SunCounter.getText()) < plant.getSun_Cost())
//                return;
        }

        else if(elem.getId().equals("potato_btn"))
        {
            plant=new PotatoMine(200,25,200,false,null,5);
            Lawn.getChildren().add(plant.get_main_img());
//            Lawn.getChildren().add(plant.get_other_img());
//            if(Integer.parseInt(SunCounter.getText()) < plant.getSun_Cost())
//                return;
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

        elem.setOnMouseReleased(event -> peashooter_img.setVisible(false));

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
                SunCounter.setText(String.valueOf(Integer.parseInt(SunCounter.getText()) - finalPlant.getSun_Cost()));
                if(elem == sunflower_btn)
                    buttonSeed[0] = 5;
                else if(elem == peashooter_btn)
                    buttonSeed[1] = 5;
                else if(elem == walnut_btn)
                    buttonSeed[2] = 10;
                else
                    buttonSeed[3] = 10;
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

                    if(finalPlant instanceof PeaShooter)
                    {
                        ((PeaShooter) finalPlant).shootPea(pea_img,peashooter_img);
                    }

                    else if(finalPlant instanceof SunFlower)
                    {
                        ((SunFlower) finalPlant).SunFlowerAnim(pea_img,peashooter_img, SunCounter);
                    }

                    for(Plants plant_img: Plants_List)
                    {
                        if(pea_img!=null || plant_img.get_other_img()!=null)
                        {
                            for(Default_Zombie zombie : Zombies_Wave_1)
                            {
                                collisionPeaZombie(plant_img,zombie);
                                collisionPlantZombie(plant_img,zombie);
                            }

                            for(Default_Zombie zombie : Zombies_Wave_2)
                            {
                                collisionPeaZombie(plant_img,zombie);
                                collisionPlantZombie(plant_img,zombie);
                            }

                            for(Default_Zombie zombie : Zombies_Wave_3)
                            {
                                collisionPeaZombie(plant_img,zombie);
                                collisionPlantZombie(plant_img,zombie);
                            }
                        }
                    }
                }

                tile.setOpacity(1);
                tile.setStyle("-fx-background-color: transparent");

//                System.out.println(elem.getId());
//
//                if(elem.getId().equals("potato_btn")) {
//                    new Thread(() -> {
//                        Platform.runLater(() -> elem.setDisable(true));
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException ex) {
//                        }
//                        Platform.runLater(() -> elem.setDisable(false));
//                    }).start();
//                }
//
//                if(elem.getId().equals("sunflower_btn")){
//                    new Thread(() -> {
//                        Platform.runLater(() -> elem.setDisable(true));
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException ex) {
//                        }
//                        Platform.runLater(() -> elem.setDisable(false));
//                    }).start();
//                }
//                if(elem.getId().equals("peashooter_btn")) {
//                    new Thread(() -> {
//                        Platform.runLater(() -> elem.setDisable(true));
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException ex) {
//                        }
//                        Platform.runLater(() -> elem.setDisable(false));
//                    }).start();
//                }
//                if(elem.getId().equals("walnut_btn")) {
//                    new Thread(() -> {
//                        Platform.runLater(() -> elem.setDisable(true));
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException ex) {
//                        }
//                        Platform.runLater(() -> elem.setDisable(false));
//                    }).start();
//                }
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
//        movezombie.play();
        movesun.play();
//        movepea.play();
    }

    public void CollectSun(MouseEvent actionEvent)
    {
        FallSun.setVisible(false);
        suncounter.setStyle("-fx-border-color: #ffcf00");
        SunCounter.setText(String.valueOf(Integer.parseInt(SunCounter.getText()) + 50));
//        collectsun = new TranslateTransition(Duration.seconds(4),FallSun);
//        collectsun.setToX(-304);
//        collectsun.setToY(50);
//        collectsun.play();
//        collectsun.setOnFinished(e ->
//        {
//        });
    }

    public void ButtonActive()
    {
        if(Integer.parseInt(SunCounter.getText()) >= 100)
        {
            if(buttonSeed[0] > 0) {
                sunflower_btn.setDisable(true);
                buttonSeed[0]--;
            }
            else
                sunflower_btn.setDisable(false);

            if(buttonSeed[1] > 0) {
                peashooter_btn.setDisable(true);
                buttonSeed[1]--;
            }
            else
                peashooter_btn.setDisable(false);

            if(buttonSeed[2] > 0) {
                walnut_btn.setDisable(true);
                buttonSeed[2]--;
            }
            else
                walnut_btn.setDisable(false);

            if(buttonSeed[3] > 0) {
                potato_btn.setDisable(true);
                buttonSeed[3]--;
            }
            else
                potato_btn.setDisable(false);
        }

        else if(Integer.parseInt(SunCounter.getText()) >= 50)
        {
            if(buttonSeed[0] > 0) {
                sunflower_btn.setDisable(true);
                buttonSeed[0]--;
            }
            else
                sunflower_btn.setDisable(false);

            peashooter_btn.setDisable(true);

            if(buttonSeed[2] > 0) {
                walnut_btn.setDisable(true);
                buttonSeed[2]--;
            }
            else
                walnut_btn.setDisable(false);

            if(buttonSeed[3] > 0) {
                potato_btn.setDisable(true);
                buttonSeed[3]--;
            }
            else
                potato_btn.setDisable(false);
        }

        else if(Integer.parseInt(SunCounter.getText()) >= 25)
        {
            sunflower_btn.setDisable(true);
            peashooter_btn.setDisable(true);
            walnut_btn.setDisable(true);
            if(buttonSeed[2] > 0) {
                potato_btn.setDisable(true);
                buttonSeed[2]--;
            }
            else
                potato_btn.setDisable(false);
        }

        else
        {
            sunflower_btn.setDisable(true);
            peashooter_btn.setDisable(true);
            walnut_btn.setDisable(true);
            potato_btn.setDisable(true);
        }
    }

    private class TimeHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent actionEvent) {
            createPlants();
        }
    }

    private class ButtonHide implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent actionEvent) {
        ButtonActive();
        }

    }

    public void setupTime()
    {
        KeyFrame kf = new KeyFrame(Duration.seconds(1),new ButtonHide());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setupTimelines()
    {
        KeyFrame kf = new KeyFrame(Duration.seconds(1),new TimeHandler());
        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    // Init //
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createLawnmower();
        createWaves(2,3,5);
        setupTimelines();
        setupTime();
        releaseWave_1();
        FallSun();
        System.out.println(collisions_list);


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
