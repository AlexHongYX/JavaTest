/**
* 如何设计一个类
* 一、根据需求列出功能
* 	1.传 年、月、日 构造日期类
* 	2.在当前日期上增加多少天
* 	3.在当前日期上减少多少天
* 	4.可以返回字符串String的方法  "2019-05-20"
* 	5.限制：年支持的范围{1900,2100}
*	6.如果给定两个日期，希望计算一下两个日期之间相差多少天
*
* 二、设计属性
* 	年、月、日
*
*
*
*
*/

public class Date{
	
	private int year;
	private int month;
	private int day;
	
	//构造方法
	public Date(int year,int month,int day){
		//至少做基本的参数检查
		if(year<1900||year>2100){
			//最好的方法是抛异常
			System.out.println("年不合法："+year);
		}
		if(month<1||month>12){
			System.out.println("月不合法："+month);
		}
		/*
		if(day<0||day>getDayOfMonth(year,month)){
			System.out.println("日不合法："+day);
		}*/
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	//支持的方法
	public void add(int days){
		//days必须是正数
		
	}
	
	public void sub(int days){
		//days必须是正数
		if(day<0){
			System.out.println("days 不合法"+days);
			return;
		}
		
		day -= days;
		while(day<1){
			/*
			day += getDayOfMonth(year,month-1);
			month--;
			if(month<1){
				month = 12;
				year--;
			}*/
			//先减月和年，使用的是上一年（有可能会变成上一年）和上一月的天数
			month--;
			if(month<1){
				month = 12;
				year--;
			}
			//day += getDayOfMonth(year,month);
		}
	}
	
	public String toString(){
		return String.format("%4d-%2d-%2d",year,month,day);
	}
	
	//内部使用的方法，private
	public static void main(String[] args){
		Date date = new Date(2019,5,20);
		System.out.println(date.toString());
	}
}