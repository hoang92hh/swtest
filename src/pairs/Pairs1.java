package pairs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Pairs1 {
    private static int TC;
    private static int R;

    private static int[][] arr;
    private static int[][] arrExtend;
    private static Integer[] list;
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

            list = new Integer[R];
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
        Stack<Map<String,Object>> stack = new Stack<>();

        for (int i = 0; i < list.length; i++) {
            Map<String,Object> map = new HashMap<>() ;
            map.put("min", arrExtend[0][i]);
            map.put("neibor", deletePerson(list, list[i]));
            map.put("row", 1);
            stack.push(map);

            handleMinDFS(stack);

        }


    }


    private static void getMaxPoint() {
        minOfMaxPoint = 0;
        sumOfCase = R*(R+1)/2;

        Stack<Map<String,Object>> stack = new Stack<>();

        for (int i = 0; i <list.length ; i++) {
            Map<String,Object> map = new HashMap<>() ;
            map.put("min", sumOfCase-arrExtend[0][i]);
            map.put("neibor", deletePerson(list, list[i]));
            map.put("row", 1);
            stack.push(map);

            handleMaxDFS(stack);

        }

    }

    private static void handleMinDFS(Stack<Map<String, Object>> stack) {
        while(!stack.isEmpty()){
            Map<String, Object> map = stack.pop();

            Integer minOfCase = (Integer) map.get("min");
            Integer[] nextNeibor = (Integer[]) map.get("neibor");
            Integer row = (Integer) map.get("row");

            //condition to backtrack
            if(minOfCase>=minPoint && minPoint !=0){
                continue;
            }
            //Condition to set minPoint
            if(row==R){
                if(minOfCase<minPoint || minPoint==0){
                    minPoint = minOfCase;
                }
                continue;
            }

            for (int i = 0; i <nextNeibor.length ; i++) {
                int pointB = arrExtend[row][nextNeibor[i]-1];
                Map<String, Object> nextMap = new HashMap<>();
                nextMap.put("min", minOfCase+pointB);
                nextMap.put("neibor", deletePerson(nextNeibor, nextNeibor[i]));
                nextMap.put("row", row+1);
                stack.push(nextMap);

            }
        }
    }

    private static void handleMaxDFS(Stack<Map<String, Object>> stack) {
        while (!stack.isEmpty()) {

            Map<String, Object> map = stack.pop();

            Integer minOfMaxCase = (Integer) map.get("min");
            Integer[] nextNeibor = (Integer[]) map.get("neibor");
            Integer row = (Integer) map.get("row");

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
                Map<String, Object> nextMap = new HashMap<>();
                nextMap.put("min", minOfMaxCase+ sumOfCase-pointB);
                nextMap.put("neibor", deletePerson(nextNeibor, nextNeibor[i]));
                nextMap.put("row", row+1);
                stack.push(nextMap);

            }

        }
    }

    private static Integer[] deletePerson(Integer[] personlist, int personB) {
        int newLength = personlist.length - 1;
        Integer[] newArr = new Integer[newLength];
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
