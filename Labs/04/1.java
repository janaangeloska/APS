/*Даден е некој аритметички израз. Аритметичкиот израз е во облик (A+B) или (A-B) каде што А и B истовремено се други аритметички изрази или цифри од 0-9. Потребно е да го евалуирате дадениот израз.
Име на класата (Java): ArithmeticExpression
=================================================================

An arithmetic expression is given. An arithmetic expression is of the form (A+B) or (A-B) where A and B are both other arithmetic expressions or digits from 0-9. You need to evaluate the given expression.
Name of Java class: ArithmeticExpression*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArithmeticExpression {
    
    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        // Vasiot kod tuka
        int zbir = 0, num = 0, znak = 1;
        // 1 -> pozitiven, -1 -> negativen
        for (int i = l; i <= r; i++) {
            char a = c[i];
            if (Character.isDigit(a)) {
                num = Character.getNumericValue(a);
            } else if (a == '+') {
                zbir += znak * num;
                znak = 1;
                num = 0;
            } else if (a == '-') {
                zbir += znak * num;
                znak = -1;
                num = 0;
            } else if (a == '(') {
               //se bara intervalcheto do soodvetnata zagrada za zatvaranje
                int otvorenInterval = 1;
                int j = i + 1;
                while (j <= r && otvorenInterval > 0) {
                    if (c[j] == '(') {
                        otvorenInterval++;
                    } else if (c[j] == ')') {
                        otvorenInterval--;
                    }
                    j++;
                }
                int subResult = presmetaj(c, i + 1, j - 1);
                //Se povikuva rekurzija za 
                //presmetka na podrezultat vo zagradite,pocnuvajkji od karakterot posle otvoranjeto na zagradata (i + 1)
                //zavrshno so karakterot pred zagradata za zatvaranje (j - 1) i zachuvaj ja vrednosta vo subResult
                zbir += znak * subResult;
                
                //indeksot se pomestuva do zagr na zatvaranje
                i = j - 1;
            }
        }

        //dodavannje na posl cifra vo izrazot
        zbir += znak * num;
        return zbir;
    }
    
    public static void main(String[] args) throws Exception {
        int i,j,k;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String expression = br.readLine();
        char exp[] = expression.toCharArray();
        
        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);
        
        br.close();
        
    }
    
}
