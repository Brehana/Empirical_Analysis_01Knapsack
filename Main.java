/*
 * Driver code for empirical analysis of two solutions to the  01-Knapsack Problems
 * 
 * @author Miguel Ramos
 * @version March 6, 2024
 */
public class Main {
    public static void main(String[] args) {

        //Generating a test set of Items
        int NUM_ITEMS = 10;
        int WEIGHT_THRESHOLD = 30;
        int[] values = new int[NUM_ITEMS];
        int[] weights = new int[NUM_ITEMS];
        System.out.println("The Set: {");
        for(int i = 0; i < NUM_ITEMS; i++){
            Item theItem = new Item().RandomItem(10, 10);
            values[i] = theItem.getValue();
            weights[i] = theItem.getWeight();
            System.out.printf("[%d]: ", i);
            theItem.display();
        }
        System.out.printf("}\n", args);

        //Testing algorithms
        KnapsackBottomUpDP dp = new KnapsackBottomUpDP();
        KnapsackBruteForce bf = new KnapsackBruteForce();

        long startTime = System.nanoTime();
        Object[] optimalSet = dp.solve(values, weights, WEIGHT_THRESHOLD);
        long endTime = System.nanoTime();

        System.out.println("[Dynamic Programming Results]");
        System.out.println("Output:");
        for(int i = 0; i < optimalSet.length; i++){
            System.out.println(optimalSet[i]);
        }
        System.out.printf("Time to compute: %dns\n", endTime - startTime);


        startTime = System.nanoTime();
        Object[] bfOptimalSet = bf.solve(values, weights, WEIGHT_THRESHOLD);
        endTime = System.nanoTime();
        System.out.println("[Brute Force Results]");
        System.out.println("Output:");
        for(int i = 0; i < bfOptimalSet.length; i++){
            System.out.println(bfOptimalSet[i]);
        }
        System.out.printf("Time to compute: %dns\n", endTime - startTime);
    }
}
