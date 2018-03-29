package com.card.model;

public class Hierophant extends Soldier {

    public final static String EntityName = "Hierophant";

    private Specialism specialism;
    private int experience;

    public Hierophant() {
        super();
        specialism = Specialism.A;
        experience = 0;
    }

    public Hierophant(Specialism specialism, int experience) {
        super();
        this.specialism = specialism;
        this.experience = experience;
    }

    public Hierophant(String name, int health, Specialism specialism, int experience, int credit) {
        super(credit, name, health);
        this.specialism = specialism;
        this.experience = experience;
    }

    public Specialism getSpecialism() {
        return specialism;
    }

    public void setSpecialism(Specialism specialism) {
        this.specialism = specialism;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return String.format("Hierophant : [ name: %6s   health: %5s  specialism: %2s  experience: %4d  price: %5d ]", getName(), getHealth(), specialism, experience, getCredit());
    }
}
