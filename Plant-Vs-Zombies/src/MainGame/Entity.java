package MainGame;

import java.io.Serializable;

public class Entity implements Serializable,Cloneable
{
    public static final long serialVersionUID = 3L;
    private int hitValue;
    private double health;
    private boolean alive;
    protected int yPos;

    public Entity() {}

    public Entity(int hit_value, double health, int yPos)
    {
        this.hitValue = hit_value;
        this.health = health;
        this.yPos = yPos;
        this.alive = true;
    }
    public int getHitValue() {return hitValue;}
    public double getHealth() {return health;}
    public void setHealth(double health) {this.health = health;}
    public int getYPos() {return yPos;}
    public Entity clone()
    {
        Entity e  = null;
        try
        {
            e = (Entity)super.clone();
        }
        catch (CloneNotSupportedException c) {}

        return e;
    }
    public boolean isAlive() {return alive;}
}
