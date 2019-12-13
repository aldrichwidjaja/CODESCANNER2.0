package com.example.testingapp1.Model;

public class Listitem {

    int id;
    String code;
    String type;

    public Listitem(int id, String code, String type) {
        this.id = id;
        this.code = code;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
