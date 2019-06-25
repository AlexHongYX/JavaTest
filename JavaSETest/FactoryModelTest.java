import java.util.Scanner;

interface Computer{
    void printComputer();
}

class MacbookPro implements Computer{
    public void printComputer(){
        System.out.println("This is a mac");
    }
}

class SurfaceBook implements Computer{
    public void printComputer(){
        System.out.println("This is a surface");
    }
}

class Alienware implements Computer{
    public void printComputer(){
        System.out.println("This is a alien");
    }
}

class ComputerFactory{
    public static Computer getInstance(String type){
        if(type.equals("mac")){
            return new MacbookPro();
        }else if(type.equals("surface")){
            return new SurfaceBook();
        }else if(type.equals("alien")){
            return new Alienware();
        }
        return null;
    }
}

public class FactoryModelTest{
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择电脑：");
        String type = scanner.nextLine();
        Computer computer = ComputerFactory.getInstance(type);
        computer.printComputer();
    }
}