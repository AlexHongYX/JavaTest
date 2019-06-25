interface IMyInterface{
    public static final String MSG = "test";
    public abstract void test();
}

class MyInterfaceImpl implements IMyInterface{
    public void test(){
        System.out.println(MSG);
    }
}

public class InterfaceTest{
    public static void main(String[] args) {
        IMyInterface iMyInterface = new MyInterfaceImpl();
        iMyInterface.test();
    }
  
}