/**
 *  简单工厂模式
 */
import java.util.Scanner;

interface Computer{
    void buyComputer();
}

class MacBookPro implements Computer{
    public void buyComputer(){
        System.out.println("This is MacBookPro");
    }
}

class AlienBook implements Computer{
    public void buyComputer(){
        System.out.println("This is AlienBook");
    }
}

class ComputerFactory{
    public Computer buyComputer(String type){
        if(type.equals("mac")){
            return new MacBookPro();
        }else if(type.equals("alien")){
            return new AlienBook();
        }
        return null;
    }
}


public class FactoryModelTest{
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input your choose:");
        String type = scanner.nextLine();
        ComputerFactory computerFactory = new ComputerFactory();
        Computer computer = computerFactory.buyComputer(type);
        if(computer!=null){
            computer.buyComputer();
        }else{
            System.out.println("No such computer");
        }
        
    }
}