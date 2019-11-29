package MainGame;

public abstract class Plant extends Entity
{
    private int buyValue;
    private int xPos;
    private long genTime;
    public Plant() {}
    public Plant(int buy_val ,int xPos,long genTime)
    {
        buyValue = buy_val;
        this.xPos = xPos;
        this.genTime = genTime;
    }
    public int getBuyValue() {return buyValue;}
    public int getXPos() {return xPos;}
    public void setXPos(int xPos) {this.xPos = xPos;}
    public void setYPos(int yPos) {this.yPos = yPos;}
    public Plant clone()
    {
        return (Plant)super.clone();
    }
    public boolean equals() {return false;}
    public abstract void plantAction();
}
