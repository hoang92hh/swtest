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
    private static int[][] arrExtend;
    private static int[] list;
    private static int minPoint;
    private static int minOfMaxPoint;
    private static int sumOfCase;





    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("---time start --- "+ LocalTime.now());
//        Scanner sc =  new Scanner(new FileInputStream("pairs2.txt"));
        Scanner sc =  new Scanner(new FileInputStream("pairs.txt"));
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
            arrExtend = new int[R][R];
            for (int j = 0; j < R; j++) {
                for (int k = 0; k < R; k++) {
                    arrExtend[j][arr[j][k]-1] =k+1;
                }

            }

            list = "";
            for (int j = 0; j < R; j++) {
                list +=(j+1);
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
        handleMaxDFS(list, 0, 0);
    }

    private static void handleMaxDFS(String personlist, int minOfMaxCase, int row) {
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
        for (int i = 0; i <personlist.length(); i++) {
            int personB = personlist.charAt(i);
            int pointB = getPointOfPerson(arr,row,personB);
            String newString = personlist.replace(String.valueOf(personB), "");
            handleMaxDFS(newString, minOfMaxCase + (sumOfCase-pointB),  row+1);

        }
    }

    private static int[] deletePerson(int[] personlist, int personB) {
        int newLength = personlist.length- 1;
        int[] newArr = new int[newLength];
        boolean next = false;
        for (int i = 0; i < newLength; i++) {
            if (personlist[i] == personB) {
                next = true;
                newArr[i] = personlist[i+1];
            } else if (next) {
                newArr[i] = personlist[i+1];
            } else {
                newArr[i] = personlist[i];
            }
        }
        return newArr;

    }

    private static int getPointOfPerson(int[][] arr, int row, int person) {
        for (int i = 0; i <R ; i++) {
            if(arr[row][i] == person) return i+1;
        }

        return 0;
    }

    private static void getMinPoint() {
        minPoint = 0;
        handleMinDFS(list, 0, 0);


    }

    private static void handleMinDFS(String personList, int minOfCase, int row) {
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
        for (int i = 0; i <personList.length(); i++) {
            int personB = personList.charAt(i);
            int pointB = getPointOfPerson(arr,row,personB);
            String  newString = personList.replace(String.valueOf(personB), "");
            handleMinDFS(newString,  minOfCase  + pointB,  row+1);
        }

    }





}
