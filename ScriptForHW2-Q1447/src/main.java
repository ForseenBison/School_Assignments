/**
 * Created by Mason Andrew on 7/11/19.
 */
public class main
{
    static int count = 0;
    static int numHistories = 0;
    static int numCorrect =0;

    public static void main(String[] args)
    {
        String [] original = new String[] {"a1", "a2", "a3", "b1", "b2", "c1", "c2", "c3", "d1", "d2"}; //Initial array of strings

        printAllRecursive(10, original);
        System.out.println("Total Permutations: "+count);
        System.out.println("Total Histories: "+numHistories);
        System.out.println("Total numCorrect: "+numCorrect);
        double percentage = (double)numCorrect/(double)numHistories;
        percentage = 1-percentage;
        System.out.println("Percentage: "+ percentage);
    }

    public static void printAllRecursive(int n, String[] elements)
    {
        if(n == 1) //Base case end of the recusion that means elements is a custom permutation
        {
            count++;
            if(check(elements, "a1", "a2") && //This checks to make sure the the permutation is a valid history
                    check(elements, "a2", "a3") &&
                    check(elements, "b1", "b2") &&
                    check(elements, "c1", "c2") &&
                    check(elements, "c2", "c3") &&
                    check(elements, "d1", "d2"))
            {
                numHistories++;

                if(check(elements, "a1", "c2") && //This check makes sure that the history is valid given the other constraints
                        check(elements, "c3", "a3") &&
                        check(elements, "c3", "b2") &&
                        check(elements, "d2", "c3"))
                {
                    numCorrect++;
                }

            }
        }
        else //Everything below here sets up elements to be a new permutation
        {
            for(int i = 0; i < n-1; i++)
            {
                printAllRecursive(n - 1, elements);
                if(n % 2 == 0)
                {
                    swap(elements, i, n-1);
                }
                else
                {
                    swap(elements, 0, n-1);
                }
            }
            printAllRecursive(n - 1, elements);
        }
    }

    private static void swap(String[] input, int a, int b) //Swap function that allows the above to create permutations
    {
        String tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

    private static boolean check(String[] input, String first, String second) //Function allows quick check to see if the strings are in the correct order
    {
        int i = 0;
        while (input[i] != first)
        {
            i++;
        }

        int j = 0;
        while (input[j] != second)
        {
            j++;
        }

        if (i < j) {
            return true;
        }
        return false;
    }
}
