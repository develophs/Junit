package com.study.junitproject.domain;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest //DB와 관련된 컴포넌트만 메모리에 로딩, Controller,Service관련 구동X, 구동이 가벼워진다.
@Slf4j
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    //DB쪽 관련 테스트
    //책이 등록 될때, author가 null이다.
    //-> 유효성 검증은 controller에서 하는것.

    // 1. 책 등록
    @Test
    public void 책등록_test(){
        //given(데이터 준비)
        String title = "Junit5";
        String author= "Jake";

        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        //when(테스트 실행)
        Book savedBook = bookRepository.save(book);

        //then(검증)
        assertThat(savedBook.getTitle()).isEqualTo(title);
        assertThat(savedBook.getAuthor()).isEqualTo(author);
    }

    // 2. 책 목록보기

    // 3. 책 한건보기

    // 4. 책 수정

    // 5. 책 삭제
}
