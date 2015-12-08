import java.util.Random;

/**
 * Created by jacobpiskorik on 12/1/15.
 * class for the dice
 */

public class Die <T>
{
    Random r = new Random();
    public static int Low = 1;
    public static int High = 6;

    private int number;

    //if the user wants a certain dice number for testing
    public Die(int n)
    {
        number=n;
    }

    //if a random dice is defined a random number will be assigned
    public Die()
    {
        number =r.nextInt(High-Low) + Low;

    }

    //get the dice number
    public int getDie()
    {
        return number;
    }

    //setting the dice number to something else
    public int setDie(int n)
    {
        number = n;
        return number;
    }

    //rerolling the dice getting a new random number
    public void reroll()
    {
        number=r.nextInt(High-Low+1) + Low;

    }
}
