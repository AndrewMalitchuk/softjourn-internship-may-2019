package com.practice.web.model;

import javax.persistence.*;

@Entity
@Table(name="user_books")
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdUserBook;

    @Column
    private  int bookId;

    @Column
    private int userId;

    public int getIdUserBook() {
        return IdUserBook;
    }

    public void setIdUserBook(int idUserBook) {
        IdUserBook = idUserBook;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
