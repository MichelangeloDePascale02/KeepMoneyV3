package com.example.keepmoneyv3.utility;

import java.io.Serializable;

public class WishLists implements Serializable {
    private int id;
    private String name;
    private String description;
    private int isValid;

    public WishLists(int id, String name, String description, int isValid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isValid = isValid;
    }

    public String getName() {
        return name;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
