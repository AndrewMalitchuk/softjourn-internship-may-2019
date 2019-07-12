package com.practice.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;


@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;

    @Min(2)
    @Max(10)
    private String name;

    @Min(2)
    @Max(10)
    private String surname;

    @Min(3)
    @Max(6)
    private String sex;

    private Date dateOfBirth;

    @Min(4)
    @Max(50)
    private String address;

    //TODO: add regexp according to chosen tel. format
    @Min(3)
    @Max(20)
    private String phone;

}
