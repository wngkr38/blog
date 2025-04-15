package com.estsoft.jdbc;

import java.sql.*;

public class DeleteJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "jiuh2113";

    public static void main(String[] args) {


        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
        ) {
            int deleteRow = statement.executeUpdate("DELETE FROM students WHERE id = 1 ");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            System.out.println("delete" + deleteRow);
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id"));
                System.out.println("name: " + resultSet.getString("name"));
                System.out.println("age: " + resultSet.getInt("age"));
                System.out.println("address: " + resultSet.getString("address"));
            }

        } catch (
                SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}

