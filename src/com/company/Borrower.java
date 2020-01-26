package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Borrower extends Person implements Serializable {
    //transient Scanner scanner= new Scanner(System.in);


   // private List<Book> borrowerLoanList = new ArrayList<>();

    public Borrower(String name, int id) {
        super(name, id);
    }
/*
    public void addLoan(){
        Book book = checkBookAvailability();
        if(book!=null){
            borrowerLoanList.add(book);//has problem here
            System.out.println("The book " + book.getTitle().toUpperCase() + " has been successfully added to your loan list.");
            book.setAvailable(false);
            //showBorrowerLoan();
        }else {//specify the reason. Is the book not in the library or there is no such a book
            System.out.println("Failed to borrow the book.");
        }
    }

    public void showBorrowerLoan(){
        boolean didHaveLoan = false;
        for(Book book: borrowerLoanList){
            if(book!=null){
                System.out.println("Now you have: " + book);
                didHaveLoan = true;
            }
        }
        if(didHaveLoan==false){
            System.out.println("Now you have no Loan.");
        }
    }
    public void returnBook(){
        System.out.println("Please input the title of the book you want to return: ");
        String bookToReturn = scanner.nextLine();
        borrowerLoanList.remove(bookToReturn);
        System.out.println("The book " + bookToReturn.toUpperCase() + " has been successfully remove from your loan List.");

        for(Book book: totalBookList) {
            if (bookToReturn.equals(book.getTitle())) {
                System.out.println(book);
                book.setAvailable(true);
            }
        }
    }




    public void daysToDueDate(){

    }

 */



    @Override
    public String toString() {

        return "Borrower name: " + getName() +
                ", id: " + getId() + ".";
    }
}
