package com.xiaoaxiao.test.design_pattern.factory;

/**
 * Created by xiaoaxiao on 2019/9/2
 * Description: 抽象工厂模式
 */
/**
 * 创建产品Computer(电脑类型)接口
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

/**
 * 创建产品OperatingSystem(操作系统)接口
 */
interface OperatingSystem{
    void printSystem();
}

class MacOsSytem implements OperatingSystem{

    @Override
    public void printSystem() {
        System.out.println("This is a Mac Os");
    }
}

class Windows10System implements OperatingSystem{

    @Override
    public void printSystem() {
        System.out.println("This is a windows 10");
    }
}

/**
 * 创建工厂接口，并在该接口中定义方法用于实例化产品，将实例化产品延迟到子类中
 * 一个工厂——>多个产品(产品组)
 */
interface ComputerFactory{
    Computer createComputer();
    OperatingSystem createSystem();
}

/**
 * 在工厂接口的实现类中对多个产品(产品组——相互之间存在关系的产品)进行实例化
 */
class AppleFactory implements ComputerFactory{
    public Computer createComputer(){
        return new MacbookPro();
    }

    @Override
    public OperatingSystem createSystem() {
        return new MacOsSytem();
    }
}

class MsFactory implements ComputerFactory{
    public Computer createComputer(){
        return new AlienBook();
    }

    @Override
    public OperatingSystem createSystem() {
        return new Windows10System();
    }
}
public class FactoryModelTest3 {



    public static void main(String[] args) {
        // 一个工厂接口的具体实现类对应一个产品组
        ComputerFactory computerFactory = new MsFactory();
        Computer computer = computerFactory.createComputer();
        OperatingSystem operatingSystem = computerFactory.createSystem();
        computer.buyComputer();
        operatingSystem.printSystem();
    }
}
