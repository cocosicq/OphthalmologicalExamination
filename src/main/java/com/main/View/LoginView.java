package com.main.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class LoginView {
	
	public Stage starts(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getClassLoader().getResource("loginMenu.fxml"));
			Scene scene = new Scene(root,353.0,204.0);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Авторизація");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return primaryStage;
	}
}
