package com.company;

import java.io.Serializable;
import java.util.Scanner;

public class Book implements Serializable {
    public boolean available;
    private String title;
    private String author;
    private String description;

    public Book(String title, String author, String description, boolean available) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
        return "Book title: " + title +
                ". Author: " + author +
                ". Description: " + description + "."
                ;
    }
}
