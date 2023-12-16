/*Дадена е низа со N природни броеви. Треба да се сортира низата со помош на таканареченото shaker (cocktail) сортирање. Ова сортирање е варијација на сортирањето со меурчиња (bubble sort) со тоа што во секоја итерација низата се изминува два пати. Во првото поминување најмалиот елемент се поместува на почетокот на низата, а при второто најголемиот елемент се поместува на крајот. 
Во првиот ред од влезот даден е бројот на елементи во низата N, а во вториот ред се дадени броевите. На излез треба да се испечати низата по секое изминување во посебен ред.
Име на класата: ShakerSort
-------------------

Given a sequence of N natural numbers. The array should be sorted using the so-called shaker (cocktail) sort. This sort is a variation of the bubble sort in that in each iteration the array is traversed twice. In the first pass, the smallest element is moved to the beginning of the array, and in the second pass, the largest element is moved to the end.
In the first line of the input, the number of elements in the array N is given, and in the second line, the numbers are given. The output should print the string after each pass in a separate line.
Class Name: ShakerSort*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ShakerSort {
  
    static void shakerSort(int a[], int n) {
        int i = 0,j;
        boolean flag;
        while (i < (n + 1) / 2) {
            flag = false;
            //levo->desno
            j = n - 1;
            while (j > i) {
                if (a[j] < a[j - 1]) {
                    int temporary = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temporary;
                    flag = true;
                }
                j--;
            }
            for (j = 0; j < n-1; j++) {
                System.out.print(a[j] + " ");
            }
            //za da nema extra prazno mesto plus nov red posle posledniot el
            System.out.println(a[n - 1]);
           //desno->levo
            j = i;
            while (j < n - 1) {
                if (a[j] > a[j + 1]) {
                    int temporary = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temporary;
                    flag = true;
                }
                j++;
            }

            for (j = 0; j < n-1; j++) {
                System.out.print(a[j] + " ");
            }
            System.out.println(a[n - 1]);

            if (!flag)
                break;

            i++;
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
        shakerSort(a,n);
    }
}
