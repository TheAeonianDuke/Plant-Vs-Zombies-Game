package MainGame;

import java.util.Random;

public class RandomGenerator
{
    private static Random random;

    private RandomGenerator()
    {
    }

    public static Random getRandom()
    {
        if(random == null)
            random = new Random();
        return random;
    }

}
