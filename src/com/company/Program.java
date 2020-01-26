package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Program {

    private Person person;
    private Borrower activeBorrower;
    private Librarian librarian;
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Borrower> borrowers = new ArrayList<>();
    private ArrayList<Book> totalBookList = new ArrayList<>();
    private ArrayList<Book> borrowerLoanList = new ArrayList<>();

    public void start() throws IOException {
        loadObjectFiles("TotalBook", "Borrowers","BorrowerLoan");
        librarian = new Librarian("Megan", 4444);

        while (true) {
            int choice = 999;
            System.out.println("Select the menu: \n 1.I am a borrower. \n 2.I am a librarian.\n 3.Exit.");

            try {
                choice = Integer.parseInt((scanner.nextLine()));
            } catch (Exception e) {
                System.out.println("You must select a number.");
            }

            switch (choice) {
                case 1:
                    Borrower borrower = borrowerLogInCheck();
                    if(borrower!=null){
                        activeBorrower = borrower;
                        borrowerOperate(activeBorrower);
                    }
                    break;
                case 2:
                    //librarianLogIn();
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }


    private Borrower borrowerLogInCheck() {
        System.out.println("Please input your name: ");
        try {
            String checkBName = scanner.nextLine();
            System.out.println("Please input your id: ");
            int checkBID = Integer.parseInt(scanner.nextLine());
            Borrower borrower = checkBorrower(checkBName,checkBID);
            if (borrower!=null) {
                System.out.println("Hi, " + checkBName + ", you are successfully log in.");
                return borrower;
            }
        } catch (Exception e) {
            System.out.println("You must select a number.");
        }
        System.out.println("You are not allowed to log in. Go to reception to register as borrower.");
        return null;
    }

    private Borrower checkBorrower(String checkBName, int checkBID) {
        for (Borrower borrower : borrowers) {
            if (borrower.getName().toUpperCase().equals(checkBName.toUpperCase()) && borrower.getId() == checkBID) {
                return borrower;
            }
        }
        return null;
    }

    private void borrowerOperate(Borrower borrower) {
        while (true){
            System.out.println("Select the menu:  \n 1.Show all the books. \n 2.Show all the books available to borrow." +
                    "\n 3.Search book. \n 4.Check if a book is available. \n 5.Borrow Book." +
                    "\n 6.Return Book. \n 7.Show all my loan.\n 8.Show days to due date. \n 9.Exit");
            int choice = 999;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("You must select a number.");
            }

            switch (choice) {
                case 1:
                    showTotalBookList();
                    break;
                case 2:
                    showRemainBookList();
                    break;
                case 3:
                    searchBook();// search once then break, need while true or continue ask to choose
                    break;
                case 4:
                    checkBookAvailability();
                    break;
                case 5:
                    addLoan(activeBorrower);
                    break;
                case 6:
                    returnBook(activeBorrower);
                    break;
                case 7:
                    showBorrowerLoan(activeBorrower);
                    break;
                //case 8:
                    //borrower.daysToDueDate();
                    //break;
                case 9:
                    FileUtility.saveObject("TotalBook.ser",totalBookList);
                    FileUtility.saveObject("Borrowers.ser",borrowers);
                    FileUtility.saveObject("BorrowerLoan.ser",borrowerLoanList);
                    System.exit(0);
                default:
                    System.out.println("You must choose a number between 1-9.");
            }
        }
    }

    private void showBorrowerLoan(Borrower activeBorrower) {

        for(Book book: borrowerLoanList) {
            if (book != null) {
                System.out.println("Now you have: " + book);
            } else {
                System.out.println("Now you have no Loan.");
            }
        }
    }


    private void addLoan(Borrower activeBorrower) {
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

    private void returnBook(Borrower activeBorrower) {

        Book bookToReturn =  searchBookInBorrowerLoanListByTitle();
        if(bookToReturn!=null) {
            borrowerLoanList.remove(bookToReturn);
            System.out.println("The book " + bookToReturn.getTitle().toUpperCase() + " has been successfully remove from your loan List.");
        }
        bookToReturn.setAvailable(true);
        /*
        for(Book book: totalBookList) {
            if (bookToReturn.getTitle().toUpperCase().equals(book.getTitle().toUpperCase())) {
                book.setAvailable(true);
            }
        }

         */
    }

    public Book searchBookInBorrowerLoanListByTitle() {
        System.out.println("Please input the title: ");
        String titleToSearch = scanner.nextLine();

        for(Book book: borrowerLoanList){
            if(titleToSearch.toUpperCase().equals(book.getTitle().toUpperCase())){
                return book;
            }
        }
        System.out.println("No book with this title in your loan.");
        return null;
    }

    public Book checkBookAvailability(){
        System.out.println("Please input the title: ");
        String titleToSearch = scanner.nextLine();
        for (Book book : totalBookList) {
            if (titleToSearch.toUpperCase().equals(book.getTitle().toUpperCase())&& book.isAvailable()) {
                System.out.println("The book is in the library. You can borrow the book.");
                return book;
            }
            if(titleToSearch.toUpperCase().equals(book.getTitle().toUpperCase())&& !book.isAvailable()){
                System.out.println("The book is lent out");
                return null;
            }
        }
        System.out.println("The library doesn't have this book. Contact the reception to order this book.");
        return null;
    }

    private void searchBook() {
        System.out.println("Select from the menu: \n 1.Search book by title. \n 2.Search book by author.");
        try{
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    searchBookByAuthor();
                    break;
                default:
                    System.out.println("You must select a number between 1-2");
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Book searchBookByTitle() {
        System.out.println("Please input the title: ");
        String titleToSearch = scanner.nextLine();
        //boolean didFindBook = false;

        for(Book book: totalBookList){
            if(titleToSearch.toUpperCase().equals(book.getTitle().toUpperCase())){
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
            if(authorToSearch.toUpperCase().equals(book.getAuthor().toUpperCase())){
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


    private void showRemainBookList() {
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



    private void showTotalBookList() {
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

    private void showBooksRandomly() {
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


/*
    private void showTotalBookList() {
        for(Book book: totalBookList){
            System.out.println("");
        }
    }

 */


/*
    public void borrowerChoice(Borrower borrower){
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
                        person.showTotalBookList();
                        break;
                    case 2:
                        person.showRemainBookList();
                        break;
                    case 3:
                        person.searchBookByTitle();// search once then break, need while true or continue ask to choose
                        break;
                    case 4:
                        person.searchBookByAuthor();
                        break;
                    case 5:
                        person.checkBookAvailability();
                        break;
                    case 6:
                        borrower.addLoan();
                        break;
                    case 7:
                        borrower.returnBook();
                        break;
                    case 8:
                        borrower.showBorrowerLoan();
                        break;
                    case 9:
                        borrower.daysToDueDate();
                        break;
                    case 10:
                        FileUtility.saveObject("TotalBook.ser",totalBookList);
                        FileUtility.saveObject("Borrowers.ser",borrowers);
                        System.exit(0);
                }
            }
        }

 */



/*
    public void librarianChoice() {
        bookAndBorrowerList.AdminLogIn();
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
                    bookAndBorrowerList.showBorrowerLoan();
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


    public boolean AdminLogIn() {

        System.out.println("Please input your name: ");
        String lName = scanner.nextLine();
        if (lName.equals("Irene")) {
            System.out.println("You have successfully log in as an admin.");
            return true;
        } else {
            System.out.println("You are not allowed to log in. You are not the Admin.");
            System.exit(0);
            return false;
        }

     */



    public void setBorrowers (ArrayList < Borrower > borrowers) {
        this.borrowers = borrowers;
    }

    public void setTotalBookList (ArrayList < Book > totalBookList) {
        this.totalBookList = totalBookList;
    }

    public void setBorrowerLoanList(ArrayList<Book> borrowerLoanList) {
        this.borrowerLoanList = borrowerLoanList;
    }

    public void showBorrowerList() {
        for(Borrower borrower: borrowers){
            System.out.println(borrower);
        }
    }

    private void loadObjectFiles (String fileName1, String fileName2, String fileName3) throws IOException

    {
        fileName1 = fileName1 + ".ser";
        fileName2 = fileName2 + ".ser";
        fileName3 = fileName3 + ".ser";
        Path path1 = Paths.get(fileName1);
        Path path2 = Paths.get(fileName2);
        Path path3 = Paths.get(fileName3);
        if (!Files.exists(path1) && !Files.exists(path2)&& !Files.exists(path3)) {

            Files.createFile(path1);
            Files.createFile(path2);
            Files.createFile(path3);

            totalBookList.add(new Book("Where the Crawdads Sing", "Delia Owens", " A murder mystery", true));
            totalBookList.add(new Book("Becoming", "Michelle Obama", " An intimate, powerful, and inspiring memoir ", true));
            totalBookList.add(new Book("Educated", "Tara Westover", "A coming-of-age Memoir", true));
            totalBookList.add(new Book("Dog Man", "Pilkey Dav", "Action-oriented cartoons.", true));
            totalBookList.add(new Book("Girl, Wash your Face", "Rachel Hollis", "An Encouraging book for girls", true));
            borrowers.add(new Borrower("Alex", 1111));
            borrowers.add(new Borrower("Allen", 2222));
            borrowers.add(new Borrower("Alice", 3333));
            borrowers.add(new Borrower("Lisa", 6666));


            FileUtility.saveObject("TotalBook.ser",totalBookList, StandardOpenOption.TRUNCATE_EXISTING);
            FileUtility.saveObject("Borrowers.ser",borrowers,StandardOpenOption.TRUNCATE_EXISTING);
            FileUtility.saveObject("BorrowerLoan.ser",borrowerLoanList,StandardOpenOption.TRUNCATE_EXISTING);


        } else {
            ArrayList<Book> totalBookList = (ArrayList<Book>) FileUtility.loadObject("TotalBook.ser");
            setTotalBookList(totalBookList);
            ArrayList<Borrower> borrowers = (ArrayList<Borrower>) FileUtility.loadObject("Borrowers.ser");
            setBorrowers(borrowers);
            ArrayList<Book> borrowerLoanList = (ArrayList<Book>) FileUtility.loadObject("BorrowerLoan.ser");
            setBorrowerLoanList(borrowerLoanList);

        }
    }
}
















