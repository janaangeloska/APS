/*Дадена е двојно поврзана листа од двојно поврзани листи. Да се најде сума на секоја од подлистите, а потоа производ на овие суми
Влез:
Број N кој кажува колку листи има
Број М кој кажува колку елементи има во секоја листа
Во следните М линии се податоците 1<=A<=1000 за секоја од листите 
Излез:
Еден број што е производот на сумите од листите.
Пример:
Влез:
3
4
1 2 3 4
2 3 4 5
6 7 8 9
Излез:
1400
/
A double linked list of double linked lists is given. Find the sum of each sub-list, and then the product of all those sums.
Input:
An integer N that tells us how many lists there are.
An integer M that tells us how many elements there are in each list.
Output:
The product of the sums of the sub-lists.
Example:
Input:
3
4
1 2 3 4
2 3 4 5
6 7 8 9
Output:
1400*/



import java.util.Scanner;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return "<-" + element.toString() + "->";
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

}

public class ListaOdListi {

    public static long findMagicNumber(DLL<DLL<Integer>> list) {
        //Vashiot kod tuka...
        DLLNode<DLL<Integer>> tmp=list.getFirst();
        int [] sums = new int [list.length()];
        for (int i = 0; i < list.length(); i++) {
            sums[i]=0;
            DLLNode<Integer>num=tmp.element.getFirst();
            for (int j = 0; j < tmp.element.length(); j++) {
                sums[i]+=num.element;
                num=num.succ;
            }
            tmp=tmp.succ;
        }
        long product=1;
        for (int i = 0; i < sums.length; i++) {
            product*=sums[i];
        }
        return product;
    }
    /*DLLNode<DLL<Integer>> tmp = list.getFirst();
        //pokazuvac kon prv node na listata
        int [] sums = new int[list.length()];
        //niza za da gi soberime vrednostite na podlistite
        for (int i = 0; i < list.length(); i++) {
            //ja iterira nadvoreshnata lista
            sums[i]=0;
            //se kreira pokazuvac na prviot el od prvata podlista
            DLLNode<Integer> num = tmp.element.getFirst();
            for (int j = 0; j < tmp.element.length(); j++) {
                //pochuvame loop za da gi svrtime elementite na tekovnata podlista
                //vo sumata i se dodavaat site clenovi na itata podlista
                sums[i]+=num.element;
                //numm pokazhuva kon sledbenikot
                num=num.succ;
            }
            //nareden jazol na podlista vo glavnata lista
            tmp=tmp.succ;
        }
        //na kraj pravime samo eden loop za da gi sobereme site vrednosti/mnoziteli vo listata
        //odnosno da ja izvrtime nizata sho ja kreiravme so vrednosti
        long product=1;
        for (int i = 0; i < sums.length; i++) {
            product*=sums[i];
        }
        return product;*/

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        DLL<DLL<Integer>> list = new DLL<DLL<Integer>>();
        for (int i = 0; i < n; i++) {
            DLL<Integer> tmp = new DLL<Integer>();
            for (int j = 0; j < m; j++) {
                tmp.insertLast(in.nextInt());
            }
            list.insertLast(tmp);
        }
        in.close();
        System.out.println(findMagicNumber(list));
    }

}
