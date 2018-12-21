package com.main.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SearchView {
	public Stage starts(Stage stageMainAnchorPane) {
		try {
    		AnchorPane mainAnchorPane = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("search.fxml"));
    		Scene sceneMainAnchorPane = new Scene(mainAnchorPane,1150.0,600.0);
    		sceneMainAnchorPane.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
    		stageMainAnchorPane.setScene(sceneMainAnchorPane);
    		stageMainAnchorPane.setMaximized(true);
    		stageMainAnchorPane.setTitle("Панель пошуку");
    		
    
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stageMainAnchorPane;
	}
}
