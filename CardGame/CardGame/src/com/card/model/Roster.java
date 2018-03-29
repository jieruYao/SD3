package com.card.model;

import java.util.ArrayList;

public class Roster {

    public final static String EntityName = "Roster";

    public final static int capacity = 20;

    private ArrayList<Soldier> soldiers;

    public Roster() {
        soldiers = Shop.getInstance().getInitRoster();
    }

    public boolean addSolider(Soldier soldier) {
        if (isFull())
            return false;
        soldiers.add(soldier);
        return true;
    }

    public Soldier remoeFromRoster(int index) {
        if (isEmpty())
            return null;
        if (index<0||index>=getSize())
            return null;
        return soldiers.remove(index);
    }

    public boolean isFull() {
        return getSize() >= capacity;
    }

    public boolean isEmpty() {
        return getSize() <= 0;
    }

    public int getSize() {
        return soldiers.size();
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }


    public void show() {
        System.out.println("=============================================Roster=============================================");
        System.out.println("\nYou Roster info is as follows:\n");
        int i = 0;
        for (Soldier soldier : soldiers)
            System.out.println("index: " + i++ + " " + soldier);
        System.out.println("\n=============================================Roster=============================================");
    }
}
