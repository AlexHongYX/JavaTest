public class Test201905193{
	
	public static void main(String[] args){
		
		Group group = new Group("java10班",50);
		
		group.print("欢迎来到比特,");
		
		B b = new B(1,2,3);
		b.print();
	}
}

class Group{

	public String className;
	public int classPersonNumber;
	
	public Group(String className,int classPersonNumber){
		this.className = className;
		this.classPersonNumber = classPersonNumber;
	}
	
	void print(String welcome){
		System.out.println(welcome+className+"的"+classPersonNumber+"位同学");
	}
}

class B{
	
	int sum;
	
	B(int a,int b,int c){
		System.out.println(a+b+c);
		sum = a+b+c;
	}
	
	void print(){
		System.out.println(sum);
	}
}