/*Да се напише алгоритам кој ќе врши евалуација на израз во постфикс нотација.
На влез се чита низа од знаци за изразот (стринг), а на излез се печати вредноста на изразот по евалуацијата.
Име на класата (Java): PostFixEvaluation

----------

Write an algorithm that will evaluate an expression in postfix notation.
A sequence of characters for the expression (string) is read at the input, and the value of the expression after evaluation is printed at the output.
Class Name (Java): PostFixEvaluation>*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear();
    // Go prazni stekot.

    public void push(E x);
    // Go dodava x na vrvot na stekot.

    public E pop();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty() {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek() {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth - 1];
    }


    public void clear() {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }


    public void push(E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop() {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class PostFixEvaluation {
    static int operation(char operator, int operand1, int operand2) {
        if (operator == '+') {
            return operand1 + operand2;
        } else if (operator == '-') {
            return operand2 - operand1;
        } else if (operator == '*') {
            return operand1 * operand2;
        } else if (operator == '/') {
            return operand2 / operand1;
        } else {
            return 0;
        }
    }

    static int evaluatePostfix(char[] izraz, int n) {
        ArrayStack<Integer> stack = new ArrayStack<>(n);
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(izraz[i])) {
                //za povekje cifreni brojka pa brojka 
                if (Character.isDigit(izraz[i + 1])) {
                    num = num * 10 + Character.getNumericValue(izraz[i]);
                }
                //za ednocifreni i za tie shto zavrshuvaat kako brojka
                else if(!Character.isDigit(izraz[i + 1])) {
                        num = num * 10 + Character.getNumericValue(izraz[i]);
                        stack.push(num);
                        num = 0;
                    }
                }
                if (izraz[i] == '+' || izraz[i] == '-' || izraz[i] == '*' || izraz[i] == '/') {
                    int operand1 = stack.pop();
                    int operand2 = stack.pop();
                    int result = operation(izraz[i], operand1, operand2);
                    stack.push(result);
                }
            }
            return stack.pop();
    }


        public static void main(String[] args) throws Exception {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String expression = br.readLine();
            char exp[] = expression.toCharArray();

            int rez = evaluatePostfix(exp, exp.length);
            System.out.println(rez);

            br.close();

        }

    }
