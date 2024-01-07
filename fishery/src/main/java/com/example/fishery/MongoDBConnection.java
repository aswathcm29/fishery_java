package com.example.fishery;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConnection {
    public static MongoClient connector()
    {
        try
        {
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            System.out.println("Database connected succesfully");
            return mongoClient;

        }
        catch(Exception e)
        {
            return null;
        }
    }
}
