package com.practice.web.model;


import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="user")
public class User {
    public static final String EMAIL_REGEXP = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
            "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]" +
            "|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b" +
            "\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_user;


    @Column(name = "name")
    @NotNull
    @Size(min=2,max=10)
    private String name;

    @Column(name = "surname")
    @NotNull
    @Size(min=2,max=10)
    private String surname;


    @NotNull
    @Size(min = 7, max = 50)
    @Pattern(regexp = EMAIL_REGEXP)
    private String email;


    @Column(name = "password")
    @NotNull
    //@Size(min=6,max=50)
    private  String password;

    @Column(name = "sex")
    @NotNull
    @Size(min=3,max=6)
    private String sex;

    @Column(name = "address")
    @NotNull
    @Size(min=4,max=50)
    private String address;

    @Column(name = "phone")
    @NotNull
    //TODO: add regexp according to chosen tel. format
    @Size(min=3,max=20)
    private String phone;

    @Column(name = "date_of_birth")
    @NotNull
    private Date date_of_birth;


    @Column(name = "enabled")
    private Integer enabled;


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles;

}
