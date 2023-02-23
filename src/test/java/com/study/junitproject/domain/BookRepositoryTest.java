package com.study.junitproject.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        log.info("책등록_test 실행");
    }

    // 2. 책 목록보기

    // 3. 책 한건보기

    // 4. 책 수정

    // 5. 책 삭제
}
