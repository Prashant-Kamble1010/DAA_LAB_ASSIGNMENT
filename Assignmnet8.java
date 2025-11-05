//Name: Prashant Kamble
//PRN : 124B2F002

// Title : Scenario: University Timetable Scheduling
// A university is facing challenges in scheduling exam timetables due to overlapping student
// enrollments in multiple courses. To prevent clashes, the university needs to assign exam
// slots efficiently, ensuring that no two exams taken by the same student are scheduled at the
// same time.
// To solve this, the university decides to model the problem as a Graph Coloring Problem,
// where:
// ● Each course is represented as a vertex.
// ● An edge exists between two vertices if a student is enrolled in both courses.
// ● Each vertex (course) must be assigned a color (time slot) such that no two adjacent
// vertices share the same color (no two exams with common students are scheduled in the
// same slot).
// As a scheduling system developer, you must:
// 5. Model the problem as a graph and implement a graph coloring algorithm (e.g., Greedy
// Coloring or Backtracking).
// 6. Minimize the number of colors (exam slots) needed while ensuring conflict-free
// scheduling.
// 7. Handle large datasets with thousands of courses and students, optimizing performance.
// 8. Compare the efficiency of Greedy Coloring, DSATUR, and Welsh-Powell algorithms
// for better scheduling.
// Extend the solution to include room allocation constraints where exams in the same slot
// should fit within available classrooms.

    
import java.util.*;
public class SwiftShipTSP {
    static class Node implements Comparable<Node> {
        int level;         
        int pathCost;       
        int bound;          
        List<Integer> path; 
        Node(int level, int pathCost, int bound, List<Integer> path) {
            this.level = level;
            this.pathCost = pathCost;
            this.bound = bound;
            this.path = new ArrayList<>(path);
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.bound, other.bound); 
        }
    }
    static int calculateBound(Node node, int[][] costMatrix, int N) {
        int bound = node.pathCost;
        boolean[] visited = new boolean[N];
        for (int city : node.path) visited[city] = true;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                int minEdge = Integer.MAX_VALUE;
                for (int j = 0; j < N; j++) {
                    if (i != j && !visited[j]) {
                        minEdge = Math.min(minEdge, costMatrix[i][j]);
                    }
                }
                bound += (minEdge == Integer.MAX_VALUE) ? 0 : minEdge;
            }
        }
        return bound;
    }
    static List<Integer> tspBranchAndBound(int[][] costMatrix) {
        int N = costMatrix.length;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        List<Integer> bestPath = new ArrayList<>();
        int minCost = Integer.MAX_VALUE;
        Node root = new Node(0, 0, 0, new ArrayList<>(List.of(0)));
        root.bound = calculateBound(root, costMatrix, N);
        pq.add(root);
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.bound >= minCost) continue; 
            if (node.level == N - 1) {
             
                int last = node.path.get(node.path.size() - 1);
                if (costMatrix[last][0] > 0) {
                    int totalCost = node.pathCost + costMatrix[last][0];
                    if (totalCost < minCost) {
                        minCost = totalCost;
                        bestPath = new ArrayList<>(node.path);
                        bestPath.add(0); 
                    }
                }
                continue;
            }
          
            int lastCity = node.path.get(node.path.size() - 1);
            for (int nextCity = 0; nextCity < N; nextCity++) {
                if (!node.path.contains(nextCity) && costMatrix[lastCity][nextCity] > 0) {
                    List<Integer> newPath = new ArrayList<>(node.path);
                    newPath.add(nextCity);
                    int newCost = node.pathCost + costMatrix[lastCity][nextCity];
                    Node child = new Node(node.level + 1, newCost, 0, newPath);
                    child.bound = calculateBound(child, costMatrix, N);
                    if (child.bound < minCost) pq.add(child); 
                }
            }
        }
        System.out.println("Minimum cost: " + minCost);
        return bestPath;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of cities: ");
        int N = sc.nextInt();
        int[][] costMatrix = new int[N][N];
        System.out.println("Enter cost matrix (NxN):");
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                costMatrix[i][j] = sc.nextInt();
        List<Integer> optimalRoute = tspBranchAndBound(costMatrix);
        System.out.println("Optimal route:");
        for (int city : optimalRoute) {
            System.out.print(city + " -> ");
        }
        System.out.println("End");
    }
}

#Output
    Enter number of cities: 4
Enter cost matrix (NxN):
0 10 15 20
10 0 25 35
15 35 0 30
20 25 30 0
Minimum cost: 80
Optimal route:
0 -> 2 -> 3 -> 1 -> 0 -> End


