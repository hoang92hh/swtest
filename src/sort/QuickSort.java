package sort;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class QuickSort {

    private int[] arr ;

    public static void main(String[] args) {
    Integer[] arr = {1,5,2,4,2,6,8,1,3};
    quickSort(arr);
    System.out.println(Arrays.toString(arr));

    }

    public static Object[] quickSort( Object[] arr){
        String value = "0";
        if(arr[0] instanceof String){
            value = "1";
        }
        if(arr[0] instanceof Integer){
            value = "2";
        }
        switch (value){
            case  "1": return  handleSortString(arr);
            case "2" : return handleSortInteger(arr);
            default:
        }
        return arr;
    }

    private static Object[] handleSortInteger(Object[] arr) {
        int left = 0;
        int right =arr.length -1;
        int pivot = 0;
        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();
        leftStack.push(left);
        rightStack.push(right);

        while (!leftStack.isEmpty()){
            int leftPoint = leftStack.pop();
            int rightPoint = rightStack.pop();

            int i = leftPoint;
            int j = rightPoint;
            Integer value = (Integer) arr[rightPoint];

            while (i <= j) {
                while ((Integer)arr[i] < value) {
                    i++;
                }

                while ((Integer)arr[j] > value) {
                    j--;
                }

                if (i <= j) {
                    int temp = (Integer) arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }else{
                    while(j>0?true:false){
                        if((Integer)arr[j]< (Integer) arr[j-1]){
                            int temp = (Integer) arr[j];
                            arr[j] = arr[j-1];
                            arr[j-1] = temp;
                            j--;
                        }else{break;}


                    }
                }

            }
            if(leftPoint<j){
                leftStack.push(leftPoint);
                rightStack.push(j);
            }
            if(i<rightPoint){
                leftStack.push(i);
                rightStack.push(rightPoint);
            }

            System.out.println("----Duyet xong 1 phan tu trong stack");
        }

        return arr;
    }



    private static Object[] handleSortString(Object[] arr) {

        return arr;
    }

    private int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin-1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swapTemp;

        return i+1;
    }


}
