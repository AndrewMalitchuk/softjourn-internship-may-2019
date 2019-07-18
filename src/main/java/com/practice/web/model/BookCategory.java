package com.practice.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "book_category")
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookCategoryId;

    @NotNull
    @Min(1)
    @Column
    private int categoryId;

    @NotNull
    @Min(1)
    @Column
    private int bookId;

}
