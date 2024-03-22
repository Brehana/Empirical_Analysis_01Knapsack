import java.util.Random;
/*
 * Abstract Data Type representing an Item for the 01-Knapsack Problem.
 * 
 * @author Miguel Ramos
 * @version March 6, 2024
 */

public class Item {

    private int value;
    private int weight;

    Item() {
        value = 0;
        weight = 0;
    }

    Item(int theValue, int theWeight) {
        value = theValue;
        weight =  theWeight;
    }
    public Item RandomItem(int theMaxValue, int theMaxWeight){
        Random rand = new Random();

        int randvalue = rand.nextInt(theMaxValue) + 1;
        int randweight = rand.nextInt(theMaxValue) + 1;

        return new Item(randvalue,randweight);
    }
    public void copy(Item toCopy){
        value = toCopy.value;
        weight = toCopy.weight;

    }

    public void display() {
        System.out.printf("[value: %d | weight: %d]\n", value, weight);
    }

    public int getWeight(){
        return weight;
    }

    public int getValue(){
        return value;
    }

}
