package com.company;

import java.io.Serializable;
import java.util.Scanner;

public class Book implements Serializable {
    public boolean isBookAvailable;// what if book has same name or same author has several books?
    private String title;
    private String author;
    private String description;
   // Scanner scanner = new Scanner(System.in);

    public Book(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.isBookAvailable = true;
    }

    public Book() {

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
/*
    public boolean isBookAvailable() {
        return true;
    }

 */



    @Override
    public String toString() {
        return "Book title: " + title +
                ". Author: " + author +
                ". Description: " + description + "."
                ;
    }

    public boolean isBookAvailable() {
        return true;
    }
}
