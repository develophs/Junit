package com.study.junitproject.web;

import com.study.junitproject.service.BookService;
import com.study.junitproject.web.dto.response.BookRespDto;
import com.study.junitproject.web.dto.request.BookSaveReqDto;
import com.study.junitproject.web.dto.response.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    // 1.책 등록
    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody BookSaveReqDto bookSaveReqDto){
        BookRespDto bookRespDto = bookService.책등록하기(bookSaveReqDto);
        CMRespDto body = new CMRespDto(1, "성공", bookRespDto);
        return new ResponseEntity<>(body,HttpStatus.CREATED); //201
    }

    // 2.책 목록보기
    public ResponseEntity<?> getBookList(){
        return null;
    }

    // 3.책 한건보기
    public ResponseEntity<?> getBookOne(){
        return null;
    }

    // 4.책 삭제하기
    public ResponseEntity<?> deleteBook(){
        return null;
    }

    // 5.책 수정하기
    public ResponseEntity<?> updateBook(){
        return null;
    }
}
