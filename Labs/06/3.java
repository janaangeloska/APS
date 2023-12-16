/*Луѓето доаѓаат наутро во МВР за да извадат еден или повеќе документи.
Документите може да бидат:
1. Лична карта
2. Пасош
3. Возачка дозвола
Кога се отвора шалтерот прво се услужуваат луѓето кои чекаат за лична карта, па потоа оние за пасош и на крај оние за возачка дозвола.
Секој човек кога ќе дојде си застанува во редицата за соодветната исправа која ја вади (т.е. или во редицата за лични карти или во редицата за пасоши или во редицата за возачки дозволи). Доколку еден човек има повеќе документи за вадење прво вади лична карта, па пасош и на крај возачка. Така ако еден човек треба да вади и лична карта и возачка дозвола прво застанува во редицата за лични карти и откако ќе заврши таму оди на крајот на редицата за возачки дозволи.
Влез: Првиот ред означува колку луѓе вкупно дошле во МВР. Потоа за секој човек се внесуваат четири реда, во првиот е името и презимето на човекот, а во останатите три реда се кажува кој документ соодветно (лична карта, пасош и возачка) треба да се земе, притоа 1 значи дека треба да се земе тој документ, 0 значи дека не треба да се земе.
На пример:
Aleksandar Aleksandrovski
1
0
1
означува дека Александар Александровски ќе вади и лична карта и возачка дозвола, но нема да вади пасош.
Излез: Ги печати имињата на луѓето по редоследот по кој завршуваат со вадење на документи.
----------------

People come to the Ministry of the Interior in the morning to retrieve one or more documents.
The documents can be:
1. Identity card
2. Passport
3. Driver's license
When the counter opens, people waiting for ID cards are served first, then those for passports, and finally those for driver's licenses.
When each person arrives, they stand in the queue for the appropriate document that they need (ie either in the queue for ID cards or in the queue for passports or in the queue for driver's licenses). If a person needs several documents, first they get an ID card, then a passport and finally a driver's license. Thus, if a person needs to get both an ID card and a driver's license, he first stands in the queue for ID cards, and after he finishes there, he goes to the end of the queue for driver's licenses.
Input: The first row indicates how many people came to the Ministry of Interior in total. Then four lines are entered for each person, in the first is the name and surname of the person, and in the remaining three lines it is said which document (identity card, passport and driver's license) is needed, where 1 means that the document is needed, 0 means it is not.
For example:
Aleksandar Aleksandrovski
1
0
1
indicates that Aleksandar Aleksandrovski will get an ID card and driver's license, but will not get a passport.
Output: Prints the names of the people in the order in which they finish extracting documents.*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

interface Queue<E> {
    public boolean isEmpty();

    public int size();

    public E peek();

    public void clear();

    public void enqueue(E el);

    public E dequeue();
}

class ArrayQueue<E> implements Queue<E> {
    E[] elements;
    int length, front, rear;

    @SuppressWarnings("unchecked")
    ArrayQueue(int maxLength) {
        elements = (E[]) new Object[maxLength];
        clear();
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public E peek() {
        if (length > 0)
            return elements[front];
        else
            throw new NoSuchElementException();
    }

    @Override
    public void clear() {
        length = 0;
        front = rear = 0;
    }

    @Override
    public void enqueue(E el) {
        if (length == elements.length)
            throw new NoSuchElementException();
        elements[rear++] = el;
        if (rear == elements.length)
            rear = 0;
        length++;
    }

    @Override
    public E dequeue() {
        if (length > 0) {
            E frontmost = elements[front];
            elements[front++] = null;
            if (front == elements.length)
                front = 0;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}

class Gragjanin {
    String name;
    int id;
    int passport;
    int driversLicense;

    public Gragjanin(String name, int id, int passport, int driversLicense) {
        this.name = name;
        this.id = id;
        this.passport = passport;
        this.driversLicense = driversLicense;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public int getPassport() {
        return passport;
    }

    public int getDriversLicense() {
        return driversLicense;
    }

}

public class MVR {

    public static void main(String[] args) {

        Scanner br = new Scanner(System.in);
        int N = Integer.parseInt(br.nextLine());
        ArrayQueue<Gragjanin> idQueue = new ArrayQueue<>(N);
        ArrayQueue<Gragjanin> passportQueue = new ArrayQueue<>(N);
        ArrayQueue<Gragjanin> driversLicenseQueue = new ArrayQueue<>(N);
        for (int i = 1; i <= N; i++) {
            String imePrezime = br.nextLine();
            int lKarta = Integer.parseInt(br.nextLine());
            int pasos = Integer.parseInt(br.nextLine());
            int vozacka = Integer.parseInt(br.nextLine());
            Gragjanin covek = new Gragjanin(imePrezime, lKarta, pasos, vozacka);
            enqueueCitizen(covek, idQueue, passportQueue, driversLicenseQueue);
        }

        while (!idQueue.isEmpty() || !passportQueue.isEmpty() || !driversLicenseQueue.isEmpty()) {
            processQueues(idQueue, passportQueue, driversLicenseQueue);

        }
    }

        private static void enqueueCitizen(Gragjanin covek, ArrayQueue<Gragjanin> idQueue,
                ArrayQueue<Gragjanin> passportQueue, ArrayQueue<Gragjanin> driversLicenseQueue) {
            if(covek.getId() == 1)
                idQueue.enqueue(covek);
            else if(covek.getPassport() == 1)
                passportQueue.enqueue(covek);
            else if(covek.getDriversLicense() == 1)
                driversLicenseQueue.enqueue(covek);
        }

        private static void processQueues(ArrayQueue<Gragjanin> idQueue, ArrayQueue<Gragjanin> passportQueue,
                ArrayQueue<Gragjanin> driversLicenseQueue) {
        if (!idQueue.isEmpty()) {
            Gragjanin person = idQueue.dequeue();
            if (person.getPassport() == 1) {
                passportQueue.enqueue(person);
            } else if (person.getDriversLicense() == 1) {
                driversLicenseQueue.enqueue(person);
            } else {
                System.out.println(person.getName());
            }
        } else if (!passportQueue.isEmpty()) {
            Gragjanin person = passportQueue.dequeue();
            if (person.getDriversLicense() == 1) {
                driversLicenseQueue.enqueue(person);
            } else {
                System.out.println(person.getName());
            }
        } else {
            System.out.println(driversLicenseQueue.dequeue().getName());
        }
    }
}
