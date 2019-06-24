public class MainTest{
	public static void main(String[]args){
		learnStringAdd();
		learnUnicode();
		learnTypeCast();
	}
	
	public static void learnStringAdd(){
		String a = "我爱";
		String b = "中国";
		
		System.out.println("结果是"+a+b);
		//System.out.println("结果是"+(a+b));
	}
	
	public static void learnTypeCast(){
		long a=100L;
		int b=100;
		
		int c = (int)a;
		long d = b;
		System.out.println(c);
		System.out.println(d);
	}
	
	public static void learnUnicode(){
		char a = '中';
		char b = '\u4e2d';
		System.out.println(a);
		System.out.println(b);
	}
}