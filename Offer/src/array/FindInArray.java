package array;

/**
 * Created by xiaoaxiao on 2019/11/12
 * Description: 二维数组中查找
 */
public class FindInArray {
    private int i = 0;
    private int j = 0;
    private int[][] arrays;
    public boolean Find(int target, int [][] array) {
        arrays = array;
        return FindSame(target,i,j);
    }

    public boolean FindSame(int target,int i,int j){
        if(i>=arrays.length||j>=arrays[i].length){
            return false;
        }
        // 如果目标值等于当前值，则返回true
        if(target==arrays[i][j]){
            return true;
        }
        // 如果目标值小于当前值，说明目标值应该在当前值的左边或上面，而当前值是前一个值由左边或上面移动过来的
        // 因此前一个值都没判断相等并返回。说明该值处于当前值与其前一个值的中间，也就是在该二维数组中并不存在
        // 因此直接返回false即可。
        if(target<arrays[i][j]){
            return false;
        }
        return FindSame(target,i+1,j)||FindSame(target,i,j+1);
    }

    public static void main(String[] args) {
        FindInArray findInArray = new FindInArray();
        int[][] arr = { {1,2,3,4},
                        {5,6,7,8},
                        {9,10,11,12},
                        {13,14,15,16}};
        System.out.println(findInArray.Find(12,arr));
    }
}
