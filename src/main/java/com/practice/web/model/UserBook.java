package com.practice.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="user_books")
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdUserBook;

    @Column
    private Long bookId;

    @Column
    private int userId;

}
