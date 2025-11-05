// Name: Prashant Kamble
// PRN : 124B2F002

// Title : Scenario: Emergency Relief Supply Distribution
// A devastating flood has hit multiple villages in a remote area, and the government, along

// with NGOs, is organizing an emergency relief operation. A rescue team has a limited-
// capacity boat that can carry a maximum weight of W kilograms. The boat must transport

// critical supplies, including food, medicine, and drinking water, from a relief center to the
// affected villages.
// Each type of relief item has:
// ● A weight (wi) in kilograms.
// ● Utility value (vi) indicating its importance (e.g., medicine has higher value than food).
// ● Some items can be divided into smaller portions (e.g., food and water), while others must
// be taken as a whole (e.g., medical kits).
// As the logistics manager, you must:
// 1. Implement the Fractional Knapsack algorithm to maximize the total utility value of the
// supplies transported.
// 2. Prioritize high-value items while considering weight constraints.
// 3. Allow partial selection of divisible items (e.g., carrying a fraction of food packets).
// 4. Ensure that the boat carries the most critical supplies given its weight limit W.

    
import java.util.*;

class Item {
    String name;
    double weight;
    double value;
    boolean divisible;

    Item(String name, double weight, double value, boolean divisible) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.divisible = divisible;
    }

    double ratio() {
        return value / weight;
    }
}

public class FractionalKnapsackRelief {

    public static double maximizeUtility(List<Item> items, double capacity) {
        items.sort((a, b) -> Double.compare(b.ratio(), a.ratio()));

        double totalValue = 0.0;
        double remainingCapacity = capacity;

        System.out.println("Selected items for transport:");
        for (Item item : items) {
            if (remainingCapacity <= 0) break;

            if (item.weight <= remainingCapacity) {
                totalValue += item.value;
                remainingCapacity -= item.weight;
                System.out.println("- " + item.name + " (100%) | value = " + item.value);
            } else if (item.divisible) {

                double fraction = remainingCapacity / item.weight;
                double valueTaken = item.value * fraction;
                totalValue += valueTaken;
                System.out.println("- " + item.name + " (" + (fraction * 100) + "%) | value = " + valueTaken);
                remainingCapacity = 0;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Medical Kits", 10, 500, false));
        items.add(new Item("Food Packets", 20, 300, true));
        items.add(new Item("Drinking Water", 15, 200, true));
        items.add(new Item("Blankets", 25, 150, false));
        items.add(new Item("Baby Formula", 5, 180, false));

        double boatCapacity = 40; 

        double maxValue = maximizeUtility(items, boatCapacity);
        System.out.println("\n Maximum total utility value carried: " + maxValue);
    }
}

#Output

    Selected items for transport:
- Medical Kits (100%) | value = 500.0
- Baby Formula (100%) | value = 180.0
- Food Packets (60.0%) | value = 180.0

🚤 Maximum total utility value carried: 860.0




