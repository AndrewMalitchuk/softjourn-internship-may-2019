package com.practice.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;

    @NotNull
    @Size(min=2,max=10)
    private String name;

    @NotNull
    @Size(min=2,max=10)
    private String surname;

    @NotNull
    @Size(min=3,max=6)
    private String sex;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    @Size(min=4,max=50)
    private String address;

    @NotNull
    //TODO: add regexp according to chosen tel. format
    @Size(min=3,max=20)
    private String phone;

}
