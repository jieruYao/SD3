package com.card.model;

import java.util.ArrayList;

public class Team {

    public final static String EntityName = "Team";

    public final static int capacity = 10;

    private ArrayList<Soldier> soldiers;

    public Team() {
        soldiers = new ArrayList<>();
    }

    public boolean addSolider(Soldier soldier) {
        if (isFull())
            return false;
        soldiers.add(soldier);
        return true;
    }

    public Soldier getSoldier(int index) {
        if (index >= capacity || capacity < 0)
            return null;
        return soldiers.get(index);
    }

    public Soldier removeSoldier(int index) {
        if (isEmpty())
            return null;
        if (index < 0 || index >= getSize())
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

    public void show() {
        System.out.println("=============================================Team=============================================");
        System.out.println("\nYou team info is as follows:\n");
        int i = 0;
        for (Soldier soldier : soldiers)
            System.out.println("index: " + i++ + " " + soldier);
        System.out.println("\n=============================================Team=============================================");
    }

    public void isWin(boolean flag) {//if the player is win, the experience of the Captain and the Hierophant will increase 10
	                                 //if the player is loss, the experence of the Captain and the Hierophant will decrease 2
        for (Soldier soldier : soldiers) {
            if (flag) {
                if (soldier instanceof Hierophant)
                    ((Hierophant) soldier).setExperience(((Hierophant) soldier).getExperience() - 2);
                else if (soldier instanceof Captain)
                    ((Captain) soldier).setExperience(((Captain) soldier).getExperience() - 2);
            } else {
                if (soldier instanceof Hierophant)
                    ((Hierophant) soldier).setExperience(((Hierophant) soldier).getExperience() + 10);
                else if (soldier instanceof Captain)
                    ((Captain) soldier).setExperience(((Captain) soldier).getExperience() + 10);
            }
        }
    }

}
