//Name: Prashant Kamble
//PRN : 124B2F002

// Title : Scenario: Disaster Relief Resource Allocation
// A massive earthquake has struck a remote region, and a relief organization is transporting
// essential supplies to the affected area. The organization has a limited-capacity relief truck that
// can carry a maximum weight of W kg. They have N different types of essential items, each
// with a specific weight and an associated utility value (importance in saving lives and meeting
// urgent needs).
// Since the truck has limited capacity, you must decide which items to include to maximize the
// total utility value while ensuring the total weight does not exceed the truck's limit.
// Your Task as a Logistics Coordinator:
// 1. Model this problem using the 0/1 Knapsack approach, where each item can either be
// included in the truck (1) or not (0).
// 2. Implement an algorithm to find the optimal set of items that maximizes utility while
// staying within the weight constraint.
// 3. Analyze the performance of different approaches (e.g., Brute Force, Dynamic
// Programming, and Greedy Algorithms) for solving this problem efficiently.
// 4. Optimize for real-world constraints, such as perishable items (medicines, food) having
// priority over less critical supplies.
// Extend the model to consider multiple trucks or real-time decision-making for dynamic supply
// chain management.


public class DisasterReliefEasy {

    public static void main(String[] args) {
        // Items: Food, Medicine, Blanket
        int[] weight = {10, 20, 30};   // weight in kg
        int[] value = {60, 100, 120};  // utility value
        int capacity = 50;             // truck can carry 50 kg

        int n = weight.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Build table dp[n][capacity]
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(value[i - 1] + dp[i - 1][w - weight[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("🚚 Maximum Utility Value: " + dp[n][capacity]);

        // Find which items are selected
        System.out.println("✅ Items Selected:");
        int w = capacity;
        for (int i = n; i > 0 && dp[i][w] > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println("Item " + i + " (Weight: " + weight[i - 1] + ", Value: " + value[i - 1] + ")");
                w -= weight[i - 1];
            }
        }
    }
}


o/p
🚚 Maximum Utility Value: 220
✅ Items Selected:
Item 3 (Weight: 30, Value: 120)
Item 2 (Weight: 20, Value: 100)





