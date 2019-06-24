abstract class A{
    public A(){
        this.print();
    }

    public abstract void print();
}

class B extends A{
    private int num = 100;
    public B(int num){
        super();
        this.num = num;
    }
    public void print(){
        System.err.println(this.num);
    }
}

public class AbstractInterview{
    public static void main(String[] args) {
        // new B(30);  //0
        new B(30).print();
    }
    
}