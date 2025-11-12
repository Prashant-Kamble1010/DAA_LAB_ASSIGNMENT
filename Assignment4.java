// Name: Prashant Kamble
// PRN : 124B2F002

// Title : Scenario: Smart Traffic Management for Emergency Vehicles
// A smart city is implementing an intelligent traffic management system to assist ambulances
// in reaching hospitals as quickly as possible. The city’s road network is represented as a
// graph, where:
// ● Intersections (junctions) are nodes.
// ● Roads between intersections are edges with weights representing travel time (in minutes)
// considering traffic congestion.
// An ambulance is currently at Source (S) and needs to reach the nearest hospital (Destination
// D) in the shortest possible time. Due to dynamic traffic conditions, the weight of each road
// segment may change in real time.
// As a transportation engineer, you are assigned to:
// 1. Implement Dijkstra’s algorithm to find the shortest path from the ambulance's current
// location (S) to all possible hospitals.
// 2. Account for dynamic weight updates as traffic conditions change.
// 3. Optimize the system to work efficiently for a large city with thousands of intersections
// and roads.
// 4. Provide a visual representation of the optimal path for navigation.
// Expected Outcome:
// The system should suggest the quickest route for the ambulance, updating dynamically
// based on real-time traffic conditions, ensuring minimal response time to emergencies.
    
import java.util.*;

class Edge {
    int to;        // destination node
    int weight;    // travel time
    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class DijekstraAlgo {

    // Dijkstra’s Algorithm
    public static void dijkstra(List<List<Edge>> graph, int source, int n) {
        int[] dist = new int[n];             // shortest distance from source
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0];
            int d = node[1];

            if (d > dist[u]) continue; // skip outdated entry

            for (Edge e : graph.get(u)) {
                int v = e.to;
                int newDist = d + e.weight;
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.add(new int[]{v, newDist});
                }
            }
        }

        // Print results
        System.out.println("Shortest travel time from Source " + source + " to all intersections:");
        for (int i = 0; i < n; i++) {
            System.out.println("To node " + i + " → " + (dist[i] == Integer.MAX_VALUE ? "Not reachable" : dist[i] + " mins"));
        }
    }

    public static void main(String[] args) {
        int n = 6; // number of intersections (0 to 5)
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        // Add roads (edges) between intersections
        graph.get(0).add(new Edge(1, 4));
        graph.get(0).add(new Edge(2, 2));
        graph.get(1).add(new Edge(2, 1));
        graph.get(1).add(new Edge(3, 5));
        graph.get(2).add(new Edge(3, 8));
        graph.get(2).add(new Edge(4, 10));
        graph.get(3).add(new Edge(5, 2));  // hospital
        graph.get(4).add(new Edge(5, 3));  // hospital

        int source = 0; // ambulance location

        dijkstra(graph, source, n);
    }
}


Shortest travel time from Source 0 to all intersections:
To node 0 → 0 mins
To node 1 → 4 mins
To node 2 → 2 mins
To node 3 → 9 mins
To node 4 → 12 mins
To node 5 → 11 mins
