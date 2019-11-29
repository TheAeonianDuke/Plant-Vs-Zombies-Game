package MainGame;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Wallnut extends Plants
{
    private static final String plant_id = "Wallnut";
    private final ImageView wallnut_img = new ImageView("main/resources/walnut_full_life.gif");

    public Wallnut(int health, int sun_Cost, int attack_power, boolean isDead, AnchorPane tilePlaced, int recharge) {
        super(plant_id, health, sun_Cost, attack_power, isDead, tilePlaced, recharge);
    }

    public void setTilePlaced(AnchorPane tilePlaced) {
        TilePlaced = tilePlaced;
    }

    @Override
    public ImageView get_main_img() {
        return wallnut_img;
    }

    @Override
    public ImageView get_other_img() {
        return null;
    }
}
