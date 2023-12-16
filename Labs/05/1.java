/*Дадена е низа со N природни броеви. Треба да се сортира низата така што во првиот дел од низата ќе бидат подредени непарните броеви од неа во растечки редослед, а во вториот дел парните броеви во опаѓачки редослед. 
Во првиот ред од влезот даден е бројот на елементи во низата N, а во вториот ред се дадени броевите. На излез треба да се испечати сортираната низа.
Име на класата: OddEvenSort
----------------

Given a sequence of N natural numbers. It is necessary to sort the sequence so that in the first part of the sequence the odd numbers from it will be sorted in ascending order, and in the second part the even numbers will be sorted in descending order.
In the first line of the input, the number of elements in the array N is given, and in the second line, the numbers are given. The output should print the sorted array.
Class Name: OddEvenSort*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.swap;

public class OddEvenSort {

    static void sortedUp(int arr[], int n) {
        int i, j, temp;
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void sortedDown(int arr[], int n) {
        int i, j, temp;
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void oddEvenSort(int a[], int n) {
        int[] parni = new int[n];
        int[] neparni = new int[n];
        int brp = 0;
        int brn = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] % 2 != 0) {
                neparni[brn] = a[i];
                brn++;
            } else {
                parni[brp] = a[i];
                brp++;
            }
        }
        sortedUp(neparni, brn);
        sortedDown(parni, brp);

        int index = 0;
        for (int i = 0; i < brn; i++) {
            a[index] = neparni[i];
            index++;
        }
        for (int i = 0; i < brp; i++) {
            a[index] = parni[i];
            index++;
        }
    }


    public static void main(String[] args) throws IOException{
        int i;
        BufferedReader stdin = new BufferedReader( new InputStreamReader(System.in));
        String s = stdin.readLine();
        int n = Integer.parseInt(s);

        s = stdin.readLine();
        String [] pom = s.split(" ");
        int [] a = new int[n];
        for(i=0;i<n;i++)
            a[i]=Integer.parseInt(pom[i]);
        oddEvenSort(a,n);
        for(i=0;i<n-1;i++)
            System.out.print(a[i]+" ");
        System.out.print(a[i]);
    }
}
