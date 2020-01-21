package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    Menu menu = new Menu();
    BookList bookList = new BookList();
    private ArrayList<Borrower> borrowers = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    BorrowerList borrowerList = new BorrowerList();

    public void start() {

        if (Files.exists(Paths.get("TotalBook.ser"))) {
            bookList = (BookList) FileUtility.loadObject("TotalBook.ser");
            bookList.scanner = new Scanner(System.in);
        } else {
            bookList.setTotalBookObject();// set but not load here?
        }



        //FileUtility.loadObject("TotalBook.ser"); should add this line?

        if (Files.exists(Paths.get("BorrowerList.ser"))) {
            borrowerList = (BorrowerList)FileUtility.loadObject("BorrowerList.ser");
        } else {
            borrowerList.setBorrowerListObject();
        }
        //FileUtility.loadObject("BorrowerList.ser"); Maybe load when use it


        while (true) {
            System.out.println("Select the menu:  \n 1.Show all the books." +
                    "\n 2.Search a book by title. \n 3.Search a book by author." +
                    "\n 4.Check if a book is available.\n 5.Borrow a book. \n 6.Return a book." +
                    "\n 7.Show all my loan.\n 8. Show all the books that lent out."+
                    "\n 9. Show all the books available to borrow. \n 10.Exit.");
            int choice=0;

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
                    bookList.showTotalLoanList();
                    break;
                case 9:
                    bookList.showRemainBookList();
                    break;
                case 10:
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

