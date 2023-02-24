package com.study.junitproject.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest //DB와 관련된 컴포넌트만 메모리에 로딩, Controller,Service관련 구동X, 구동이 가벼워진다.
@Slf4j
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    //DB쪽 관련 테스트
    //책이 등록 될때, author가 null이다.
    //-> 유효성 검증은 controller에서 하는것.

    // @BeforeAll 테스트전 1번만 발생
    @BeforeEach // 각 테스트 시작전 발생
    public void 데이터준비(){
        String title = "Junit5";
        String author= "Jake";

        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
    } //트랜잭션은 데이터준비 + 각각의 테스트 메서드 유지


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
        // 트랜잭션 종료(저장된 데이터를 초기화한다)
    }
    // 2. 책 목록보기
    @Test
    public void 책목록보기_test(){
        //given
        String title = "Junit5";
        String author= "Jake";

        //when
        List<Book> books = bookRepository.findAll();

        //then
        assertThat(books.get(0).getTitle()).isEqualTo(title);
        assertThat(books.get(0).getAuthor()).isEqualTo(author);
        assertThat(books.size()).isEqualTo(1);
    }
    // 3. 책 한건보기
    @Sql("classpath:db/tableInit.sql") //Auto_Increment 초기화, id로 찾는 test 사용시
    @Test
    public void 책한건보기_test(){
        //given
        String title = "Junit5";
        String author= "Jake";

        //when
        Book findBook = bookRepository.findById(1L).orElseThrow();

        //then
        assertThat(findBook.getTitle()).isEqualTo(title);
        assertThat(findBook.getAuthor()).isEqualTo(author);
    }
    // 4. 책 삭제
    @Sql("classpath:db/tableInit.sql") //Auto_Increment 초기화, id로 찾는 test 사용시
    @Test
    public void 책삭제_test(){
        //given
        Long id = 1L;

        //when
        bookRepository.deleteById(1L);

        //then
        assertThat(bookRepository.findById(id).isEmpty()).isTrue();
    }

    // 5. 책 수정

}
