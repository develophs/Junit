package com.study.junitproject.service;

import com.study.junitproject.domain.Book;
import com.study.junitproject.domain.BookRepository;
import com.study.junitproject.util.MailSender;
import com.study.junitproject.web.dto.BookRespDto;
import com.study.junitproject.web.dto.BookSaveReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final MailSender mailSender;

    //1.책 등록
    @Transactional
    public BookRespDto 책등록하기(BookSaveReqDto dto){
        Book savedBook = bookRepository.save(dto.toEntity());
        if (savedBook != null){
            if(!mailSender.send()){
                throw new RuntimeException("메일이 전송되지 않았습니다");
            }
        }
        return new BookRespDto(savedBook);
    }

    //2.책 목록보기
    public List<BookRespDto> 책목록보기(){
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .map(BookRespDto::new)
                .collect(Collectors.toList());
    }

    //3.책 한건보기
    public BookRespDto 책한건보기(Long id){
        Book savedBook = bookRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 아이디를 찾을 수 없습니다."));
        return new BookRespDto(savedBook);
    }

    //4.책 삭제
    @Transactional
    public void 책삭제하기(Long id){
        bookRepository.deleteById(id);//id == null -> IllegalArgumentException
    }

    //5.책 수정, 트랜잭션 종료시 더티체킹 후 commit 된다.
    @Transactional
    public BookRespDto 책수정하기(Long id, BookSaveReqDto dto){
        Book savedBook = bookRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 아이디를 찾을 수 없습니다."));
        savedBook.update(dto.getTitle(),dto.getAuthor());
        return new BookRespDto(savedBook);
    }

}
