/*Дадена е една мапа со еднонасочни патишта меѓу N градови во Македонија. За секој пат се знае должината на патот, почетниот и крајниот град. Да се најде вкупната должина на минималниот пат од еден до друг град и назад, и да се испечатат патеките со градовите низ кои треба да се помине напред и во обратен правец. 
Мапата со патишта е претставена како насочен тежински граф. Во првиот ред од влезот е даден број на градови N. Во вториот ред од влезот е даден бројот на насочени патишта. Во следните редови се дадени патиштата во облик: реден број на почетниот град, име на почетниот град, реден број на крајниот град, име на крајниот град, должина на патот. Во последните два реда се дадени имињата на градовите меѓу кои треба да се најде најкраткиот пат. 
Во првиот ред од излезот треба да се испечатат градовите низ кои треба да се помине за да се стигне по минимален пат од почетниот до крајниот град, а во вториот ред градовите низ кои треба да се помина за минималниот пат назад. Во последниот ред од излезот треба да се испечати должината на вкупниот минимален пат во двата правци. 
------------------------------------------------------------------------------
You are given a map with one way roads between N cities in Macedonia. For each road you are given the length of the road, the starting city and the destination city. Find the minimal distance from one city to another and back, and print the roads you are going to take.
The map with roads is given as a directed weighted graph.
Input: In the first line you are given the number of cities N. In the second line you are given the number of roads M. In the following M lines you are given the roads as following: the number of the starting city, the name of the starting city, the number of the destination city, the name of the destination city, length of the road. In the last 2 lines you are given the names of the starting and the destination city.
Output: In the first line of the output you are supposed to print the route which you are supposed to take starting from the starting city and ending at the destination city. In the second line you are supposed to print the route which you are supposed to take starting from the destination city and ending at the starting city. In the last line of the output you are supposed to print a single integer representing the total length of the routes.
*/

import java.util.*;

class AdjacencyListGraph<T> {
    private Map<T, Map<T, Double>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashMap<>());
        }
    }

    public void removeVertex(T vertex) {
        for (Map<T, Double> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        adjacencyList.remove(vertex);
    }

    public void addEdge(T source, T destination, double weight) {
        addVertex(source);
        addVertex(destination);

        adjacencyList.get(source).put(destination, weight);
    }

    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
//        if (adjacencyList.containsKey(destination)) {
//            adjacencyList.get(destination).remove(source); // for undirected graph
//        }
    }

    public Map<T, Double> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashMap<>());
    }

    public void DFS(T startVertex) {
        Set<T> visited = new HashSet<>();
        DFSUtil(startVertex, visited);
    }

    private void DFSUtil(T vertex, Set<T> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");

        for (T neighbor : getNeighbors(vertex).keySet()) {
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
                for (T neighbor : getNeighbors(vertex).keySet()) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    class Pair<K, V> {
        private final K first;
        private final V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public K getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }
    }
    
    public void printPath(T source, T destination, Map<T, String> cityNames) {
        Map<T, T> previousVertices = new HashMap<>();
        Set<T> visited = new HashSet<>();
        PriorityQueue<Pair<T, Double>> queue = new PriorityQueue<>(Comparator.comparingDouble(Pair::getSecond));

        queue.add(new Pair<>(source, 0.0));
        visited.add(source);

        while (!queue.isEmpty()) {
            Pair<T, Double> currentPair = queue.poll();
            T currentVertex = currentPair.getFirst();

            for (T neighbor : getNeighbors(currentVertex).keySet()) {
                if (!visited.contains(neighbor)) {
                    double weight = getNeighbors(currentVertex).get(neighbor);
                    queue.add(new Pair<>(neighbor, currentPair.getSecond() + weight));
                    visited.add(neighbor);
                    previousVertices.put(neighbor, currentVertex);
                }
            }
        }

        Stack<T> path = new Stack<>();
        T currentVertex = destination;

        while (currentVertex != null) {
            path.push(currentVertex);
            currentVertex = previousVertices.get(currentVertex);
        }

        while (!path.isEmpty()) {
            T vertex = path.pop();
            System.out.print(cityNames.get(vertex) + " ");
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

            for (T neighbor : getNeighbors(vertex).keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }


    public Map<T, Double> shortestPath(T startVertex) {
        Map<T, Double> distances = new HashMap<>();
        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        Set<T> explored = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(startVertex, 0.0);

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            explored.add(current);

            for (Map.Entry<T, Double> neighborEntry : adjacencyList.get(current).entrySet()) {
                T neighbor = neighborEntry.getKey();
                double newDist = distances.get(current) + neighborEntry.getValue();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);

                    if (!explored.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return distances;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder(new String());
        for (Map.Entry<T, Map<T, Double>> vertex : adjacencyList.entrySet())
            ret.append(vertex.getKey()).append(": ").append(vertex.getValue()).append("\n");
        return ret.toString();
    }


}

public class MapaPatishta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdjacencyListGraph<Integer> map=new AdjacencyListGraph<>();
//        број на градови N
        int N = Integer.parseInt(sc.nextLine());
//        бројот на насочени патишта
        int roads = Integer.parseInt(sc.nextLine());
        Map<Integer, String> cityNames = new HashMap<>();
//        реден број на почетниот град, име на почетниот град, реден број на крајниот град, име на крајниот град, должина на патот.

        for (int i = 0; i < roads; i++) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");

            int startCity = Integer.parseInt(parts[0]);
            String startCityName = parts[1];
            int endCity = Integer.parseInt(parts[2]);
            String endCityName = parts[3];
            double pathLength = Double.parseDouble(parts[4]);
            //mapa so values iminja na gradovi i nivnite teminja kako keys
            cityNames.put(startCity, startCityName);
            cityNames.put(endCity, endCityName);
            //dodavame rebro i negova tezhina
            map.addEdge(startCity, endCity, pathLength);
        }
        String city1=sc.nextLine();
        String city2=sc.nextLine();
        int key1=-1,key2=-1;
        for(Map.Entry<Integer, String> entry: cityNames.entrySet()) {
            if(entry.getValue().equals(city1)) {
                key1= entry.getKey();
            }
            if(entry.getValue().equals(city2)) {
                key2= entry.getKey();
            }
        }
        Map<Integer, Double> path1 = map.shortestPath(key1);
        Map<Integer, Double> path2 = map.shortestPath(key2);

        map.printPath(key1, key2, cityNames);
        System.out.println();
        map.printPath(key2, key1, cityNames);
        System.out.println();
        double totalLength = path1.get(key2) + path2.get(key1);
        System.out.println(totalLength);
    }
}

