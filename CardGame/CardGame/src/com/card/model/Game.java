package com.card.model;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private User user;
    private boolean logout_flag = false;


    public void login(Scanner scanner) {
        String username;
        String password;
        System.out.println();
        System.out.println();
        scanner.nextLine();
        System.out.println("Please enter you username: ");
        username = scanner.nextLine();
        while (username.trim().isEmpty()) {
            System.out.println("User name should not be empty.");
            System.out.println("Please enter you username: ");
            username = scanner.nextLine();
        }
        System.out.println("Please enter you password: ");
        password = scanner.nextLine();
        while (password.trim().isEmpty()) {
            System.out.println("User name should not be empty.");
            System.out.println("Please enter you username: ");
            password = scanner.nextLine();
        }
        User user = UserHelper.login(username, password);
        if (user != null) {
            this.user = user;
            System.out.println("Welcome back, " + user.getUsername());
            user.show();
            logout_flag = false;
            while (!logout_flag)
                startSecondMenu(scanner);
        }
    }

    private void startSecondMenu(Scanner scanner) {
        int choose;
        System.out.println("===========================================Welcome to my card game=============================================");
        System.out.println("1   my info");
        System.out.println("2   my team info");
        System.out.println("3   my roster info");
        System.out.println("4   move soldier to roster");
        System.out.println("5   move soldier to team");
        System.out.println("6   buy soldier");
        System.out.println("7   fight with computer");
        System.out.println("8   logout");

        System.out.print("Please enter you choose: ");
        choose = scanner.nextInt();
        switch (choose) {
            case 1:
                user.show();
                break;
            case 2:
                user.showTeam();
                break;
            case 3:
                user.showRoster();
                break;
            case 4:
                move2Roster(scanner);
                break;
            case 5:
                move2Team(scanner);
                break;
            case 6:
                user.buySoldier(scanner, true);
                break;
            case 7:
                playWithComputer();
                break;
            case 8:
                logout();
                break;
            default:
                break;
        }
    }

    private void logout() {
        logout_flag = true;
        user = null;
        System.out.println("Log out Successfully!!");
    }

    private void move2Roster(Scanner scanner) {
        System.out.println("\n");
        if (user.getTeam().getSize() == 0) {
            System.out.println("You have no soldier in your team, please add from roster first");
            return;
        }
        int choose;
        System.out.println("\n");
        System.out.println("You team info is as follow:");
        user.showTeam();
        System.out.println("\n");
        System.out.println("Please enter the index of solider:");
        choose = scanner.nextInt();
        Soldier soldier = user.getTeam().removeSoldier(choose);
        if (soldier == null) {
            System.out.println("\n");
            System.out.println("You enter is invalid");
            return;
        }
        user.getRoster().addSolider(soldier);
        UserHelper.saveUser(user);
        System.out.println("\n");
        System.out.println("Soldier add to roster successfully");
        System.out.println("\n");
    }

    private void move2Team(Scanner scanner) {
        System.out.println("\n");
        if (user.getRoster().getSize() == 0) {
            System.out.println("\n");
            System.out.println("You have no soldier in your Roster, please add from team first");
            return;
        }
        int choose;
        System.out.println("\n");
        System.out.println("You roster info is as follow:");
        user.showRoster();
        System.out.println("\n");
        System.out.println("Please enter the index of solider:");
        choose = scanner.nextInt();
        Soldier soldier = user.getRoster().remoeFromRoster(choose);
        if (soldier == null) {
            System.out.println("\n");
            System.out.println("You enter is invalid");
            return;
        }
        user.getTeam().addSolider(soldier);
        System.out.println("\n");
        System.out.println("Soldier add to team successfully");
        UserHelper.saveUser(user);
        System.out.println("\n");
    }

    public void start(Scanner scanner) {
        while (true)
            startMenu(scanner);
    }

    public void startMenu(Scanner scanner) {
        int choose;
        System.out.println("===========================================Welcome to my card game=============================================");
        System.out.println("1   login");
        System.out.println("2   register");
        System.out.println("3   quit");
        System.out.print("Please enter you choose: ");
        choose = scanner.nextInt();
        switch (choose) {
            case 1:
                login(scanner);
                break;
            case 2:
                register(scanner);
                break;
            case 3:
                System.out.println("Bye Bye");
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public void register(Scanner scanner) {
        String username;
        String password;
        System.out.println();
        System.out.println();
        scanner.nextLine();
        System.out.println("Please enter you username: ");
        username = scanner.nextLine();
        while (username.trim().isEmpty()) {
            System.out.println("User name should not be empty.");
            System.out.println("Please enter you username: ");
            username = scanner.nextLine();
        }
        System.out.println("Please enter you password: ");
        password = scanner.nextLine();
        while (password.trim().isEmpty()) {
            System.out.println("User name should not be empty.");
            System.out.println("Please enter you username: ");
            password = scanner.nextLine();
        }
        UserHelper.register(username, password);
    }

    public void playWithComputer() {
        Team team = user.getTeam();
        Team pcTeam = Shop.getInstance().getRandomTeam(user.getTeam().getSize());
        if (user.getTeam().getSize() == 0) {
            System.out.println("\nSorry,your team has no soldier now.Fight cannot be started.\n");
            return;
        }
        int term = 0;
        int user_dead_index = 0;
        int pc_dead_index = 0;
        int pc_blood;
        int user_blood;
        pc_blood = pcTeam.getSoldier(pc_dead_index).getHealth();
        user_blood = team.getSoldier(user_dead_index).getHealth();
        while (true) {
            if (user_dead_index == team.getSize()) {
                System.out.println("\nYou Loss!!!\n");
                user.getTeam().isWin(false);
                break;
            }
            if (pc_dead_index == pcTeam.getSize()) {
                System.out.println("\nYou win!!!");
                user.setCredit(user.getCredit() + 50);
                user.getTeam().isWin(true);
                break;
            }
            try {
                Thread.sleep(2000);
                int harm;
                System.out.println();
                if (term == 0) {
                    System.out.println("User's " + (user_dead_index + 1) + " solider " + team.getSoldier(user_dead_index).getName() + " term");
                    harm = getRandomHarm();
                    if (pc_blood > harm) {
                        System.out.println("\nharm: " + harm);
                        System.out.println("PC's " + (pc_dead_index + 1) + " solider " + pcTeam.getSoldier(pc_dead_index).getName());
                        System.out.println("Health before: " + pc_blood);
                        pc_blood -= harm;
                        System.out.println("Health now: " + pc_blood);
                    } else {
                        System.out.println("\nharm: " + harm);
                        System.out.println("PC's " + (pc_dead_index + 1) + " solider " + pcTeam.getSoldier(pc_dead_index).getName());
                        System.out.println("Health before: " + pc_blood);
                        pc_blood = 0;
                        System.out.println("Health now: " + pc_blood);
                        System.out.println("Soldier Dead,Pc change to next soldier.");
                        pc_dead_index++;
                        if (pc_dead_index < pcTeam.getSize())
                            pc_blood = pcTeam.getSoldier(pc_dead_index).getHealth();
                        continue;
                    }
                } else {
                    System.out.println("PC's " + (pc_dead_index + 1) + " solider " + team.getSoldier(pc_dead_index).getName() + " term");
                    harm = getRandomHarm();
                    if (user_blood > harm) {
                        System.out.println("\nharm: " + harm);
                        System.out.println("User's " + (user_dead_index + 1) + " solider " + team.getSoldier(user_dead_index).getName());
                        System.out.println("Health before: " + user_blood);
                        user_blood -= harm;
                        System.out.println("Health now: " + user_blood);
                    } else {
                        System.out.println("\nharm: " + harm);
                        System.out.println("User's " + (user_dead_index + 1) + " solider " + team.getSoldier(user_dead_index).getName());
                        System.out.println("Health before: " + user_blood);
                        user_blood = 0;
                        System.out.println("Health now: " + user_blood);
                        System.out.println("User Dead,User change to next soldier.");
                        user_dead_index++;
                        if (user_dead_index < team.getSize())
                            user_blood = team.getSoldier(user_dead_index).getHealth();
                        continue;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            term = (term + 1) % 2;
        }
    }

    private int getRandomHarm() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    public static void main(String[] args) {
        new Game().start(new Scanner(System.in));
    }
}
