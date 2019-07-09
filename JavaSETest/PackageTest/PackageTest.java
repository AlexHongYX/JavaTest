// 红色下划线只是说明该java文件生成的class文件和该java文件没有在同一根目录下
package www.xiaoaxiao.test;
import www.xiaoaxiao.test.util.Child;
import www.xiaoaxiao.test.util.Father;

public class PackageTest{
    public static void main(String[] args) {
        Child child = new Child();
        child.print();
        // System.out.println(new Father().msg);
    }
}