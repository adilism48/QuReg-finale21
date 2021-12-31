package com.qureg;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnector {
    private static final DBConnector dc = new DBConnector();
    public static DBConnector getDC() {
        return dc;
    }

    private static final String DBUrl = "jdbc:postgresql://localhost:5432/qureg";
    private static final String DBUser = "postgres";
    private static final String DBPassword = "123456";

    public static void writeToDB(Integer card_id, String firstname, String surname, String info) {
        String query = "INSERT INTO people(card_id, firstname, surname, info) VALUES(?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, card_id);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, info);
            preparedStatement.executeUpdate();
            System.out.println("Successfully wrote");
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(DBConnector.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public static void delFromDB(Integer id) {
        String query = "DELETE FROM people WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Deleted");
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(DBConnector.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public List<Person> getPeopleList() {
        String query = "SELECT * FROM people";

        List<Person> personList = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(DBUrl, DBUser, DBPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer card_id = resultSet.getInt("card_id");
                String firstname = resultSet.getString("firstname");
                String surname = resultSet.getString("surname");
                String info = resultSet.getString("info");

                Person person = new Person(id, card_id, firstname, surname, info);
                personList.add(person);
            }
            System.out.println("Successfully updated");
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(DBConnector.class.getName());
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        return personList;
    }
}
