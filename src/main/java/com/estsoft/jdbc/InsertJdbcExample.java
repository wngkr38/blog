package com.estsoft.jdbc;

import java.sql.*;
/*
 * db
 * sql ->insert into
 * 결과 출력
 */

public class InsertJdbcExample {
    private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String username = "root";
    private static final String password = "jiuh2113";

    public static void main(String[] args) {
        try (
                Connection conn = DriverManager.getConnection(url, username, password);
                Statement statement = conn.createStatement();
        ) {
            int insertRow = statement.executeUpdate("INSERT INTO students (name,age,address) values('name',21,'add')");
            System.out.println("insert" + insertRow);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id"));
                System.out.println("name: " + resultSet.getString("name"));
                System.out.println("age: " + resultSet.getInt("age"));
                System.out.println("address: " + resultSet.getString("address"));
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
