/*Во една организација постојат повеќе членови. На една од седниците избрано е кој член ќе биде претседател на таа организација. Потребно е за секој член (освен за претседателот) да се определи еден претпоставен од кој ќе се примаат директни наредби. Во организацијата треба да има хиерархиска структура така што на врвот на хиерархијата ќе биде претседателот. Проблемот е што некои членови подобро соработуваат меѓу себе, а некои полошо. За секој пар членови постои број од 1 до 10 што означува колку добро соработуваат двата членови (1 значи дека многу добро соработуваат, а 10 значи дека многу тешко соработуваат). Многу важно за организацијата е да се избере хиерархијата на тој начин што збирот од мерките за соработка помеѓу сите парови на членови кои што се во подреден-надреден врска да е минимална, што значи дека членовите најдобро соработуваат. 
Да се напише алгоритам кој на излез ќе го врати збирот од оптималните мерки за соработка.
Хиерархијата во организацијата е претставена како тежински граф. Во првиот ред од влезот е даден број на членови во организацијата. Во вториот ред од влезот е даден бројот на соработки. Во следните редови се дадени соработките во облик: реден број на прв член, име на прв член, реден број на втор член, име на втор член, мерка за соработка. Во последниот ред е дадено името на претседателот. 
На излез треба да се испечати збирот од најдобрите соработки.
-------------------------------------------------------------------------------------------
One organisation has a single president and multiple regular members. If you know the collaboration degree (1 being perfect, 10 being worst) between the members choose the best hierarchy for the organisation. By best it means that the sum of all collaboration degrees between the members should be minimum.
Input:
In the first line of the input you are given a single integer N, representing the number of members the organisation has. In the second line of the input you are given a single integer M, representing the number of collaborations between members from the organisation. In the following M lines you are given the collaborations like following: ID of a member, name of that member, ID of a member, name of that member, degree of collaboration between the two members given previously. In the last line you are given the name of the president.
Output:
After choosing the best hierarchy print the sum of all collaboration degrees for that hierarchy.*/

import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class OrganizacijaGraph<T> {
    private int numVertices;
    private int[][] matrix;
    private T[] vertices;

    @SuppressWarnings("unchecked")
    public OrganizacijaGraph(int numVertices) {
        this.numVertices = numVertices;
        matrix = new int[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                matrix[i][j] = 0;
            }
        }
        vertices = (T[]) new Object[numVertices];
    }

    public void addVertex(int index, T data) {
        vertices[index] = data;
    }

    public T getVertex(int index) {
        return vertices[index];
    }

    public void addEdge(int source, int destination, int weight) {
        matrix[source][destination] = weight;
        matrix[destination][source] = weight; // For undirected graph
    }

    public boolean isEdge(int source, int destination) {
        return matrix[source][destination] != 0;
    }


    public void removeEdge(int source, int destination) {
        matrix[source][destination] = 0;
        matrix[destination][source] = 0; // For undirected graph
    }

    @SuppressWarnings("unchecked")
    public void removeVertex(int vertexIndex) {
        if (vertexIndex < 0 || vertexIndex >= numVertices) {
            throw new IndexOutOfBoundsException("Vertex index out of bounds!");
        }
        int[][] newMatrix = new int[numVertices - 1][numVertices - 1];
        T[] newVertices = (T[]) new Object[numVertices - 1];
        // Copy the vertices and matrix excluding the given vertex
        int ni = 0;
        for (int i = 0; i < numVertices; i++) {
            if (i == vertexIndex) continue;
            int nj = 0;
            for (int j = 0; j < numVertices; j++) {
                if (j == vertexIndex) continue;
                newMatrix[ni][nj] = matrix[i][j];
                nj++;
            }
            newVertices[ni] = vertices[i];
            ni++;
        }
        // Replace the old matrix and vertices with the new ones
        matrix = newMatrix;
        vertices = newVertices;
        numVertices--;
    }

    public List<T> getNeighbors(int vertexIndex) {
        List<T> neighbors = new ArrayList<>();
        for (int i = 0; i < matrix[vertexIndex].length; i++) {
            if (matrix[vertexIndex][i] != 0) {
                neighbors.add(vertices[i]);
            }
        }
        return neighbors;
    }

    private List<Edge> getAllEdges() {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (isEdge(i, j)) {
                    edges.add(new Edge(i, j, matrix[i][j]));
                }
            }
        }

        return edges;
    }

    private void union(int u, int v, int[] trees) {
        int findWhat, replaceWith;
        if (u < v) {
            findWhat = trees[v];
            replaceWith = trees[u];
        } else {
            findWhat = trees[u];
            replaceWith = trees[v];
        }

        for (int i = 0; i < trees.length; i++) {
            if (trees[i] == findWhat) {
                trees[i] = replaceWith;
            }
        }
    }

    public List<Edge> kruskal() {
        List<Edge> mstEdges = new ArrayList<>();
        List<Edge> allEdges = getAllEdges();

        allEdges.sort(Comparator.comparingInt(Edge::getWeight));

        int trees[] = new int[numVertices];

        for (int i = 0; i < numVertices; i++)
            trees[i] = i;

        for (Edge e : allEdges) {
            if (trees[e.getFrom()] != trees[e.getTo()]) {
                mstEdges.add(e);

                union(e.getFrom(), e.getTo(), trees);
            }
        }

        return mstEdges;
    }

    public List<Edge> prim(int startVertexIndex) {
        List<Edge> mstEdges = new ArrayList<>();
        Queue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        boolean included[] = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            included[i] = false;
        }

        included[startVertexIndex] = true;

        for (int i = 0; i < numVertices; i++) {
            if (isEdge(startVertexIndex, i)) {
                q.add(new Edge(startVertexIndex, i, matrix[startVertexIndex][i]));
            }
        }

        while (!q.isEmpty()) {
            Edge e = q.poll();

            if (!included[e.getTo()]) {
                included[e.getTo()] = true;
                mstEdges.add(e);
                for (int i = 0; i < numVertices; i++) {
                    if (!included[i] && isEdge(e.getTo(), i)) {
                        q.add(new Edge(e.getTo(), i, matrix[e.getTo()][i]));
                    }
                }
            }
        }

        return mstEdges;
    }

    @Override
    public String toString() {
        String ret = "  ";
        for (int i = 0; i < numVertices; i++)
            ret += vertices[i] + " ";
        ret += "\n";
        for (int i = 0; i < numVertices; i++) {
            ret += vertices[i] + " ";
            for (int j = 0; j < numVertices; j++)
                ret += matrix[i][j] + " ";
            ret += "\n";
        }
        return ret;
    }

}

class Edge {
    private int fromVertex, toVertex;
    private int weight;

    public Edge(int from, int to, int weight) {
        this.fromVertex = from;
        this.toVertex = to;
        this.weight = weight;
    }

    public int getFrom() {
        return this.fromVertex;
    }

    public int getTo() {
        return this.toVertex;
    }

    public int getWeight() {
        return this.weight;
    }
}

public class Organizacija {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Во првиот ред од влезот е даден број на членови во организацијата.
        int N = Integer.parseInt(sc.nextLine());
        OrganizacijaGraph<Integer> mapa = new OrganizacijaGraph<>(N);
        // Во вториот ред од влезот е даден бројот на соработки.
        int sorabotki = Integer.parseInt(sc.nextLine());
        
        Map<Integer, String> vraboteni = new HashMap<>();

        // Во следните редови се дадени соработките во облик:
        // реден број на прв член, име на прв член,
        // реден број на втор член, име на втор член,
        for (int i = 0; i < sorabotki; i++) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");

            int clen1 = Integer.parseInt(parts[0]);
            String imeNaClen1 = parts[1];
            int clen2 = Integer.parseInt(parts[2]);
            String imeNaClen2 = parts[3];
            int kolegijalnost = Integer.parseInt(parts[4]);

            vraboteni.put(clen1, imeNaClen1);
            vraboteni.put(clen2, imeNaClen2);
            mapa.addEdge(clen1, clen2, kolegijalnost);
        }
// Во последниот ред е дадено името на претседателот.
        String pretsedatel = sc.nextLine();

        int key = -1;
        for (Map.Entry<Integer, String> entry : vraboteni.entrySet()) {
            if (entry.getValue().equals(pretsedatel)) {
                key = entry.getKey();
                break;
            }
        }

        List<Edge> mstEdges = mapa.prim(key);

        int zbir = 0;
        for (Edge edge : mstEdges) {
            zbir += edge.getWeight();
        }

        System.out.println(zbir);
    }
}
