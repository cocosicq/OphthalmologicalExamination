package com.main.Controller;

import com.main.DataBased.ConnectDataBased;
import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class ChangePasswordController extends ConnectDataBased{

	@FXML
	private TextField newPasswordTextField;

	@FXML
	private TextField newRepeatPasswordTextField;

	@FXML
	private Button changePasswordButton;

	@FXML
	private Label nameLabel;

	@FXML
	void changePasswordButtonAction(ActionEvent event) {
		boolean correctField = true;
		if (newPasswordTextField.getText().equals("") == true || newPasswordTextField.getLength() < 6) {
			correctField = false;
		}
		if (newRepeatPasswordTextField.getText().equals("") == true || newRepeatPasswordTextField.getLength() < 6) {
			newRepeatPasswordTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (!newPasswordTextField.getText().equals(newRepeatPasswordTextField.getText())) {
			correctField = false;
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Паролі не збігаються");
			alert.setContentText("Паролі є різними! Переконайтесь чи правильно\n введені поля"
					+ "(поле виділені червоним кольором)");
			alert.showAndWait();
			newPasswordTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			newRepeatPasswordTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
		}
		if (correctField) {
			try {
				PreparedStatement statement = connectDB("Update admin_bd.RegistrationData Set password = ? "
						+ "Where id = ?;");
				statement.setString(1, newPasswordTextField.getText());
				statement.setInt(2, Integer.parseInt(LoginController.getIdWorker()));
				statement.executeUpdate();
				Alert alerts = new Alert(Alert.AlertType.INFORMATION);
				alerts.setTitle("Information");
				alerts.setHeaderText("Зміна пароля");
				alerts.setContentText("Пароль було успішно змінено!");
				alerts.showAndWait();
			}catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Помилка");
				alert.setHeaderText("Помилка з'єднання");
				alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
				alert.showAndWait();
			}
		}
	}

	@FXML
	void initialize() {
		newPasswordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newPasswordTextField.getText().equals("") == true || newPasswordTextField.getLength() < 6) {
				newPasswordTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				newPasswordTextField.setStyle("-fx-border-width: 1.2;");
			}
		});
		newRepeatPasswordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newRepeatPasswordTextField.getText().equals("") == true || newRepeatPasswordTextField.getLength() < 6) {
				newRepeatPasswordTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				newRepeatPasswordTextField.setStyle("-fx-border-width: 1.2;");
			}
		});
		nameLabel.setText("КОРИСТУВАЧ :\n" + LoginController.getSurname() + " " + LoginController.getName() + " "
				+ LoginController.getFathername());
	}
}
