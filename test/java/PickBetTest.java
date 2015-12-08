import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jacobpiskorik on 11/30/15.
 */
public class PickBetTest
{


    @Test
    //test that tests the place bet method
    //makes sure that betsplaced[] array is being updated after the
    //user enters a correct buyin amount
    public final void PlaceBetTest()
    {
        Craps.MoneyInChips=100;
        Craps.placeBet(2, 10.0);
        assertEquals(10.0, Craps.betsplaced[2]);
    }


    @Test
    //test that tests the add dice method
    //makes sure the total is correctly calculated
    public final void AddDiceTest()
    {
        Die mockDie1 = mock(Die.class);
        Die mockDie2 = mock(Die.class);
        when(mockDie1.getDie()).thenReturn(3);
        when(mockDie2.getDie()).thenReturn(3);
        int total =Craps.addDice(mockDie1, mockDie2);
        assertEquals(total, 6);

    }

    @Test
    //test that tests the roll dice method
    //if the rollDice method is called the total
    //should be updated accordingly
    public final void RollDiceTest()
    {
        Die mockDie1 = mock(Die.class);
        Die mockDie2 = mock(Die.class);
        when(mockDie1.getDie()).thenReturn(5);
        when(mockDie2.getDie()).thenReturn(5);
        int total = Craps.rollDice(mockDie1, mockDie2);
        assertEquals(total, 10);
    }

    @Test
    //test that tests the reroll dice method
    //if the reroll method is called the total
    //should be updated accordingly
    public final void ReRollTest()
    {
        Die mockDie1 = mock(Die.class);
        Die mockDie2 = mock(Die.class);
        when(mockDie1.getDie()).thenReturn(6);
        when(mockDie2.getDie()).thenReturn(6);
        int total = Craps.reroll(mockDie1, mockDie2);
        assertEquals(total, 12);
    }

}
