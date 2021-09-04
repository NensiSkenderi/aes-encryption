package com.aes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/encrypt_decrypt.fxml"));
		Scene scene = new Scene(root);
		stage.getIcons().add(new Image("/images/icon.png"));
		stage.setScene(scene);
		stage.setTitle("AES Algorithm");
		stage.setResizable(false);
		stage.show();
		
		
	}
}
