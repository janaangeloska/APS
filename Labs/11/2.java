/*На еден факултет се поставени предмети кои задолжително треба да се слушаат и изборни предмети. За секој предмет е даден предуслов: кои предмети треба да се положени за да може да се слуша избраниот предмет. Ваша задача е да за даден последен положен предмет, да најдете кој е следен предмет кој може да го слуша студентот.
Влез: Во првиот ред е даден број n - број на предмети Во следните n редови се дадени шифрите на предметите. Во следнииот ред е даден број m - број на зависности Во следните m реда се дадени шифри на предмети, разделени со празно место. Првата шифра го означува предметот за кој се дефинира зависност, а следните шифри се предметите кои треба да се положени за да се слуша предметот даден со првата шифра. Во последниот ред е дадена шифрата на последниот слушан предмет.
Излез: Се печати шифрата на следниот предмет кој треба да се слуша.
---------------------------------------------------------------------------------------------------
On one university there are mandatory and elective subjects. For each subject you are given, a list of subjects that need to be passed in order to be eligible for that subject is given. Find the next available subject for you, if you know what is the last subject that you have passed.
Input: In the first line you are given a single integer N representing the number of elements. In the following N lines you are given the IDs of the subjects. In the following line you are given a single integer M representing the number of dependences between the subjects. In the following M lines you are given a list of IDs separated with a single white space. Starting from the second ID to the last ID you have the subjects that you need to pass in order to be able to take the subjected defined with the first ID in this line. In the last line you are given the last subject that you have passed.
Output: The output should contain a single ID representing the next available subject.*/

import java.util.Scanner;
import java.util.*;

class GraphPredmeti<T> {
    private Map<T, Set<T>> adjacencyList;

    public GraphPredmeti() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    public void removeVertex(T vertex) {
        for (Set<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        adjacencyList.remove(vertex);
    }

    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
    }

    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
    }

    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    public void DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                DFSUtil(neighbor, visited);
            }
        }
    }


    public void DFSNonRecursive(T startVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(startVertex);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                System.out.print(vertex + " ");
                for (T neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    public void printPath(T source, T destination) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        stack.push(source);
        visited.add(source);
        while (!stack.isEmpty() && !stack.peek().equals(destination)) {
            T vertex = stack.peek();

            boolean f = true;
            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                    f = false;
                    break;
                }
            }

            if (f) {
                stack.pop();
            }
        }

        Stack<T> path = new Stack<>();

        while (!stack.isEmpty()) {
            path.push(stack.pop());
        }

        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
    }

    public void BFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            System.out.print(vertex + " ");

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    private void topologicalSortUtil(T vertex, Set<T> visited, Stack<T> stack) {
        visited.add(vertex);
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                topologicalSortUtil(neighbor, visited, stack);
            }
        }
        stack.push(vertex);
    }

    public List<T> topologicalSort() {
        Stack<T> stack = new Stack<>();
        Set<T> visited = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                topologicalSortUtil(vertex, visited, stack);
            }
        }

        List<T> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        return order;
    }


    @Override
    public String toString() {
        String ret = new String();
        for (Map.Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }


}

public class Predmeti {
    private static void sledenPredmet(GraphPredmeti<String> graphPredmeti, String posledenPredmet) {
        List<String> predmeti = graphPredmeti.topologicalSort();
        boolean flag = false;
        for (String predmet : predmeti) {
            if (flag) {
                System.out.println(predmet);
                break;
            }
            if (predmet.equals(posledenPredmet)) {
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GraphPredmeti<String> graphPredmeti = new GraphPredmeti<String>();
        //Во првиот ред е даден број n - број на предмети
        int n = Integer.parseInt(sc.nextLine());
        // Во следните n редови се дадени шифрите на предметите.
        for (int i = 0; i < n; i++) {
            String sifra1 = sc.nextLine();
            graphPredmeti.addVertex(sifra1);
        }
        //Во следнииот ред е даден број m - број на зависности
        int m = Integer.parseInt(sc.nextLine());
        //Во следните m реда се дадени шифри на предмети, разделени со празно место.
        for (int i = 0; i < m; i++) {
            String sifra2 = sc.nextLine();
            String[] parts = sifra2.split("\\s+");
            //Првата шифра го означува предметот за кој се дефинира зависност,
            //а следните шифри се предметите кои треба да се положени за да се слуша предметот даден со првата шифра.
            String predmet = parts[0];
            for (int j = 1; j < parts.length; j++) {
                String uslovZaPredmet = parts[j];
                graphPredmeti.addEdge(uslovZaPredmet, predmet);
            }
        }
        //Во последниот ред е дадена шифрата на последниот слушан предмет.
        String posledenPredmet = sc.nextLine();
        sledenPredmet(graphPredmeti, posledenPredmet);
    }
}
