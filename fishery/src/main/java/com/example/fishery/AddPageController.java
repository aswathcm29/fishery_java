package com.example.fishery;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.bson.Document;

public class AddPageController {

    @FXML
    private TextField Name;

    @FXML
    private TextField Population;

    @FXML
    private TextField Quantity;

    @FXML
    private TextField Region;

    @FXML
    private TextField Use;

    @FXML
    private TextField accssibility;

    @FXML
    private Label fishTypeLabel;

    @FXML
    void addNew(ActionEvent event) {
        MongoClient mongoClient = MongoClients.create("mongodb://hostOne:27017,hostTwo:27018");
        MongoDatabase db = mongoClient.getDatabase("fishes");

    }

    @FXML
    void backbtn(ActionEvent event) {

    }

}
