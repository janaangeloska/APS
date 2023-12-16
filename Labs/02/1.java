/*За дадена низа од N зборови, да се најде зборот чија должина е најблиску до просечната должина на зборовите во низата. Ако постојат два збора со должина која е на исто растојание до просечната должина, да се врати подолгиот од нив. Дополнително, доколку се со иста должина, се зема првиот. На пример за низата "a", "an", "cat", "door", "apple" просечната должина е (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3, што значи дека зборот кој треба да се врати и чија должина е најблиску до просечната е "cat" (должина 3).
За низата "I", "on", "dog", "star", "water", "bright" просечната должина е 3.5 и зборовите "dog" и "star" се со должини на исто растојание од просечната (имаат должини 3 и 4, соодветно). Точната вредност која треба да се врати е подолгиот од нив, а тоа е "star" (должина 4).
Во низата може да има дупликати.
Влез: Првиот број од влезот е бројот на зборови во низата N, а потоа во секој ред се дадени зборовите.
Излез: Најдениот збор чија што должина е најблиску до просечната должина на зборовите во низата.
Внимавајте:
1. Даден е целосниот код на структурата којашто треба да се користи. Дадена е и тест класата ArrayMeanWordLength.java, со целосно имплементиран input и output. Потребно е да се менува само во рамки на String wordClosestToAverageLength(Array<String> arr) функцијата.
2. Не смее да менувате во main функцијата !
/

For a given array with N words, find the word with length closest to the average length of the words in the array. If there are two words with lengths that are on the same distance to the average length, return the longer word. Additionally, if they are with the same length, you take the first one. For example for the array "a", "an", "cat", "door", "apple" the average word length is (1 + 2 + 3 + 4 + 5) / 5 = 15 / 5 = 3, which means that the word with length closest to the average length is "cat" (length is 3).
For the array "I", "on", "dog", "star", "water", "bright" the average word length is 3.5 and both words "dog" and "star" have lengths that are equally distant to the average length (lengths are 3 and 4, respectively). The correct answer is the longer word which is "star" (with length 4).
There can be duplicates in the array.
Input: The first number in the input is the number of words in the array N, then the words follow in each line.
Output: The found word, that is with length closest to the average word length in the array.
Pay attention:
1. All the needed code for the structure that you need to use is given. The test class ArrayMeanWordLength.java is also given, with completely implemented input and output. You only need to change the code of the String wordClosestToAverageLength(Array<String> arr) method.
2. You must not change the main method !*/

import java.util.Scanner;


@SuppressWarnings("unchecked")
class Array<E> {
    private E data[]; // declared to be an Object since it would be too
    // complicated with generics
    private int size;

    public Array(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    public void insertLast(E o) {
        //check if there is enough capacity, and if not - resize
        if(size + 1 > data.length)
            this.resize();
        data[size++] = o;
    }

    public void insert(int position, E o) {
        // before all we check if position is within range
        if (position >= 0 && position <= size) {
            //check if there is enough capacity, and if not - resize
            if(size + 1 > data.length)
                this.resize();
            //copy the data, before doing the insertion
            for(int i=size;i>position;i--) {
                data[i] = data[i-1];
            }
            data[position] = o;
            size++;
        } else {
            System.out.println("Ne mozhe da se vmetne element na taa pozicija");
        }
    }

    public void set(int position, E o) {
        if (position >= 0 && position < size)
            data[position] = o;
        else
            System.out.println("Ne moze da se vmetne element na dadenata pozicija");
    }

    public E get(int position) {
        if (position >= 0 && position < size)
            return data[position];
        else
            System.out.println("Ne e validna dadenata pozicija");
        return null;
    }

    public int find(E o) {
        for (int i = 0; i < size; i++) {
            if(o.equals(data[i]))
                return i;
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    public void delete(int position) {
        // before all we check if position is within range
        if (position >= 0 && position < size) {
            // first resize the storage array
            E[] newData = (E[]) new Object[size - 1];
            // copy the data prior to the delition
            for (int i = 0; i < position; i++)
                newData[i] = data[i];
            // move the data after the deletion
            for (int i = position + 1; i < size; i++)
                newData[i - 1] = data[i];
            // replace the storage with the new array
            data = newData;
            size--;
        }
    }

    public void resize() {
        // first resize the storage array
        E[] newData = (E[]) new Object[size*2];
        // copy the data
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        // replace the storage with the new array
        this.data = newData;
    }

    @Override
    public String toString() {
        String ret = new String();
        if(size>0) {
            ret = "{";
            ret += data[0];
            for(int i=1;i<size;i++) {
                ret += "," + data[i];
            }
            ret+="}";
            return ret;
        }
        else {
            ret = "Prazna niza!";
        }
        return ret;
    }

}

public class ArrayMeanWordLength {

    //TODO: implement function
    public static String wordClosestToAverageLength(Array<String> arr) {
            float vk=0, prosek;
            int index=0, najmal=arr.get(0).length(), dolzina=arr.getSize();
            for (int i = 0; i < dolzina; i++) {
                vk+=arr.get(i).length();
            }
            prosek=vk/dolzina;
            double rezultat = Math.ceil(prosek);
            double razlika=prosek-najmal;
            for(int i = 0; i < dolzina; i++){
                double temp=rezultat-arr.get(i).length();
                if(razlika>Math.abs(temp)){
                    razlika=temp;
                    index=i;
                }
            }
            return arr.get(index);

        }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int N = input.nextInt();
        Array<String> arr = new Array<>(N);
        input.nextLine();

        for(int i=0;i<N;i++) {
            arr.insertLast(input.nextLine());
        }

        System.out.println(wordClosestToAverageLength(arr));
    }
}
