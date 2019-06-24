import java.util.Arrays;

public class Test201905192{
	
	public static void main(String[] args){
		int[] arrays = {8,2,3,9,5,2,7};
		int key = 4;
		int ret = binarySearch(arrays,key);
		System.out.printf("%d的下标为%d%n",key,ret);
		bubbleSort(arrays);
		System.out.println(Arrays.toString(arrays));
	}
	
	public static int binarySearch(int[] arrays,int key){
		//left和right的左闭右闭
		int left=0;
		int right=arrays.length-1;
		int mid=0;
		//循环的条件一定是有=，因为左闭右闭，（=时还有数）
		while(left<=right){
			//mid = (left+right)/2;
			mid = left + (right-left)/2;
			if(arrays[mid]<key){
				left = mid+1;
			}else if(arrays[mid]>key){
				right = mid-1;
			}else{
				return mid;
			}
		}
		return -1;
	}
	
	public static void bubbleSort(int[] arrays){
		//减治算法
		for(int i=0;i<arrays.length;i++){
			//除了保证j在无序区间内，也要保证j+1在无序区间内
			boolean isSwapped = false;
			
			for(int j=0;j<arrays.length-i-1;j++){
				if(arrays[j]>arrays[j+1]){
					swap(arrays,j,j+1);
					/*int tmp = arrays[j];
					arrays[j] = arrays[j+1];
					arrays[j+1] = tmp;*/
					isSwapped = true;
				}
			}
			
			//若一次完整的冒泡过程中，一次交换都没有发生
			//表明数组已经有序，所以排序完成
			if(!isSwapped){
				break;
			}
		}
	}
	
	//交换array数组的i和j下标位置的数
	//因为修改array[i]对应的修改就是引用共同指向的数据
	//所以，修改时可以生效的
	public static void swap(int[] arrays,int i,int j){
		int t = arrays[i];
		arrays[i] = arrays[j];
		arrays[j] = t;
	}
}