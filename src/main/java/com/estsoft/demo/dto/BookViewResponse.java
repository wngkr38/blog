package com.estsoft.demo.dto;


import com.estsoft.demo.domain.Book;
import lombok.*;

@Getter
@NoArgsConstructor
public class BookViewResponse {
    private String id;
    private String name;
    private String author;

    public BookViewResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
    }
}
