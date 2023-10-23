//Name : Vedant Ghumade
//TYCOA68
//Knapsack using greedy approach
import java.util.*;

class mapping{
    double pw;
    double weight;
    double obj;
    mapping(double pw, double weight,double obj){
        this.weight = weight;
        this.pw = pw;
        this.obj = obj;
    }
}

public class greedyKnapsack {
  public static void main(String[] args) {
        int n = 7;
        double m = 15;
        double p = 0;
        double[] obj = {1,2,3,4,5,6,7};
        double[] profit = {10,5,15,7,6,18,3};
        double[] weight = {2,3,5,7,1,4,1};
        double[] pdivw = new double[n];
        for(int i=0;i<n;i++){
            pdivw[i] = (profit[i]/weight[i]);
        } 
        List<mapping> arrmap = new ArrayList<>();
        for(int i=0;i<pdivw.length;i++){
            arrmap.add(new mapping(pdivw[i], weight[i],obj[i]));
        }
        Collections.sort(arrmap, new Comparator<mapping>() {
            // @Override
            public int compare(mapping m1, mapping m2) {
                return Double.compare(m2.pw, m1.pw); // Sort in descending order of pw
            }
        });
        System.out.println("\n");
        double[] include = new double[n];
        for(mapping mapping : arrmap){
            if(mapping.weight<=m && m>0){
                m = m - mapping.weight;
                p = p + (mapping.pw*mapping.weight);
                include[(int)(mapping.obj-1)] = 1;
            }
            else break;
        }
      
        for(mapping mapping : arrmap){
            if(include[(int)(mapping.obj-1)]==0 && m!=0){
               double fract = (m/mapping.weight);
               include[(int)(mapping.obj-1)] = fract; 
               m = m - fract*(mapping.weight);
               p = p + fract*(mapping.pw*mapping.weight);
            }
        }
        System.out.println("Objects to be included : ");
        System.out.print("[");
         for(int i=0;i<n;i++){
             System.out.print(String.format("%.2f",include[i])+" ");
        } 
        System.out.print("]");
        System.out.println("\nTotal Profit obtained is : "+String.format("%.2f",p));
    }
}
