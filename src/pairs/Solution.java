package pairs;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        System.out.println("the start");
        int[][] arr = {
                {1, 2, 3, 4},
                {3, 2, 1, 4},
                {4, 1, 2, 3},
                {1, 4, 2, 3},
        };
        int[] listPerson = {1, 2, 3, 4};
        finDFS(arr, listPerson);

    }

    private static void finDFS(int[][] arr, int[] listPerson) {

        for (int e:listPerson
             ) {
        handleDFS(arr, listPerson, e);
        }


    }

    private static void handleDFS(int[][] arr, int[] listPerson, Integer element) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();
        Stack<int[]> minVisitedStack = new Stack<>();
        Stack<int[]> neighBorStack = new Stack<>();

        //khoi tao gia tri dau tien de duyet
        stack.push(element);
        minStack.push(0);
        //khoi tao gia tri neighbor cua phan tu dau tien
        int[] firstNeighbor = deleteElement(listPerson, element);
        neighBorStack.push(firstNeighbor);

        //khoi tao gia tri dau tien cho Case

        int minOfCase = 0;
        int length = listPerson.length;
        int[] minVisited = new int[0];


        //bat dau duyet cac phan tu trong stack
        while (!stack.isEmpty()) {

            Integer personNumber = stack.pop();
            minOfCase = minStack.pop();
            int[] neighborArr = neighBorStack.pop();
            if(!minVisitedStack.isEmpty()){
                minVisited = minVisitedStack.pop();
            }


            //tim person 2 phu hop  nhat , kem theo so diem.
            int minValue = 1;
            while (minValue <= length) {
                Integer person2 = arr[personNumber - 1][minValue - 1];
                if (!isExitsElement(minVisited, person2)) {
                    minVisited = addElement(minVisited,person2);
                    minOfCase = minOfCase + minValue;
                    System.out.println("PerSon A" + personNumber + " ghep doi voi PerSon B" + person2 + " co diem uu tien la " + minOfCase );
                    if(minVisited.length==length){
                        System.out.println("-----------------Thu thap du nguoi =--------------");
                    }
                    break;
                } else {
                    minValue ++;
                }

            }

            //tim next Neibor cho danh sach neighbor  va cac  param lan duyet tiep theo
            for (int ele:neighborArr
                 ) {
                stack.push(ele);
                minStack.push(minOfCase);
                minVisitedStack.push(minVisited);
                int[] neighBorOfNeighbor = deleteElement(neighborArr, ele);
                neighBorStack.push(neighBorOfNeighbor);
            }


        }

    }

    private static int[] addElement(int[] arr, Integer newElement) {
        int newLength = arr.length +1;
        int[] newArr = new int[newLength];
        for (int i = 0; i < arr.length ; i++) {
            newArr[i] = arr[i];

        }
        newArr[newLength-1] = newElement;
        return newArr;

    }

    private static boolean isExitsElement(int[] minVisited, Integer person2) {
        for (int i = 0; i < minVisited.length; i++) {
            if(minVisited[i]==person2){
                return true;
            }
        }
        return false;
    }

    private static int[] deleteElement(int[] arr, Integer element) {
        int newLength = arr.length -1;
        int[] newArr = new int[newLength];
        boolean next = false;
        for (int i = 0; i < newLength; i++) {
            if(arr[i]==element){
                next =true;
                newArr[i] = arr[i]+1;
            }else if(next){
                newArr[i] = arr[i]+1;
            }else{
                newArr[i] = arr[i];
            }
        }
        return newArr;

    }
}
