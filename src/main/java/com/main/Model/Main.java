package com.main.Model;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import com.main.DataBased.ConnectDataBased;
import com.main.View.LoginView;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

public class Main extends Application {
	private String programVersion = "1.0.0";

	public static void downloadFileFromURL(String urlString, String destination) throws Throwable {
		URL website = new URL(urlString);
		try (ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream(destination);) {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		}catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void start(Stage primaryStage) {
		LoginView loginWindowView = new LoginView();
		Stage prStage = loginWindowView.starts(primaryStage);
		prStage.show();/*
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {
			url = new URL("http://v93966.hosted-by-vdsina.ru/");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (programVersion.equals(result)) {
			LoginView loginWindowView = new LoginView();
			Stage prStage = loginWindowView.starts(primaryStage);
			prStage.show();
		} else {
			Alert alertS = new Alert(Alert.AlertType.CONFIRMATION);
			alertS.setTitle("Обновлення програми");
			alertS.setHeaderText("Вам  потрібно завантажити обновлення програми");
			alertS.setContentText("Версія програми: " + programVersion + " , актуальна версія: " + result + "");
			Optional<ButtonType> resultButton = alertS.showAndWait();
			if (resultButton.get() == ButtonType.OK) {
				System.out.println("ЗАВАНТАЖЕННЯ");
				Main m = new Main();
				try {
					m.downloadFileFromURL("http://v93966.hosted-by-vdsina.ru/Java " + result + ".exe",
							"update.exe");
				} catch (Throwable e) {
					e.getMessage();
				}

			} else if (resultButton.get() == ButtonType.CANCEL) {
				LoginView loginWindowView = new LoginView();
				Stage prStage = loginWindowView.starts(primaryStage);
				prStage.show();
			}
		}*/
	}

	public static void main(String[] args) {
		launch(args);
	}
}
