package MainGame;

public class PlantDiedException extends RuntimeException
{
    private PlantDiedException(String message)
    {
        super(message);
    }
}
