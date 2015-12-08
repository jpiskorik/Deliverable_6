import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jacobpiskorik on 12/3/15.
 */
public class WinLoseTest
{
    @Test
    //test the FirstRollWin() method
    //if the user rolls a 7 the users total chips should
    //be more than they were before
    public final void FirstRollWinTest()
    {
        Craps.MoneyInChips =10;
        Craps.betsplaced[0]= 5;
        Craps.firstRollWin(7);
        assertEquals(Craps.MoneyInChips, 20.0);

    }


    @Test
    //test the FirstRollWin() method
    //if the user rolls a 2 the users bet should
    //be cleared from the table
    public final void FirstRollLoseTest()
    {
        Craps.MoneyInChips =10;
        Craps.betsplaced[0]= 5;
        Craps.firstRollWin(2);
        assertEquals(Craps.betsplaced[0], 0.0);
    }

    @Test
    //test the FirstRollWin() method
    //if the user rolls a 6 then a 1 should be returned
    //so the user can keep rolling
    public final void FirstRollKeepRollingTest()
    {
        int keepRolling = Craps.firstRollWin(6);
        assertEquals(keepRolling, 1);
    }

    @Test
    //test the CheckHardways() method
    //if two dice match each other than the
    //users chip total should be more than before
    public final void CheckHardwaysTest()
    {
        Craps.MoneyInChips =10;
        Craps.betsplaced[3]= 10;
        Die mockDie1 = mock(Die.class);
        Die mockDie2 = mock(Die.class);
        when(mockDie1.getDie()).thenReturn(3);
        when(mockDie2.getDie()).thenReturn(3);
        Craps.checkHardways(mockDie1, mockDie2);
        assertEquals(Craps.MoneyInChips, 70.0);
    }

    @Test
    //test the CheckField() method
    //if two dice total are not inbetween 4 and 8
    //users chip total should be more than before
    public final void CheckFieldWinTest()
    {
        Craps.MoneyInChips =5;
        Craps.betsplaced[2]= 5;
        Craps.checkField(3);
        assertEquals(Craps.MoneyInChips, 15.0);
    }

    @Test
    ////test the CheckField() method
    //if two dice total are inbetween 4 and 8
    //users bet should be cleared from the table
    public final void CheckFieldLoseTest()
    {
        Craps.betsplaced[2]= 5;
        Craps.checkField(8);
        assertEquals(Craps.betsplaced[0], 0.0);
    }

    @Test
    //Test the PaytheDoNotPassLine() method
    //if the user craps out then users total chips
    //should be more than before
    public final void WinDoNotPassLineTest()
    {
        Craps.MoneyInChips =15;
        Craps.betsplaced[1]= 15;
        Craps.PaytheDoNotPassLine();
        assertEquals(Craps.MoneyInChips, 45.0);
    }

    @Test
    //Test the DoNotPassLineLose() method
    //if the user wins then the do not pass line loses
    //so the users bet is cleared from the table
    public final void DoNotPassLineLose()
    {
        Craps.betsplaced[1]= 15;
        Craps.DoNotPassLineLose();
        assertEquals(Craps.betsplaced[1], 0.0);
    }


    @Test
    //test the NextRollStandings() method
    //if the user rolls a 7 then they crap out
    // and the users bet is taken from the table
    public final void NextRollSevenTest()
    {
        Craps.betsplaced[0]= 15;
        Craps.NextRollStandings(4, 7);
        assertEquals(Craps.betsplaced[0], 0.0);
    }

    @Test
    //test the NextRollStandings() method
    //if the user rolls a the number they got on the first roll
    // then they win and the users total chips are increased
    public final void NextRollHitsPointTest()
    {
        Craps.MoneyInChips =10;
        Craps.betsplaced[4]=0;
        Craps.betsplaced[0]= 5;
        Craps.NextRollStandings(4, 4);
        assertEquals(Craps.MoneyInChips, 20.0);
    }

    @Test
    //test the NextRollStandings() method
    //if the user rolls a 4 that they got on the first roll
    // then they win their bet plus 5 times their odds
    public final void NextRollFourOddsTest()
    {
        Craps.MoneyInChips =10;
        Craps.betsplaced[4]=5;
        Craps.betsplaced[0]= 5;
        Craps.NextRollStandings(4, 4);
        assertEquals(Craps.MoneyInChips, 40.0);
    }

    @Test
    //test the NextRollStandings() method
    //if the user rolls a 5 that they got on the first roll
    // then they win their bet plus 4 times their odds
    public final void NextRollFiveOddsTest()
    {
        Craps.MoneyInChips =10;
        Craps.betsplaced[4]=5;
        Craps.betsplaced[0]= 5;
        Craps.NextRollStandings(5, 5);
        assertEquals(Craps.MoneyInChips, 35.0);
    }

    @Test
    //test the NextRollStandings() method
    //if the user rolls a 6 that they got on the first roll
    // then they win their bet plus 3 times their odds
    public final void NextRollSixOddsTest()
    {
        Craps.MoneyInChips =10;
        Craps.betsplaced[4]=5;
        Craps.betsplaced[0]= 5;
        Craps.NextRollStandings(6, 6);
        assertEquals(Craps.MoneyInChips, 30.0);
    }
}
