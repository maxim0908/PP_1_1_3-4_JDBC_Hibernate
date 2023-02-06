package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.getConnection();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("USER_1", "USER_1", (byte) 22);
        System.out.println("User с именем – USER_1 добавлен в базу данных");

        userService.saveUser("USER_2", "USER_2", (byte) 34);
        System.out.println("User с именем – USER_2 добавлен в базу данных");

        userService.saveUser("USER_3", "USER_3", (byte) 30);
        System.out.println("User с именем – USER_3 добавлен в базу данных");

        userService.saveUser("USER_4", "USER_4", (byte) 99);
        System.out.println("User с именем – USER_4 добавлен в базу данных");

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();

        Util.closeConnection();
    }
}
