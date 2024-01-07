package com.example.fishery;

import com.mongodb.client.MongoCollection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.bson.Document;

public class DetailViewController {
    @FXML
    private Label fishDetailsLabel;
    private Stage Primarystage;
    
    public void setPrimarystage(Stage stage) {
        this.Primarystage = stage;
    }

    @FXML
    private Label fishTypeLabel;

    @FXML
    private Label Distribution;

    @FXML
    private Label PStatus;

    @FXML
    private Label Quantity;

    @FXML
    private Label Quality;

    @FXML
    private Label Accessibility;

    @FXML
    private Label PUses;
    public void initialize(){
        setFishType();
    }
    
    public Object backbtn() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HomeController loginController = fxmlLoader.getController();
        loginController.setPrimarystage(this.Primarystage);
        Primarystage.setTitle("Home coming!");
        Primarystage.setScene(scene);
        return null;
    }

    public String fishCLicked;
    public void setFishType( ) {
        HomeController h1 = new HomeController();
        fishCLicked=h1.fishType;
        MongoCollection<Document> usersCollection = MongoDBConnection.connector()
                .getDatabase("fishery")
                .getCollection("fishes");
        Document userDoc = usersCollection.find(new Document("fishType",fishCLicked)).first();
        System.out.println(userDoc);
        assert userDoc != null;
        fishTypeLabel.setText(userDoc.getString("fishType"));
        Distribution.setText(userDoc.getString("distribution"));
        PStatus.setText(userDoc.getString("populationStatus"));
        Quantity.setText(Integer.toString(userDoc.getInteger("quantity")));
        Quality.setText(userDoc.getString("quality"));
        Accessibility.setText(userDoc.getString("accessibility"));
        PUses.setText(userDoc.getString("potentialUses"));
    }




//        System.out.println("Fish Type: " + userDoc.getString("fishType"));
//        System.out.println("Distribution: " + userDoc.getString("distribution"));
//        fishTypeLabel.setText(fishDoc.getString("fishType"));
//        System.out.println("---------------------------");
    }

