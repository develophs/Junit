package com.study.junitproject.web.dto.request;

import com.study.junitproject.domain.Book;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter //Controller에서 Setter가 호출되면서 Dto에 값이 채워짐.
public class BookSaveReqDto {

    //null, 공백 체크
    @Size(min=1, max=50)
    @NotBlank
    private String title;

    @Size(min=2, max=20)
    @NotBlank
    private String author;

    public Book toEntity(){
        return Book.builder()
                .title(title)
                .author(author)
                .build();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
