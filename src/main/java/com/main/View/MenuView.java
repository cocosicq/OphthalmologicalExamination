package com.main.View;





import java.time.*;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuView {
	public Stage starts(Stage stageMainAnchorPane) {
		try {
    		AnchorPane mainAnchorPane = (AnchorPane)FXMLLoader.load(getClass().getClassLoader().getResource("MenuMedical.fxml"));
    		Scene sceneMainAnchorPane = new Scene(mainAnchorPane,554.0,209.0);
    		sceneMainAnchorPane.getStylesheets().add(getClass().getClassLoader().getResource("application.css").toExternalForm());
    		stageMainAnchorPane.setScene(sceneMainAnchorPane);


  
    		stageMainAnchorPane.setTitle("Головне меню");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stageMainAnchorPane;
	}
}
