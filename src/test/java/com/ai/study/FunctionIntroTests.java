package com.ai.study;

import com.ai.study.vo.Book;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FunctionIntroTests {

    @Test
    public void givenListOfBooks_thenExplainThreAdvantageOfFunction(){
        String authorA = "张三";
        String authorB = "李四";
        String authorC = "王五";
        String authorD = "前朝太监";
    List<Book> books = new ArrayList<Book>(Arrays.asList(
            new Book("C++编程",authorA,1216),
            new Book("C#编程",authorA,365),
            new Book("Java编程",authorB,223),
            new Book("Ruby编程",authorB,554),
            new Book("21天精通Python",authorB,607),
        new Book("21天精通Go",authorC,352),
        new Book("葵花宝典",authorD,1200),
        new Book("编程圣经",authorA,320)
    ));
        ArrayList<Book> booksFiltered = new ArrayList<>();
        for (Book book : books){
            if (!"葵花宝典".equals(book.getTitle())){
                booksFiltered.add(book);
            }
        }
        booksFiltered.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getPages().compareTo(o1.getPages());
            }
        });

        books.stream().filter(book -> !"葵花宝典".equals(book.getTitle()))
                .sorted((b1,b2) -> b2.getPages().compareTo(b1.getPages()))
                .limit(3)
                .map(Book::getAuthor)
                .distinct()
                .forEach(System.out::println);
    }
}
