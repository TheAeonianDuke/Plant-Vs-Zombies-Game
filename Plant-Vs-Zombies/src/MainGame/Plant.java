package MainGame;

public class Plant extends Entity
{
    private int buyValue;
    private int xPos;
    private long genTime;
    public Plant() {}
    public Plant(int buy_val ,int xPos,long gemTime) {}
    public int getBuyValue() {return 0;}
    public int getXPos() {return 0;}
    public void setXPos(int xPos) {}
    public void setYPos(int yPos) {}
    public Plant clone() {return new Plant();}
    public boolean equals() {return false;}
    public void plantAction() {}
}
