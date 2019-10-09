package com.xiaoaxiao.library.classes;

import com.xiaoaxiao.library.databases.BookShelf;
import com.xiaoaxiao.library.exceptions.NoSuchBookException;

/**
 * Created by xiaoaxiao on 2019/10/9
 * Description:
 */
public class Action {
    public static Book putBook(String ISBN,String title,String author,double price,int count){
        BookShelf bookShelf = BookShelf.getInstance();
        try {
            Book book = bookShelf.search(ISBN);
            book.increaseCount(count);
            return book;
        } catch (NoSuchBookException e) {
            Book book = new Book(ISBN,title,author,price,count);
            bookShelf.putBook(book);
            return book;
        }
    }

    public static void AllBook(){
        BookShelf bookShelf = BookShelf.getInstance();
        bookShelf.allBook();
    }
}
