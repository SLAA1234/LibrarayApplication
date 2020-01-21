package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BorrowerList implements Serializable {
    private List<Borrower> borrowerList = new ArrayList<>();

    public void setBorrowerListObject(){
        borrowerList.add(new Borrower("Alex"));
        borrowerList.add(new Borrower("Allen"));
        borrowerList.add(new Borrower("Alice"));
        FileUtility.saveObject("BorrowerList.ser",this);
    }
}
