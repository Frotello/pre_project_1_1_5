package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService testUser = new UserServiceImpl();

//        testUser.createUsersTable();

        testUser.saveUser("Name1", "LastName1", (byte) 20);
//        testUser.saveUser("Name2", "LastName2", (byte) 25);
//        testUser.saveUser("Name3", "LastName3", (byte) 31);
//        testUser.saveUser("Name4", "LastName4", (byte) 38);

//        testUser.removeUserById(1);
//        testUser.getAllUsers();
//        testUser.cleanUsersTable();
//        testUser.dropUsersTable();
    }
}
