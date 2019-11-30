package MainGame;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class Plants {
    private static int idgen=0;
    private int id;
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
        id=idgen;
        idgen++;
        plant_ID = plant_id;
        Health = health;
        Sun_Cost = sun_Cost;
        Attack_power = attack_power;
        this.isDead = isDead;
        TilePlaced = tilePlaced;
        Recharge = recharge;
    }



    public int getId() {
        return id;
    }



    public void setTilePlaced(AnchorPane tilePlaced) {
        TilePlaced = tilePlaced;
    }


    public abstract ImageView get_main_img();

    public String getPlant_ID() {
        return plant_ID;
    }

    public int getHealth() {
        return Health;
    }

    public int getSun_Cost() {
        return Sun_Cost;
    }

    public int getAttack_power() {
        return Attack_power;
    }

    public boolean isDead() {
        return isDead;
    }

    public AnchorPane getTilePlaced() {
        return TilePlaced;
    }

    public int getRecharge() {
        return Recharge;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public abstract ImageView get_other_img();

}
