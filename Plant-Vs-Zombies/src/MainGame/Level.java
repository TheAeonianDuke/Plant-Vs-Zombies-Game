package MainGame;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable
{
    public static final long serialVersionUID = 1L;
    private long timer;
    private int currZombieCount;
    private int remainingZombieCount;
    private ArrayList<Integer> waveSize;
    private int numberOfWaves;
    private int remainingWaves;
    private boolean playerWon;
    private GameBoard lawn;

    public Level(int numWave, ArrayList<Integer> waveSize)
    {
        this.numberOfWaves = numWave;
        this.waveSize = waveSize;
    }

    public void addZombie(int y)
    {

    }

    public void addPlant(int x, int y) {}
    public void removePlant(int x,int y) {}
    protected int[] getZombieLocation() {return new int[]{1,2};}
    private void moveZombie() {}
    public void zombieWave() {}
    public int getCurrZombieCount() {return currZombieCount;}
    private int getRemainingZombieCount() {return remainingZombieCount;}
    public void activateLawnMower(int yPos) {}
    public void PlayerMove() {}
    private void incrementSunCounter() {}
    public void SunFromSky() {}
    public boolean getLevelWon() {return playerWon;}
}
