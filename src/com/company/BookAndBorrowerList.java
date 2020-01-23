package com.company;

import java.io.Serializable;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class BookAndBorrowerList implements Serializable {
    private ArrayList<Book> totalBookList = new ArrayList<>();//List to save space when run, can create new ArrayList
    private List<Book> totalLoanList = new ArrayList<>();
    private List<Book> borrowerLoanList = new ArrayList<>();
    private List<Book> remainBookList = new ArrayList<>();
    private List<Borrower> borrowerList = new ArrayList<>();
    Book book;



    transient Scanner scanner;
    long serialVersionUID = 1;

    BookAndBorrowerList() {
        scanner = new Scanner(System.in);
    }

    public void setTotalBookObject() {// when want to create File outside project, want to read or easy modify,
        //create txt, like Readme. When wants to save object, InputStream or OutputStream, create ser
        totalBookList.add(new Book("Where the Crawdads Sing", "Delia Owens", " A murder mystery"));
        totalBookList.add(new Book("Becoming", "Michelle Obama", " An intimate, powerful, and inspiring memoir "));
        totalBookList.add(new Book("Educated", "Tara Westover", "A coming-of-age Memoir"));
        totalBookList.add(new Book("Dog Man", "Pilkey Dav", "Action-oriented cartoons."));
        totalBookList.add(new Book("Girl, Wash your Face", "Rachel Hollis", "An Encouraging book for girls"));
        remainBookList.add(new Book("Where the Crawdads Sing", "Delia Owens", " A murder mystery"));
        remainBookList.add(new Book("Becoming", "Michelle Obama", " An intimate, powerful, and inspiring memoir "));
        remainBookList.add(new Book("Educated", "Tara Westover", "A coming-of-age Memoir"));
        remainBookList.add(new Book("Dog Man", "Pilkey Dav", "Action-oriented cartoons."));
        remainBookList.add(new Book("Girl, Wash your Face", "Rachel Hollis", "An Encouraging book for girls"));
        borrowerList.add(new Borrower("Alex"));
        borrowerList.add(new Borrower("Allen"));
        borrowerList.add(new Borrower("Alice"));


        FileUtility.saveObject("TotalBook.ser", this, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
    }


    public void showTotalBookList() {
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
        for (Book book : remainBookList) {
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
            System.out.println("The book " + book.getTitle().toUpperCase() + " has been successfully added to your loan list.");
            showBorrowerLoan();
            totalLoanList.add(book);
            remainBookList.remove(book);

        }else {//specify the reason. Is the book not in the library or there is no such a book
            System.out.println("Failed to borrow the book.");
        }
    }

    public void returnLoan(){// when return all books, sth strange shows. need to fix
        System.out.println("Input the title of the book you want to return: ");
        String bookToSearchFor = scanner.nextLine();
        List<Book>bookToReturn = new ArrayList<>();
        boolean didBorrowTheBook = false;

        for(Book book: borrowerLoanList){
            if(bookToSearchFor.equals(book.getTitle())){
                bookToReturn.add(book);
                didBorrowTheBook=true;
                System.out.println("The book " + bookToSearchFor.toUpperCase() + " has been successfully remove from your loan List.");
                //borrowerLoanList.remove(book);//forloop can't add or remove, for it will change the loop, so this doesn't work
            }
        }
        borrowerLoanList.removeAll(bookToReturn);
        totalLoanList.removeAll(bookToReturn);
        remainBookList.addAll(bookToReturn);

        if(didBorrowTheBook==false){
            System.out.println("The borrower doesn't borrow this book with title " + bookToSearchFor.toUpperCase() + ". So you can not return this book.");
        }
        showBorrowerLoan();
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

    public void showTotalLoanList(){
        for(Book book: totalLoanList){
            System.out.println("Now these books are lent out: \n"+ book);
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

    private void showAvailableBooksRandomly(){
        System.out.println("Available Books Catalogue\n");

        for(Book book: remainBookList){
            System.out.println(book);
        }
    }

    private void showAvailableBooksByAuthor(){
        System.out.println("Available Books Catalogue By Author\n");
        remainBookList.sort(Comparator.comparing(Book::getAuthor));
        for(Book book: remainBookList){
            System.out.println(book);
        }
    }

    private void showAvailableBooksByTitle(){
        System.out.println("Available Books Catalogue By Title\n");
        remainBookList.sort(Comparator.comparing(Book::getTitle));
        for(Book book: remainBookList){
            System.out.println(book);
        }
        System.out.println("Now these books are available to borrow: \n");
        for(Book book: remainBookList){
            System.out.println(book);
        }
    }

    public void daysToDueDate() {
    }

    public void addBookToTotalBookList() {
        System.out.println("Please enter the author of the new book: ");
        String author = scanner.nextLine();
        System.out.println("Please enter the title of the new book: ");
        String title = scanner.nextLine();
        System.out.println("Please enter the description of the new book: ");
        String description =  scanner.nextLine();
        totalBookList.add(new Book(title, author, description));
        System.out.println("The book " + title.toUpperCase()+ " has been successfully added to the total book list.");
    }

    public void removeBookFromTotalBookList() {
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
        }
    }

    private void removeByAuthor() {
        System.out.println("Please enter the author of the book you want to remove: ");
        String author = scanner.nextLine();
        List<Book>bookToRemove = new ArrayList<>();
        boolean didExitTheBook = false;

        for(Book book: totalBookList){
            if(author.equals(book.getAuthor())){
                bookToRemove.add(book);
                didExitTheBook=true;
            }
        }
        totalBookList.removeAll(bookToRemove);
        System.out.println("The book with author " + author.toUpperCase() + " has been successfully remove from total book List.");
        remainBookList.removeAll(bookToRemove);


        if(didExitTheBook==false){
            System.out.println("The book with author " + author.toUpperCase() + " doesn't exit. So you can not remove this book.");
        }
        showBorrowerLoan();

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
        remainBookList.removeAll(bookToRemove);


        if(didExitTheBook==false){
            System.out.println("The book with title " + title.toUpperCase() + " doesn't exit. So you can not remove this book.");
        }
        showBorrowerLoan();

    }

    public void showBorrowerList() {
        for(Borrower borrower: borrowerList){
            System.out.println(borrower);
        }
    }

    public void searchBorrowerByName() {
        System.out.println("Please enter the name of the borrower: ");
        String nameToSearch = scanner.nextLine();
        boolean didFindBorrower = false;

        for(Borrower borrower: borrowerList){
            if(nameToSearch.equals(borrower.getName())){
                System.out.println(borrower);//"There is no book with this author."
                didFindBorrower = true;
            }
        }
        if(didFindBorrower==false){
            System.out.println("No borrower with the name.");
        }
        
        
    }

    public void showBorrowersAndTotalLoan() {
    }

    public void addBookToBorrowerLoan() {
    }

    public void removeBookFromBorrowerLoan() {
    }

    public void setLoanPeriod() {
    }

    public void sendReminder() {
    }


    public boolean borrowerLogIn() {

        System.out.println("Please input your name: ");
        String bName= scanner.nextLine();
        for(Borrower borrower: borrowerList){
            if(bName.equals(borrower.getName())){
                System.out.println("You have successfully log in.");
                return true;
            }
        }
        System.out.println("You are not allowed to log in as Borrower. Register at Reception first to become a member.");
        System.exit(0);
        return false;
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
    }
/*
    public void borrowerLogIn() {
        if(isAllowedToLogInAsBorrower()){
            System.out.println("You have successfully log in.");
        }else{
            System.out.println("You are not allowed to log in. Register at Reception first to become a member.");
            System.exit(0);
        }
    }

 */


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


