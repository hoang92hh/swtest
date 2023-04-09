package pairs;


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
        System.out.println("---time start --- " + LocalTime.now());
        Scanner sc = new Scanner(new FileInputStream("pairs2.txt"));
//        Scanner sc =  new Scanner(new FileInputStream("pairs.txt"));
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
                    arrExtend[j][arr[j][k] - 1] = k + 1;
                }

            }

            list = new int[R];
            for (int j = 0; j < R; j++) {
                list[j] = (j + 1);
            }

            getMinPoint();
            getMaxPoint();
            System.out.println("#" + tc + " " + minPoint + " " + (R * sumOfCase - minOfMaxPoint));


        }
        System.out.println("---time end --- " + LocalTime.now());


    }

    private static void getMinPoint() {
        minPoint = 0;

        Stack<Integer> minStack = new Stack<>();
        Stack<int[]> neiborStack = new Stack<>();
        Stack<Integer> rowStack = new Stack<>();

        for (int i = 0; i < list.length; i++) {
            minStack.push(arrExtend[0][i]);
            neiborStack.push(deletePerson(list, list[i]));
            rowStack.push(1);
            handleMinDFS(minStack, neiborStack, rowStack);

        }


    }


    private static void getMaxPoint() {
        Stack<Integer> minOfMaxStack = new Stack<>();
        Stack<int[]> neiborStack = new Stack<>();
        Stack<Integer> rowStack = new Stack<>();


        minOfMaxPoint = 0;
        sumOfCase = R*(R+1)/2;
        for (int i = 0; i <list.length ; i++) {
            minOfMaxStack.push(sumOfCase-arrExtend[0][list[i]-1]);
            neiborStack.push(deletePerson(list, list[i]));
            rowStack.push(1);
            handleMaxDFS(minOfMaxStack,neiborStack,rowStack);
        }

    }

    private static void handleMinDFS(Stack<Integer> minStack, Stack<int[]> neiborStack, Stack<Integer> rowStack) {
        while(!minStack.isEmpty()){
            int minOfCase = minStack.pop();
            int[] nextNeibor = neiborStack.pop();
            int row = rowStack.pop();

            //condition to backtrack
            if(minOfCase>=minPoint && minPoint !=0){
                continue;
            }
            //
            if(row==R){
                if(minOfCase<minPoint || minPoint==0){
                    minPoint = minOfCase;
                }
                continue;
            }

            for (int i = 0; i <nextNeibor.length ; i++) {
                int pointB = arrExtend[row][nextNeibor[i]-1];

                minStack.push(minOfCase+ pointB);
                neiborStack.push(deletePerson(nextNeibor, nextNeibor[i]));
                rowStack.push(row+1);
            }
        }
    }

    private static void handleMaxDFS(Stack<Integer> minOfMaxStack, Stack<int[]> neiborStack, Stack<Integer> rowStack) {
        while (!minOfMaxStack.isEmpty()) {
            //get element trong stack
            int minOfMaxCase = minOfMaxStack.pop();
            int[] nextNeibor = neiborStack.pop();
            int row = rowStack.pop();

            //condition to backtrack
            if(minOfMaxCase>=minOfMaxPoint && minOfMaxPoint !=0){
                continue;
            }
            //condition to set MinOfMaxPoint
            if(row==R){
                if(minOfMaxCase<minOfMaxPoint || minOfMaxPoint==0){
                    minOfMaxPoint = minOfMaxCase;
                }
                continue;
            }

            for (int i = 0; i <nextNeibor.length ; i++) {
                int pointB = arrExtend[row][nextNeibor[i]-1];

                minOfMaxStack.push(minOfMaxCase+ sumOfCase-pointB);
                neiborStack.push(deletePerson(nextNeibor, nextNeibor[i]));
                rowStack.push(row+1);
            }

        }
    }

    private static int[] deletePerson(int[] personlist, int personB) {
        int newLength = personlist.length - 1;
        int[] newArr = new int[newLength];
        boolean next = false;
        for (int i = 0; i < newLength; i++) {
            if (personlist[i] == personB) {
                next = true;
                newArr[i] = personlist[i + 1];
            } else if (next) {
                newArr[i] = personlist[i + 1];
            } else {
                newArr[i] = personlist[i];
            }
        }
        return newArr;

    }






}