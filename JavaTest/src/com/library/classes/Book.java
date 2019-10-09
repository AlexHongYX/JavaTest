package com.xiaoaxiao.library.classes;

/**
 * Created by xiaoaxiao on 2019/10/9
 * Description: 书类
 */
public class Book {
    private String ISBN;
    private String name;
    private String author;
    private double price;
    private int currentCount;
    private int sumCount;
    private int borrowedCount;

    public Book(String isbn, String title, String author, double price, int count) {
        this.ISBN = isbn;
        this.name = title;
        this.author = author;
        this.price = price;
        this.currentCount = count;
        this.sumCount = count;
        this.borrowedCount = 0;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getSumCount() {
        return sumCount;
    }

    public void setSumCount(int sumCount) {
        this.sumCount = sumCount;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
    }

    public boolean is(String isbn){
        return this.ISBN.equals(isbn);
    }

    public void increaseCount(int count) {
        this.sumCount += count;
        this.currentCount += count;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", currentCount=" + currentCount +
                ", sumCount=" + sumCount +
                ", borrowedCount=" + borrowedCount +
                '}';
    }
}
