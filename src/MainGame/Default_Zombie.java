package MainGame;


import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class Default_Zombie {


    private TranslateTransition movezombie;
    private static int idgen=0;
    private int id;
    private int Health=150;
    private final int Attack=20;

    private boolean isDead=false;
    private double yPos;

    public ImageView getZombie_img() {
        return zombie_img;
    }

    // Move Zombie Anim //
    public void moveZombie(int duration,int xPos) {
        movezombie = new TranslateTransition(Duration.seconds(15), getZombie_img());
        movezombie.setFromX(xPos);
        movezombie.setToX(-1280);
        movezombie.setCycleCount(1);
        movezombie.play();

    }

    private ImageView zombie_img= new ImageView("main/resources/zombie_normal.gif");

    public Default_Zombie(double yPos) {
        id=idgen;
        idgen++;
        this.yPos = yPos;
        zombie_img.setFitWidth(95);
        zombie_img.setFitHeight(95);
        zombie_img.setPreserveRatio(true);
        zombie_img.setX(1280);
        zombie_img.setLayoutY(yPos-15);
    }

    public TranslateTransition getMovezombie() {
        return movezombie;
    }

    public int getHealth() {
        return Health;
    }

    public int getAttack() {
        return Attack;
    }

    public boolean isDead() {
        return isDead;
    }

    public double getyPos() {
        return yPos;
    }

    public int getId() {
        return id;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }


}
