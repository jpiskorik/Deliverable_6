import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jacobpiskorik on 12/2/15.
 * testing the die class
 */
public class DieTest
{
    @Test
    //test that makes sure if a number is passed then
    //that is the number the die is
    public final void DieParametesTest()
    {
        Die TestDie = new Die(6);
        assertEquals(TestDie.getDie(), 6);
    }


    @Test
    //test that determines if a random number is chosen
    //between 1 and 6
    public final void RandomDieTest()
    {
        int test;
        Die TestDie = new Die();
        int diceNumber = TestDie.getDie();
        if (diceNumber<6 && diceNumber>0)
        {
            test =1;
        }
        else
        {
            test =0;
        }
        assertEquals(test, 1);
    }

    @Test
    //test that tests the getDie() method
    public final void getDiceTest()
    {
        Die TestDie = (Mockito.mock(Die.class));
        when(TestDie.getDie()).thenReturn(1);
        int value = TestDie.getDie();
        assertEquals(value, 1);

    }

    @Test
    //test that tests the setDie() method
    public final void setDiceTest()
    {
        Die TestDie = new Die();
        TestDie.setDie(11);
        assertEquals(TestDie.getDie(), 11);

    }

    @Test
    //test that tests the reroll() method
    //should give another random number
    public final void rerollTest()
    {
        Die TestDie = new Die();
        TestDie.reroll();
        int check;
        if(TestDie.getDie()!= 0)
        {
            check =1;
        }
        else{check =0;}
        assertEquals(check,1);
    }



}
