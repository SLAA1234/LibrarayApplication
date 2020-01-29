package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Borrower extends Person implements Serializable {
    public ArrayList<Book> borrowerLoanList = new ArrayList<>();

    public Borrower(String name, int id) {
        super(name, id);
    }


    public void showBorrowerLoans(Borrower borrower){
        borrower.showLoan();
    }

    private void showLoan() {
        for(Book book: borrowerLoanList) {
            if (book != null) {
                System.out.println("Now have: " + book);
            } else {
                System.out.println("Now have no Loan.");
            }
        }
    }


    @Override
    public String toString() {

        return "Borrower name: " + getName() +
                ", id: " + getId() + ".";
    }
}
