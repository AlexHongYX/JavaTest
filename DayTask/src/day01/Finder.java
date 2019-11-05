package day01;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiaoaxiao on 2019/11/5
 * Description: 寻找第K大
 */
public class Finder {
    public int findKth(int[] a, int n, int K) {
        // write code here
        if (a.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : a){
            set.add(i);
        }
        int[] arr = new int[set.size()];
        int i = 0;
        for (int s : set){
            arr[i++] = s;
        }
        if(K>set.size()){
            return 0;
        }
        return find(arr, 0, arr.length - 1, K);
    }

    public int find(int[] a, int low, int high, int K) {
        int par = partion(a, low, high);
        if (a.length - par == K) {
            return a[par];
        }
        if (par >= low + 1 && a.length - par < K) {
            return find(a, low, par - 1, K);
        }
        if (par <= high - 1 && a.length - par > K) {
            return find(a, par + 1, high, K);
        }
        return 0;
    }

    public int partion(int[] a, int low, int high) {
        int tmp = a[low];
        while (low < high) {
            while (low < high && a[high] > tmp) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low] <= tmp) {
                low++;
            }
            a[high] = a[low];
        }
        a[low] = tmp;
        return low;
    }

    public static void main(String[] args) {
        Finder finder = new Finder();
        int[] a = {1, 3, 5, 2, 2,4,6,7,9,5};
        System.out.println(finder.findKth(a, 5, 3));
    }
}
