package com.main.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ForgotPasswordView {
	public Stage starts(Stage stageMainAnchorPane) {
		try {
    		Pane mainAnchorPane = (Pane)FXMLLoader.load(getClass().getClassLoader().getResource("forgotPassword.fxml"));
    		Scene sceneMainAnchorPane = new Scene(mainAnchorPane,271.0,146.0);
    		sceneMainAnchorPane.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
    		stageMainAnchorPane.setScene(sceneMainAnchorPane);
    		stageMainAnchorPane.setTitle("Забули пароль");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stageMainAnchorPane;
	}
}
