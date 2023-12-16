/*Во една мистична игра со карти, секоја карта е претставена со специфичен тип (int type), животна вредност (int health), и вредност за магична моќ (int magicPower). Значајноста на една карта се одредува како производ од нејзината животна вредност и вредноста за магична моќ.
Двајца волжебници решаваат да се предизвикаат на дуел, т.ш. во две еднострано поврзани листи се чуваат податоците за картите кои им се доделени. Во првата листа се чуваат картите доделени на првиот волжебник, а додека пак во втората се чуваат податоците за картите доделени на вториот волжебник. И двајцата волшебници на почеток имаат точно 8 карти.
На самиот почетокот на дуелот, правилата налагаат првиот волшебник да ја предаде својата најдобра карта на другиот волшебник, т.ш. кога вториот волшебник ќе ја земе картата истата треба да ја постави во средина на своите карти. Тоа значи дека потребно е од листата која ги чува картите на првиот волшебник да се отстрани (избрише) најдобрата карта и таа карта да се додаде на средина на листата каде што се чуваат картите на вториот волшебник. 
Влез: Во секој еден ред се дадени податоци за една карта, одделени со празно место, во формат type health magicPower. Притоа, прво се дадени картите на првиот волшебник, по што следуваат податоците за картите на вториот волшебник.
Излез: Во првиот ред type на сите карти на првиот пријател. Во вториот ред type на сите карти на вториот пријател.
Внимавајте:
1. Даден е целосниот код на структурата којашто треба да се користи. Дадена е и тест класата MysticalCardGame.java, со целосно имплементиран input и output. Потребно е да се менува само во рамки на void startDuel(SLL<Card> firstSorcererCards, SLL<Card> secondSorcererCards) функцијата.
2. Не смее да менувате во main функцијата !
/

In a mystical card game, each card embodies a creature with a specific type (int type), a health value (int health), and a magic power value (int magicPower). The significance of a card is determined by the product of its health and magic power values.
Two sorcerers decide to challenge each other in a duel, each with their own deck of cards. The decks are represented as two single linked lists. In the first list, we keep the cards of the first sorcerer, and in the second list, we keep the cards of the second sorcerer. Initially, both sorcerers have exactly 8 cards each.
At the very beginning of the duel, the rules require that the first sorcerer gives their best card to the other sorcerer, so that the second sorcerer takes that card and has to put it in the middle of their deck of cards. This means that we need to remove (delete) the best card from the list that keeps the cards of the first sorcerer and to add that card in the middle of the list that keeps the cards of the second sorcerer.
Input: In each row of input we have the data for one card, separated with space, in the format type health magicPower. First we have the cards of the first sorcerer, and after that follow the cards for the second sorcerer.
Output: In the first row the type of all the cards of the first frieend. In the second row the type of all the cards of the second friend.
Pay attention:
1. All the needed code for the structure that you need to use is given. The test class MysticalCardGame.java is also given, with completely implemented input and output. You only need to change the code of the void startDuel(SLL<Card> firstSorcererCards, SLL<Card> secondSorcererCards) method.
2. You must not change the main method !*/


import java.util.Scanner;

class Card {
    private int type;
    private int health;
    private int magicPower;

    public Card(int type, int health, int magicPower) {
        this.type = type;
        this.health = health;
        this.magicPower = magicPower;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMagicPower() {
        return magicPower;
    }

    public void setMagicPower(int magicPower) {
        this.magicPower = magicPower;
    }

    public int significance() {
        return health * magicPower;
    }


    @Override
    public String toString() {
        return String.valueOf(type);
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " " + tmp;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }
}

public class MysticalCardGame {

    //TODO: implement function
    public static void startDuel(SLL<Card> firstSorcererCards, SLL<Card> secondSorcererCards) {
    SLLNode<Card> tmp = firstSorcererCards.getFirst();
    SLLNode<Card> bestCard = firstSorcererCards.getFirst(); 
        while (tmp != null) {
            int significance = tmp.element.significance();
        if (significance > bestCard.element.significance()) {
            bestCard = tmp; 
        }
        tmp = tmp.succ;
    }
    tmp = secondSorcererCards.getFirst(); 
    for (int i = 0; i < 4; i++) {
        if (i == 3) {
            secondSorcererCards.insertAfter(bestCard.element, tmp);
            firstSorcererCards.delete(bestCard);
        }
        tmp = tmp.succ; 
    }
}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SLL<Card> firstSorcererCards = new SLL<Card>();
        SLL<Card> secondSorcererCards = new SLL<Card>();

        for (int i = 0; i < 8; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            firstSorcererCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        for (int i = 0; i < 8; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            secondSorcererCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        startDuel(firstSorcererCards, secondSorcererCards);
        System.out.println(firstSorcererCards);
        System.out.println(secondSorcererCards);
    }
}
