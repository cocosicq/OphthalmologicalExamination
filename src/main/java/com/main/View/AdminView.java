package com.main.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminView {
	public Stage starts(Stage stageMainAnchorPane) {
		try {
    		AnchorPane mainAnchorPane = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("admin.fxml"));
    		Scene sceneMainAnchorPane = new Scene(mainAnchorPane,628,467);
    		sceneMainAnchorPane.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
    		stageMainAnchorPane.setScene(sceneMainAnchorPane);
    		stageMainAnchorPane.setMaximized(true);
    		stageMainAnchorPane.setTitle("Панель адміністратора");
    		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stageMainAnchorPane;
	}
}
