package MainGame;

import java.io.Serializable;

public class Entity implements Serializable,Cloneable
{
    public static final long serialVersionUID = 3L;
    private int hitValue;
    private double health;
    private boolean alive;
    private int yPos;

    public Entity() {}

    public Entity(int hit_value, double health, int yPos) {}
    public int getHitValue() {return 0;}
    public double getHealth() {return 0;}
    public void setHealth(double health) {}
    public int getYPos() {return 0;}
    public Entity clone() {return new Entity();}
    public boolean isAlive() {return false;}
}
