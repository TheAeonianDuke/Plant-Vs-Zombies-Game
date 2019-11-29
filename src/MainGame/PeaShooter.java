package MainGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;



public class PeaShooter {
    private int Health=100;
    private final int Sun_Cost=100;
    private final int Attack_power=20;
    private boolean isDead=false;
    private AnchorPane TilePlaced=null;
    private final ImageView peashooter_img = new ImageView("main/resources/pea_shooter.gif");
    private final ImageView pea = new ImageView("main/resources/Pea.png");
    private final int Recharge=5;
    private int xPos;
    private int yPos;

    public PeaShooter() {}

    public void setTilePlaced(AnchorPane tilePlaced) {
        TilePlaced = tilePlaced;
    }

    public ImageView getPeashooter_img() {
        return peashooter_img;
    }

    public ImageView getPea() {
        return pea;
    }
}
