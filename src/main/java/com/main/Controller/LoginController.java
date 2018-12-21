package com.main.Controller;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;

import com.main.DataBased.ConnectDataBased;
import com.main.Model.Main;
import com.main.View.AdminView;
import com.main.View.ForgotPasswordView;
import com.main.View.LoginView;
import com.main.View.MenuView;
import com.main.View.NewPatientView;
import com.mysql.jdbc.PreparedStatement;

import javafx.scene.input.KeyCode;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class LoginController extends ConnectDataBased {
	private static String idWorker;
	private static String surname;
	private static String name;
	private static String fathername;
	private static String accessTypeDB;
	private static long subscriptionTerm;

	static public String getIdWorker() {
		return idWorker;
	}

	private void setIdWorker(String idWorker) {
		this.idWorker = idWorker;
	}

	static public String getSurname() {
		return surname;
	}

	private void setSurname(String surname) {
		this.surname = surname;
	}

	static public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	static public String getFathername() {
		return fathername;
	}

	private void setFathername(String fathername) {
		this.fathername = fathername;
	}

	static public String getAccessTypeDB() {
		return accessTypeDB;
	}

	private void setAccessTypeDB(String accessTypeDB) {
		this.accessTypeDB = accessTypeDB;
	}

	public static long getSubscriptionTerm() {
		return subscriptionTerm;
	}

	public static void setSubscriptionTerm(long subscriptionTerm) {
		LoginController.subscriptionTerm = subscriptionTerm;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button loginButton;

	@FXML
	private Button forgotPasswordButton;

	@FXML
	private TextField passTextField;

	@FXML
	private TextField loginTextField;

	@FXML
	private Label responseAuthTextField;

	@FXML
	private Label wrongLoginPassword;

	@FXML
	void forgotPasswordButtonAction(ActionEvent event) {
		// Створення форми і сцени
		ForgotPasswordView forgotPasswordView = new ForgotPasswordView();
		Stage stageMainAnchorPane = forgotPasswordView.starts(new Stage());
		stageMainAnchorPane.show();
	}

	@SuppressWarnings("restriction")
	@FXML
	

	
	void handleButtonAction(ActionEvent event) {
		try {
			PreparedStatement statement = connectDB(
					"select * FROM admin_bd.RegistrationData r inner join admin_bd.Workers w ON r.id = w.id and r.login = ? and r.password = ?;");
			statement.setString(1, loginTextField.getText());
			statement.setString(2, passTextField.getText());

			PreparedStatement statementAdmin = connectDB("select * FROM admin_bd.RegistrationData where id = '1';");
			ResultSet rs = statement.executeQuery();
			ResultSet rsAdmin = statementAdmin.executeQuery();
			rs.next();
			rsAdmin.next();
			if (rs.first()) {
				if (rs.getString(2).equals(rsAdmin.getString(2)) && rs.getString(3).equals(rsAdmin.getString(3))) {
					// Створення форми і сцени
					AdminView adminView = new AdminView();
					Stage stageMainAnchorPane = adminView.starts(new Stage());
					stageMainAnchorPane.show();
					// Закриття вікна
					Stage stage = (Stage) loginButton.getScene().getWindow();
					stage.close();
				} else {
					String[] ymd = rs.getString(12).split("-");
					int yearPidp = Integer.parseInt(ymd[0]);
					int monthPidp = Integer.parseInt(ymd[1]);
					int dayPidp = Integer.parseInt(ymd[2]);

					int dateYear = Calendar.getInstance().get(Calendar.YEAR);
					int dateMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
					int dateDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

					LocalDate last = LocalDate.of(yearPidp, monthPidp, dayPidp);
					LocalDate first = LocalDate.of(dateYear, dateMonth, dateDay);
					long resultDays = ChronoUnit.DAYS.between(first, last);

					if (resultDays > 0) {
						String[] name = rs.getString(5).split(" ");
						setIdWorker(rs.getString(1));
						setSurname(name[0]);
						setName(name[1]);
						setFathername(name[2]);
						setAccessTypeDB(rs.getString(11));
						setSubscriptionTerm(resultDays);
						// Створення форми і сцени
						MenuView menuView = new MenuView();
						Stage stageMainAnchorPane = menuView.starts(new Stage());
						stageMainAnchorPane.show();
						// Закриття вікна
						Stage stage = (Stage) loginButton.getScene().getWindow();
						stage.close();
					} else {
						wrongLoginPassword.setTextFill(Color.web("#FF0000"));
						wrongLoginPassword.setText("       Термін підписки\n           вичерпаний");
					}
				}
			} else {
				wrongLoginPassword.setTextFill(Color.web("#FF0000"));
				wrongLoginPassword.setText("Неправильно введений\n     логін або пароль");
			}

		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Помилка з'єднання");
			alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
			alert.showAndWait();
		}

	}

	@SuppressWarnings("restriction")
	@FXML
	void initialize() {
		
		
		passTextField.setOnKeyPressed((event) -> {
			if (event.getCode() == KeyCode.ENTER) {
				loginButton.fire();
			}
		});
		loginTextField.setPromptText("Login");
		passTextField.setPromptText("Password");
	}
}
