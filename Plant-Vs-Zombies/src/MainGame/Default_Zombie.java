package MainGame;


import javafx.scene.image.ImageView;

public class Default_Zombie extends Zombie
{

    private ImageView zombie_img= new ImageView("main/resources/zombie_normal.gif");

    public Default_Zombie(int health, int Attack ,boolean isDead, double yPos) {
        super(health,Attack,isDead,yPos);
        zombie_img.setFitWidth(95);
        zombie_img.setFitHeight(95);
        zombie_img.setPreserveRatio(true);
        zombie_img.setX(1280);
        zombie_img.setLayoutY(yPos-15);
    }

    public ImageView getZombie_img()
    {
        return zombie_img;
    }

}
