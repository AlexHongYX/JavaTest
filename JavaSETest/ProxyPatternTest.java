/**
 * 代理模式
 */
// 业务接口
interface ISubject{
    void buyComputer();
}

class RealSubject implements ISubject{
    public void buyComputer(){
        System.out.println("buy a alien");
    }
}

class ProxySubject implements ISubject{

    // 将真正的“买主”注入进代理类（因为代理需要知道谁付的钱）
    private ISubject iSubject;

    // 构造方法注入
    public ProxySubject(ISubject iSubject){
        this.iSubject = iSubject;
    }

    public void buyComputer(){
        beforeSale();
        this.iSubject.buyComputer();
        afterSale();
    }

    public void beforeSale(){
        System.out.println("produce a alien");
    }

    public void afterSale(){
        System.out.println("after sale alien");
    }
}

class Factory{
    public static ISubject getInstance(){
        return new ProxySubject(new RealSubject());
    }
}

public class ProxyPatternTest{
    public static void main(String[] args) {
        Factory.getInstance();
    }    
}