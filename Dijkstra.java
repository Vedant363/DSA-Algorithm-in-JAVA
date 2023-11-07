import java.util.Scanner;

public class Dijkstra {

    public static void dijkstra(int[][] graph, int source, int destination) {
        int count = graph.length;
        System.out.println(count);
        boolean[] visitedVertex = new boolean[count];
        int[] distance = new int[count];
        int[] parent = new int[count];
        
        for (int i = 0; i < count; i++) {
            visitedVertex[i] = false;
            distance[i] = Integer.MAX_VALUE;
        }

        distance[source] = 0;
        parent[source] = -1;
        
        for (int i = 0; i < count; i++) {
            int u = findMinDistance(distance, visitedVertex);
            visitedVertex[u] = true;

            for (int v = 0; v < count; v++) {
                if (!visitedVertex[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v])) {
                    distance[v] = distance[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        for (int i = 0; i < distance.length; i++) {
            System.out.println(String.format("Distance from %s to %s is %s", source+1, i+1, distance[i]));
        }
        System.out.print("\nShortest Path : ");
        printPath(parent, destination);
        System.out.println("\n\nMinimum Cost: " + distance[destination]);
    }

    private static void printPath(int[] parent, int j) {
        if (parent[j] == -1) {
            System.out.print(j+1);
            return;
        }
        printPath(parent, parent[j]);
        System.out.print("->");
        System.out.print((j+1));
    }

    private static int findMinDistance(int[] distance, boolean[] visitedVertex) {
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;
        for (int i = 0; i < distance.length; i++) {
            if (!visitedVertex[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minDistanceVertex = i;
            }
        }
        return minDistanceVertex;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of nodes in the graph:");
        int numNodes = scanner.nextInt();

        int[][] graph = new int[numNodes][numNodes];

        System.out.println("Enter the adjacency matrix (use 0 for no connection):");
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the starting node:");
        int startNode = scanner.nextInt();

        System.out.println("Enter the destination node:");
        int destNode = scanner.nextInt();

        dijkstra(graph, startNode, destNode);
        scanner.close();
    }
}
