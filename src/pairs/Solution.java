package pairs;


import sun.awt.EventQueueItem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.time.LocalTime;
import java.util.*;

public class Solution {
    private static int TC;
    private static int R;

    private static int[][] arr;
    private static int[] list;
    private static int minPoint;
    private static int minOfMaxPoint;
    private static int sumOfCase;





    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("---time start --- "+ LocalTime.now());
        Scanner sc =  new Scanner(new FileInputStream("pairs2.txt"));
//        Scanner sc =  new Scanner(new FileInputStream("pairs.txt"));
//        Scanner sc = new Scanner(System.in);
         TC = sc.nextInt();

        for (int tc = 1; tc <= TC; tc++) {
            R = sc.nextInt();

            arr = new int[R][R];
            for (int j = 0; j < R; j++) {
                sc.nextLine();
                for (int k = 0; k < R; k++) {
                    arr[j][k] = sc.nextInt();
//                    System.out.println(arr[j][k]);
                }

            }

            list = new int[R];
            for (int j = 0; j < R; j++) {
                list[j] = j + 1;
            }

            getMinPoint();
            getMaxPoint();
            System.out.println("#" + tc +" "+minPoint + " " + (R*sumOfCase-minOfMaxPoint) );



        }
        System.out.println("---time end --- "+ LocalTime.now());


    }

    private static void getMaxPoint() {
        minOfMaxPoint = 0;
        sumOfCase = R*(R+1)/2;
        handleMaxDFS(new int[R], 0, 0);
    }

    private static void handleMaxDFS(int[] visited, int minOfMaxCase, int row) {
        if(minOfMaxCase>=minOfMaxPoint && minOfMaxPoint !=0){
            return;
        }
        //condition to set MinPoint again
        if(row==R){
            if(minOfMaxCase<minOfMaxPoint || minOfMaxPoint==0){
                minOfMaxPoint = minOfMaxCase;
            }
            return;
        }
        for (int i = R-1; i >=0 ; i--) {
            int personB = arr[row][i];
            if(visited[personB-1]==0){
                visited[personB-1]=1;
                handleMaxDFS(visited, minOfMaxCase + (sumOfCase-i-1),  row+1);
                visited[personB-1]=0;
            }
        }
    }

    private static void getMinPoint() {
        minPoint = 0;
        handleMinDFS(new int[R], 0, 0);


    }

    private static void handleMinDFS(int[] visited, int minOfCase, int row) {
        //condition to backtrack
        if(minOfCase>=minPoint && minPoint !=0){
            return;
        }
        //condition to set MinPoint again
        if(row==R){
            if(minOfCase<minPoint || minPoint==0){
                minPoint = minOfCase;
            }
            return;
        }
        for (int i = 0; i <R ; i++) {
            int personB = arr[row][i];
            if(visited[personB-1]==0){
                visited[personB-1]=1;
                handleMinDFS(visited, minOfCase+ i+1,  row+1);
                visited[personB-1]=0;
            }

        }

    }





}
