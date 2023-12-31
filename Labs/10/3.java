/*Дадена е една социјална мрежа во која за корисниците се чуваат податоци за име и презиме. Исто така, за секој корисник познати се 
неговите пријатели во социјалната мрежа. Да се напише алгоритам кој за дадени имиња на двајца корисници го одредува степенот на разделеност 
помеѓу корисниците т.е. преку колку најмалку корисници се поврзани (преку колку најмалку корисници може да се стигне од едниот до другиот корисник)
во социјалната мрежа. Социјалната мрежа е претставена како нетежински граф со листа на соседство. Во првиот ред од влезот е даден бројот на 
корисниците. Потоа, во следниот ред е даденo името и презимето на првиот корисник, а потоа и бројот на пријателите на првиот корисник, а во 
следните редови се наведени неговите пријатели со име и презиме. Понатаму се дадени на истиот начин информациите за сите корисници. На крај во 
последните два реда се дадени имињата на двата корисници за кои треба да се одреди степенот на разделеност.
/
You're given a social network where the name and surname are stored for each user. The friends of the users are also known. Write an algorithm that, when given the names of two users finds the degree of separation between the users (i.e. the minimum number of users to get from one to another user) in the social network.
The social network is represented as an unweighted graph using an adjacency list. The number of users is given in the first input line. The second line contains the name of the first user, followed by the number of friends of the first user. In the next lines, the names of the friends of the first user are listed. The information for all users is given in the same way. The last two input lines contain the names of the 2 users that you should compute the degree of separation for.*/

import java.util.*;
import java.util.Map.Entry;

class ALG<T> {
    private Map<T, Set<T>> adjacencyList;

    public ALG() {
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
        StringBuilder ret = new StringBuilder(new String());
        for (Entry<T, Set<T>> vertex : adjacencyList.entrySet())
            ret.append(vertex.getKey()).append(": ").append(vertex.getValue()).append("\n");
        return ret.toString();
    }
    
    
    public void stepenNaPrijatelstvo(T prijatel1, T prijatel2) {
    Set<T> visited = new HashSet<>();
    Queue<T> queue = new LinkedList<>();
    int stepenNaPrijatelstvo = 0;

    queue.add(prijatel1);
    visited.add(prijatel1);

    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        for (int i = 0; i < levelSize; i++) {
            T vertex = queue.poll();
            if (vertex.equals(prijatel2)) {
                System.out.println(stepenNaPrijatelstvo);
                return;
            }
            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        stepenNaPrijatelstvo++;
    }
    System.out.println("0");
  }
  
}
public class Prijatelstvo {
    public static void main(String[] args) {
        ALG<String> mrezhaPrijatelstva=new ALG<>();
        Scanner in=new Scanner(System.in);
        int brNaKorisnici=Integer.parseInt(in.nextLine());
        for (int i = 0; i < brNaKorisnici; i++) {
            String korisnik=in.nextLine();
            mrezhaPrijatelstva.addVertex(korisnik);
            int prijateliNaKorisnikotI=Integer.parseInt(in.nextLine());
            for (int j = 0; j < prijateliNaKorisnikotI; j++) {
                String prijatel=in.nextLine();
                mrezhaPrijatelstva.addEdge(korisnik,prijatel);
            }
        }
        String prijatel1=in.nextLine();
        String prijatel2=in.nextLine();
        mrezhaPrijatelstva.stepenNaPrijatelstvo(prijatel1,prijatel2);
    }
}

//ideja: graf kaj sho korisnicive kje se teminja a nivnive prijatelstva kje se vrski i bi se istrazhil stepen od prijatel do prijatel so BFS
