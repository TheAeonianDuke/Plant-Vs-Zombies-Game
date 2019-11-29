package MainGame;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class Plants {
    protected final String plant_ID;
    protected int Health;
    protected final int Sun_Cost;
    protected final int Attack_power;
    protected boolean isDead=false;
    protected AnchorPane TilePlaced;
    protected final int Recharge;

    protected int xPos;
    protected int yPos;

    public Plants(String plant_id, int health, int sun_Cost, int attack_power, boolean isDead, AnchorPane tilePlaced, int recharge) {
        plant_ID = plant_id;
        Health = health;
        Sun_Cost = sun_Cost;
        Attack_power = attack_power;
        this.isDead = isDead;
        TilePlaced = tilePlaced;
        Recharge = recharge;
    }

    public void setTilePlaced(AnchorPane tilePlaced) {
        TilePlaced = tilePlaced;
    }


    public abstract ImageView get_main_img();

    public abstract ImageView get_other_img();

}
