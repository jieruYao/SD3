package com.card.model;

public enum Specialism {
    A("JOB_A", 1), B("JOB_B", 2);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private Specialism(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (Specialism tree : Specialism.values()) {
            if (tree.getIndex() == index) {
                return tree.name;
            }
        }
        return null;
    }

    // get set 方法
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