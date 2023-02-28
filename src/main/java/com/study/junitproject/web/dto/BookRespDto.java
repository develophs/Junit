package com.study.junitproject.web.dto;

import com.study.junitproject.domain.Book;

public class BookRespDto {
    private Long id;
    private String title;
    private String author;

    public BookRespDto() {
    }

    public BookRespDto (Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
