import java.util.ArrayList;
/*
 * Solves the 0-1 Knapsack Problem by way of botttom up dynamic programming.
 * 
 * @author Miguel Ramos
 * @version March 6, 2024
 */
public class KnapsackBottomUpDP {
    static int[][] table;

    KnapsackBottomUpDP(){};
    /*
     * Computes subset of Items with optimal value within a weight threshold using a
     * Bottom Up Dynamic Programming approach.
     * 
     * @param values - an array of item values
     * @param weights - an array of item weights
     * @param W - weight threshold
     * 
     * @return - an array of indices indicating which items are in the optimal subset.
     */
    public Object[] solve(int[] values, int[] weights, int W){
        int numItems = values.length;
        ArrayList<Integer> optimalItemIndices = new ArrayList<Integer>();
        table = new int[numItems + 1][W + 1];
        
        //Formatting into Item objects. Redundant, but good for my own readability.
        Item itemSet[] = new Item[numItems];
        for(int i = 0; i < numItems; i++){
            itemSet[i] = new Item(values[i], weights[i]);
        }
        //Fill out table to determine value of optimal subset
        for(int i = 0; i < numItems + 1; i++){
            for(int j = 0; j < W + 1; j++){
                if(j == 0 || i == 0){
                    table[i][j] = 0;
                }
                else{
                    table[i][j] = valueAtPosition(i, j, new Item(values[i - 1], weights[i - 1]));
                }  
            }
        }
        //Backtrace to determine optimal subset.
        int remainingCapacity = W;
        for(int i = numItems - 1; i >= 0; i--){
            //if there is still room in the knapsack...
            if(remainingCapacity - itemSet[i].getWeight() >= 0)
            {
                //...Check if value above current value in table is equal
                if(i > 0){
                    //...if yes, move to upper value in table
                    if(table[i + 1][remainingCapacity] == table[i][remainingCapacity]){
                        continue;
                    }
                }
                //...if no, add index to return set
                optimalItemIndices.add(i);
                remainingCapacity -= itemSet[i].getWeight();
            }
        }
        return optimalItemIndices.toArray();
    }

    private int valueAtPosition(int i,int j, Item theItem){
        if(i <= 0 || j <= 0){
            return 0;
        }
        if(j - theItem.getWeight() < 0){
            return table[i-1][j];
        }
        else{
            return Math.max(table[i-1][j], theItem.getValue() + table[i-1][j-theItem.getWeight()]);
        }
    }


}
