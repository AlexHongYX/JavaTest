package string;

/**
 * Created by xiaoaxiao on 2019/11/4
 * Description: 有序数组k的个数
 */
public class GetK {

    public int GetNumberOfK(int [] array , int k) {
        int length = array.length;
        if(length==0){
            return 0;
        }
        int firstK = getFirstK(array,0,length-1,k);
        int lastK = getLastK(array,0,length-1,k);
        if(firstK!=-1&&lastK!=-1){
            return lastK-firstK+1;
        }
        return 0;
    }

    public int getFirstK(int[] array,int start,int end,int k){
        if(start>end){
            return -1;
        }
        int mid = (start+end)>>1;
        if(array[mid]<k){
            return getFirstK(array,mid+1,end,k);
        }else if(array[mid]>k){
            return getFirstK(array,start,mid-1,k);
        }else if(mid-1>start&&array[mid-1]==k){
            return getFirstK(array,start,mid-1,k);
        }else{
            return mid;
        }
    }

    public int getLastK(int[] array,int start,int end,int k){
        int mid = (start+end)>>1;
        while(start<=end){
            if(array[mid]<k){
                start = mid+1;
            }else if(array[mid]>k){
                end = mid-1;
            }else if(mid+1<=end&&array[mid+1]==k){
                start = mid+1;
            }else{
                return mid;
            }
            mid = (start+end)>>1;
        }
        return -1;
    }

    public static void main(String[] args) {
        GetK getK = new GetK();
        int[] array = {3,3,3,3};
        System.out.println(getK.GetNumberOfK(array,3));
    }
}
