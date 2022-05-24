package com.assignment.su22.game.enums;


public enum Symbols {
    COMMA("."),
    GREATER(">"),
    ARROW("v"),
    HOLE("x");


    private final String value;
    private Symbols(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString()
    {
        return this.value;
    }
}



