package com.xiaoaxiao.test.design_pattern.factory;

/**
 * Created by xiaoaxiao on 2019/9/2
 * Description: 工厂方法模式
 */
public class FactoryModelTest2{

    /**
     * 创建产品接口
     */
    interface Computer{
        void buyComputer();
    }

    static class MacbookPro implements Computer{
        public void buyComputer(){
            System.out.println("This is a mac");
        }
    }

    static class AlienBook implements Computer{
        public void buyComputer(){
            System.out.println("This is a alien");
        }
    }

    /**
     * 创建工厂接口，并在该接口中定义方法用于实例化产品，将实例化产品延迟到子类中
     */
    interface ComputerFactory{
        Computer createComputer();
    }

    /**
     * 在工厂接口的实现类中对产品进行实例化
     */
    static class AppleFactory implements ComputerFactory{
        public Computer createComputer(){
            return new MacbookPro();
        }
    }

    static class MsFactory implements ComputerFactory{
        public Computer createComputer(){
            return new AlienBook();
        }
    }

    public static void main(String[] args) {
        ComputerFactory computerFactory = new MsFactory();
        Computer computer = computerFactory.createComputer();
        computer.buyComputer();
    }
}