package MainGame;

import java.io.Serializable;
import java.util.ArrayList;

public class GameBoard implements Serializable
{
    public static final long serialVersionUID = 2L;
    public int[] columns;
    public static final int X_Min = 0 ;
    public static final int X_Max = 8;
    public static final int Y_Min = 0;
    public static final int Y_Max = 4;
    private boolean[] LawnMower;
    private int SunCounter;
    private ArrayList<Zombie> Zombies;
    private ArrayList <Plant> Plants;

    public GameBoard()
    {

    }

    public void PaintBoard() {}
    public boolean isLawnMowerActivated(int yPos) {return false;}
    public int getSunCounter() {return 0;}
    public void setLawnMower(int yPos) {}
}
