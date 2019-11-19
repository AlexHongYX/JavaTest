package test;

/**
 * Created by xiaoaxiao on 2019/11/19
 * Description:
 */
public class QuickAndBubbleSort {

    public static void main(String[] args) {
        int[] array = {2, 9, 5, 6, 3, 4, 8, 7, 5, 6, 1};
//        quick(array);
//        bubbleSort(array);
        heapSort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void quick(int[] array) {
        quickRec(array, 0, array.length - 1);
    }

    private static void quickRec(int[] array, int start, int end) {
        int par = partion(array, start, end);
        if (par > start + 1) {
            quickRec(array, start, par - 1);
        }
        if (par < end - 1) {
            quickRec(array, par + 1, end);
        }
    }

    private static int partion(int[] array, int start, int end) {
        int tmp = array[start];
        while (start < end) {
            while (start < end && array[end] >= tmp) {
                end--;
            }

            if (start < end) {
                array[start] = array[end];
            } else {
                break;
            }

            while (start < end && array[start] < tmp) {
                start++;
            }

            if (start < end) {
                array[end] = array[start];
            }

        }

        array[start] = tmp;
        return start;
    }

    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

//    public static void heapSort(int[] array) {
//        for (int i = array.length / 2 - 1; i >= 0; i--) {
//            adjustDown(array, array.length, i);
//        }
//
//        for (int i = array.length - 1; i > 0; i--) {
//            int tmp = array[0];
//            array[0] = array[i];
//            array[i] = tmp;
//
//            // 传入的i就是边界值
//            // eg：i==array.length - 1，此时已经将array.length - 1位置上的值与array[0]进行了交换
//            // 此时的array.length - 1位置上就是最大值，这个位置上的值已经确定了，因此该位置可以作为边界
//            // 就相当于完整数组传入的array.length
//            adjustDown(array, i, 0);
//        }
//    }
//
//    private static void adjustDown(int[] array, int length, int i) {
//        int parent = i;
//        int child = parent * 2 + 1;
//        while (child < length) {
//            if (child + 1 < length && array[child + 1] > array[child]) {
//                child++;
//            }
//            if (array[parent] < array[child]) {
//                int tmp = array[parent];
//                array[parent] = array[child];
//                array[child] = tmp;
//
//                parent = child;
//                child = parent * 2 + 1;
//            } else {
//                break;
//            }
//        }
//    }
    public static void heapSort(int[] array){
        for (int i = array.length/2-1; i >= 0; i--) {
            adjustDown(array,array.length,i);
        }

        for (int i = array.length-1; i > 0; i--) {
            int tmp = array[i];
            array[i] = array[0];
            array[0] = tmp;

            adjustDown(array,i,0);
        }
    }

    private static void adjustDown(int[] array, int length, int i) {
        int parent = i;
        int child = parent*2+1;
        while (child<length){
            if(child+1<length&&array[child+1]>array[child]){
                child++;
            }

            if(array[child]>array[parent]){
                int tmp = array[parent];
                array[parent] = array[child];
                array[child] = tmp;

                parent = child;
                child = parent*2+1;
            }else {
                break;
            }
        }
    }


}
