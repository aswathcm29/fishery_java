package com.example.fishery;

import com.mongodb.client.MongoCollection;
import javafx.scene.paint.Color;
import org.bson.Document;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
public class HelloController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    private Stage Primarystage; // Declare a field to store the primary stage
    public void setPrimaryStage(Stage primaryStage) {
        this.Primarystage= primaryStage;
    }
    @FXML
    protected void onHelloButtonClick() {
        try {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();
            MongoCollection<Document> usersCollection = MongoDBConnection.connector()
                    .getDatabase("fishery")
                    .getCollection("users");
            Document userDoc = usersCollection.find(new Document("username", enteredUsername)).first();

            if (userDoc != null) {
                String storedPassword = userDoc.getString("password");
                if (enteredPassword.equals(storedPassword)) {
                    System.out.println("Welcome, " + enteredUsername + "!");
                    toHome();
                } else {
                    System.out.println("Incorrect password for user: " + enteredUsername);
                }
            } else {
                System.out.println("User does not exist: " + enteredUsername);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setPrimarystage(Stage stage){
        this.Primarystage=stage;
    }

    public String toHome() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            HomeController loginController = fxmlLoader.getController();
            loginController.setPrimarystage(this.Primarystage);
            Primarystage.setTitle("Home coming!");
            Primarystage.setScene(scene);
        return null;
    }
}
