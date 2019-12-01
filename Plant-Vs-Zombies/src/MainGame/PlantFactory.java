package MainGame;

public class PlantFactory
{
    public Plants createPlant(String id)
    {
        if(id.equals("peashooter_btn"))
        {
            return new PeaShooter(300,100,50,false,null,5);
        }

        else if(id.equals("sunflower_btn"))
        {
            return new SunFlower(300,50,0,false,null,5);
        }

        else if(id.equals("walnut_btn"))
        {
            return new Wallnut(600,50,0,false,null,5);
        }

        else if(id.equals("potato_btn"))
        {
            return new PotatoMine(200,25,200,false,null,5);
        }

        else
            return new Cherry_Bomb(200,150,200,false,null,5);
    }
}
