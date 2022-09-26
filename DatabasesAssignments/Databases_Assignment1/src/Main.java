import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args) throws FileNotFoundException
    {
        File input = new File("Stockmarket-1990-2015.txt");
        Scanner sc = new Scanner(input);

        Date current;
        Date yesterday;
        int numCrazyDays;
        int numSplitDays;

        Date[] crazyDays = new Date[100];
        numCrazyDays=0;

        Date[] splitDays = new Date[100];
        numSplitDays=0;

        String[] companies = new String[100];
        int companyCtr=0;

        while(sc.hasNext())
        {
            yesterday = new Date(sc.next(), sc.next(), Double.parseDouble(sc.next()), Double.parseDouble(sc.next()),
                    Double.parseDouble(sc.next()), Double.parseDouble(sc.next()), Long.parseLong(sc.next()),
                    Double.parseDouble(sc.next()));

            //System.out.println("Processing " + yesterday.getSymbol());
            //System.out.println("======================");

            companies[companyCtr]=yesterday.getSymbol();
            companyCtr++;

            if (yesterday.crazyDay() >= 15)
            {
                crazyDays[numCrazyDays]= new Date(yesterday);
                numCrazyDays++;
            }

            current = new Date(sc.next(), sc.next(), Double.parseDouble(sc.next()), Double.parseDouble(sc.next()),
                    Double.parseDouble(sc.next()), Double.parseDouble(sc.next()), Long.parseLong(sc.next()),
                    Double.parseDouble(sc.next()));

            if(current.crazyDay()>=.15)
            {
                crazyDays[numCrazyDays]=new Date(current);
                numCrazyDays++;
            }

            int splitCheck=yesterday.splitcheck(current);
            //System.out.println(splitCheck);

            if(splitCheck>1)
            {
                splitDays[numSplitDays]=new Date(current);
                numSplitDays++;
            }


            while (current.getSymbol().equals(yesterday.getSymbol()))
            {
                if(sc.hasNext())
                {
                    yesterday = current;

                    current = new Date(sc.next(), sc.next(), Double.parseDouble(sc.next()), Double.parseDouble(sc.next()),
                            Double.parseDouble(sc.next()), Double.parseDouble(sc.next()), Long.parseLong(sc.next()),
                            Double.parseDouble(sc.next()));

                    if (current.crazyDay() >= .15)
                    {
                        crazyDays[numCrazyDays] = new Date(current);
                        numCrazyDays++;
                    }

                    splitCheck = current.splitcheck(yesterday);
                    //System.out.println(splitCheck);


                    if (splitCheck >1)
                    {
                        splitDays[numSplitDays] = new Date(current);
                        numSplitDays++;
                    }
                }
                else
                {
                    current.end();
                }
            }

            crazyDays[numCrazyDays]=null;
            splitDays[numSplitDays]=null;

            numCrazyDays++;
            numSplitDays++;
        }


        //Print data
        boolean loopcheck=true;
        int crazyCounter=0;
        int splitCounter=0;
        companyCtr=0;
        while(loopcheck)
        {
            int counter=0;
            String company = companies[companyCtr];
            companyCtr++;
            Date craziest = crazyDays[crazyCounter];

            System.out.println("Processing "+company);
            System.out.println("=======================");

            while(crazyDays[crazyCounter]!=null)
            {
                System.out.printf("Crazy Day: %10s %.2f %n", crazyDays[crazyCounter].getDate(), crazyDays[crazyCounter].crazyDay()*100);
                if(craziest.crazyDay()<crazyDays[crazyCounter].crazyDay())
                {
                    craziest=crazyDays[crazyCounter];
                }
                crazyCounter++;
                counter++;
            }

            System.out.println("Total Number of Crazy Days: "+counter);
            if(counter>0)
            {
                System.out.printf("The Craziest Day: %s %.2f", craziest.getDate(), craziest.crazyDay() * 100);
            }

            System.out.println("\n");
            crazyCounter++;
            counter=0;

            while(splitDays[splitCounter]!=null)
            {
                if(splitDays[splitCounter].getSplit()==2)
                {
                    System.out.print("2:1");
                }
                else if(splitDays[splitCounter].getSplit()==3)
                {
                    System.out.print("3:1");
                }
                else if(splitDays[splitCounter].getSplit()==4)
                {
                    System.out.print("3:2");
                }

                System.out.printf(" split on %s %.2f --> %.2f%n", splitDays[splitCounter].getDate(), splitDays[splitCounter].getClose(), splitDays[splitCounter].gettOpen());
                splitCounter++;
                counter++;
            }

            splitCounter++;

            System.out.println("Total number of splits: "+counter+"\n");

            //System.out.println(companies.length+"::"+companyCtr);
            if(companies[companyCtr]==null)
            {
                loopcheck=false;
            }
        }
        /*
        for( Date x: crazyDays)
        {
            if(x==null)
            {
                System.out.println("null");
            }
            else
            {
                System.out.println(x.getSymbol()+"::"+x.getDate());
            }
        }

         */
    }
}