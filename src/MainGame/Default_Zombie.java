package MainGame;


import javafx.scene.image.ImageView;

public class Default_Zombie {
    private int Health=200;
    private final int Attack=50;

    private boolean isDead=false;
    private double yPos;

    public ImageView getZombie_img() {
        return zombie_img;
    }

    private ImageView zombie_img= new ImageView("main/resources/zombie_normal.gif");

    public Default_Zombie(double yPos) {
        this.yPos = yPos;
        zombie_img.setFitWidth(95);
        zombie_img.setFitHeight(95);
        zombie_img.setPreserveRatio(true);
        zombie_img.setX(1280);
        zombie_img.setLayoutY(yPos-15);
    }
}
