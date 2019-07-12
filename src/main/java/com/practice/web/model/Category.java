package com.practice.web.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;

    @Min(3)
    @Max(50)
    @Column
    private String typeCategory;

    @Min(3)
    @Max(20)
    @Column
    private String genre;

    @Min(2)
    @Max(15)
    @Column
    private String language;

}
