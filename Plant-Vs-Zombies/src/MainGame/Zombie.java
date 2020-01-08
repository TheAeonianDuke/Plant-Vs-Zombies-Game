package MainGame;

public class Zombie extends Entity
{
    private double moveSpeed;
    private double currXPos;
    private int GameBoardEnd;

    public Zombie() {}
    public double getMoveSpeed() {return moveSpeed;}
    public int attack() {return 0;}
    public double getCurrXPos() {return currXPos;}
    public void setCurrXPos(double currXPos)
    {
        this.currXPos = currXPos;
    }
    public Zombie clone()
    {
        return (Zombie)super.clone();
    }
}
