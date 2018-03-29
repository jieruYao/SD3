package com.card.model;

public enum Specialism {
    A("JOB_A", 1), B("JOB_B", 2);
    
    private String name;
    private int index;

    
    private Specialism(String name, int index) {
        this.name = name;
        this.index = index;
    }

    
    public static String getName(int index) {
        for (Specialism tree : Specialism.values()) {
            if (tree.getIndex() == index) {
                return tree.name;
            }
        }
        return null;
    }

    // get set 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}