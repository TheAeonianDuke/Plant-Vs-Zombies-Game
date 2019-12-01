package MainGame;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SunFlower extends Plants
{
    private static final String plant_id = "SunFlower";
    private TranslateTransition sunanim;
    private final ImageView sunflower_img = new ImageView("main/resources/sun_flower.gif");
    private final ImageView sun_img = new ImageView("main/resources/sun.gif");


    public SunFlower(int health, int sun_Cost, int attack_power, boolean isDead, AnchorPane tilePlaced, int recharge) {
        super(plant_id, health, sun_Cost, attack_power, isDead, tilePlaced, recharge);
    }

    //SunFlower Anim//
    public void SunFlowerAnim(ImageView sun, ImageView sunflower, Text SunCounter)
    {
        sun.setVisible(true);
        sunanim = new TranslateTransition(Duration.seconds(2), sun);
        sunanim.setFromX(sunflower.getX());
        sunanim.setToY(sunflower.getX()+50);
//        sunanim.setCycleCount(1000);
        sunanim.play();
        sun.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sun.setVisible(false);
                SunCounter.setStyle("-fx-border-color: #ffcf00");
                SunCounter.setText(String.valueOf(Integer.parseInt(SunCounter.getText()) + 50));
            }
        });


    }

    public TranslateTransition getSunanim() {
        return sunanim;
    }

    public void setTilePlaced(AnchorPane tilePlaced) {
        TilePlaced = tilePlaced;
    }

    @Override
    public ImageView get_main_img() {
        return sunflower_img;
    }

    @Override
    public ImageView get_other_img() {
        return sun_img;
    }
}
