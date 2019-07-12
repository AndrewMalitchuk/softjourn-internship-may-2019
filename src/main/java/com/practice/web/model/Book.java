package com.practice.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;


@Getter
@Setter
@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBook;

    @Min(3)
    @Max(50)
    @Column
    private String name;

    @Min(3)
    @Max(50)
    @Column
    private String author;

    @Column
    private Double price;

    @Column
    private Date datePublishing;

    @Min(3)
    @Max(50)
    @Column
    private String namePublishing;

    @Column
    private Date dateArrival;

    @Column
    private Date dateCancellation;

    @Column
    private Integer countPages;

    @Column
    private Integer countCopies;


}
