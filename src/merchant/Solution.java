package merchant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.time.LocalTime;
import java.util.*;

public class Solution {
    private static int TC;
    private static int R, C;


    private static int[][] pieceArr;
    private static int[][] costArr;
    private static int currentPoint;
    private static int quantity;
    private static int profit;


    private static int maxDay;
    private static int maxRow = 100;
    private static String[] actionArr = {"product", "travel", "trade"};


    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("---time start --- " + LocalTime.now());
        Scanner sc = new Scanner(new FileInputStream("E:\\IT\\swtest\\src\\merchant\\merchant.txt"));


        TC = sc.nextInt();

        for (int tc = 0; tc < TC; tc++) {
            R = sc.nextInt();
            C = sc.nextInt();

            currentPoint = (sc.nextInt() - 1) * maxRow + (sc.nextInt() - 1);
            maxDay = sc.nextInt();
            quantity = sc.nextInt();

            pieceArr = new int[R][C];
            for (int i = 0; i < R; i++) {
                sc.nextLine();
                for (int j = 0; j < C; j++) {
                    pieceArr[i][j] = sc.nextInt();
                }
            }
            costArr = new int[R][C];
            for (int i = 0; i < R; i++) {
                sc.nextLine();
                for (int j = 0; j < C; j++) {
                    costArr[i][j] = sc.nextInt();

                }
            }

            getMaxSalary(tc);


        }


    }

    private static void getMaxSalary(int tc) {
        profit = 0;
        handleDSF(pieceArr, costArr, currentPoint, 0, quantity, profit);
        System.out.println("#" + tc + " " + profit);
    }

    private static void handleDSF(int[][] pieceArr, int[][] costArr, int currentPoint, int numberOfday, int quantity, int caseProfit) {
        if (numberOfday >= maxDay) {
            if (caseProfit > profit) {
                profit = caseProfit;
            }
            return;
        }

        numberOfday = numberOfday + 1;
        for (String action : actionArr) {

            if (action.equals("product")) {

                if (numberOfday < maxDay) {
                    int piece = pieceArr[currentPoint / maxRow][currentPoint % maxRow];
                    if (piece > 0) {
                        pieceArr[currentPoint / maxRow][currentPoint % maxRow] = piece / 2;
                        handleDSF(pieceArr, costArr, currentPoint, numberOfday, quantity + piece, caseProfit);
                        pieceArr[currentPoint / maxRow][currentPoint % maxRow] = piece;
                    }
                }
            }

            if (action.equals("travel")) {
                if (numberOfday < maxDay) {
                    int[] nextCurrent = getNextCurrent(currentPoint);
                    //nguy hiem boi vi gia tri
                    for (int i = 0; i < 4; i++) {
                        int next = nextCurrent[i];
                        if (next / maxRow >= 0 && next / maxRow < R && next % maxRow >= 0 && next % maxRow < C) {
                            handleDSF(pieceArr, costArr, next, numberOfday, quantity, caseProfit);
                        }
                    }

                }
            }
            if (action.equals("trade")) {
                if (quantity > 0) {
                    int cost = costArr[currentPoint / maxRow][currentPoint % maxRow];
                    if (cost >= 0) {
                        int newProfit = caseProfit+ quantity*cost;
                        costArr[currentPoint / maxRow][currentPoint % maxRow] = cost - 1;
                        handleDSF(pieceArr, costArr, currentPoint, numberOfday, 0, newProfit);
                        costArr[currentPoint / maxRow][currentPoint % maxRow] = cost;
                    }
                }

            }

        }

    }



    private static int[] getNextCurrent(int currentPoint) {
        int[] result = new int[4];
        result[0] = currentPoint - 1;
        result[1] = currentPoint + maxRow;
        result[2] = currentPoint + 1;
        result[3] = currentPoint - maxRow;
        return result;

        //luu y , neu dua bien nextCurrent vao trong ham de quy thi can phai setting lai gia tri ban dau sau khi goi de quy

    }

}