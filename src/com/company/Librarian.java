package com.company;

import java.awt.*;
import java.io.Serializable;

public class Librarian extends Person implements Serializable {

    public Librarian(String name, int id) {
        super(name, id);
    }

    @Override
    public String toString() {

        return "Librarian name: " + getName() +
                ", id: " + getId() + ".";
    }
}
