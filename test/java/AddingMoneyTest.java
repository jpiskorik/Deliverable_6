import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by jacobpiskorik on 11/30/15.
 */
public class AddingMoneyTest
{
    //Testing to see if the user enters a normal buyin amount
    @Test
    public final void BuyinNormalTest()
    {
        Craps.MoneyInChips=0;
        Craps.AddMoney(100.0);
        assertEquals(Craps.MoneyInChips, 100.0);

    }

    //test to see if a user enters less than the allowed amount
    @Test
    public final void BuyinLessThanMinimumTest()
    {
        Craps.MoneyInChips=0;
        Craps.AddMoney(9.9);
        assertEquals(Craps.MoneyInChips, 0.0);
    }

    //test to see if a user enters more than the allowed amount
    @Test
    public final void BuyinMoreThanMaximumTest()
    {
        Craps.MoneyInChips=0;
        Craps.AddMoney(10000.0);
        assertEquals(Craps.MoneyInChips, 0.0);
    }

    //test to see if a user enters a number not recognized
    @Test
    public final void BuyinSymbolTest()
    {
        Craps.MoneyInChips=0;
        Craps.AddMoney(-10.0);
        assertEquals(Craps.MoneyInChips, 0.0);
    }

}
