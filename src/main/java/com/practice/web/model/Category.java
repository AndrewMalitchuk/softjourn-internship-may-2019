package com.practice.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategory;

    @NotNull
    @Size(min = 3, max = 50)
    @Column
    private String typeCategory;

    @NotNull
    @Size(min = 3, max = 50)
    @Column
    private String genre;

    @NotNull
    @Size(min = 2, max = 50)
    @Column
    private String language;

}
