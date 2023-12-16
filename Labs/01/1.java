/*За дадена низа од случајни броеви кои се внесуваат од стандарден влез, да се направи преместување на сите нули на почеток на низата. На стандарден излез да се испечати трансформираната низа.
/
For a given array of random numbers given from standard input, perform a shift of all zeros at the beginning of the sequence. Print the transformed array to standard output.*/

import java.util.Scanner;

public class PushZero
{
    static void pushZerosToBeginning(int arr[], int n)
    {
        int[] arr2 =new int[n];
        int counter=0;
        for (int i = 0; i < n; i++) {
            if(arr[i]==0){
                System.out.print(arr[i] + " ");
            }else{
                arr2[counter]=arr[i];
                counter++;
            }
        }
        for (int i = 0; i < counter; i++) {
            System.out.print(arr2[i] + " ");

        }
    }

    public static void main (String[] args)
    {
        Scanner input= new Scanner(System.in);
        int n=input.nextInt();
        int[] arr =new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=input.nextInt();
        }
        System.out.println("Transformiranata niza e:");
        pushZerosToBeginning(arr,n);
    }
}
