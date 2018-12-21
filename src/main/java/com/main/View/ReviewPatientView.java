package com.main.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ReviewPatientView {
	public Stage starts(Stage stageMainAnchorPane) {
		try {
    		AnchorPane mainAnchorPane = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("reviewPatient.fxml"));
    		Scene sceneMainAnchorPane = new Scene(mainAnchorPane,800.0,650.0);
    		sceneMainAnchorPane.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
    		stageMainAnchorPane.setScene(sceneMainAnchorPane);
    		stageMainAnchorPane.setTitle("Дані пацієнта");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stageMainAnchorPane;
	}
}
