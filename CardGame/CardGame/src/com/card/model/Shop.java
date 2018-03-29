package com.card.model;

import java.util.ArrayList;
import java.util.Random;

public class Shop {
    public final static int number = 25;
    public static Shop instance;

    private Shop() {
        initData();
    }

    public static Shop getInstance() {
        if (instance == null)
            instance = new Shop();
        return instance;
    }

    private String[] names = {"Jack", "Ross", "Mike", "Ketty", "Simple",
            "Osca", "Alpha", "Betta", "Sigma", "Dinphine",
            "Slina", "Hallen", "Motor", "Terry", "Sadi",
            "Gate", "Dog", "Stiphe", "Agel", "Baby",
            "Testal", "Vettu", "Uoni", "Django", "Java",};

    private Specialism[] jobs = {Specialism.A, Specialism.A, Specialism.A, Specialism.A, Specialism.A,
            Specialism.B, Specialism.B, Specialism.B, Specialism.B, Specialism.B,
            Specialism.A, Specialism.A, Specialism.A, Specialism.A, Specialism.A,
            Specialism.B, Specialism.B, Specialism.B, Specialism.B, Specialism.B,
            Specialism.A, Specialism.A, Specialism.A, Specialism.A, Specialism.A};

    private int[] healths = {100, 200, 300, 400, 100,
            100, 700, 800, 900, 1000,
            1050, 950, 750, 650, 450,
            250, 150, 750, 650, 440,
            444, 343, 234, 343, 456};

    private int[] credits = {40, 50, 30, 40, 50,
            60, 70, 80, 90, 100,
            120, 170, 200, 300, 400,
            500, 600, 750, 650, 440,
            444, 343, 234, 343, 456};

    private ArrayList<Soldier> soldiers;

    public void initData() {
        soldiers = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            if (i % 3 == 0)
                soldiers.add(new Soldier(credits[i], names[i], healths[i]));
            else if (i % 3 == 1)
                soldiers.add(new Captain(names[i], healths[i], jobs[i], 0, credits[i]));
            else
                soldiers.add(new Hierophant(names[i], healths[i], jobs[i], 0, credits[i]));
        }
    }

    public void showSoldiers() {
        System.out.println("=========================================Soldier Shop==========================================");
        System.out.println("The soldiers in my shop is as follows:\n");
        for (int i = 0; i < number; i++) {
            System.out.printf("index: %2d  ", i);
            System.out.println(soldiers.get(i).toString());
            System.out.println();
        }
        System.out.println("=========================================Soldier Shop==========================================");
    }

    public static void main(String[] args) {
//        Shop.getInstance().showSoliders();
//        Shop.getInstance().getRandomTeam();
    }

    public Team getRandomTeam(int number) {
        Team team = new Team();
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int index = random.nextInt(10);
            team.addSolider(soldiers.get(index));
        }
        return team;
    }

    public ArrayList<Soldier> getInitRoster() {
        ArrayList<Soldier> soldiers = new ArrayList<>();
        soldiers.add(this.soldiers.get(4));
        soldiers.add(this.soldiers.get(5));
        return soldiers;
    }


    public Soldier getSoldier(int index) {
        if (index < 0 || index >= number)
            return null;
        else return soldiers.get(index);
    }


}
