package com.practice.web.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;

    @Column
    private String nickname;

    @Column
    private String type_user;

    @Column
    private  String password;

    @Column
    private String email;

}
