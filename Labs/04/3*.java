/*Дадена е низа од N природни броеви и еден број K. Нека броевите се означени од a0 до aN−1. Да ја дефинираме сумата од апсолутни разлики како abs(a1−a0)+abs(a2−a1)+…+abs(aN−1−aN−2). Да се изберат точно K броеви од низата, така што кога ќе се спојат во една низа, сумата од апсолутни разлики е максимална. Да се испечати оваа сума.
Влез: Во првата линија ви се дадени два броеви N (1≤N≤100) и K (1≤K≤100, K≤N). Во втората линија ви се дадени N позитивни природни броеви, секој од броевите е помал од 1,000.
Излез: Да се испечати бараната максималната сума од апсолутни разлики.
Забелешка: Броевите се земаат во оној редослед во кој што се дадени во првата низа. Не смее да се менува редоследот на броевите во новодобиената низа.
Делумно решение: Задачата се смета за делумно решена доколку се поминати 5 тест примери.
Име на класата (Java): SumOfAbsoluteDifferences.

=================================================================

Given is a sequence of N positive integers and a number K. Let the numbers be denoted by a0 to aN−1. Let us define the sum of absolute differences as abs(a1−a0)+abs(a2−a1)+…+abs(aN−1−aN−2). Choose exactly K numbers from the sequence so that when they are joined into a single sequence, the sum of absolute differences is maximal. Print this sum.
Input: In the first line the two numbers N (1≤N≤100) and K (1≤K≤100, K≤N) are given. In the second line you N positive integers are given, each of which is less than 1,000.
Output: Print the requested maximum sum of absolute differences.
Note: The numbers are taken in the order in which they are given in the first sequence. The order of the numbers in the newly obtained sequence must not be changed.
Partial Solution: If 5 test cases are correct.

Name of Java class: SumOfAbsoluteDifferences*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOfAbsoluteDifferences {
    
    static int solve(int numbers[], int N, int K) {
        // vasiot kod ovde
        // mozete da napisete i drugi funkcii dokolku vi se potrebni
        return 0;
    }
    
    public static void main(String[] args) throws Exception {
        int i,j,k;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int numbers[] = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for (i=0;i<N;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        int res = solve(numbers, N, K);
        System.out.println(res);
        
        br.close();
        
    }
    
}
