package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    BookAndBorrowerList bookAndBorrowerList = new BookAndBorrowerList();
    Scanner scanner = new Scanner(System.in);

    public void start() {

        if (Files.exists(Paths.get("TotalBook.ser"))) {
            bookAndBorrowerList = (BookAndBorrowerList) FileUtility.loadObject("TotalBook.ser");
            bookAndBorrowerList.scanner = new Scanner(System.in);
        } else {
            bookAndBorrowerList.setTotalBookObject();// set but not load here?, set then doesn't load
        }


        while (true) {
            System.out.println("Select the menu: \n 1.I am a borrower. \n 2.I am a librarian.\n 3.Exit.");
            int choice = 999;

            try {
                choice = Integer.parseInt((scanner.nextLine()));
            } catch (Exception e) {
                System.out.println("You must select a number.");
            }
            switch (choice) {
                case 1:
                    borrowerChoice();
                    break;
                case 2:
                    librarianChoice();
                    break;
                case 3:
                    System.exit(0);

            }
        }
    }

    public void borrowerChoice() {//
        //add a log in method, so can remember user information. fix this.
        while (true) {
            System.out.println("Select the menu:  \n 1.Show all the books." +
                    "\n 2.Show all the books available to borrow" +
                    "\n 3.Search a book by title. \n 4.Search a book by author." +
                    "\n 5.Check if a book is available.\n 6.Borrow a book. \n 7.Return a book." +
                    "\n 8.Show all my loan. \n 9.Show how many days left to due date. \n 10.Exit.");
            int choice = 999;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("You must select a number.");
            }

            switch (choice) {
                case 1:
                    bookAndBorrowerList.showTotalBookList();
                    break;
                case 2:
                    bookAndBorrowerList.showRemainBookList();
                    break;
                case 3:
                    bookAndBorrowerList.searchBookByTitle();// search once then break, need while true or continue ask to choose
                    break;
                case 4:
                    bookAndBorrowerList.searchBookByAuthor();
                    break;
                case 5:
                    bookAndBorrowerList.checkBookAvailability();
                    break;
                case 6:
                    bookAndBorrowerList.addLoan();
                    break;
                case 7:
                    bookAndBorrowerList.returnLoan();
                    break;
                case 8:
                    bookAndBorrowerList.showBorrowerLoan();
                    break;
                case 9:
                    bookAndBorrowerList.daysToDueDate();
                    break;
                case 10:
                    System.exit(0);
            }
        }
    }

    public void librarianChoice() {
        while (true) {
            System.out.println("Select the menu:  \n 1.Show all the books." +
                    "\n 2.Add a new book to total book list. \n 3.Remove a book from total book list" +
                    "\n 4.Show all the books available to borrow. \n 5.Check if a book is available." +
                    "\n 6.Show all the books that lent out. \n 7.Show all the borrowers." +
                    "\n 8.Search a borrower by name. \n 9.Add a book to a borrower's loan list. " +
                    "\n 10.Remove a book from a borrower's loan list." +
                    "\n 11.Show all the books a borrower has lent." +
                    "\n 12.Show all the borrowers and the books they have borrowed." +
                    "\n 13.Set length a book can be borrowed." +
                    "\n 14.Send a reminder to borrower to return book. " +
                    "\n 15.Exit.");
            int choice = 999;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("You must select a number.");
            }

            switch (choice) {
                case 1:
                    //FileUtility.loadObject("TotalBook.ser");
                    bookAndBorrowerList.showTotalBookList();
                    break;
                case 2:
                    bookAndBorrowerList.addBookToTotalBookList();// search once then break, need while true or continue ask to choose
                    break;
                case 3:
                    bookAndBorrowerList.removeBookFromTotalBookList();
                    break;
                case 4:
                    bookAndBorrowerList.showRemainBookList();
                    break;
                case 5:
                    bookAndBorrowerList.checkBookAvailability();
                    break;
                case 6:
                    bookAndBorrowerList.showTotalLoanList();
                    break;
                case 7:
                    bookAndBorrowerList.showBorrowerList();
                    break;
                case 8:
                    bookAndBorrowerList.searchBorrowerByName();
                    break;
                case 9:
                    bookAndBorrowerList.addBookToBorrowerLoan();
                    break;
                case 10:
                    bookAndBorrowerList.removeBookFromBorrowerLoan();
                    break;
                case 11:
                    bookAndBorrowerList.showBorrowerLoan();
                    break;
                case 12:
                    bookAndBorrowerList.showBorrowersAndTotalLoan();
                    break;
                case 13:
                    bookAndBorrowerList.setLoanPeriod();
                    break;
                case 14:
                    bookAndBorrowerList.sendReminder();
                    break;
                case 15:
                    System.exit(0);
            }
        }
    }
}











