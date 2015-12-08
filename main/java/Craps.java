import java.util.Random;
import java.util.Scanner;

/**
 * Created by jacobpiskorik on 11/30/15.
 * Craps game that implements more than the standard 7 or 11 win
 * also adds the hardways the field and do not pass line
 */
public class Craps
{

    //Array that holds where the bets are
    //0 = Pass Line
    //1 = Don't Pass
    //2 = the field
    //3 = Hardways
    //4 = odds
    public static double[] betsplaced= new double [5];
    public static double MoneyInChips = 0;
    public static Scanner scan = new Scanner(System.in);
    public static Die Die1 = new Die();
    public static Die Die2 = new Die();


    //Main method that simulates stepping up to the craps table
    //buying in, and playing until you either lose all your
    //money or cash out
    public static void main(String[] args)
    {
        int diceTotal;
        int selection =1;
        int odds_once =0;
        System.out.println("Please enter a buyin amount less than 1000 but greater than 10?");
        int buyin = scan.nextInt();

        //adding money to the table
        AddMoney(buyin);

        while (MoneyInChips !=0)
        {
            selection = PickBet(scan);
            if (selection == -1)
            {
                System.exit(0);
            }
            while (selection != 0)
            {
                if (selection!=5)
                {
                    System.out.println("Money in Chips = " + MoneyInChips);
                    System.out.println(" ");
                    System.out.println("Please enter an amount?");

                    int amt = scan.nextInt();
                    placeBet(selection - 1, amt);
                }
                selection = PickBet(scan);
            }
            diceTotal = rollDice(Die1, Die2);
            checkField(diceTotal);
            int keeprolling = firstRollWin(diceTotal);
            while (keeprolling ==1)
            {
                if (odds_once==0)
                {
                    addOdds();
                    odds_once =1;
                }
                checkHardways(Die1, Die2);
                int NewDiceTotal=reroll(Die1, Die2);
                keeprolling=NextRollStandings(diceTotal,NewDiceTotal);

            }
            odds_once=0;

        }
        System.out.println("Sorry you have ran out of money!");
        System.exit(0);

    }



    //putting money into the craps game
    //if not in between $10 and $1000 do not let user know
    public static void AddMoney(double bankroll)
    {
        if(bankroll< 1000 && bankroll > 10) {
            MoneyInChips = bankroll;
        }
        else if(bankroll< 10 && bankroll>0)
        {
            System.out.println("Please buy in with a larger amount");
            System.out.println("Table minimum is $10");
        }
        else if(bankroll > 1000)
        {
            System.out.println("Please buy in with a smaller amount");
            System.out.println("Table maximum is $1000");
        }
        else
        {
            System.out.println("Sorry this table only accepts U.S. currency");
        }


    }

    //options menu where the user picks what to bet on and
    // gives the user a description of what each bet is if picked
    public static int PickBet(Scanner sc)
    {
        System.out.println("Hello these are the fields you can bet on:");
        System.out.println("1) Pass Line");
        System.out.println("2) Do Not Pass Line");
        System.out.println("3) The field");
        System.out.println("4) The Hardways");
        System.out.println("5) Questions");
        System.out.println("Enter -1 to leave table");
        System.out.println("Enter 0 to begin");
        System.out.println("");
        System.out.println("Please select a field");

        int choice =sc.nextInt();
        while (choice>5)
        {
            System.out.println("Please enter a valid choice");
            choice =sc.nextInt();
        }

        if(choice ==5)
        {
            System.out.println("Pass Line:\t On the first roll a win is awarded with a total");
            System.out.println("\t\t of 7 or 11 and a lose on 2 3 or 12.  If one of ");
            System.out.println("\t\tthese is not rolled the shooter will continue");
            System.out.println("\t\tto roll until they roll their number again (win)");
            System.out.println("\t\tor they roll a 7(lose)");
            System.out.println("Do Not pass line:\t Exact opposite of pass line betting");
            System.out.println("\t\t that the shooter does not hit 7 or 11 on first roll");
            System.out.println("\t\t and rolls a 7 after their first roll");
            System.out.println("The Field:\t One time bet. Betting that the first roll will");
            System.out.println("\t\t not be a 4, 5, 6, 7, 8");
            System.out.println("The Hardways:\t One time bet. Betting that after the second roll");
            System.out.println("\t\t Die 1 and 2 will equal each other.  If user wins/loses on first");
            System.out.println("\t\t roll the bet will stay on the table");

        }
        return choice;

    }

    //function that places a bet
    //emulates putting your chips on the table
    public static void placeBet(int choice, double amount)
    {

        if (amount > MoneyInChips)
        {
            System.out.println("You do not have that much money");
            System.out.println("Please enter an amount?");
            amount =scan.nextInt();
        }
        MoneyInChips= MoneyInChips-amount;
        betsplaced[choice]=betsplaced[choice]+ amount;
    }


    // function that is performed if the user is playing the pass line
    //and does not win or lose on the first roll
    //lets the user add more money to bet that they will match their roll
    public static void addOdds()
    {
        if(betsplaced[0]!=0) {
            System.out.println("Money in Chips = " + MoneyInChips);
            System.out.println(" ");


            System.out.println("Add Odds?");
            System.out.println("1=yes");
            System.out.println("2=no");
            int p = scan.nextInt();
            if (p == 1)
            {
                System.out.println("How much?");
                int oddsamount = scan.nextInt();
                while (oddsamount > MoneyInChips) {
                    System.out.println("Sorry you don't have that much");
                    System.out.println("How much?");
                    oddsamount = scan.nextInt();
                }
                MoneyInChips = MoneyInChips - oddsamount;
                //adding money to odds
                betsplaced[4] = oddsamount;

            }
        }
    }

    //gets the total of the two dice
    public static int addDice(Die D1, Die D2)
    {
     return D1.getDie() + D2.getDie();
    }

    //rolling the dice for the first roll and printing out each die
    public static int rollDice(Die D1, Die D2)
    {
        System.out.println("You are the shooter. Good Luck");
        D1.reroll();
        D2.reroll();
        int firstroll = addDice(D1, D2);
        System.out.println("User rolls " + D1.getDie() + " and " + D2.getDie() + "= " + firstroll);
        return firstroll;
    }

    //rerolling the dice and printing the new dice
    public static int reroll(Die D1, Die D2)
    {
        D1.reroll();
        D2.reroll();
        int nextroll = addDice(D1, D2);
        System.out.println("User rolls " + D1.getDie() + " and " + D2.getDie() + "= " + nextroll);
        return nextroll;
    }

    //function that determines if the user won, lost, or is still rolling
    //win if a 7 or 11 is rolled
    //lose if a 2,3, or 12 is rolled (taxes)
    //keeps rolling if any other dice is rolled on the first roll
    public static int firstRollWin(int total)
    {
        if(total == 7 || total ==11)
        {
            if(betsplaced[0]!=0) {
                System.out.println("Out of the gate winner!");
                MoneyInChips = betsplaced[0] * 2 + MoneyInChips;
                betsplaced[0] = 0;
            }
            DoNotPassLineLose();
            return 0;

        }

        //before odds so the user gets his odds bet back
        else if(total ==2 || total==3 || total==12)
        {
            if(betsplaced[0]!=0)
            {
                System.out.println("TAXES. User loses");
                betsplaced[0] = 0;
            }
            PaytheDoNotPassLine();
            return 0;

        }
        else
        {
            return 1;
        }

    }

    //function that first checks to see if the user bets on the hardways
    //if the player does bet on the hardways then the user wins if Die 1
    // matches Die 2 and loses with anything else
    public static void checkHardways(Die D1, Die D2)
    {
        if(betsplaced[3]!= 0)
        {
            if (D1.getDie() == D2.getDie()) {
                System.out.println("Hardways hit!");
                System.out.println("Pays 6 times");
                MoneyInChips = betsplaced[3] * 6+ MoneyInChips;
                betsplaced[3] = 0;
            } else {
                System.out.println("Hardways didn't hit");
                betsplaced[3] = 0;
            }
        }
    }

    //function that first checks to see if the user bets on the field
    //if the total roll of die 1 and 2 is not between 4 and 8 the user wins
    //if else the user loses
    public static void checkField(int total)
    {
        if(betsplaced[2]!=0)
        {
            if (total < 4 || total > 8) {
                System.out.println("Field winner");
                System.out.println("Pays double");
                MoneyInChips = betsplaced[2] * 2 + MoneyInChips;
                betsplaced[2] = 0;
            } else {
                System.out.println("Sorry Field loses");
                betsplaced[2] = 0;
            }
        }
    }

    //function if the player bets on "do not pass line" and wins
    public static void PaytheDoNotPassLine()
    {
        if(betsplaced[1]!=0)
        {
            System.out.println("Craps! Pay the do not pass line!");
            MoneyInChips = betsplaced[1] * 2 + MoneyInChips;
            betsplaced[1]=0;
        }
    }

    //function if the player bets on "do not pass line" and loses
    public static void DoNotPassLineLose()
    {
        if(betsplaced[1]!=0)
        {
            System.out.println("Roller hits his point. Do not pass line loses");
            betsplaced[1]=0;
        }

    }

    //function that determines if the user wins or loses after the first roll
    //if a 7 is hit after the first roll the user loses if playing the pass line
    //if the user played odds and pass line and wins the winnings will vary
    //based on the number the user rolled
    public static int NextRollStandings(int point, int NextRoll)
    {
        double winnings;
        if(NextRoll ==7)
        {
            if(betsplaced[0]!=0) {
                System.out.println("Craps! User loses");
                betsplaced[0] = 0;
                return 0;
            }
            else if(betsplaced[1]!=0)
            {
                PaytheDoNotPassLine();
                return 0;
            }

        }
        //user wins both bet and odds
        else if(NextRoll == point)
        {
            System.out.println("User hits his point");
            DoNotPassLineLose();
            if (point == 4 || point == 10) {
                betsplaced[4]= betsplaced[4]* 4;
                betsplaced[0]= betsplaced[0]* 2;
                winnings= betsplaced[0] +betsplaced[4];
                MoneyInChips = winnings + MoneyInChips;
                betsplaced[4]= 0;
                betsplaced[0]= 0;
                return 0;
            } else if (point == 5 || point == 9) {
                betsplaced[4]= betsplaced[4]* 3;
                betsplaced[0]= betsplaced[0]* 2;
                winnings= betsplaced[0] +betsplaced[4];
                MoneyInChips = winnings + MoneyInChips;
                betsplaced[4]= 0;
                betsplaced[0]= 0;
                return 0;
            } else {
                betsplaced[4]= betsplaced[4]* 2;
                betsplaced[0]= betsplaced[0]* 2;
                winnings= betsplaced[0] +betsplaced[4];
                MoneyInChips = winnings + MoneyInChips;
                betsplaced[4]= 0;
                betsplaced[0]= 0;
                return 0;
            }
        }
        return 1;
    }


}
