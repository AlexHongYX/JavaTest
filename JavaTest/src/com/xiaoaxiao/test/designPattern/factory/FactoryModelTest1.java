package com.xiaoaxiao.test.design_pattern.factory;

/**
 * Created by xiaoaxiao on 2019/9/2
 * Description: 简单工厂模式
 */
import java.util.Scanner;

public class FactoryModelTest1{

    interface Computer{
        void buyComputer();
    }

    static class MacBookPro implements Computer{
        public void buyComputer(){
            System.out.println("This is MacBookPro");
        }
    }

    static class AlienBook implements Computer{
        public void buyComputer(){
            System.out.println("This is AlienBook");
        }
    }

    /**
     * 创建电脑对应的简单工厂，在该工厂内通过客户端输入的电脑编号，进行对应产品类的实例化
     */
    static class ComputerFactory{
        public Computer buyComputer(String type){
            if(type.equals("mac")){
                return new MacBookPro();
            }else if(type.equals("alien")){
                return new AlienBook();
            }
            return null;
        }
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input your choose:");
        String type = scanner.nextLine();

        // 在静态方法中只能实例化静态内部类，导致一系列的内部类都需要设置为static
        ComputerFactory computerFactory = new ComputerFactory();
        Computer computer = computerFactory.buyComputer(type);
        if(computer!=null){
            computer.buyComputer();
        }else{
            System.out.println("No such computer");
        }
        
    }
}