abstract class A{
    public A(){
        this.print();
    }

    public abstract void print();
}

class C extends A{
    public void print(){
        System.out.println("This is C");
    }
}

class B extends A{
    private int num = 100;
    public B(int num){
        super();
        this.num = num;
    }
    public void print(){
        System.out.println("This is B");
        System.out.println(this.num);
    }
}



public class AbstractInterview{
    public static void main(String[] args) {
        // new B(30);  //0
        new B(30).print(); //0 30
    }
    
}