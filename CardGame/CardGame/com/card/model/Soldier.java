package com.card.model;

public class Soldier {

    public final static String EntityName = "Soldier";

    private int health;
    private String name;
    private int credit;

    public Soldier() {
        this.health = 100;
        this.name = "";
    }

    public Soldier(int credit, String name, int health) {
        this.health = health;
        this.name = name;
        this.credit = credit;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return String.format("  Soldier  : [ name: %6s   health: %6s  price: %5d ]", name, health, credit);
    }
}
