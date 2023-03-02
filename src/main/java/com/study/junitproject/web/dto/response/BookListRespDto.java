package com.study.junitproject.web.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class BookListRespDto {
    List<BookRespDto> items;

    public BookListRespDto(List<BookRespDto> items) {
        this.items = items;
    }
}
