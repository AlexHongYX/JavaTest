import java.util.Arrays;

public class Test20190519{
	
	
	public static void main(String[] args){
		
		int[] array = new int[5];
		
		for(int i=0;i<array.length;i++){
			array[i]=i;
		}
		
		int max = max(array);
		
		System.out.println("max="+max);
		
		int index = indexOf(array,3);
		System.out.println("index="+index);
		
		double ave = average(array);
		System.out.println("ave="+ave);
		
		System.out.println("newLength>length:"+Arrays.toString(copyOf(array,10)));
		System.out.println("newLength<length:"+Arrays.toString(copyOf(array,5)));
		
		int[] dest = {1,2,3};
		arraycopy(array,0,dest,0,3);
		System.out.println("dest="+Arrays.toString(dest));
	}
	
	public static int max(int[] array){
		
		//把max赋值为Integer.MIN_VALUE
		
		int max = array[0];
		
		for(int arr:array){
			if(arr>max){
				max = arr;
			}
		}
		return max;
	}
	
	public static int indexOf(int[] array,int key){
		for(int i=0;i<array.length;i++){
			if(array[i]==key){
				return i;
			}
		}
		return -1;
		
	}
	
	public static double average(int[] scores){
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		max = min = scores[0];
		
		for(int i=0;i<scores.length;i++){
			if(scores[i]>max){
				max = scores[i];
			}
			if(scores[i]<min){
				min = scores[i];
			}
		}
		
		int sum = 0;
		
		for(int i=0;i<scores.length;i++){
			if(scores[i]==max||scores[i]==min){
				continue;
			}
			sum += scores[i];
		}
		
		return sum*1.0/(scores.length-2);
	}
	
	public static int[] copyOf(int[] src,int newLength){
		//newLength可能大于src.length
		//newLength可能小于等于src.length
		int[] ret = new int[newLength];
		/*
		if(newLength>src.length){
			for(int i=0;i<newLength;i++){
				if(i<src.length){
					ret[i] = src[i];
				}else{
					ret[i] = 0;
				}	
			}
		}else{
			for(int i=0;i<newLength;i++){
				ret[i] = src[i];	
			}
		}
		*/
		//src.length和newLength谁短以谁做基准
		int minLength = src.length<newLength?src.length:newLength;
		//利用默认值的特性每个值都是0
		for(int i=0;i<minLength;i++){
			ret[i] = src[i];
		}
		return ret;
	}
	
	public static void arraycopy(int[] src,int srcPos,int[] dest,int destPos,int length){
		for(int i=srcPos;i<srcPos+length;i++){
			dest[destPos++] = src[i];
		}
	}
}