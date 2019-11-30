package MainGame;

import javafx.scene.image.ImageView;

public abstract class Zombie
{
    protected int Health;
    protected final int Attack;

    protected boolean isDead;
    protected double yPos;

    public Zombie(int health, int Attack ,boolean isDead, double yPos) {
        Health = health;
        this.Attack = Attack;
        this.isDead = isDead;
        this.yPos = yPos;
    }

    public abstract ImageView getZombie_img();

    public int getAttack() {
        return Attack;
    }

    public boolean isDead() {
        return isDead;
    }

    public int getHealth() {
        return Health;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setHealth(int health) {
        Health = health;
    }
}
