package test;

/**
 * Created by xiaoaxiao on 2019/11/28
 * Description:
 */
public class Merge {
    public static void main(String[] args) {
        int[] arr1 = {1,3,5,7,9};
        int[] arr2 = {2,4,6,8,10};
        int[] ret = getArray(arr1,arr2);
        for (int i = 0; i < ret.length; i++) {
            System.out.print(ret[i]+" ");
        }
    }

    public static int[] getArray(int[] arr1,int[] arr2){
        if (arr1.length==0){
            return arr2;
        }
        if (arr2.length==0){
            return arr1;
        }
        int i = 0;
        int j = 0;
        int[] ret = new int[arr1.length+arr2.length];
        int index = 0;
        while (i<arr1.length&&j<arr2.length){
            if (arr1[i]<arr2[j]){
                ret[index++] = arr1[i++];
            }else {
                ret[index++] = arr2[j++];
            }
        }
        while (i<arr1.length){
            ret[index++] = arr1[i++];
        }
        while (j<arr2.length){
            ret[index++] = arr2[j++];
        }
        return ret;
    }
}
