package com.practice.web.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBook;

    @Column
    private String name;

    @Column
    private String author;

    @Column
    private Double price;

    @Column
    private Date datePublishing;

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

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setPrice(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDatePublishing() {
        return datePublishing;
    }

    public void setDatePublishing(Date datePublishing) {
        this.datePublishing = datePublishing;
    }

    public Date getDateCancellation() {
        return dateCancellation;
    }

    public void setDateCancellation(Date dateCancelation) {
        this.dateCancellation = dateCancelation;
    }

    public String getNamePublishing() {
        return namePublishing;
    }

    public void setNamePublishing(String namePublishing) {
        this.namePublishing = namePublishing;
    }

    public Date getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(Date dateArrival) {
        this.dateArrival = dateArrival;
    }

    public Integer getCountPages() {
        return countPages;
    }

    public void setCountPages(Integer countPages) {
        this.countPages = countPages;
    }

    public Integer getCountCopies() {
        return countCopies;
    }

    public void setCountCopies(Integer countCopies) {
        this.countCopies = countCopies;
    }

}
