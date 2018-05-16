package br.com.javamongodb.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class PersonDAO {
    
    private DBCollection col;
    MongoClient mongo;

    public PersonDAO(MongoClient mongo) {
        this.mongo = mongo;
        this.col = mongo.getDB("admin").getCollection("person");

    }
   
    public List<String> readAllUsers() {
        List<String> data = new ArrayList<String>();
        
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            data.add(doc.toString());
        }
        cursor.close();
        mongo.close();
        return data;
    }
}
