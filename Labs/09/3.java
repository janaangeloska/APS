/*Еден автобус со даден капацитет capacity вози на својата рута и во него се качуваат и симнуваат патници. За секој патник се знае во кое време се качил во автобусот и во кое време се симнал од автобусот. Потребно е да се пронајде дали сите патници можат да се возат со автобусот, односно дали во секој момент бројот на патници што моментално се наоѓаат во автобусот не го надминува неговиот капацитет. Во првата линија е даден капацитет на автобусот, а во втората бројот на патници кои чекаат да влезат, N. Секоја од наредните линии го означува часот и минутите на вретето накачување на патникот, како и колку часот и минутите на времето на симнување на патникот, во формат: HH:MM.
За да се реализира оваа задача потребно е да се користи соодветна податочна структура со која со најмала сложеност ќе се постигне бараниот резултат. Автобусот вози до до 23:59.
На излез треба да се испечати true ако максималнот број на патници во автобусот не го надминува неговиот капацитет, инаку false.
Влез: Прво е даден капацитетот. Потоа е даден бројот на патници N. Во напредните N редици е дадено времето на качување и симнување на патниците во формат HH:MM
Излез: true ако максималниот број на патници што се наоѓаат во автобусот истовремено не го надминува капацитетот, инаку false.
Пример: 
2
3
08:30 9:30
09:25 10:10
07:00 9:00
Излез: true
/
A bus with capacity capacity drives its route and picks up and drops off passengers. For each passenger, you're given the time when they boarded the bus and the time when they got off the bus. You should determine whether all passengers can ride the bus, that is, whether the number of passengers currently on the bus doesn't exceed the capacity at any moment. In the first line, you're given the capacity. In the next line, you're given the number of passengers N. In the next N lines, you're given the time each passenger boarded and got off the bus in the format HH:MM.
To solve this problem you should use an appropriate data structure that will let you achieve the desired result with minimal complexity. The bus rides until  23:59.
You should print true if the maximum number of passengers on the bus at the same time doesn't exceed the capacity, otherwise false.
Input: In the first line, you're given the capacity. In the next line, you're given the number of passengers N. In the next N lines, you're given the time each passenger boarded and got off the bus in the format HH:MM.
Output: true if the maximum number of passengers on the bus at the same time doesn't exceed the capacity, otherwise false.
Example: 
2
3
08:30 9:30
09:25 10:10
07:00 9:00
Output: true*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

class Heap<E extends Comparable<E>> {

    private E elements[];


    int getParent(int i) {
        return (i+1)/2-1;
    }

    public E getAt(int i) {
        return elements[i];
    }

    int getLeft(int i) {
        return (i+1)*2-1;
    }

    int getRight(int i) {
        return (i+1)*2;
    }

    void setElement(int index, E elem) {
        elements[index] = elem;
    }

    void swap(int i, int j) {
        E tmp = elements[i];
        elements[i] = elements[j];
        elements[j] = tmp;
    }

    void adjust(int i, int n) {
        while (i < n) {
            int left = getLeft(i);
            int right = getRight(i);
            int smallest = i;

            if ((left < n) && (elements[left].compareTo(elements[smallest]) > 0))
                smallest = left;
            if ((right < n) && (elements[right].compareTo(elements[smallest]) > 0))
                smallest = right;

            if (smallest == i)
                break;

            swap(i, smallest);
            i = smallest;
        }
    }

    void buildHeap() {
        int i;
        for (i=elements.length/2-1;i>=0;i--)
            adjust(i, elements.length);
    }

    public void heapSort() {
        int i;
        buildHeap();
        for (i=elements.length;i>1;i--) {
            swap(0, i-1);
            adjust(0, i-1);
        }
    }

    @SuppressWarnings("unchecked")
    public Heap(int size) {
        elements = (E[])new Comparable[size];
    }

}
public class busTest {

    public static int convertTime(String time){
        int h = Integer.parseInt(time.split(":")[0])*60;
        int m =  Integer.parseInt(time.split(":")[1]);
        return h + m;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxCapacity = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        Heap<Integer> arriveTime = new Heap<>(n);
        Heap<Integer> exitTimes = new Heap<>(n);
        int onBus = 0;
        int offBus = 0;
        boolean capacity = true;
        for(int i = 0; i < n; i++) {
            String [] parts = br.readLine().split("\\s+");
            int arrivalTime = convertTime(parts[0]);
            int exitTime = convertTime(parts[1]);
            arriveTime.setElement(i, arrivalTime);
            exitTimes.setElement(i, exitTime);
        }
        arriveTime.heapSort();
        exitTimes.heapSort();
        for(int i = 0; i < n; i++) {
            onBus++;
            if(onBus > maxCapacity) {
                capacity = false;
            }
            if(exitTimes.getAt(offBus) < arriveTime.getAt(i)) {
                onBus--;
                offBus++;
            }
        }
        System.out.println(capacity);
    }
}
