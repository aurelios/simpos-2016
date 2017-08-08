package br.com.javamysql.dao;

import br.com.javamysql.model.City;
import br.com.javamysql.model.Person;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

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
            rs = stmt.executeQuery("SELECT p.id_person, p.name, p.id_city, c.name FROM person p, city c where p.id_city = c.id_city");
            while (rs.next()) {
                Person p = new Person();
                p.setId(rs.getLong(1));
                p.setNome(rs.getString(2));
                p.setCity(new City(rs.getLong(3),rs.getString(4)));
                personsList.add(p);
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return personsList;
    }


}
