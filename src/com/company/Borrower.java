package com.company;

import java.io.Serializable;

public class Borrower extends Person implements Serializable {

    public Borrower(String name, int id) {
        super(name, id);
    }


    @Override
    public String toString() {

        return "Borrower name: " + getName() +
                ", id: " + getId() + ".";
    }
}
