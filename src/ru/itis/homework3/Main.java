package ru.itis.homework3;

import java.util.Optional;

public class Main {
    public static void main(String[] args)  {
        PgStorage storage = new PgStorage();
        storage.checkDriver();
        storage.createConnection();

        // ID
        Optional<User> userOptionalOne = storage.findUserById(3L);
        System.out.println(userOptionalOne.get()  + "\n");

        // Name
        Optional<User> userOptionalTwo = storage.findUserByName("Marsel");
        System.out.println(userOptionalTwo.get() + "\n");

        // ALL
        Optional<User[]> usersOptional = storage.findAll();
        for (User usr : usersOptional.get()){
            System.out.println(usr);
        }
    }
}

