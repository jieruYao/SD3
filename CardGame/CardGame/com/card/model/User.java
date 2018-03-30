package com.card.model;

import com.google.gson.Gson;

import java.util.Scanner;

public class User {
    private Roster roster;
    private Team team;
    private String username;
    private String password;
    private int credit;

//    public User() {
//        roster = new Roster();
//        team = new Team();
//    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        roster = new Roster();
        team = new Team();
        credit = 500;
    }

    public void buySoldier(Scanner scanner, boolean isFirst) {//buy soldiers in the shop
        Shop shop = Shop.getInstance();
        if (isFirst)
            shop.showSoldiers();
        System.out.println("-1 to back");
        int choose;
        System.out.println("\nPlease enter the index of soldier you want: ");
        choose = scanner.nextInt();
        if (choose >= -1 && choose < Shop.number) {
            if (choose == -1) {
                System.out.println("\nWelcome back! ^~^");
            } else {
                Soldier soldier = shop.getSoldier(choose);
                if (soldier != null) {
                    if (soldier.getCredit() > getCredit()) {//not enough credits to buy
                        System.out.println("\nSorry,you credit is not enough.");
                        buySoldier(scanner, false);
                    } else {
                        roster.addSolider(soldier);
                        setCredit(credit - soldier.getCredit());//calculate the balance after a purchase
                        System.out.println("\nBuy " + soldier.getName() + " Successfully, the credit is " + credit + " left.");
                        buySoldier(scanner, false);
                    }
                }
            }
        } else {
            System.out.println("\nSorry, you input is not valid.");
            buySoldier(scanner, false);
        }
    }

    public void showTeam() {
        team.show();
    }

    public void showRoster() {
        roster.show();
    }

    public Roster getRoster() {
        return roster;
    }

    public void setRoster(Roster roster) {
        this.roster = roster;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public void show() {
        System.out.println("\n");
        System.out.println("Your info is as follow:");
        System.out.println("\n");
        System.out.println("Username: " + getUsername());
        System.out.println("Credit: " + getCredit());
        System.out.println("");
        System.out.println("Team Info:");
        getTeam().show();
        System.out.println("Roster info");
        getRoster().show();
        System.out.println("\n");
    }
}
