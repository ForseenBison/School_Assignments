/*
 * Mason Andrew
 * CSCI 405, Program 1
 *
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        //This first section of the program is just for reading the file and obtaining the array
        File input = new File(args[0]);
        Scanner scan = new Scanner(input);

        ArrayList<Integer> inputList = new ArrayList<Integer>();

        while(scan.hasNextLine())
        {
            inputList.add(scan.nextInt());
        }

        countArray(inputList);
    }

    private static void countArray(ArrayList<Integer> inputList)
    {
        int start = 0;
        int end = 2;
        int currentNum = inputList.get(0);

        ArrayList<Integer> nums = new ArrayList<Integer>();
        ArrayList<Integer> numCount = new ArrayList<Integer>();

        while(end+2!=inputList.size())
        {
            System.out.println("Looping::"+end+"::"+inputList.size());
            while (currentNum == inputList.get(end))
            {
                //System.out.println("End moving");
                end = end + 2;
                if(inputList.size()>end)
                {
                    break;
                }
            }

            if (inputList.get(end - 1) == currentNum)
            {
                //System.out.println("End-1");
                nums.add(currentNum);
                numCount.add(end - start);
                start = end;
            }
            else
            {
                //System.out.println("End");
                nums.add(currentNum);
                numCount.add((end - 1) - start);
                start = end - 1;
            }
            currentNum=inputList.get(start);
        }

        for(int i =0; i< nums.size(); i++)
        {
            System.out.println(nums.get(i) + " appears " + numCount.get(i) + " times");
        }
    }
}