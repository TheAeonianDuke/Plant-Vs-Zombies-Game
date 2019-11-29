package MainGame;

public class SunPlant extends Plant
{
    private long sunGenTime;
    private final int sunValue = 10;
    public SunPlant() {}
    public SunPlant clone() {return new SunPlant();}
    public long getSunGenTime() {return 0;}
    public void generateSun() {}
}
