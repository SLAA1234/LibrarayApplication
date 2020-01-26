package com.company;

import java.awt.print.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Person implements Serializable {

    private String name;
    private int id;
    //protected List<Book> totalBookList=new ArrayList<>();
    //why set borrowedBooks here? Because Libararian also get? or in program call borrower methods ok?
    //transient Scanner scanner = new Scanner(System.in);
    //long serialVersionUID = 1;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

/*
    public void showTotalBookList(){
        System.out.println("Select from the menu: \n 1.Show all the books in random order. \n 2.Show books according to name order." +
                "\n 3.Show books according to title order.");
        int choice = 999;
        try {
            choice = Integer.parseInt((scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("You must select a number.");
        }
        switch (choice) {
            case 1:
                showBooksRandomly();
                break;
            case 2:
                showBooksByAuthor();
                break;
            case 3:
                showBooksByTitle();
                break;
        }
    }

    private void showBooksRandomly(){
        System.out.println("Catalogue\n");

        for(Book book: totalBookList){
            System.out.println(book);
        }
    }

    private void showBooksByAuthor(){
        System.out.println("Catalogue By Author\n");
        totalBookList.sort(Comparator.comparing(Book::getAuthor));
        for(Book book: totalBookList){
            System.out.println(book);
        }
    }

    private void showBooksByTitle(){
        System.out.println("Catalogue By Title\n");
        totalBookList.sort(Comparator.comparing(Book::getTitle));
        for(Book book: totalBookList){
            System.out.println(book);
        }
    }

    public void showRemainBookList(){
        System.out.println("Select from the menu: \n 1.Show available books in random order. \n 2.Show available books according to name order." +
                "\n 3.Show available books according to title order.");
        int choice = 999;
        try {
            choice = Integer.parseInt((scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("You must select a number.");
        }
        switch (choice) {
            case 1:
                showAvailableBooksRandomly();
                break;
            case 2:
                showAvailableBooksByAuthor();
                break;
            case 3:
                showAvailableBooksByTitle();
                break;
        }
    }

    private void showAvailableBooksRandomly() {
        System.out.println("Available Books Catalogue\n");

        for (Book book : totalBookList) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }
    private void showAvailableBooksByAuthor(){
        System.out.println("Available Books Catalogue By Author\n");
        totalBookList.sort(Comparator.comparing(Book::getAuthor));
        for(Book book: totalBookList){
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    private void showAvailableBooksByTitle(){
        System.out.println("Available Books Catalogue By Title\n");
        totalBookList.sort(Comparator.comparing(Book::getTitle));
        for(Book book: totalBookList){
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    public Book checkBookAvailability(){
        System.out.println("Please input the title: ");
        String titleToSearch = scanner.nextLine();
        for (Book book : totalBookList) {
            if (titleToSearch.equals(book.getTitle())&& book.isAvailable()) {
                System.out.println("The book is in the library. You can borrow the book.");
                return book;
            }
            if(titleToSearch.equals(book.getTitle())&& !book.isAvailable()){
                System.out.println("The book is lent out");
                return null;
            }
        }
        System.out.println("The library doesn't have this book. Contact the reception to order this book.");
        return null;
    }



    public Book searchBookByTitle() {
        System.out.println("Please input the title: ");
        String titleToSearch = scanner.nextLine();
        //boolean didFindBook = false;

        for(Book book: totalBookList){
            if(titleToSearch.equals(book.getTitle())){
                System.out.println(book);//how to express "There is no book with this title."
               // didFindBook = true;
                return book;
            }
        }

        //if(didFindBook==false) {
            //System.out.println("No book with this title.");
        //}
        System.out.println("No book with this title.");
        return null;
    }


    public Book searchBookByAuthor() {
        System.out.println("Please input the author: ");
        String authorToSearch = scanner.nextLine();
        //boolean didFindAuthor = false;

        for(Book book: totalBookList){
            if(authorToSearch.equals(book.getAuthor())){
                System.out.println(book);//"There is no book with this author."
                //didFindAuthor = true;
            }
        }
        //if(didFindAuthor==false){
          //  System.out.println("No book with this author.");
        //}
        System.out.println("No book with this author.");
        return null;

    }
    */

    @Override
    public String toString() {
        return "Person name: " + name +
                ", id: " + id + ".";
    }
}

