package com.practice.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBook;

    @NotNull
    @Size(min = 3, max = 50)
    @Column
    private String name;

    @NotNull
    @Size(min = 3, max = 50)
    @Column
    private String author;

    @NotNull
    @Min(0)
    @Column
    private Double price;

    @NotNull
    @Column
    private Date datePublishing;

    @NotNull
    @Size(min = 3, max = 50)
    @Column
    private String namePublishing;

    @NotNull
    @Column
    private Date dateArrival;

    @NotNull
    @Column
    private Date dateCancellation;

    @NotNull
    @Min(1)
    @Column
    private Integer countPages;

    @NotNull
    @Min(0)
    @Column
    private Integer countCopies;

}
