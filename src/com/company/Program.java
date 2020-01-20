package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    Menu menu = new Menu();
    BookList bookList = new BookList();
    private ArrayList<Borrower> borrowers = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void start() {
        if (Files.exists(Paths.get("TotalBook.ser"))) {
            bookList = (BookList) FileUtility.loadObject("TotalBook.ser");
            bookList.scanner = new Scanner(System.in);
        } else {
            bookList.setTotalBookObject();
        }

        Borrower borrower1 = new Borrower("Alex");
        Borrower borrower2 = new Borrower("Allen");
        Borrower borrower3 = new Borrower("Alice");
        borrowers.add(borrower1);
        borrowers.add(borrower2);
        borrowers.add(borrower3);
        while (true) {
            System.out.println("Select the menu:  \n 1.Show all the books." +
                    "\n 2.Search a book by title. \n 3.Search a book by author." +
                    "\n 4.Check if a book is available.\n 5.Borrow a book. \n 6.Return a book." +
                    "\n 7.Show all my loan.\n 8.Exit.");
            int choice = 999;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("You must select a number.");
            }

            switch (choice) {
                case 1:
                    //FileUtility.loadObject("TotalBook.ser");
                    bookList.showTotalBookList();
                    break;
                case 2:
                    bookList.searchBookByTitle();// search once then break, need while true or continue ask to choose
                    break;
                case 3:
                    bookList.searchBookByAuthor();
                    break;
                case 4:
                    bookList.checkBookAvailability();
                    break;
                case 5:
                    bookList.addLoan();
                    break;
                case 6:
                    bookList.returnLoan();
                    break;
                case 7:
                    bookList.showBorrowerLoan();
                    break;
                case 8:
                    System.exit(0);
            }
        }


    }


    //menu.chooseUserMenu();


}







    /*
    public void addBorrower(Borrower borrower){
        borrowers.add(borrower);
    }

     */

