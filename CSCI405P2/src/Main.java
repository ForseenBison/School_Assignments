/*
 * Mason Andrew
 * CSCI 405, Program 2
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
        public static void main(String[] args) throws FileNotFoundException
        {
            //This first section of the program is just for reading the file and obtaining the array
            File input = new File(args[0]);
            Scanner scan = new Scanner(input);

            String x = scan.nextLine();
            String y = scan.nextLine();
            String z = scan.nextLine();

            int i = x.length();
            int j = y.length();
            int k = z.length();

            //x y and z are all strings being compared, i j and k are the sizes of those strings
            int [][][] outputArray = longestCommonSubsequence3Strings(x,y,z,i,j,k);

            String LCS = "";

            //This while loop goes though and builds the string using the 3d array
            while(i > 0 && j > 0 && k > 0)
            {
                //System.out.println("#"+x.charAt(i-1)+"#"+y.charAt(j-1)+"#"+z.charAt(k-1)+"#");
                if(x.charAt(i-1) == y.charAt(j-1) && x.charAt(i-1) == z.charAt(k-1))
                {
                    //System.out.println("+"+x.charAt(i-1)+"+");
                    LCS= String.valueOf(x.charAt(i-1)) + LCS;
                    i--;
                    j--;
                    k--;
                }
                else if(outputArray[i-1][j][k] > outputArray[i][j-1][k] && outputArray[i-1][j][k] > outputArray[i][j][k-1])
                {
                    i--;
                    //System.out.println("i--");
                }
                else if(outputArray[i][j-1][k] > outputArray[i-1][j][k] && outputArray[i][j-1][k] > outputArray[i][j][k-1])
                {
                    j--;
                    //System.out.println("j--");
                }
                else
                {
                    k--;
                    //System.out.println("k--");
                }
            }

            System.out.println(LCS);
        }

        //This method builds a 3d array based on an altered version of the longest common subsequence algorithm that we made during class
        private static int[][][] longestCommonSubsequence3Strings(String x, String y, String z, int xLen, int yLen, int zLen)
        {
            int [][][] array3d = new int[xLen+1][yLen+1][zLen+1];

            for(int i=0; i <= xLen; i++)
            {
                for(int j=0; j<=yLen; j++)
                {
                    for(int k=0; k<=zLen; k++)
                    {
                        if(i==0||j==0||k==0)
                        {
                            array3d[i][j][k]=0;
                        }
                        else if(x.charAt(i-1)==y.charAt(j-1) && x.charAt(i-1)==z.charAt(k-1))
                        {
                            array3d[i][j][k] = array3d[i-1][j-1][k-1]+1;
                        }
                        else
                        {
                            array3d[i][j][k]=Math.max(Math.max(array3d[i-1][j][k], array3d[i][j-1][k]), array3d[i][j][k-1]);
                        }
                    }
                }
            }
            return array3d;
        }
}
