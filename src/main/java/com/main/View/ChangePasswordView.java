package com.main.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChangePasswordView {
	public Stage starts(Stage stageMainAnchorPane) {
		try {
    		Pane mainAnchorPane = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource("changePassword.fxml"));
    		Scene sceneMainAnchorPane = new Scene(mainAnchorPane,338.0,212.0);
    		sceneMainAnchorPane.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
    		stageMainAnchorPane.setScene(sceneMainAnchorPane);
    		stageMainAnchorPane.setTitle("Змінити пароль");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stageMainAnchorPane;
	}
}
