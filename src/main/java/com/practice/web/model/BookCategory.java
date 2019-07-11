package com.practice.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="book_category")
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookCategoryId;

    @Column
    private int categoryId;

    @Column
    private int bookId;

}
