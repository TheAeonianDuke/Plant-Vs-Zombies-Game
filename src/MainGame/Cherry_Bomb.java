package MainGame;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Cherry_Bomb extends Plants {

    private static final String plant_id = "CherryMine";
    private final ImageView cherry_img = new ImageView("main/resources/cherrygif.gif");


    public Cherry_Bomb(int health, int sun_Cost, int attack_power, boolean isDead, AnchorPane tilePlaced, int recharge) {
        super(plant_id, health, sun_Cost, attack_power, isDead, tilePlaced, recharge);
        cherry_img.setFitHeight(50);
        cherry_img.setFitWidth(50);
        cherry_img.preserveRatioProperty();
    }



    public void setTilePlaced(AnchorPane tilePlaced) {
        TilePlaced = tilePlaced;
    }

    @Override
    public ImageView get_main_img() {
        return cherry_img;
    }

    @Override
    public ImageView get_other_img() {
        return null;
    }
}
