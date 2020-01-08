package MainGame;

public class ShooterPlant extends Plant
{
    private long rateOfFire;
    public ShooterPlant() {}
    @Override
    public void plantAction() {}
    public long getRateOfFire() {return rateOfFire;}
    public ShooterPlant clone()
    {
        return (ShooterPlant)super.clone();
    }
}
