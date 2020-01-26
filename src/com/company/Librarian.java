package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Librarian extends Person {

   // protected ArrayList<Borrower> borrowers=new ArrayList<>();

    public Librarian(String name, int id) {
        super(name, id);
    }


/*





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


    public void checkAllBorrowerLoan(){//can put i program

    }
    public void showBorrowerList() {
        for(Borrower borrower: borrowers){
            System.out.println(borrower);
        }
    }



    public void addBorrower(){
        borrowers.add(new Borrower(getName(),getId()));
    }

    public void removeBorrower(Borrower borrower) {
        System.out.println("Please input borrower's name: ");
        try {
            String nameToSearch = scanner.nextLine();
            if (nameToSearch.equals(borrower.getName())) {
                borrowers.remove(borrower);
                System.out.println("The borrower "+ nameToSearch.toUpperCase() + "has been successfully deleted.");
            }

        } catch (Exception e) {
            System.out.println("Check you spelling. The is no borrower with this name.");
        }
    }

    public Borrower checkBorrower(){

        try {
            System.out.println("Please input your name: ");
            String bName = scanner.nextLine();
            System.out.println("Please input your id: ");
            int bID = Integer.parseInt(scanner.nextLine());
            for(Borrower borrower: borrowers){
                if(bName.equals(borrower.getName())&& bID==borrower.getId()){
                    System.out.println("Hi, " + borrower.getName() + ", you are successfully log in.");
                    return borrower;
                }
                if(!bName.equals(borrower.getName())|| bID!=borrower.getId()){
                    System.out.println("You are not allowed to log in. Go to reception to register as borrower.");
                }
            }
        } catch (Exception e) {
            System.out.println("You must select a number.");
        }

        return null;
    }

    public Borrower searchBorrowerByName() {
        System.out.println("Please input name: ");

        String nameToSearch = scanner.nextLine();
        for(Borrower borrower: borrowers){
            if(nameToSearch.equals(borrower.getName())){
                System.out.println(borrower);
                return borrower;
            }
        }
        return null;
    }

 */


    @Override
    public String toString() {

        return "Librarian name: " + getName() +
                ", id: " + getId() + ".";
    }
}
