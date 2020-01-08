package MainGame;

public class SunPlant extends Plant
{
    private long sunGenTime;
    private final int sunValue = 10;
    public SunPlant() {}
    public SunPlant clone()
    {
        return  (SunPlant)super.clone();
    }
    public long getSunGenTime() {return sunGenTime;}
    @Override
    public void plantAction() {}
}
