package com.estsoft.demo.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column(updatable = false,nullable = false,unique = true)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

}
