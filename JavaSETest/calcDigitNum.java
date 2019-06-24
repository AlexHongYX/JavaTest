
public class calcDigitNum{
	public static int calcDigitNum(long n){
		int count=0;
		//只能是逻辑表达式，不能只写个n
		for(;n!=0;n/=10){
			count++;
		}
		return count;
	}

	public static void main(String[] args){
		int count = calcDigitNum(464498446);
		System.out.println(count);
		long ret = factorial(5);
		System.out.println(ret);
		double e = calcE(1E-4);
		System.out.println(e);
		double pi1 = calcPi(5000000);
		System.out.println(pi1);
		double pi2 = calcPi(1E-4);
		System.out.printf("%.8f%n",pi2);
		
		String message = "Hello";
		String r = reverse(message);
		System.out.println(r);
		
		String hex = decToHex(21954);
		System.out.println(hex);
		
	}
	
	//求阶乘
	public static long factorial(int n){
		if(n==1){
			return 1;
		}else{
			return factorial(n-1)*n;
		}
	}
	
	//求e
	public static double calcE(double precision){
		//precision代表的是精度：1E-4
		double sum=1;
		double tmp=0;
		/*
		int tmp = 1;
		for(int i=1;i<=n;i++){
			tmp *= i;
			sum += 1.0/tmp;
		}
		*/
		for(int i=1;;i++){
			tmp = 1.0/factorial(i);
			
			//只要tmp<precision就代表精确到precision了，比这个0.0001还小的就不加了
			if(tmp<precision){
				break;
			}
			sum += tmp;
		}
		/*精度另一种算法
		int i = 0;
		while(true){
			double tmp = 1.0/factorial(i++);
			sum += item;
			if(item<precision){
				break;
			}
		}
		*/
		return sum;
	}
	
	//求PI1
	public static double calcPi(int n){
		//n代表多少项
		double pi = 0;
		for(int i=0;i<n;i++){
			if(i%2==0){
				pi += 1.0/(2*i+1); 
			}else{
				pi -= 1.0/(2*i+1);
			}
		}
		return pi*4;
	}
	
	//求PI2
	public static double calcPi(double precision){
		//n代表多少项
		double pi = 0;
		for(int i=0;;i++){
			if(1.0/(2*i+1)<precision){break;}
			if(i%2==0){
				pi += 1.0/(2*i+1); 
			}else{
				pi -= 1.0/(2*i+1);
			}
		}
		return pi*4;
	}
	
	//逆置字符串
	public static String reverse(String s){
		String r = "";
		for(int i=s.length()-1;i>=0;i--){
			r += s.charAt(i);
		}
		return r;
	}
	
	//10进制转16进制
	public static String decToHex(int n){
		String ret = "";
		int tmp;
		for(;n>=16;n/=16){
			tmp = n%16;
			if(tmp>9){
				char h = (char)('A'+tmp-10);
				ret = h + ret;
			}else{
				ret = tmp + ret;
			}
		}
		ret = n + ret;
		return ret;
	}
}
