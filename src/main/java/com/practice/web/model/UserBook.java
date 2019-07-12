package com.practice.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name="user_books")
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdUserBook;

    @NotNull
    @Min(1)
    @Column
    private Long bookId;

    @NotNull
    @Min(1)
    @Column
    private int userId;

}
