package MainGame;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class PeaShooter extends Plants{
    private static final String plant_id = "Peashooter";
    private TranslateTransition movepea;
    private final ImageView peashooter_img = new ImageView("main/resources/pea_shooter.gif");
    private final ImageView pea = new ImageView("main/resources/Pea.png");

    public PeaShooter(int health, int sun_Cost, int attack_power, boolean isDead, AnchorPane tilePlaced, int recharge) {
        super(plant_id, health, sun_Cost, attack_power, isDead, tilePlaced, recharge);
    }

    // Pea Shooting Anim //
    public void shootPea(ImageView Pea, ImageView Peashooter) {
        Pea.setVisible(true);
        movepea = new TranslateTransition(Duration.seconds(2), Pea);
        movepea.setFromX(Peashooter.getX());
        movepea.setToX(1280);
        movepea.setCycleCount(1000);
        movepea.play();
    }

    public void setTilePlaced(AnchorPane tilePlaced) {
        TilePlaced = tilePlaced;
    }

    @Override
    public ImageView get_main_img() {
        return peashooter_img;
    }

    public TranslateTransition getMovepea() {
        return movepea;
    }

    @Override
    public ImageView get_other_img() {
        return pea;
    }




}
