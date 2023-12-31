/*Ваша задача е да креирате неориентиран нетежински граф со листа на соседство, каде темињата како информација содржат број. Графот го креирате според наредбите кои се добиваат. Ќе ви биде дадена низа од команди што можат да бидат од следните типови:
CREATE - треба да креирате нов граф. 
ADDEDGE [број1] [број2] - треба да креирате ребро меѓу темињата со реден број број1 и реден број број2. 
DELETEEDGE [број1] [број2] - треба да го избришете реброто меѓу темињата со реден број број1 и реден број број2. 
ADЈACENT [број1] [број2] - треба да испечатите true доколку темињата со реден број број1 и реден број број2 се соседни, во спротивно false.
PRINTGRAPH - Треба да ја испечатите листата на соседство
Во првата линија на влезот е даден бројот на команди кои ќе следуваат.
/
Your task is to create an unoriented unweighted graph by using an adjacency list, where each vertex information is аn integer. You create the graph according to the received commands. You will be given an array of commands that can be one of the following:
CREATE - you should create a new graph.
ADDEDGE [number1] [number2] - you should create an edge between the vertices with ordinal number number1 and ordinal number number2. 
DELETEEDGE [number1] [number2] - you should remove the edge between the vertices with ordinal number number1 and ordinal number number2.
ADЈACENT [number1] [number2] - you should print true if the vertices with ordinal number number1 and ordinal number number2 are adjacent, otherwise print false.
PRINTGRAPH - you should print the adjacency list. 
The number of commands is given in the first input line.*/
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
        adjacencyList.get(destination).add(source); // for undirected graph
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

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder(new String());
        for (Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret.append(vertex.getKey()).append(": ").append(vertex.getValue()).append("\n");
        return ret.toString();
    }
}

public class NeorientiranNetezhinskiGraf {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        AdjacencyListGraph<Integer> graph = null;
        int count = Integer.parseInt(in.nextLine());
        for (int i = 0; i < count; i++) {
            String nl = in.nextLine();
            String[] parts = nl.split("\\s+");
            if (parts[0].equals("CREATE")) {
                graph = new AdjacencyListGraph<>();
            } else if (parts[0].equals("ADDEDGE")) {
                graph.addEdge((Integer.parseInt(parts[1])), (Integer.parseInt(parts[2])));
            } else if (parts[0].equals("DELETEEDGE")) {
                graph.removeEdge((Integer.parseInt(parts[1])), (Integer.parseInt(parts[2])));
            } else if (parts[0].equals("ADJACENT")) {
                if(graph.getNeighbors(Integer.parseInt(parts[1])).contains(Integer.parseInt(parts[2])) || graph.getNeighbors(Integer.parseInt(parts[2])).contains(Integer.parseInt(parts[1]))){
                    System.out.println("true");
                }else{
                    System.out.println("false");
                }
            } else if (parts[0].equals("PRINTGRAPH")) {
                System.out.println(graph.toString());
            }
        }
    }
}

