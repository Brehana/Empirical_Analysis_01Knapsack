import java.util.ArrayList;
/*
 * Solves the 0-1 Knapsack problem by way of brute force.
 * 
 * @author Miguel Ramos
 * @version March 6, 2024
 */

public class KnapsackBruteForce {

    //Wrapper code to make my algorithm adhere to the input/output requirements of the assignment.
    public Object[] solve(int[] values, int[] weights, int W){
        Item[] items = new Item[values.length];
        for(int i = 0; i < values.length; i++){
            items[i] = new Item(values[i], weights[i]);
        }
        Item[] theSet = find_optimal_set(items, items.length, W);
        ArrayList <Integer> returnList = new ArrayList<Integer>();
        for(int i = 0; i < theSet.length; i++){
            if(theSet[i] != null){
                returnList.add(i);
            }
        }
        return returnList.toArray();
    }
    /*
     * Computes subset of Items with optimal value within a weight threshold using a
     * Brute Force approach.
     * 
     * @param items - the set of items from which to find an optimal subset
     * @param n - size of items set
     * @param w - weight threshold
     * 
     * @return - a subset of items of size n where selected items are included and unselected items are set to null.
     */
    private Item[] find_optimal_set(Item[] items, int n, int w){
        //Track optimal set so far and it's value
        int bestValue = 0;
        Item[] bestSet = new Item[n];
        //Compute ALL 2 to the power n subsets
        double size = Math.pow((double)2,(double)n);
        for(int i = 0; i < size; i++){
            //Compute subset i
            int setWeight = 0;
            int setValue = 0;
            Item[] set = new Item[n];
            for(int j = 0; j < n; j++){
                //Subsets are computed using the bitwise AND operation 
                if((i & (1 << j)) > 0){
                    set[j] = new Item();
                    set[j].copy(items[j]);
                    setWeight += items[j].getWeight();
                    setValue += items[j].getValue();
                }
            }
            //If value of subset i > current best value and it's weight is within the weight threshold, save it.
            if(setWeight <= w && setValue > bestValue){
                bestSet = set;
                bestValue = setValue;
            }
        }
        //After all subsets are computed, return saved subset.
        return bestSet;
    }

}
