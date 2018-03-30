package com.card.model;

import com.google.gson.Gson;

import java.io.*;
import java.util.HashMap;

public class UserHelper {

    private static final String fileName = "user.txt";

    public static void register(String username, String password) {
        HashMap<String, User> maps = getUserMap();
        if (maps.containsKey(username)) {//the account has already existed
            System.out.println("\n");
            System.out.println("username has been used already");
            return;
        }
        User user = new User(username, password);
        maps.put(user.getUsername(), user);
        System.out.println("\n");
        System.out.println("Register successfully!!");
        saveMap(maps);
        System.out.println("\n");
    }

    public static User login(String username, String password) {
        HashMap<String, User> maps = getUserMap();
        if (!maps.containsKey(username)) {//wrong(invalid) username input
            System.out.println("\n");
            System.out.println("no such user!please register first");
            return null;
        }
        User user = maps.get(username);
        if (!password.equals(user.getPassword())) {//wrong password input
            System.out.println("\n");
            System.out.println("password not match!");
            return null;
        }
        System.out.println("\n");
        System.out.println("login successfully!!");
        System.out.println("\n");
        return user;
    }

    private static HashMap<String, User> getUserMap() {//function for serialising (betwwen json and java)
        testFile();
        HashMap<String, User> maps = new HashMap<>();
        Gson gson = new Gson();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String json;
            User user;
            while ((json = br.readLine()) != null) {
                user = gson.fromJson(json, User.class);
                maps.put(user.getUsername(), user);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maps;
    }

    private static void saveMap(HashMap<String, User> maps) {
        testFile();
        try {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(writer);
            for (User user : maps.values()) {
                bw.write(user.toString() + "\n");
            }
            bw.flush();
            bw.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveUser(User user) {
        HashMap<String, User> maps = getUserMap();
        User user1 = maps.get(user.getUsername());
        maps.put(user1.getUsername(), user);
        saveMap(maps);
    }

    private static void testFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
