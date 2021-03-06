package br.com.javamysql.dao;

import br.com.javamysql.model.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {



    public List<Person> readAllUsers() {
        Statement stmt = null;
        ResultSet rs = null;
        List<Person> personsList = new ArrayList<Person>();
        try {
            Connection con = DataSource.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT p.id_person, p.name, p.id_city FROM person p");
            Person p;
            while (rs.next()) {
                p = new Person();
                p.setId(rs.getLong(1));
                p.setNome(rs.getString(2));
                p.setCity(rs.getLong(3));
                personsList.add(p);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return personsList;
    }


}
