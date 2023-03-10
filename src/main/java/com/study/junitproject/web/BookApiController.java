package com.study.junitproject.web;

import com.study.junitproject.service.BookService;
import com.study.junitproject.web.dto.response.BookListRespDto;
import com.study.junitproject.web.dto.response.BookRespDto;
import com.study.junitproject.web.dto.request.BookSaveReqDto;
import com.study.junitproject.web.dto.response.CMRespDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    // 1.책 등록
    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveReqDto bookSaveReqDto){
        //BindingResult가 존재하면 예외가 안터진다.!!!! 주의하기!!!
        //유효성 검증에 실패하면 ExceptionHandler가 낚아챈다.
        BookRespDto bookRespDto = bookService.책등록하기(bookSaveReqDto);
        return new ResponseEntity<>(new CMRespDto(1, "성공", bookRespDto),HttpStatus.CREATED); //201
    }

    // 2.책 목록보기
    @GetMapping("/api/v1/book")
    public ResponseEntity<?> getBookList(){
        BookListRespDto bookListRespDto = bookService.책목록보기();
        return new ResponseEntity<>(new CMRespDto<>(1,"책 목록보기 성공",bookListRespDto),HttpStatus.OK);
    }

    // 3.책 한건보기
    @GetMapping("/api/v1/book/{id}")
    public ResponseEntity<?> getBookOne(@PathVariable Long id){
        BookRespDto bookRespDto = bookService.책한건보기(id);
        return new ResponseEntity<>(new CMRespDto<>(1,"책 한건보기 성공",bookRespDto),HttpStatus.OK);
    }

    // 4.책 삭제하기
    @DeleteMapping("/api/v1/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        bookService.책삭제하기(id);
        //상태값 리턴은 협의해야한다.
        return new ResponseEntity<>(new CMRespDto<>(1,"책 삭제하기 성공",null),HttpStatus.OK);
    }

    // 5.책 수정하기
    @PutMapping("/api/v1/book/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody @Valid BookSaveReqDto bookSaveReqDto){
        BookRespDto bookRespDto = bookService.책수정하기(id, bookSaveReqDto);
        return new ResponseEntity<>(new CMRespDto<>(1,"책 수정하기 성공",bookRespDto),HttpStatus.OK);
    }
}

