//Dynamic Knapsack for finding maximum nutrition value from given groceries with limited budget
import java.util.*;

class Mapping {
    double price;
    double nutritionalValuePerPrice;
    int item;

    Mapping(double price, double nutritionalValuePerPrice, int item) {
        this.price = price;
        this.nutritionalValuePerPrice = nutritionalValuePerPrice;
        this.item = item;
    }
}

public class dynamicKnapsack {
    public static void main(String[] args) {
        int n = 4;
        int budget = 8;
        String[] itemNames = {"Noodles", "Pasta", "Pastery", "Cake"};
        int[] items = { 1, 2, 3, 4 };
        double[] nutritionalValue = {1,2,5,6};
        double[] price = {2,3,4,5};

        List<Mapping> arrmap2 = new ArrayList<>();
        for (int i = 0; i < price.length; i++) {
            arrmap2.add(new Mapping(price[i], nutritionalValue[i], items[i]));
        }

        Collections.sort(arrmap2, new Comparator<Mapping>() {
            public int compare(Mapping m1, Mapping m2) {
                return Double.compare(m1.price, m2.price);
            }
        });
       

        double[][] dp = new double[n + 1][budget + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= budget; w++) {
                if (arrmap2.get(i - 1).price <= w) {
                    dp[i][w] = Math.max(arrmap2.get(i - 1).nutritionalValuePerPrice + dp[i - 1][w - (int)arrmap2.get(i - 1).price], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

       double maxValue  = dp[n][budget];
       int[] include = new int[n];
       double temp = 0;
       int x = 0;
        for(int i = n-1; i>=0 ; i--){
            boolean isIncluded = false;
            for(int j=budget; j>=0;j--){
                if(dp[i][j]>temp && arrmap2.get(i).price<=budget){
                    temp = dp[i][j];
                    budget = (int) (budget - arrmap2.get(i).price);
                    include[x] = arrmap2.get(i).item;
                    isIncluded = true;
                }
            }
            if(isIncluded){
                x++;
            }
            temp = 0;
        }
        Arrays.sort(include);
        System.out.println("Groceries to be included : ");
        for(int i=0;i<include.length;i++){
            if(include[i]!=0){
                int index = include[i];
                System.out.println(itemNames[index-1]);
            }
        }
       
        System.out.println("\nMaximum Nutritional Value Obtained: " + maxValue+" calorie units");
    }
}