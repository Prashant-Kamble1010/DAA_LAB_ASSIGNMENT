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

class Item
{
    String name;
    double weight;
    double value;
    boolean divisible;

    Item(String name, double weight, double value, boolean divisible)
    {
        this.name=name;
        this.weight=weight;
        this.value=value;
        this.divisible=divisible;
    }
}
public class FractionalKnapsack
{
    public static double maximizeUtility(List<Item> items, double capacity)
    {
        items.sort((a,b)->Double.compare(b.value/b.weight, a.value/a.weight));

        double totalValue=0, weight=0;
        for(Item i:items)
        {
            if(weight+i.weight<=capacity)
            {
                weight+=i.weight;
                totalValue+=i.value;
                System.out.println("Taking full: " + i.name);
            }
            else if(i.divisible)
            {
                double remain=capacity-weight;
                totalValue+=i.value*(remain/i.weight);
                System.out.println("Taking " + remain + "kg of: " + i.name);
                break;
            }
        }
        System.out.printf("Total Utility: %.2f (%.2f kg used of %.2f)\n", totalValue, weight, capacity);
        return totalValue;
    }
    public static void main(String[] args)
    {
        List<Item> items = Arrays.asList(
                new Item("Medicine",10,120,false),
                new Item("Food",20,100,true),
                new Item("Water",30,90,true),
                new Item("Blankets",15,60,false),
                new Item("Clothes",25,75,false)
        );

        System.out.println("Emergency Relief Optimization");
        double capacity=50;
        double result = maximizeUtility(items,capacity);
        System.out.println("✅ Maximum Utility Value: " + result);
    }
}


o/p
Emergency Relief Optimization
Taking full: Medicine
Taking full: Food
Taking full: Blankets
Taking 5.0kg of: Water
Total Utility: 295.00 (45.00 kg used of 50.00)
✅ Maximum Utility Value: 295.0





