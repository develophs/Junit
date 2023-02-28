package com.study.junitproject.web;

import com.study.junitproject.service.BookService;
import com.study.junitproject.web.dto.response.BookRespDto;
import com.study.junitproject.web.dto.request.BookSaveReqDto;
import com.study.junitproject.web.dto.response.CMRespDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    // 1.책 등록
    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveReqDto bookSaveReqDto,
                                      BindingResult bindingResult){
        //추후에 AOP처리
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            log.info("hasErrors={}",bindingResult.hasErrors());
            log.info("errorMap={}",errorMap.toString());
            return new ResponseEntity<>(new CMRespDto(-1, "실패", errorMap.toString()),HttpStatus.BAD_REQUEST); //400
        }

        BookRespDto bookRespDto = bookService.책등록하기(bookSaveReqDto);
        return new ResponseEntity<>(new CMRespDto(1, "성공", bookRespDto),HttpStatus.CREATED); //201
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

