package com.xiaoaxiao.test.jvm_test.copy_test;

/**
 * Created by xiaoaxiao on 2019/7/30
 * Description: 浅拷贝测试
 */

public class ShallowCopyTest {

    static class Teacher{
        private String name;
        private String direction;

        public Teacher(String name, String direction) {
            this.name = name;
            this.direction = direction;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Teacher{" +
                    "name='" + name + '\'' +
                    ", direction='" + direction + '\'' +
                    '}';
        }
    }

    static class Student implements Cloneable{
        private String name;
        private Integer age;
        private Teacher teacher;

        // 实现浅拷贝
        // 产生一个新的student对象，并且拷贝原有所有属性值
        public Student clone(){
            Student student = null;

            try {
                student = (Student) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            return student;
        }

        public Student(String name, Integer age, Teacher teacher) {
            this.name = name;
            this.age = age;
            this.teacher = teacher;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", teacher=" + teacher +
                    '}';
        }
    }

    public static void main(String[] args) {

        Teacher teacher = new Teacher("Miss Wang","English Teacher");
        Student student = new Student("xiaoaxiao",18,teacher);

        Student cloneStudent = student.clone();

        System.out.println(cloneStudent.getName());
        System.out.println(cloneStudent.getAge());

        // 通过浅拷贝，两个不同的student
        System.out.println(cloneStudent==student);

        // 但对应的是同一个teacher
        System.out.println(cloneStudent.getTeacher()==student.getTeacher());
    }
}
