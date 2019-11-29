package MainGame;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;



public class PeaShooter extends Plants{
    private static final String plant_id = "Peashooter";
    private final ImageView peashooter_img = new ImageView("main/resources/pea_shooter.gif");
    private final ImageView pea = new ImageView("main/resources/Pea.png");

    public PeaShooter(int health, int sun_Cost, int attack_power, boolean isDead, AnchorPane tilePlaced, int recharge) {
        super(plant_id, health, sun_Cost, attack_power, isDead, tilePlaced, recharge);
    }

    public void setTilePlaced(AnchorPane tilePlaced) {
        TilePlaced = tilePlaced;
    }

    @Override
    public ImageView get_main_img() {
        return peashooter_img;
    }

    @Override
    public ImageView get_other_img() {
        return pea;
    }




}
