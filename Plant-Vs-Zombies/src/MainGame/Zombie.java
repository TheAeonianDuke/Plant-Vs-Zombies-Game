package MainGame;

public class Zombie extends Entity
{
    private double moveSpeed;
    private double currXPos;
    private int GameBoardEnd;

    public Zombie() {}
    public double getMoveSpeed() {return 0;}
    public int attack() {return 0;}
    public double getCurrXPos() {return 0;}
    public void setCurrXPos(double currXPos) {}
    public Zombie clone() {return new Zombie();}
}
