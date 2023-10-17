//Name : Vedant Ghumade
//Roll No : TYCOA68
import java.util.Scanner;

public class knightTour{
    
    public static void formchessboard(int n){
          int[][] board = new int[n][n];
          boolean[][] occupy = new boolean[n][n];
          for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                occupy[i][j]= false;
            }
          }
          System.out.println("\nBefore knight tour : ");
          printchessboard(board, n);
          knighttour(board,0,0, occupy, 1,n);
         
    }

    public static void printchessboard(int[][] chess,int n){
        System.out.println("------------------------------------------------");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(chess[i][j]+"\t");
            }
            System.out.println("");
        }
    }
     
     static int flag = 1;

     static void knighttour(int[][] chess,int r, int c, boolean[][] occupy,int move, int n){
        if(r<0 ||c<0 || r>=chess.length || c>=chess.length){
            return;
        }
        if(occupy[r][c]==true){
            return;
        }
        if(move == n*n){
            chess[r][c] = move;
            if(flag==1){
            System.out.println("\nAfter knight tour : ");
            printchessboard(chess, n);
            flag++;
            System.exit(0);
            }
        }

        else if(occupy[r][c]==false){
            chess[r][c] = move;
            occupy[r][c] = true;
            knighttour(chess,r+1,c+2, occupy, move+1,n);
            knighttour(chess,r+2,c+1, occupy, move+1,n);
            knighttour(chess,r-1,c+2, occupy, move+1,n);
            knighttour(chess,r+2,c-1, occupy, move+1,n);
            knighttour(chess,r-2,c+1, occupy, move+1,n);
            knighttour(chess,r+1,c-2, occupy, move+1,n);
            knighttour(chess,r-1,c-2, occupy, move+1,n);
            knighttour(chess,r-2,c-1, occupy, move+1,n);
            occupy[r][c] = false;
            chess[r][c] = 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the dimensions of chessboard : ");
        int n = sc.nextInt();
        if(n<=4){
            System.out.println("\nInvalid Chessboard Dimensions!!");
        }
        else
        formchessboard(n);
        sc.close();
    }
}