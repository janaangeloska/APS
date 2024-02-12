/*Во земjата Лапониjа живее Дедо Мраз. Во слободното време кога не е Нова Година, додека џуџињата си работат на играчките за следната година, Дедо Мраз има хоби. Тоj сака да одгледува рибички. Но тоj своите рибички ги одгледува во природни езера. Езерата се меѓусебно поврзани со рекички, и рекичките течат од едно езерце до друго. Рибите од едно езеро слободно можат преку рекичките да отидат во друго езеро. Секоjа пролет дедо мраз сака да прави порибување на езерцата со нови рибички. Ваша задача е да му кажете на Дедо Мраз доколку тоj пушти нови рибички во езерцето X, во колку други езерца ќе можат рибичките сами да стигнат, а со тоа да нема потреба тоj самиот да ги порибува тие езерца. 
Влез: Во првата линиjа од влезот е даден броj N < 15 броjот на езерца. Во втората линиjа е даден броj U < 20 броjот на реки меѓу езерцата. Во следните U линии се дадени парови од 2 броjа R и Q, што значи постои рекичка коjа тече од R до Q, каде R и Q се броеви на езерцата. Во последната линиjа е даден броj L, во кое езерце Дедо Мраз ќе ги пушти рибичките. 
Излез: Се испишува броjот, колку езерца освен почетното ќе бидат порибени. 
/
Santa Claus lives in Lapland. In his free time when it is not around New Year, while the elves are working on toys for next year, Santa Claus has a hobby. He likes to raise fish. But he does this in natural lakes. The ponds are interconnected by creeks, and the creeks flow from one pond to another. Fish from one lake can freely go to another lake through the streams. Every spring Santa Claus likes to stock the ponds with new fish. Your task is to tell Santa Claus, if he releases new fishes into pond X, how many other ponds the fishes will be able to reach on their own, so that there is no need for him to stock those ponds himself.
Input: In the first line of the input, the number N < 15 is given, the number of ponds. In the second line, the number U < 20 is given, the number of rivers between the lakes. In the following U lines, pairs of 2 numbers R and Q are given, which means there is a river flowing from R to Q, where R and Q are pond numbers. In the last line, the number L is given, in which pond Santa Claus will release the fish.
Output: The number of ponds that will be stocked except the initial one is printed out.*/


import java.util.*;
import java.util.Map.Entry;

class AdjacencyListGraph<T> {
    private Map<T, Set<T>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    /*public Map<T, Integer> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashMap<>());
    }*/

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Set<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    // Add an edge to the graph
    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).add(destination);
//        adjacencyList.get(destination).add(source); // for undirected graph
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }

    // Get all neighbors of a vertex
    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    public void DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        // Mark the current node as visited and print it
        visited.add(vertex);
        System.out.print(vertex + " ");

        // Recur for all the vertices adjacent to this vertex
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

    public void findPath(T startVertex, T endVertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> invertedPath = new Stack<>();
        visited.add(startVertex);
        invertedPath.push(startVertex);

        while(!invertedPath.isEmpty() && !invertedPath.peek().equals(endVertex)) {
            T currentVertex = invertedPath.peek();
            T tmp = currentVertex;

            for(T vertex : getNeighbors(currentVertex)) {
                tmp = vertex;
                if(!visited.contains(vertex)) {
                    break;
                }
            }

            if(!visited.contains(tmp)) {
                visited.add(tmp);
                invertedPath.push(tmp);
            }
            else {
                invertedPath.pop();
            }
        }

        Stack<T> path = new Stack<>();
        while(!invertedPath.isEmpty()) {
            path.push(invertedPath.pop());
        }
        while(!path.isEmpty()) {
            System.out.println(path.pop());
        }
    }

    @Override
    public String toString() {
        String ret = new String();
        for (Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret += vertex.getKey() + ": " + vertex.getValue() + "\n";
        return ret;
    }


    public int sendFishes(T l) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        int sum=0;
        visited.add(l);
        queue.add(l);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    sum+=1;
                }
            }
        }
        return sum;
    }
}
public class Ezerca {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N= sc.nextInt();
        int U=sc.nextInt();
        AdjacencyListGraph<Integer> graph=new AdjacencyListGraph<>();
        for (int i = 0; i < U; i++) {
            int r=sc.nextInt();
            int q=sc.nextInt();
        graph.addEdge(r,q);
        }
        int L=sc.nextInt();
        System.out.println(graph.sendFishes(L));
    }
}
