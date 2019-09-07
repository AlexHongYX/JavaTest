package com.xiaoaxiao.test.design_pattern.template;

import java.util.Scanner;

// 优秀模板实例
// 基类(核心算法+抽象方法)
abstract class CoffeeinBeverage{

    // 冲泡的核心算法
    final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        if(customWantsCondiments()){
            add();
        }
    }

    // 所有子类所共用的方法，煮水，倒入杯
    void boilWater(){
        System.out.println("将水煮沸");
    }

    void pourInCup(){
        System.out.println("倒入杯中");
    }

    // 默认设为返回true，利用继承的关系，子类如果需要改为false就将该方法覆写
    // 否则就会使用默认设置
    boolean customWantsCondiments(){
        return true;
    }

    // 需要延迟到子类实现的方法，泡什么东西，添加什么调料
    abstract void brew();

    abstract void add();

}

// 咖啡类
class Coffee extends CoffeeinBeverage{
    void brew(){
        System.out.println("用水冲泡咖啡");
    }
    void add(){
        System.out.println("加糖和牛奶");
    }

    boolean customWantsCondiments(){
       String answer = getUserInfo();
       if(answer.equals("y")){
           return true;
       }else{
           return false;
       }
    }

    private String getUserInfo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("您想在咖啡中加入牛奶和糖吗？（y/n）");
        String result = scanner.nextLine();
        return result;
    }
}

// 茶类
class Tea extends CoffeeinBeverage{
    void brew(){
        System.out.println("用水浸泡茶叶");
    }
    void add(){
        System.out.println("加柠檬");
    }
}

public class TemplatePatternTest2{
    public static void main(String[] args) {
        CoffeeinBeverage coffee = new Coffee();
        CoffeeinBeverage tea = new Tea();
        coffee.prepareRecipe();
        tea.prepareRecipe();
    }
}