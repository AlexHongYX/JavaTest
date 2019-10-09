package com.xiaoaxiao.library.classes;

/**
 * Created by xiaoaxiao on 2019/10/9
 * Description: 借阅记录类
 */
public class Record {
    private String UserId;
    private String BookISBN;
    private String borrowedTime;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getBookISBN() {
        return BookISBN;
    }

    public void setBookISBN(String bookISBN) {
        BookISBN = bookISBN;
    }

    public String getBorrowedTime() {
        return borrowedTime;
    }

    public void setBorrowedTime(String borrowedTime) {
        this.borrowedTime = borrowedTime;
    }
}
