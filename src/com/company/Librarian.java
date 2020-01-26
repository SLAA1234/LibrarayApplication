package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Librarian extends Person {


    public Librarian(String name, int id) {
        super(name, id);
    }


/*

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
