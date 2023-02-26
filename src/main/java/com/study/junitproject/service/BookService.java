package com.study.junitproject.service;

import com.study.junitproject.domain.Book;
import com.study.junitproject.domain.BookRepository;
import com.study.junitproject.web.dto.BookRespDto;
import com.study.junitproject.web.dto.BookSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    //1.책 등록
    @Transactional
    public BookRespDto 책등록하기(BookSaveReqDto dto){
        Book savedBook = bookRepository.save(dto.toEntity());
        return new BookRespDto().toDto(savedBook);
    }

    //2.책 목록보기

    //3.책 한건보기

    //4.책 삭제

    //5.책 수정
}
