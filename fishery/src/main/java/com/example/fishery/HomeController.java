package com.example.fishery;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class HomeController {
    @FXML
    private String fishtype;

    public Stage Primarystage;

    @FXML
    private TextField addText;


    @FXML
    private TextField delText;

    public void setPrimarystage(Stage stage) {
        this.Primarystage = stage;
    }
    public static String fishType;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox buttonColumnContainer;


    ArrayList<String> buttonTexts = new ArrayList<>();
//    @FXML
//    private void initialize() {
//        createButtons();
//    }
    private void createButtons() {
        int buttonsPerRow = 3;
        int buttonIndex = 0;
        buttonColumnContainer.getChildren().clear();
        while (buttonIndex < buttonTexts.size()) {
            HBox row = new HBox();
            row.setAlignment(Pos.CENTER);
            row.setSpacing(10);
            for (int i = 0; i < buttonsPerRow && buttonIndex < buttonTexts.size(); i++) {
                String buttonText = buttonTexts.get(buttonIndex);
                Button button = new Button(buttonText);
                button.setPrefWidth(208);
                button.setPrefHeight(114);
                button.setStyle("-fx-font-weight: bold;");
                System.out.println(buttonText);
                button.setOnAction(e -> handleButtonClick(buttonText));
                row.getChildren().add(button);
                buttonIndex++;
            }
            buttonColumnContainer.getChildren().add(row);
        }
        // Center-align the column container
        buttonColumnContainer.setAlignment(Pos.CENTER);

        // Set padding around the column container
        buttonColumnContainer.setPadding(new Insets(20));
        scrollPane.setContent(buttonColumnContainer);
        buttonTexts.clear();
    }

    private void handleButtonClick(String buttonText) {
        System.out.println("Button clicked: " + buttonText);
        try {
            System.out.println("inside try");
            showDetails(buttonText);
        } catch (IOException e) {
            System.err.println("Error loading detail view: " + e.getMessage());
            e.printStackTrace();  // Print the full stack trace for more information
        }
    }
    public void triggerdb(){
        buttonTexts.clear();
        MongoCollection<Document> fishCollection = MongoDBConnection.connector()
                .getDatabase("fishery")
                .getCollection("fishes");
        FindIterable<Document> fishDocuments = fishCollection.find();
        for (Document fishDoc : fishDocuments) {
            System.out.println("Fish Type: " + fishDoc.getString("fishType"));
            System.out.println("Distribution: " + fishDoc.getString("distribution"));
            buttonTexts.add(fishDoc.getString("fishType"));
            System.out.println("---------------------------");
        }
        System.out.println(buttonTexts);
        createButtons();
    }
//    @FXML
//    public void handleButtonClick(javafx.event.ActionEvent event) {
//        Button clickedButton = (Button) event.getSource();
//        System.out.println("Button clicked: " + clickedButton.getText());
//        String fishType = clickedButton.getText();
//        System.out.println("here");
//        try {
//            System.out.println("inside try");
//            showDetails(fishType);
//        } catch (IOException e) {
//            System.err.println("Error loading detail view: " + e.getMessage());
//            e.printStackTrace();  // Print the full stack trace for more information
//        }
//    }
    public void showDetails(String fT) throws IOException {
        fishType = fT;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("detail-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            DetailViewController loginController = fxmlLoader.getController();
            loginController.setPrimarystage(this.Primarystage);
            Primarystage.setTitle("View Fish Details");
            Primarystage.setScene(scene);
        } catch (IOException e) {
            System.err.println("Error loading detail view: " + e.getMessage());
            e.printStackTrace();  // Print the full stack trace for more information
            throw e;  // Rethrow the exception to indicate that the loading failed
        }
    }
    public void addNew() throws IOException {
//        String newFishType = addText.getText();
//        addText.clear();
//        buttonTexts.add(newFishType);
//        buttonTexts.clear();
//        addNewFishTypeToDatabase(newFishType);

        //Kishore Change
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AddPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        HomeController loginController = fxmlLoader.getController();
//        loginController.setPrimarystage(this.Primarystage);
        Primarystage.setTitle("Home coming!");
        Primarystage.setScene(scene);
        Primarystage.show();

    }

    public void delNew() {
        String fishToDel = delText.getText();
        delText.clear();
        buttonTexts.remove(fishToDel);
        buttonTexts.clear();
        deleteFishTypeFromDatabase(fishToDel);
    }
    private void deleteFishTypeFromDatabase(String fishTypeToDelete) {
        MongoCollection<Document> fishCollection = MongoDBConnection.connector()
                .getDatabase("fishery")
                .getCollection("fishes");
        fishCollection.deleteOne(eq("fishType", fishTypeToDelete));
    }
    private void addNewFishTypeToDatabase(String newFishType) {
        MongoCollection<Document> fishCollection = MongoDBConnection.connector()
                .getDatabase("fishery")
                .getCollection("fishes");
        Document newFishDocument = new Document("fishType", newFishType)
                .append("distribution", "Unknown")
                .append("populationStatus", "Unknown")
                .append("quantity", 0)
                .append("quality", "Unknown")
                .append("accessibility", "Unknown")
                .append("potentialUses", "Unknown");
        fishCollection.insertOne(newFishDocument);
    }

//    public void addNewButton(String newButtonText) {
//        // Create a new array with increased size
//        String[] newButtonTexts = new String[buttonTexts.size() + 1];
//
//        // Copy existing elements to the new array
//        System.arraycopy(buttonTexts, 0, newButtonTexts, 0, buttonTexts.length);
//
//        // Add the new button text to the end of the array
//        newButtonTexts[buttonTexts.length] = newButtonText;
//
//        // Update the reference to the new array
//        buttonTexts = newButtonTexts;
//    }
}
