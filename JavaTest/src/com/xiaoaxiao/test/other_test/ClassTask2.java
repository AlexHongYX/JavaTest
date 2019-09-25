package com.xiaoaxiao.test.other_test;

/**
 * Created by xiaoaxiao on 2019/9/23
 * Description:
 */
class Student{
    public String gender;
    public String major;

    public Student(String gender, String major) {
        this.gender = gender;
        this.major = major;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null){
            return false;
        }

        if (obj==this){
            return true;
        }

        if (!(obj instanceof Student)){
            return false;
        }

        Student student = (Student)obj;
        return student.major.equals(this.major)&&student.gender.equals(this.gender);
    }
}

public class ClassTask2 {

        public static void hehe(){

        }

        public void haha(){

            hehe();
        }


    public String name = "hehe";

    public static void main(String[] args) {


        String major = "统计";
        Student[] students = new Student[50];

        students[0] = new Student("男","统计");
        students[1] = new Student("男","统计");
        students[2] = new Student("女","统计");
        students[3] = new Student("男","数学");
        students[4] = new Student("女","数学");

        int[] ret = new int[2];

        for (int i = 0; i < students.length; i++) {
            Student man = new Student("男","统计");
            Student woman = new Student("女","统计");
            if (man.equals(students[i])){
                ret[0]++;
            }else if(woman.equals(students[i])){
                ret[1]++;
            }
        }

        System.out.println("统计男："+ret[0]+"人");
        System.out.println("统计女："+ret[1]+"人");

    }


}
