package MainGame;

public class ExplosivePlant extends Plant
{
    private boolean isExploded;
    public ExplosivePlant() {}
    public int getDamage() {return 0;}
    public boolean isExploded() {return isExploded;}
    @Override
    public void plantAction() {}
    public ExplosivePlant clone() {return (ExplosivePlant)super.clone();}
}
