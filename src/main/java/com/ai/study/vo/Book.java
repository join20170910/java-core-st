package com.ai.study.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;

@Data
@AllArgsConstructor
public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private Integer pages;

    @Override
    public int compareTo(Book o) {
        return pages > o.getPages() ? 0:1;
    }
}
