package MainGame;

public class ExplosivePlant extends Plant
{
    private boolean isExploded;
    public ExplosivePlant() {}
    public int getDamage() {return 0;}
    public boolean isExploded() {return false;}
    public void explode() {}
    public ExplosivePlant clone() {return (ExplosivePlant)super.clone();}
}
