import java.util.Scanner;

// 优秀模板实例

abstract class CoffeeinBeverage{

    void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        if(customWantsCondiments()){
            add();
        }
    }

    void boilWater(){
        System.out.println("将水煮沸");
    }

    void pourInCup(){
        System.out.println("倒入杯中");
    }

    boolean customWantsCondiments(){
        return true;
    }

    abstract void brew();

    abstract void add();

}

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