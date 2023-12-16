/*Треба да изработите автоматски преведувач за зборови од анлиски јазик на македонски.
Влезот се состои од парови од зборови разделени со празно место. Така прво е даден зборот на македонски, па има едно празно место, па преводот на зборот на англиски јазик
Потоа на влез се добиваат странски зборови (секој во посебен ред).
За излез треба да се преведат овие зборови. Доколку не е познат преводот на зборот на излез се печати "/"
Влез. Прво се дава број N на поими кои ќе ги содржи речникот. Потоа во наредните N реда се дадени поимите, прв на македонски, потоа на англиски.
Потоа следуваат зборови на англиски (секој збор во посебен ред), кои треба да се преведат на македонски. За означување на крај во редицата се дава зборот KRAJ 
Излез. За секој од дадените зборови на англиски во посебен ред е даден преводот на зборот на македонски. Доколку не е познат преводот на зборот се печати /
Забелешка. Работете со хеш табела со отворени кофички. Сами треба да го одредите бројот на кофички и хеш функцијата.
Име на класа: Preveduvac
--------

You are supposed to make an automated translator, that translates words from English to Macedonian.
Input: In the first line you are given a single integer N. In the following N lines you are given two words separated with a single white space. The first one is on Macedonian and the second is on English. Use this pairs of words to create, a dictionary. After this you are given a single English word in every line, until the word "КРАЈ" is read.
Output: On the standard output you should print the Macedonian translation of the English words, using the dictionary you previously created. If you can't find a word in the dictionary print "/"
Note: Use open buckets hash table. You are supposed to define the hash table and write the hash function by yourself.
Class name: Preveduvac*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MapEntry<K extends Comparable<K>, E> implements Comparable<K> {
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo(K that) {
        @SuppressWarnings("unchecked")
        MapEntry<K, E> other = (MapEntry<K, E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString() {
        return "<" + key + "," + value + ">";
    }
}


class OBHT<K extends Comparable<K>, E> {

    private MapEntry<K, E>[] buckets;

    static final int NONE = -1;

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static final MapEntry former = new MapEntry(null, null);

    private int occupancy = 0;

    @SuppressWarnings("unchecked")
    public OBHT(int m) {
        buckets = (MapEntry<K, E>[]) new MapEntry[m];
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public MapEntry<K, E> getBucket(int i) {
        return buckets[i];
    }


    public int search(K targetKey) {
        //zema key vrakjaa (int) zavisno tipot
        int b = hash(targetKey);
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];
            if (oldEntry == null)
                return NONE;
            else if (targetKey.equals(oldEntry.key))
                return b;
            else
                b = (b + 1) % buckets.length;
        }
    }


    public void insert(K key, E val) {
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];
            if (oldEntry == null) {
                if (++occupancy == buckets.length) {
                    System.out.println("Hash tabelata e polna!!!");
                }
                buckets[b] = newEntry;
                return;
            } else if (oldEntry == former
                    || key.equals(oldEntry.key)) {
                buckets[b] = newEntry;
                return;
            } else
                b = (b + 1) % buckets.length;
        }
    }


    @SuppressWarnings("unchecked")
    public void delete(K key) {
        int b = hash(key);
        for (; ; ) {
            MapEntry<K, E> oldEntry = buckets[b];
            if (oldEntry == null)
                return;
            else if (key.equals(oldEntry.key)) {
                buckets[b] = former;
                return;
            } else {
                b = (b + 1) % buckets.length;
            }
        }
    }


    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            if (buckets[i] == null)
                temp += "\n";
            else if (buckets[i] == former)
                temp += "former\n";
            else
                temp += buckets[i] + "\n";
        }
        return temp;
    }


    public OBHT<K, E> clone() {
        OBHT<K, E> copy = new OBHT<K, E>(buckets.length);
        for (int i = 0; i < buckets.length; i++) {
            MapEntry<K, E> e = buckets[i];
            if (e != null && e != former)
                copy.buckets[i] = new MapEntry<K, E>(e.key, e.value);
            else
                copy.buckets[i] = e;
        }
        return copy;
    }
}


public class Preveduvac {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        OBHT<String, String> openHash = new OBHT<>(130);
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            String[] pom = line.split(" ");
            String kluch = pom[1];
            String vrednost = pom[0];
            openHash.insert(kluch, vrednost);
        }
        boolean condition=true;
        while (condition) {
            String prevod = br.readLine();
            if (prevod.equals("KRAJ")) {
                condition = false;
            }
            if (condition) {
                int zbor = openHash.search(prevod);
                if (zbor == -1) {
                    System.out.println("/");
                } else {
                    System.out.println(openHash.getBucket(zbor).value);
                }
            }
        }
    }
}
