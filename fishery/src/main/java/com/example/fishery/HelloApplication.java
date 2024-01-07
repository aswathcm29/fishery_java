package com.example.fishery;
import com.mongodb.client.MongoClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
        HelloController loginController = fxmlLoader.getController();
        loginController.setPrimarystage(stage);
    }
    public static void main(String[] args) {
        launch();
    }
}

//    try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
//            Parent root = loader.load();
//
//
//            HelloController helloController = loader.getController();
//            helloController.setPrimaryStage(primaryStage);
//
//            primaryStage.setScene(new Scene(root));
//            primaryStage.setTitle("Your Application Title");
//            primaryStage.show();
//            } catch (Exception e) {
//            e.printStackTrace();
//            }