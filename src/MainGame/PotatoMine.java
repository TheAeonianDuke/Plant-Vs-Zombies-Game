package MainGame;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PotatoMine extends Plants
{
    private static final String plant_id = "PotatoMine";
    private final ImageView potato_img = new ImageView("main/resources/potato-mine.png");


    public PotatoMine(int health, int sun_Cost, int attack_power, boolean isDead, AnchorPane tilePlaced, int recharge) {
        super(plant_id, health, sun_Cost, attack_power, isDead, tilePlaced, recharge);
        potato_img.setFitHeight(50);
        potato_img.setFitWidth(50);
        potato_img.preserveRatioProperty();
    }

    public void setTilePlaced(AnchorPane tilePlaced) {
        TilePlaced = tilePlaced;
    }

    @Override
    public ImageView get_main_img() {
        return potato_img;
    }

    @Override
    public ImageView get_other_img() {
        return null;
    }
}
