package com.study.junitproject.service;

import com.study.junitproject.domain.Book;
import com.study.junitproject.domain.BookRepository;
import com.study.junitproject.util.MailSender;
import com.study.junitproject.web.dto.BookRespDto;
import com.study.junitproject.web.dto.BookSaveReqDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    // 기능들이 트랜잭션을 잘 타는지
    // Service 레이어만 테스트 해야하는데
    // Repository 주입받고 테스트하면 Repository 레이어도 같이 테스트된다.

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MailSender mailSender;

    @Test
    public void 책등록하기_테스트(){
        //given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("Junit스터디");
        dto.setAuthor("Jake");

        //stub(가설, 행동 정의)
        when(bookRepository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);

        //when
        BookRespDto bookRespDto = bookService.책등록하기(dto);

        //then
        assertThat(bookRespDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(dto.getAuthor());

    }

    @Test
    public void 책목록보기_테스트(){
        //given(파라미터로 들어올 데이터)

        //stub(가설)
        List<Book> books = Arrays.asList(
                new Book(1L,"junit강의","jake"),
                new Book(2L,"spring강의","bae")
        );
        when(bookRepository.findAll()).thenReturn(books);

        //when(실행)
        List<BookRespDto> bookRespDtoList = bookService.책목록보기();

        //then(검증)
        assertThat(bookRespDtoList.get(0).getTitle()).isEqualTo("junit강의");
        assertThat(bookRespDtoList.get(0).getAuthor()).isEqualTo("jake");

        assertThat(bookRespDtoList.get(1).getTitle()).isEqualTo("spring강의");
        assertThat(bookRespDtoList.get(1).getAuthor()).isEqualTo("bae");
    }

    @Test
    public void 책한건보기_테스트(){
        //given
        Long id = 1L;
        Book book = new Book(1L, "junit강의", "jake");
        Optional<Book> bookOP = Optional.of(book);

        //stub
        when(bookRepository.findById(id)).thenReturn(bookOP);

        //when
        BookRespDto bookRespDto = bookService.책한건보기(id);

        //then
        assertThat(bookRespDto.getTitle()).isEqualTo(book.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(book.getAuthor());
    }
}
