package MainGame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;

public class LawnController4 implements Initializable {
    volatile private TranslateTransition movelawnmower;
    volatile private Timeline timeline;
    volatile private Timeline timelinesetup;
    volatile private Timeline timeline_setup;
    volatile private Timeline timeline_sun;
    Random get_random = new Random();
    volatile private int[] buttonSeed = new int[5];

    @FXML
    private Pane Lawn;

    @FXML
    private AnchorPane winscreen,losescreen;

    @FXML
    private Button peashooter_btn, sunflower_btn, walnut_btn, potato_btn, winbtn,losebtn,winbtn2;

    @FXML
    private Rectangle Gameover;

    @FXML
    private ImageView peashooter_anim, sunflower,walnut, potato,suncounter,winimg,loseimg,progressbar;

    @FXML
    private Pane menu_panel;

    @FXML
    private ImageView Zombie1, Zombie2, Zombie3, Zombie4, Zombie5;

    @FXML
    private ImageView FallSun1,FallSun2,FallSun3,FallSun4,FallSun5,FallSun6,FallSun7,FallSun8;

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

    //ArrayList of TranslateTransitions//
    ArrayList<LawnMower> lawnmower_obj= new ArrayList<>();

    // Zombies Img Arraylist //
    ArrayList<Double> Zombieimg = new ArrayList<>();

    // Peashooter ArrayList //
    volatile ArrayList<Plants> Plants_List=new ArrayList<>();

    // FallSun ArrayList //
    ArrayList<Double> Sun_List=new ArrayList<>();
    ArrayList<FallSun> FallSun_List=new ArrayList<>();

    // Zombies ArrayList //
    volatile ArrayList<Default_Zombie> Zombies_Wave_1=new ArrayList<>();
    volatile ArrayList<Default_Zombie> Zombies_Wave_2=new ArrayList<>();
    volatile ArrayList<Default_Zombie> Zombies_Wave_3=new ArrayList<>();


    // LawnMower ArrayList //
    volatile ArrayList<ImageView> Lawnmower_List=new ArrayList<>();

    // Collisions ArrayList //
    volatile ArrayList<ObservableBooleanValue> collisions_list=new ArrayList<>();

    public LawnController4() {}

    // Add Lawnmower in List //
    private void createLawnmower()
    {
        Lawnmower_List.add(LawnMower0);
        Lawnmower_List.add(LawnMower1);
        Lawnmower_List.add(LawnMower2);
        Lawnmower_List.add(LawnMower3);
        Lawnmower_List.add(LawnMower4);
        lawnmower_obj.add(new LawnMower(LawnMower0));
        lawnmower_obj.add(new LawnMower(LawnMower1));
        lawnmower_obj.add(new LawnMower(LawnMower2));
        lawnmower_obj.add(new LawnMower(LawnMower3));
        lawnmower_obj.add(new LawnMower(LawnMower4));
    }

    private synchronized void generateSun()
    {
        Sun_List.add(FallSun1.getLayoutX());
        Sun_List.add(FallSun2.getLayoutX());
        Sun_List.add(FallSun3.getLayoutX());
        Sun_List.add(FallSun4.getLayoutX());
        Sun_List.add(FallSun5.getLayoutX());
        Sun_List.add(FallSun6.getLayoutX());
        Sun_List.add(FallSun7.getLayoutX());
        Sun_List.add(FallSun8.getLayoutX());

        for (int i = 0; i < Sun_List.size() ; i++) {
            FallSun fall_sun = new FallSun(SunCounter, Sun_List.get(get_random.nextInt(Sun_List.size())));
            Lawn.getChildren().add(fall_sun.getSun_img());
            FallSun_List.add(fall_sun);
        }
    }

    public synchronized void createWaves(int wave_1 , int wave_2 , int wave_3)
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

    public void gameover_collision()
    {
        for (Default_Zombie zombies: Zombies_Wave_1) {
            collisionGameOver(Gameover,zombies);
        }
        for (Default_Zombie zombies: Zombies_Wave_2) {
            collisionGameOver(Gameover,zombies);
        }
        for (Default_Zombie zombies: Zombies_Wave_3) {
            collisionGameOver(Gameover,zombies);
        }
    }

    public void gameover_scene()
    {
        // TO-DO //
//        System.out.println("GAMEOVER");
        losescreen.toFront();
        loseimg.toFront();
        losebtn.toFront();
        losescreen.setVisible(true);
        loseimg.setVisible(true);
        losebtn.setVisible(true);
        losebtn.setFocusTraversable(true);
        losebtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Pane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Lawn.getChildren().setAll(pane);
            }
        });
    }

    public void releaseWave_1() {
        if (Zombies_Wave_1.size() != 0) {
            for (LawnMower img : lawnmower_obj) {
                for (Default_Zombie zombie : Zombies_Wave_1) {
                    collisionLawnMowerZombie(img, zombie);
                }
            }
            for (Default_Zombie zombie : Zombies_Wave_1) {
                zombie.moveZombie(get_random.nextInt(5)+ 5 , 1280 + get_random.nextInt(20));
            }
        }
    }
    public void releaseWave_2() {

        if (Zombies_Wave_2.size() != 0) {
            for (LawnMower img : lawnmower_obj) {
                for (Default_Zombie zombie : Zombies_Wave_2) {
                    collisionLawnMowerZombie(img, zombie);
                }
            }
            for (Default_Zombie zombie : Zombies_Wave_2) {
                zombie.moveZombie(get_random.nextInt(5)+ 5 , 1280 + get_random.nextInt(20));
            }
        }
    }
    public void releaseWave_3() {
        if (Zombies_Wave_3.size() != 0) {
            for (LawnMower img : lawnmower_obj) {
                for (Default_Zombie zombie : Zombies_Wave_3) {
                    collisionLawnMowerZombie(img, zombie);
                }
            }
            for (Default_Zombie zombie : Zombies_Wave_3) {
                zombie.moveZombie(get_random.nextInt(5)+ 5 , 1280 + get_random.nextInt(20));
            }
        }
    }


    // Attach Mechanism //
    private synchronized void attack(Plants plants , Default_Zombie zombie)
    {
        if(!zombie.isDead()) {
            zombie.setHealth(zombie.getHealth() - plants.getAttack_power());
            if(zombie.getHealth() <= 0){
                zombie.setHealth(0);
                zombie.setDead(true);
                try {
                    zombie.getMovezombie().stop();
                    Lawn.getChildren().remove(zombie.getZombie_img());
                    zombie.getZombie_img().setY(-1000);
                }catch (NullPointerException e){}

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
            }
        }
    }

    private synchronized void PvZFightTimer(Plants plant, Default_Zombie zombie)
    {
        timeline= new Timeline(new KeyFrame(
                Duration.millis(500),
                ae -> PvZFight(plant,zombie)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        if(plant.isDead() || zombie.isDead())
        {
            timeline.stop();
        }
    }

    private synchronized void PvZFight(Plants plants, Default_Zombie zombie)
    {
        if(!plants.isDead()) {
            plants.setHealth(plants.getHealth() - zombie.getAttack());
            if(plants.getHealth() <= 0){
                plants.setHealth(0);
                plants.setDead(true);
                plants.getTilePlaced().getChildren().removeAll();
                plants.get_main_img().setVisible(false);
                plants.get_main_img().setY(1000);
                if(plants.get_other_img()!=null){
                    plants.get_other_img().setY(1000);
                }
                if(plants.isDead || zombie.isDead())
                {
                    timeline.stop();
                }
                zombie.getMovezombie().play();

            }
        }

    }

    private synchronized void createPlants()
    {
        double peashooter_initX=peashooter_anim.getLayoutX();
        double peashooter_initY=peashooter_anim.getLayoutY();

        peashooter_btn.setOnMousePressed(event -> {
//            event.getSource();
            System.out.println("peashooter");
            peashooter_btn.setId("peashooter_btn");
            for (int i =0 ; i<45; i++) {
                DragOver(peashooter_btn,TilePanes[i], peashooter_initX,peashooter_initY, sidemenu);
            }
            event.consume();
        });

        sunflower_btn.setOnMousePressed(event -> {
            System.out.println("sunflower");
            sunflower_btn.setId("sunflower_btn");
            for (int i =0 ; i<45; i++) {
                DragOver(sunflower_btn,TilePanes[i], peashooter_initX,peashooter_initY, sidemenu);
            }
            event.consume();
        });

        walnut_btn.setOnMousePressed(event -> {
            System.out.println("walnut");
            walnut_btn.setId("walnut_btn");
            for (int i =0 ; i<45; i++) {
                DragOver(walnut_btn,TilePanes[i], peashooter_initX,peashooter_initY, sidemenu);
            }
            event.consume();
        });

        potato_btn.setOnMousePressed(event -> {
            System.out.println("potato");
            potato_btn.setId("potato_btn");
            for (int i =0 ; i<45; i++) {
                DragOver(potato_btn,TilePanes[i], peashooter_initX,peashooter_initY, sidemenu);
            }
            event.consume();
        });
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
//                    System.out.println(obj1.getId()+" collision pea");
                    if(obj1 instanceof PeaShooter)
                    {
                        ((PeaShooter) obj1).getMovepea().stop();
                        obj1.get_other_img().setVisible(false);
                        if(obj1!=null && obj2!=null){
                            attack(obj1,obj2);}
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
//                    System.out.println(obj1 instanceof  Cherry_Bomb);


                    if(obj1 instanceof PotatoMine)
                    {
                        obj1.setDead(true);
                        obj2.setDead(true);
                        obj1.get_main_img().setVisible(false);
                        obj2.getZombie_img().setVisible(false);
                        obj1.get_main_img().setY(1000);
                        obj2.getZombie_img().setY(1000);
                        obj2.setHealth(0);
                        obj2.setDead(true);
                        try {
                            obj2.getMovezombie().stop();
                            Lawn.getChildren().remove(obj2.getZombie_img());
                            obj2.getZombie_img().setY(-1000);
                        }catch (NullPointerException e){}

                        if(Zombies_Wave_1.contains(obj2))
                        {
                            Zombies_Wave_1.remove(obj2);
                            if(Zombies_Wave_1.size()==0)
                            {
                                releaseWave_2();
                            }
                        }
                        else if(Zombies_Wave_2.contains(obj2))
                        {
                            Zombies_Wave_2.remove(obj2);
                            if(Zombies_Wave_2.size()==0)
                            {
                                releaseWave_3();
                            }
                        }
                        else if(Zombies_Wave_3.contains(obj2))
                        {
                            Zombies_Wave_3.remove(obj2);
                            if(Zombies_Wave_3.size()==0)
                            {
                                return; // Throws LevelClearedException
                            }
                        }
                    }
                    else {
                        obj2.getMovezombie().pause();
                        PvZFightTimer(obj1, obj2);
                    }
                }
                else{
                }
            }
        });
    }

    private void collisionBombZombie(Plants obj1, Default_Zombie obj2)
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
                    System.out.println(obj1 instanceof  Cherry_Bomb);


                    if(obj1 instanceof Cherry_Bomb)
                    {
                        obj1.setDead(true);
                        obj2.setDead(true);
                        obj1.get_main_img().setVisible(false);
                        obj2.getZombie_img().setVisible(false);
                        obj1.get_main_img().setY(1000);
                        obj2.getZombie_img().setY(1000);
                        obj2.setHealth(0);
                        obj2.setDead(true);
                        try {
                            obj2.getMovezombie().stop();
                            Lawn.getChildren().remove(obj2.getZombie_img());
                            obj2.getZombie_img().setY(-1000);
                        }catch (NullPointerException e){}

                        if(Zombies_Wave_1.contains(obj2))
                        {
                            Zombies_Wave_1.remove(obj2);
                            if(Zombies_Wave_1.size()==0)
                            {
                                releaseWave_2();
                            }
                        }
                        else if(Zombies_Wave_2.contains(obj2))
                        {
                            Zombies_Wave_2.remove(obj2);
                            if(Zombies_Wave_2.size()==0)
                            {
                                releaseWave_3();
                            }
                        }
                        else if(Zombies_Wave_3.contains(obj2))
                        {
                            Zombies_Wave_3.remove(obj2);
                            if(Zombies_Wave_3.size()==0)
                            {
                                return; // Throws LevelClearedException
                            }
                        }
                    }
                    else {
                        obj2.getMovezombie().pause();
                        PvZFightTimer(obj1, obj2);
                    }
                }
                else{
                }
            }
        });
    }

    private void collisionLawnMowerZombie(LawnMower obj1, Default_Zombie obj2)
    {
        ObservableBooleanValue collision= Bindings.createBooleanBinding(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    return obj1.getImg().getBoundsInParent().intersects(obj2.getZombie_img().getBoundsInParent());
                }catch (NullPointerException e){return null;}
            }
        }, obj1.getImg().boundsInParentProperty(),obj2.getZombie_img().boundsInParentProperty());
        collisions_list.add(collision);
        collision.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
//                    System.out.println("collision LawnMower");
                    obj2.getZombie_img().setVisible(false);
                    obj1.triggerLawnMower();
                    obj2.setDead(true);
                    obj2.getZombie_img().setY(1000);
                    obj2.setHealth(0);
                    obj2.setDead(true);
                    try {
                        obj2.getMovezombie().stop();
                        Lawn.getChildren().remove(obj2.getZombie_img());
                        obj2.getZombie_img().setY(-1000);
                    }catch (NullPointerException e){}

                    if(Zombies_Wave_1.contains(obj2))
                    {
                        Zombies_Wave_1.remove(obj2);
                        if(Zombies_Wave_1.size()==0)
                        {
                            releaseWave_2();
                        }
                    }
                    else if(Zombies_Wave_2.contains(obj2))
                    {
                        Zombies_Wave_2.remove(obj2);
                        if(Zombies_Wave_2.size()==0)
                        {
                            releaseWave_3();
                        }
                    }
                    else if(Zombies_Wave_3.contains(obj2))
                    {
                        Zombies_Wave_3.remove(obj2);
                        if(Zombies_Wave_3.size()==0)
                        {
                            return; // Throws LevelClearedException
                        }
                    }
                }
                else{
                }
            }
        });
    }

    private void collisionGameOver(Rectangle obj1, Default_Zombie obj2)
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
//                    System.out.println("collision LawnMower");
                    gameover_scene();
                }
                else{
//                    obj1.setVisible(true);
//                    System.out.println("not colliding");
                }
            }
        });
    }


    // In-game Menu Btn //
    @FXML
    private void MenuBtn(javafx.event.ActionEvent actionEvent) throws IOException {
        menu_panel.toFront();
        menu_panel.setVisible(true);

        for(LawnMower mower: lawnmower_obj)
        {
            if(mower.isTriggered())
                mower.getMovelawnmower().pause();
        }
        try {

            for (Plants plants: Plants_List) {
                if(plants instanceof PeaShooter)
                {
                    ((PeaShooter) plants).getMovepea().pause();
                }
                if(plants instanceof SunFlower)
                {
                    ((SunFlower) plants).getSunanim().pause();
                }
            }
            for(Default_Zombie zom1: Zombies_Wave_1 ) {
                zom1.getMovezombie().pause();
            }
            for(Default_Zombie zom1: Zombies_Wave_2 ) {
                zom1.getMovezombie().pause();
            }
            for(Default_Zombie zom1: Zombies_Wave_3 ) {
                zom1.getMovezombie().pause();
            }
            for(FallSun sun : FallSun_List){
                sun.getMovesun().pause();
            }
        }catch (NullPointerException e){}
    }

    // Drag Over Tile Detection //
    private void DragOver( Button elem, AnchorPane tile, double peashooter_initX, double peashooter_initY, AnchorPane exception_tiles) {

//        System.out.println(elem.getId());
        PlantFactory plantFactory= new PlantFactory();
        Plants plant= plantFactory.createPlant(elem.getId());
        if(elem.getId().equals("peashooter_btn"))
        {
            Lawn.getChildren().add(plant.get_main_img());
            Lawn.getChildren().add(plant.get_other_img());
        }

        else if(elem.getId().equals("sunflower_btn"))
        {
            Lawn.getChildren().add(plant.get_main_img());
            Lawn.getChildren().add(plant.get_other_img());
        }

        else if(elem.getId().equals("walnut_btn"))
        {
            Lawn.getChildren().add(plant.get_main_img());

        }

        else if(elem.getId().equals("potato_btn"))
        {
            Lawn.getChildren().add(plant.get_main_img());
        }

        else if(elem.getId().equals("cherry_btn"))
        {
            plant=new Cherry_Bomb(200,150,200,false,null,5);
            Lawn.getChildren().add(plant.get_main_img());
        }

        ImageView peashooter_img=plant.get_main_img();
        ImageView pea_img=plant.get_other_img();

        peashooter_img.setVisible(false);
//        Lawn.getChildren().add(pea_img);
        if(pea_img!=null) {
            pea_img.setVisible(false);
        }
        elem.setOnMousePressed(event -> {
//            System.out.println(elem.getId());
            peashooter_img.setVisible(true);
            peashooter_img.setLayoutX(event.getSceneX() - 10);
            peashooter_img.setLayoutY(event.getSceneY() - 10);
            if(pea_img!=null) {
                pea_img.setLayoutX(event.getSceneX() - 10);
                pea_img.setLayoutY(event.getSceneY() - 10);
            }
//                System.out.println("Mouse Pressed Detected!");
//            event.consume();
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


        Plants finalPlant = plant;
        ImageView peashooter_img2= finalPlant.get_main_img();
        ImageView pea_img2=finalPlant.get_other_img();

//        System.out.println(finalPlant instanceof Wallnut);
        tile.setOnMouseDragReleased(event -> {
//            System.out.println(event.getSceneX());
            if (event.getGestureSource() != tile && tile.getChildren().isEmpty()) {
                SunCounter.setText(String.valueOf(Integer.parseInt(SunCounter.getText()) - finalPlant.getSun_Cost()));
                if(elem == sunflower_btn)
                    buttonSeed[0] = 5;
                else if(elem == peashooter_btn)
                    buttonSeed[1] = 5;
                else if(elem == walnut_btn)
                    buttonSeed[2] = 10;
                else if(elem == potato_btn)
                    buttonSeed[3] = 10;
                peashooter_img2.setVisible(true);
                finalPlant.setTilePlaced(tile);
                Plants_List.add(finalPlant);
//                System.out.println(Plants_List.size());
                peashooter_img2.setLayoutX(tile.getLayoutX() + 25);
                peashooter_img2.setLayoutY(tile.getLayoutY() + 15);

                if(pea_img2!=null)
                {
                    pea_img2.setLayoutX(tile.getLayoutX() + 30);
                    pea_img2.setLayoutY(tile.getLayoutY() + 20);
                    double init_x=pea_img2.getLayoutX();
                    double init_y=pea_img2.getLayoutY();

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
                        if(pea_img2!=null || plant_img.get_other_img()!=null)
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
                else
                {
                    for(Plants plant_img: Plants_List)
                    {
                        if(pea_img2==null || plant_img.get_other_img()==null)
                        {
                            for(Default_Zombie zombie : Zombies_Wave_1)
                            {
                                collisionPlantZombie(plant_img,zombie);
                            }

                            for(Default_Zombie zombie : Zombies_Wave_2)
                            {
                                collisionPlantZombie(plant_img,zombie);
                            }

                            for(Default_Zombie zombie : Zombies_Wave_3)
                            {
                                collisionPlantZombie(plant_img,zombie);
                            }

                            if(plant_img instanceof Cherry_Bomb)
                            {
                                for(Default_Zombie zombie : Zombies_Wave_1)
                                {
                                    collisionBombZombie(plant_img,zombie);
                                }

                                for(Default_Zombie zombie : Zombies_Wave_2)
                                {
                                    collisionBombZombie(plant_img,zombie);
                                }

                                for(Default_Zombie zombie : Zombies_Wave_3)
                                {
                                    collisionBombZombie(plant_img,zombie);
                                }
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
//                peashooter_img.setVisible(true);
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

        for (LawnMower mower:lawnmower_obj){
            if(mower.isTriggered()==true)
            {
                mower.getMovelawnmower().play();
            }
        }
        for (Plants plants: Plants_List) {
            if(plants instanceof PeaShooter)
            {
                ((PeaShooter) plants).getMovepea().play();
            }
            if(plants instanceof SunFlower)
            {
                ((SunFlower) plants).getSunanim().play();
            }
        }
        if(Zombies_Wave_1.size()!=0)
        {
            for(Default_Zombie zom1: Zombies_Wave_1 ) {
                zom1.getMovezombie().play();
            }
        }
        if(Zombies_Wave_2.size()!=0 && Zombies_Wave_1.size()==0)
        {
            for(Default_Zombie zom1: Zombies_Wave_2 ) {
                zom1.getMovezombie().play();
            }
        }
        if(Zombies_Wave_1.size()!=0 && Zombies_Wave_1.size()==0 && Zombies_Wave_2.size()==0)
        {
            for(Default_Zombie zom1: Zombies_Wave_3 ) {
                zom1.getMovezombie().play();
            }
        }
    }

    // Save Game //

    public void CollectSun(MouseEvent actionEvent)
    {
        FallSun1.setVisible(false);
        suncounter.setStyle("-fx-border-color: #ffcf00");
        SunCounter.setText(String.valueOf(Integer.parseInt(SunCounter.getText()) + 50));
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

    public void restart_level(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("Lawn.fxml"));
        Lawn.getChildren().setAll(pane);
    }

    public void save_game(MouseEvent mouseEvent) {
    }

    private class TimeHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent actionEvent) {
            createPlants();
            if(Zombies_Wave_1.size() == 0)
//                System.out.println("GameWin");
//                releaseWave_2();

                if(Zombies_Wave_1.size() == 0 && Zombies_Wave_2.size()==0)
//                System.out.println("GameWin");
//                releaseWave_3();
                    progressbar.setImage(new Image("main/resources/progress2.png"));
            if(Zombies_Wave_1.size() == 0 && Zombies_Wave_2.size()==0 && Zombies_Wave_3.size()==0)
            {
//                System.out.println("GameWin");
                progressbar.setImage(new Image("main/resources/progress1.png"));
                Winscreen();

            }
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
        timelinesetup = new Timeline(kf);
        timelinesetup.setCycleCount(Animation.INDEFINITE);
        timelinesetup.play();
    }

    public void setupTimelines()
    {
        KeyFrame kf = new KeyFrame(Duration.seconds(1),new TimeHandler());
        timeline_setup = new Timeline(kf);
        timeline_setup.setCycleCount(Animation.INDEFINITE);
        timeline_setup.play();
    }

    private class SunTimeHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent actionEvent) {
            fall_sun();
        }
    }
    public void fall_sun()
    {
        for (FallSun sun:FallSun_List) {
            if (!sun.isIsmove())
            {
                sun.FallSun();
                sun.setIsmove(true);
                break;
            }
        }
    }

    public void suntimer()
    {
        KeyFrame kf = new KeyFrame(Duration.seconds(6),new SunTimeHandler());
        timeline_sun = new Timeline(kf);
        timeline_sun.setCycleCount(Animation.INDEFINITE);
        timeline_sun.play();
    }

    private void Winscreen()
    {
        winscreen.toFront();
        winimg.toFront();
        winbtn.toFront();
        winbtn2.toFront();
        winscreen.setDisable(false);
        winbtn.setDisable(false);
        winbtn2.setDisable(false);
        winscreen.setVisible(true);
        winimg.setVisible(true);
        winbtn.setVisible(true);
        winbtn2.setVisible(true);
        winbtn.setFocusTraversable(true);
        winbtn2.setFocusTraversable(true);
        winbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Pane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Lawn.getChildren().setAll(pane);
            }
        });
        winbtn2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Pane pane = null;
                try {
                    pane = FXMLLoader.load(getClass().getResource("Lawn5.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Lawn.getChildren().setAll(pane);
            }
        });
    }

    // Init //

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createLawnmower();
        setupTimelines();
        setupTime();
        createWaves(4,5,6);
        releaseWave_1();
        generateSun();
        suntimer();
//        gameover_collision();
        System.out.println(collisions_list);

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
