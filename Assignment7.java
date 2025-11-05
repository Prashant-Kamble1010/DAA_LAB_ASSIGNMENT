
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
// 1. Model the problem as a graph and implement a graph coloring algorithm (e.g., Greedy
// Coloring or Backtracking).
// 2. Minimize the number of colors (exam slots) needed while ensuring conflict-free
// scheduling.
// 3. Handle large datasets with thousands of courses and students, optimizing performance.
// 4. Compare the efficiency of Greedy Coloring, DSATUR, and Welsh-Powell algorithms
// for better scheduling.
// Extend the solution to include room allocation constraints where exams in the same slot should
// fit within available classrooms.

    
import java.util.*;
public class UniversityTimetable {
    static class Graph {
        int V; 
        List<Integer>[] adj;
        @SuppressWarnings("unchecked")
        Graph(int V) {
            this.V = V;
            adj = new ArrayList[V];
            for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        }
        void addEdge(int u, int v) {
            adj[u].add(v);
            adj[v].add(u); 
        }
        int[] greedyColoring(int[] roomCapacities, int[] courseSizes) {
            int[] result = new int[V]; 
            Arrays.fill(result, -1);
            boolean[] available = new boolean[V]; 
            Arrays.fill(available, true);
            
            result[0] = 0;
       
            for (int u = 1; u < V; u++) {
                Arrays.fill(available, true);
         
                for (int v : adj[u]) {
                    if (result[v] != -1) {
                        available[result[v]] = false;
                    }
                }
         
                int c;
                for (c = 0; c < V; c++) {
                    if (available[c]) break;
                }
                result[u] = c;
            }
       
            if (roomCapacities != null && courseSizes != null) {
                Map<Integer, Integer> slotLoad = new HashMap<>();
                for (int i = 0; i < V; i++) {
                    slotLoad.put(result[i], slotLoad.getOrDefault(result[i], 0) + courseSizes[i]);
                }
                for (int slot : slotLoad.keySet()) {
                    if (slotLoad.get(slot) > roomCapacities[slot]) {
                        System.out.println("Warning: Slot " + slot + " exceeds room capacity.");
                    }
                }
            }
            return result;
        }
        void printTimetable(int[] colors) {
            System.out.println("Course : Exam Slot");
            for (int i = 0; i < V; i++) {
                System.out.println("Course " + i + " -> Slot " + colors[i]);
            }
            int maxSlot = Arrays.stream(colors).max().orElse(0) + 1;
            System.out.println("Total slots used: " + maxSlot);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of courses ");
        int n = sc.nextInt();
        Graph g = new Graph(n);
        System.out.print("Enter number of conflicts ");
        int m = sc.nextInt();
        System.out.println("Enter conflict pairs ");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.addEdge(u, v);
        }
        System.out.print("Do you want to input room capacities?Enter (y/n) ");
        sc.nextLine();
        String ans = sc.nextLine();
        int[] roomCapacities = null;
        int[] courseSizes = null;
        if (ans.equalsIgnoreCase("y")) {
            roomCapacities = new int[n];
            courseSizes = new int[n];
            System.out.println("Enter course size (no. of students)");
            for (int i = 0; i < n; i++) courseSizes[i] = sc.nextInt();
            System.out.println("Enter room capacities per slot (max slots = number of courses)");
            for (int i = 0; i < n; i++) roomCapacities[i] = sc.nextInt();
        }
        int[] colors = g.greedyColoring(roomCapacities, courseSizes);
        g.printTimetable(colors);
    }
}

#Output
    Enter number of courses 5
Enter number of conflicts 4
Enter conflict pairs 
0 1
1 2
2 3
3 4
Do you want to input room capacities?Enter (y/n) y
Enter course size (no. of students)
30
25
20
5

40
Enter room capacities per slot (max slots = number of courses)
50
50
0
50
50
Warning: Slot 0 exceeds room capacity.
Course : Exam Slot
Course 0 -> Slot 0
Course 1 -> Slot 1
Course 2 -> Slot 0
Course 3 -> Slot 1
Course 4 -> Slot 0
Total slots used: 2


