package test;

import java.sql.*;
import java.util.Date;

/**
 * Created by xiaoaxiao on 2019/11/18
 * Description:
 */
public class Person {
    public static void main(String[] args) {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test"
//                    ,"root","000000");
//            Statement statement = conn.createStatement();
//            String sql = " insert into person(name,age,birthday) values(\"jack\",24,\"1999-10-24 00:00:00\")";
//            statement.execute(sql);
//            statement.close();
//            conn.close();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        System.out.println(jump(10));
        System.out.println(jumpFloorII(10));
    }

    public static int jump(int n){
        if (n==0){
            return 0;
        }
        if (n==1){
            return 1;
        }
        return 2*jump(n-1);
    }

    public static int jumpFloorII(int number) {
        if(number==0) return 0;
        return (number == 1) ? 1 : 2*jumpFloorII(number-1);
    }
}
