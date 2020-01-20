package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookList implements Serializable {
    private List<Book> totalBookList = new ArrayList<>();//List to save space when run, can create new ArrayList
    private List<Book> totalLoanList = new ArrayList<>();
    private List<Book> borrowerLoanList = new ArrayList<>();
    Book book;
    //private List<Book> remainBookList = new ArrayList<>();
    //private Book book = new Book();
    transient Scanner scanner;
    long serialVersionUID = 1;

    BookList() {
        scanner = new Scanner(System.in);
    }

    public void setTotalBookObject() {// when want to create File outside project, want to read or easy modify,
        //create txt, like Readme. When wants to save object, InputStream or OutputStream, create ser
        totalBookList.add(new Book("Where the Crawdads Sing", "Delia Owens", " A murder mystery"));
        totalBookList.add(new Book("Becoming", "Michelle Obama", " An intimate, powerful, and inspiring memoir "));
        totalBookList.add(new Book("Educated", "Tara Westover", "A coming-of-age Memoir"));
        totalBookList.add(new Book("Dog Man", "Pilkey Dav", "Action-oriented cartoons."));
        totalBookList.add(new Book("Girl, Wash your Face", "Rachel Hollis", "An Encouraging book for girls"));
        FileUtility.saveObject("TotalBook.ser", this);
    }



    public void showTotalBookList(){
        for(Book book: totalBookList){
            System.out.println(book);
        }
    }

    public void searchBookByTitle() {
        System.out.println("Please input the title: ");
        String titleToSearch = scanner.nextLine();
        boolean didFindBook = false;

        for(Book book: totalBookList){
            if(titleToSearch.equals(book.getTitle())){
                System.out.println(book);//how to express "There is no book with this title."
                didFindBook = true;
            }
        }

        if(didFindBook==false) {
            System.out.println("No book with this title.");
        }
    }

    public void searchBookByAuthor() {
        System.out.println("Please input the author: ");
        String authorToSearch = scanner.nextLine();
        boolean didFindAuthor = false;

        for(Book book: totalBookList){
            if(authorToSearch.equals(book.getAuthor())){
                System.out.println(book);//"There is no book with this author."
                didFindAuthor = true;
            }
        }
        if(didFindAuthor==false){
            System.out.println("No book with this author.");
        }
    }

    public boolean isBookAvailable() {
        System.out.println("Please input the title: ");
        String titleToSearch = scanner.nextLine();
        for (Book book : totalBookList) {
            if (titleToSearch.equals(book.getTitle())) {
                this.book = book;
                return true;
            }
        }
        return false;
    }

    public void checkBookAvailability(){
        if(isBookAvailable()){
            System.out.println("The book is in the library. You can borrow the book.");
        }
        else{
            System.out.println("The book is not in the library now.");
        }
    }

    public void addLoan(){
        if(isBookAvailable()){
            borrowerLoanList.add(book);//has problem here
            System.out.println("The book has been successfully added to your cart.");
            showBorrowerLoan();
           // totalLoanList.add(book);
            totalBookList.remove(book);

        }else {//specify the reason. Is the book not in the library or there is no such a book
            System.out.println("Failed to borrow the book.");
        }

    }

    public void returnLoan(){
        System.out.println("Input the title of the book you want to return: ");
        String bookToSearchFor = scanner.nextLine();
        for(Book book: borrowerLoanList){
            if(bookToSearchFor.equals(book.getTitle())){
                borrowerLoanList.remove(book);
                //totalLoanList.remove(book);
                totalBookList.add(book);
            }
        }
        System.out.println("The borrower doesn't borrow this book.");
    }

    public void showBorrowerLoan(){
        for(Book book: borrowerLoanList){
            System.out.println("Now you have: " + book);
        }
    }




/*
    public void totalLoan(){
        if(book.isBookAvailable()){
            borrowerLoanList.add(new Book());
            book.isBookAvailable=false;
            totalLoanList.add(new Book());
            totalBookList.remove(new Book());
        }
    }
    */
}


