/**
 * 工厂方法模式
 */

 interface Computer{
     void buyComputer();
 }

class MacbookPro implements Computer{
    public void buyComputer(){
        System.out.println("This is a mac");
    }
}

class AlienBook implements Computer{
    public void buyComputer(){
        System.out.println("This is a alien");
    }
}

interface ComputerFactory{
    Computer createComputer();
}

class AppleFactory implements ComputerFactory{
    public Computer createComputer(){
        return new MacbookPro();
    }
}

class MsFactory implements ComputerFactory{
    public Computer createComputer(){
        return new AlienBook();
    }
}

public class FactoryModelTest2{
    public static void main(String[] args) {
        ComputerFactory computerFactory = new MsFactory();
        Computer computer = computerFactory.createComputer();
        computer.buyComputer();
    }
}