// 恶心代码实例

class Coffee{

    // 咖啡冲泡方法
    void prepareRecipe(){
        boilWater();
        brewCoffee();
        pourInCup();
        addSugerAndMilk();
    }

    void boilWater(){
        System.out.println("将水烧开");
    }

    void brewCoffee(){
        System.out.println("用水冲泡咖啡");
    }

    void pourInCup(){
        System.out.println("将咖啡倒进杯子");
    }

    void addSugerAndMilk(){
        System.out.println("加糖和牛奶");
    }
}

class Tea{
    void prepareRecipe(){
        boilWater();
        steepTeaBag();
        pourInCup();
        addLemon();
    }

    void boilWater(){
        System.out.println("把水烧开");
    }

    void steepTeaBag(){
        System.out.println("浸泡茶叶");
    }

    void pourInCup(){
        System.out.println("将茶倒入杯中");
    }

    void addLemon(){
        System.out.println("加柠檬");
    }
}

public class TemplatePatternTest{
    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        Tea tea = new Tea();
        coffee.prepareRecipe();
        tea.prepareRecipe();
    }
}