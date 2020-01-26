package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
                    Librarian librarian = librarianLogInCheck();
                    if(librarian!=null){
                        librarianOperate(librarian);
                    }
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

    private void librarianOperate(Librarian librarian) {
        while (true){
            System.out.println("Select the menu:  \n 1.Show all the books. \n 2.Show all the books available to borrow." +
                    "\n 3.Add a new book. \n 4.Remove a Book.\n 5.Show borrowers." +
                    "\n 6.Show a borrower loan. \n 7.Show all borrowers' loan." +
                    " \n 8.Search borrower. \n 9.Add borrower. \n 10.Remove borrower." +
                    "\n 11.Exit");
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
                    addBook();
                    break;
                case 4:
                    removeBook();
                    break;
                case 5:
                    checkBorrowerList();
                    break;
                case 6:
                    showABorrowerLoan();
                    break;
                case 7:
                    showAllBorrowersLoan();
                    break;
                case 8:
                    searchBorrower();
                    break;
                case 9:
                    addBorrower();
                    break;
                case 10:
                    removeBorrower();
                    break;
                case 11:
                    FileUtility.saveObject("TotalBook.ser",totalBookList);
                    FileUtility.saveObject("Borrowers.ser",borrowers);
                    System.exit(0);
                default:
                    System.out.println("You must choose a number between 1-11.");
            }
        }
        }

    public void addBorrower(){
        System.out.println("Please input borrower's name: ");
        String bName = scanner.nextLine();
        System.out.println("Please input borrower's id: ");
        try {
            int bId = Integer.parseInt(scanner.nextLine());
            borrowers.add(new Borrower(bName,bId));
            System.out.println("The borrower with name " + bName.toUpperCase() + " has been successfully added as member.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void removeBorrower() {
        Borrower borrowerToRemove = searchBorrower();
        borrowers.remove(borrowerToRemove);

    }


    private void showAllBorrowersLoan() {
        for (Borrower borrower: borrowers){
            for(Book book: borrowerLoanList){
                System.out.println(book);
            }
        }
    }

    private void showABorrowerLoan() {
        Borrower searchBorrower = searchBorrower();
        showBorrowerLoan(searchBorrower);

    }

    private Borrower searchBorrower() {
        System.out.println("Please input name: ");

        String nameToSearch = scanner.nextLine();
        for(Borrower borrower: borrowers){
            if(nameToSearch.toUpperCase().equals(borrower.getName().toUpperCase())){
                System.out.println(borrower);
                return borrower;
            }
        }
        System.out.println("No borrower with this name.");
        return null;
    }

    private void checkBorrowerList() {
        for(Borrower borrower: borrowers){
            System.out.println(borrower);
        }
    }

    private void removeBook() {

        System.out.println("Select from Menu: \n 1.Remove by author. \n 2.Remove by title.");
        int choice = 999;
        try {
            choice = Integer.parseInt((scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("You must select a number.");
        }
        switch (choice) {
            case 1:
                removeByAuthor();
                break;
            case 2:
                removeByTitle();
                break;
            default:
                System.out.println("You must select a number between 1-2.");
        }
    }

    private void removeByAuthor() {
        System.out.println("Please enter the author of the book you want to remove: ");
        String author = scanner.nextLine();
        List<Book> bookToRemove = new ArrayList<>();
        boolean didExitTheBook = false;

        for(Book book: totalBookList){
            if(author.equals(book.getAuthor())){
                bookToRemove.add(book);
                didExitTheBook=true;
            }
        }
        totalBookList.removeAll(bookToRemove);
        System.out.println("The book with author " + author.toUpperCase() + " has been successfully remove from total book List.");

        if(didExitTheBook==false){
            System.out.println("The book with author " + author.toUpperCase() + " doesn't exit. So you can not remove this book.");
        }

    }

    private void removeByTitle(){
        System.out.println("Please enter the title of the book you want to remove: ");
        String title = scanner.nextLine();
        List<Book>bookToRemove = new ArrayList<>();
        boolean didExitTheBook = false;

        for(Book book: totalBookList){
            if(title.equals(book.getTitle())){
                bookToRemove.add(book);
                didExitTheBook=true;
                System.out.println("The book " + title.toUpperCase() + " has been successfully remove from total book List.");
            }
        }
        totalBookList.removeAll(bookToRemove);

        if(didExitTheBook==false){
            System.out.println("The book with title " + title.toUpperCase() + " doesn't exit. So you can not remove this book.");
        }
    }

    private void addBook() {
        System.out.println("Please enter the author of the new book: ");
        String author = scanner.nextLine();
        System.out.println("Please enter the title of the new book: ");
        String title = scanner.nextLine();
        System.out.println("Please enter the description of the new book: ");
        String description =  scanner.nextLine();
        totalBookList.add(new Book(title, author, description, true));
        System.out.println("The book " + title.toUpperCase()+ " has been successfully added to the total book list.");
    }



    private Librarian librarianLogInCheck() {
        System.out.println("Please input your name: ");
        try {
            String checkLName = scanner.nextLine();
            System.out.println("Please input your id: ");
            int checkLID = Integer.parseInt(scanner.nextLine());
            Librarian librarian = checkLibrarian(checkLName,checkLID);
            if (librarian!=null) {
                System.out.println("Hi, " + checkLName + ", you are successfully log in.");
                return librarian;
            }
        } catch (Exception e) {
            System.out.println("You must input a number.");
        }
        System.out.println("You are not allowed to log in. You are not the admin.");
        return null;
    }

    private Librarian checkLibrarian(String checkLName, int checkLID) {
        if (librarian.getName().toUpperCase().equals(checkLName.toUpperCase()) && librarian.getId() == checkLID) {
            return librarian;
        }
        return null;
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
            System.out.println("You must input a number.");
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
        }else {
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
                System.out.println(book);
                return book;
            }
        }
        System.out.println("No book with this title.");
        return null;
    }


    public Book searchBookByAuthor() {
        System.out.println("Please input the author: ");
        String authorToSearch = scanner.nextLine();

        for(Book book: totalBookList){
            if(authorToSearch.toUpperCase().equals(book.getAuthor().toUpperCase())){
                System.out.println(book);
            }
        }
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
















