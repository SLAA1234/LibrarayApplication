package com.company;

import java.io.Serializable;

public class Librarian implements Serializable {
    private String name;

    public Librarian(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Librarian name:  " + name + ".";
    }
}
