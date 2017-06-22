package br.com.javapostgres.dao;

import br.com.javapostgres.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    public static void main(String[] argv) {

        System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;

        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
    }


    public Connection getConnection(){
            Connection connection = null;
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://127.0.0.1:5432/simpos", "postgres", "admin");
            } catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        if (connection != null) {
                System.out.println("You made it, take control your database now!");
            } else {
                System.out.println("Failed to make connection!");
            }
        return connection;
    }

    public List<Person> readAllUsers() {
        Statement stmt = null;
        ResultSet rs = null;
        List<Person> personsList = new ArrayList<Person>();
        try {
            stmt = this.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT * FROM person");
            while (rs.next()) {
                Person p = new Person();
                p.setId(rs.getLong(1));
                p.setNome(rs.getString(2));
                p.setCidade(rs.getInt(3));
                personsList.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return personsList;
    }


}
