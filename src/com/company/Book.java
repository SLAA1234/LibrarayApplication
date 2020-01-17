package com.company;

import java.io.Serializable;

public class Book implements Serializable {// what if book has same name or same author has several books?
    private String title;
    private String author;
    private String description;

    public Book(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Book title: " + title + "\\" +
                "author: " + author + "\\" +
                "description: " + description
                ;
    }
}
