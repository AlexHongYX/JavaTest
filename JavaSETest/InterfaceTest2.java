interface USB{
    void plugIn();
    void setUp();
    void work();
}

class Mouse implements USB{
    public void plugIn(){
        System.out.println("鼠标插入");
    }
    public void setUp(){
        System.out.println("鼠标驱动加载");
    }
    public void work(){
        System.out.println("鼠标运行");
    }
}

class Keyboard implements USB{
    public void plugIn(){
        System.out.println("键盘插入");
    }
    public void setUp(){
        System.out.println("键盘驱动加载");
    }
    public void work(){
        System.out.println("键盘运行");
    }
}

class Computer{
    public void useUSB(USB usb){
        usb.plugIn();
        usb.setUp();
        usb.work();
    }
}

public class InterfaceTest2{
    
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.useUSB(new Mouse());
        computer.useUSB(new Keyboard());
    }
}
