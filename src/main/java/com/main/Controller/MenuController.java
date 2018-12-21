package com.main.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

import org.joda.time.DateTime;

import com.main.View.AppointmentTreatmentView;
import com.main.View.ChangePasswordView;
import com.main.View.LoginView;
import com.main.View.MenuView;
import com.main.View.NewPatientView;
import com.main.View.OphthalmicStatusView;
import com.main.View.SearchView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class MenuController {

	@FXML
	private Label nameLabel;

	@FXML
	private Label subscriptionTermLabel;

	@FXML
	private Label profesionLabel;

	@FXML
	private Label dateLabel;

	@FXML
	private Button buttonNewPatient;

	@FXML
	private Button changePasswordButton;

	@FXML
	private Button buttonSearch;

	@FXML
	private Button logOutButton;

	@FXML
	void changePasswordButtonAction(ActionEvent event) {
		// Створення форми і сцени
		ChangePasswordView changePasswordView = new ChangePasswordView();
		Stage stageMainAnchorPane = changePasswordView.starts(new Stage());
		stageMainAnchorPane.show();
	}

	@FXML
	void newPatientButtonAction(ActionEvent event) {
		// Створення форми і сцени
		NewPatientView newPatientView = new NewPatientView();
		Stage stageMainAnchorPane = newPatientView.starts(new Stage());
		stageMainAnchorPane.show();
		// Закриття вікна
		Stage stage = (Stage) buttonNewPatient.getScene().getWindow();
		stage.close();
	}

	@FXML
	void searchButtonAction(ActionEvent event) {
		// Створення форми і сцени
		SearchView searchView = new SearchView();
		Stage stageMainAnchorPane = searchView.starts(new Stage());
		stageMainAnchorPane.show();
		// Закриття вікна
		Stage stage = (Stage) buttonSearch.getScene().getWindow();
		stage.close();
	}

	@FXML
	void logOutButtonAction(ActionEvent event) {
		// Створення форми і сцени
		LoginView loginView = new LoginView();
		Stage stageMainAnchorPane = loginView.starts(new Stage());
		stageMainAnchorPane.show();
		// Закриття вікна
		Stage stage = (Stage) logOutButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void initialize() {
		subscriptionTermLabel.setText("Термін підписки : " + LoginController.getSubscriptionTerm() + " днів");
		nameLabel.setText(
				LoginController.getSurname() + " " + LoginController.getName() + " " + LoginController.getFathername());
		buttonSearch.setDisable(true);
		buttonNewPatient.setDisable(true);
		System.out.println(LoginController.getAccessTypeDB());
		
		if (LoginController.getAccessTypeDB().equals("4")) {
			buttonNewPatient.setDisable(false);
		}else {
			if (LoginController.getAccessTypeDB().contains("4")) {
				buttonNewPatient.setDisable(false);
				buttonSearch.setDisable(false);
			}else {
				buttonSearch.setDisable(false);
			}
		}

  		Date currentDate = GregorianCalendar.getInstance().getTime();
		String output = new DateTime( currentDate ).toString("yyyy-MM-dd");

		dateLabel.setText("Дата: " + output);
	}
}
