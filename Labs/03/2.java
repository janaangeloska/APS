/*Дадени се N зборови (N>=2). Најдете го бројот на парови од зборови коишто почнуваат на иста буква.
Влез: Во првиот ред од влезот е даден бројот на зборови (N). Потоа, во следните N редови влез, се дадени самите зборови.
Излез: На излез треба да се испечати бројот на парови од зборови коишто почнуваат на иста буква.
/

Given N words (N>=2), find the number of pairs of words that begin with the same letter.
Input: In the first line of input you are given the number of words (N). Then, in the following N lines of input, you have the the words themselves.
Output: On output you should print the number of pairs of words that start with the same letter.*/

import java.util.Scanner;

public class CountWordPairs {

    //TODO: implement function
    public static int countWordPairs(String [] words) {
           int counter=0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i+1; j < words.length; j++) {
                if(words[i].charAt(0)==words[j].charAt(0)){
                    counter++;
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();

        String words[] = new String[N];

        for(int i=0;i<N;i++) {
            words[i] = input.next();
        }

        System.out.println(countWordPairs(words));

    }
}
