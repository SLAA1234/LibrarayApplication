package com.company;

import java.util.ArrayList;
import java.util.List;

public class BookList {

    private List<Book> totalBookList = new ArrayList<>();//List to save space when run, can create new ArrayList
    private List<Book> borrowerLoanList = new ArrayList<>();
    private List<Book> totalLoanList = new ArrayList<>();
    private List<Book> remainBookList = new ArrayList<>();

    public void setTotalBookObject(){// when want to create File outside project, want to read or easy modify,
        //create txt, like Readme. When wants to save object, InputStream or OutputStream, create ser
        FileUtility.saveObject("TotalBook.ser",totalBookList);
        totalBookList.add(new Book("Where the Crawdads Sing", "Delia Owens", " A murder mystery"));
        totalBookList.add(new Book("Becoming", "Michelle Obama"," An intimate, powerful, and inspiring memoir "));
        totalBookList.add(new Book("Educated","Tara Westover", "A coming-of-age Memoir"));
        totalBookList.add(new Book("Dog Man","Pilkey Dav", "Action-oriented cartoons."));
        totalBookList.add(new Book("Girl, Wash your Face", "Rachel Hollis", "An Encouraging book for girls"));
    }

}
