package com.xiaoaxiao.library.databases;

import com.xiaoaxiao.library.classes.Book;
import com.xiaoaxiao.library.exceptions.NoSuchBookException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoaxiao on 2019/10/9
 * Description: 书架类——存储书的集合类
 */
public class BookShelf {

    private List<Book> bookList = new ArrayList<>();

    private static BookShelf bookShelf = new BookShelf();

    public static BookShelf getInstance() {
        return bookShelf;
    }

    public Book search(String isbn) throws NoSuchBookException {
        for(Book book:bookList){
            if (book.is(isbn)){
                return book;
            }
        }
        throw new NoSuchBookException("没找到书");
    }

    public void putBook(Book book) {
        this.bookList.add(book);
    }

    public void allBook() {
        for(Book book:bookList){
            System.out.println(book);
        }
    }
}
