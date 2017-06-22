package br.com.javamysql.dao;

import br.com.javamysql.model.Person;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public Connection getConnection(){
        try {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setServerName("localhost");
            dataSource.setDatabaseName("simpos");
            dataSource.setURL("jdbc:mysql://localhost/simpos?user=root&password=root&useTimezone=true&serverTimezone=UTC");
            return dataSource.getConnection();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return null;
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