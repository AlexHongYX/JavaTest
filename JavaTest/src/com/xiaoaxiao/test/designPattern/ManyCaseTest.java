package com.xiaoaxiao.test.designPattern;

/**
 * Created by xiaoaxiao on 2019/7/11
 * Description: 多例模式（了解）
 */

class Sex{

    private String title;
    public static final int MALE_FLAG = 1;
    public static final int FEMALE_FLAG = 0;
    private static final Sex MALE = new Sex("女");
    private static final Sex FEMALE = new Sex("男");

    private Sex(String title){
        this.title = title;
    }

    public static Sex getInstance(int flag){
        switch (flag){
            case MALE_FLAG:
                return MALE;
            case FEMALE_FLAG:
                return FEMALE;
            default:
                return null;
        }
    }


    public String getTitle() {
        return title;
    }
}

public class ManyCaseTest {

    public static void main(String[] args) {
        Sex male = Sex.getInstance(Sex.MALE_FLAG);
        if(male!=null){
            System.out.println(male.getTitle());
        }
    }
}
