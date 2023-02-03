package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String cut = "CREATE TABLE IF NOT EXISTS users" +
                "                (id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "                name VARCHAR(50)," +
                "                 lastname VARCHAR(50)," +
                "                 age TINYINT(3));";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(cut);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dut = "DROP TABLE IF EXISTS users;";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(dut);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String su = "INSERT INTO users (name, lastname, age) values (?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(su)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String rubi = "DELETE FROM users WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(rubi)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String gau = "SELECT * FROM users;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(gau);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String clut = "TRUNCATE TABLE users;";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(clut);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
