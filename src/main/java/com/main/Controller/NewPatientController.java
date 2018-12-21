package com.main.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import com.main.DataBased.ConnectDataBased;
import com.main.Model.Main;
import com.main.View.MenuView;
import com.mysql.jdbc.PreparedStatement;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class NewPatientController extends ConnectDataBased {

	@FXML
	private ComboBox doctorComboBox;

	@FXML
	private CheckBox womanCheckBox;

	@FXML
	private CheckBox manCheckBox;

	@FXML
	private TextField idPatientFiedText;

	@FXML
	private TextField lastNamePatientTextField;

	@FXML
	private TextField firstNamePatientTextField;

	@FXML
	private TextField fathernamePatientTextField;

	@FXML
	private DatePicker dobPatientDatePicker;

	@FXML
	private TextField numberPhonePatientTextField;

	@FXML
	private TextField emailPatientTextField;

	@FXML
	private TextField countryPatientTextField;

	@FXML
	private TextField oblastPatientTextField;

	@FXML
	private TextField regionPatientTextField;

	@FXML
	private TextField cityPatientTextField;

	@FXML
	private TextField addressPatientTextField;

	@FXML
	private TextField housePatientTextField;

	@FXML
	private TextField flatPatientTextField;

	@FXML
	private TextArea dopInfoPatientTextArea;

	@FXML
	private Button idAddNewPatientButton;

	@FXML
	private Button exitNewPatientButton;

	@FXML
	private Button updatePatientButton;

	private static String idWorker = null;

	@FXML
	void updatePatientButtonAction(ActionEvent event) {
		boolean correctField = true;
		if (idPatientFiedText.getText().equals("")) {
			idPatientFiedText.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}

		if (firstNamePatientTextField.getText().equals("")) {
			firstNamePatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (lastNamePatientTextField.getText().equals("")) {
			lastNamePatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (fathernamePatientTextField.getText().equals("")) {
			fathernamePatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (dobPatientDatePicker.getValue() == null) {
			dobPatientDatePicker.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (numberPhonePatientTextField.getText().equals("")) {
			numberPhonePatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (NewPatientController.idWorker == null) {
			doctorComboBox.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}

		if (correctField) {
			try {
				PreparedStatement statementD = connectDB("DELETE FROM admin_bd.Patient WHERE id = ?;");
				statementD.setInt(1, Integer.parseInt(idPatientFiedText.getText()));
				statementD.executeUpdate();
				statementD.close();

				PreparedStatement statement = connectDB(
						"insert into admin_bd.Patient (id, name ,dateOfBirth,numberTelephone,eMail,"
								+ " country, oblast, region, settlement, street, house, apartment, additional_information, Workers_id, sex) "
								+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				statement.setInt(1, Integer.parseInt(idPatientFiedText.getText()));
				statement.setString(2, lastNamePatientTextField.getText() + " " + firstNamePatientTextField.getText()
						+ " " + fathernamePatientTextField.getText());
				statement.setDate(3, java.sql.Date.valueOf(dobPatientDatePicker.getValue().toString()));
				statement.setString(4, numberPhonePatientTextField.getText());
				statement.setString(5, emailPatientTextField.getText());
				statement.setString(6, countryPatientTextField.getText());
				statement.setString(7, oblastPatientTextField.getText());
				statement.setString(8, regionPatientTextField.getText());
				statement.setString(9, cityPatientTextField.getText());
				statement.setString(10, addressPatientTextField.getText());
				statement.setString(11, housePatientTextField.getText());
				statement.setString(12, flatPatientTextField.getText());
				statement.setString(13, dopInfoPatientTextArea.getText());
				statement.setInt(14, Integer.parseInt(NewPatientController.idWorker));
				if (womanCheckBox.isSelected() == true) {
					statement.setInt(15, 1);
				} else if (manCheckBox.isSelected() == true) {
					statement.setInt(15, 2);
				} else {
					statement.setInt(15, 0);
				}

				statement.executeUpdate();
				Alert alertS = new Alert(Alert.AlertType.INFORMATION);
				alertS.setTitle("Оновлення");
				alertS.setHeaderText("Оновлення даних пацієнта");
				alertS.setContentText("Успішно оновлено дані пацієнта!");
				alertS.showAndWait();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Не були введені необхідні дані про пацієнта");
			alert.setContentText(
					"Перегляньте чи всі необхідні дані були введені \nдля додавання пацієнта у бази даних\n"
							+ "(поля виділені червоним кольором)");
			alert.showAndWait();
		}
	}

	@FXML
	void addNewPatientButtonAction(ActionEvent event) {
		boolean correctField = true;
		if (idPatientFiedText.getText().equals("")) {
			idPatientFiedText.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}

		if (firstNamePatientTextField.getText().equals("")) {
			firstNamePatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (lastNamePatientTextField.getText().equals("")) {
			lastNamePatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (fathernamePatientTextField.getText().equals("")) {
			fathernamePatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (dobPatientDatePicker.getValue() == null) {
			dobPatientDatePicker.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (numberPhonePatientTextField.getText().equals("")) {
			numberPhonePatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (NewPatientController.idWorker == null) {
			doctorComboBox.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}

		if (correctField) {
			try {
				PreparedStatement statement = connectDB(
						"insert into admin_bd.Patient (id, name ,dateOfBirth,numberTelephone,eMail,"
								+ " country, oblast, region, settlement, street, house, apartment, additional_information, Workers_id, sex) "
								+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

				statement.setInt(1, Integer.parseInt(idPatientFiedText.getText()));
				statement.setString(2, lastNamePatientTextField.getText() + " " + firstNamePatientTextField.getText()
						+ " " + fathernamePatientTextField.getText());
				statement.setDate(3, java.sql.Date.valueOf(dobPatientDatePicker.getValue().toString()));
				statement.setString(4, numberPhonePatientTextField.getText());
				statement.setString(5, emailPatientTextField.getText());
				statement.setString(6, countryPatientTextField.getText());
				statement.setString(7, oblastPatientTextField.getText());
				statement.setString(8, regionPatientTextField.getText());
				statement.setString(9, cityPatientTextField.getText());
				statement.setString(10, addressPatientTextField.getText());
				statement.setString(11, housePatientTextField.getText());
				statement.setString(12, flatPatientTextField.getText());
				statement.setString(13, dopInfoPatientTextArea.getText());
				statement.setInt(14, Integer.parseInt(NewPatientController.idWorker));
				if (womanCheckBox.isSelected() == true) {
					statement.setInt(15, 1);
				} else if (manCheckBox.isSelected() == true) {
					statement.setInt(15, 2);
				} else {
					statement.setInt(15, 0);
				}

				statement.executeUpdate();
				Alert alertS = new Alert(Alert.AlertType.INFORMATION);
				alertS.setTitle("Додавання");
				alertS.setHeaderText("Додавання пацієнта");
				alertS.setContentText("Успішно додано пацієнта!");
				alertS.showAndWait();
				idAddNewPatientButton.setDisable(true);
				idPatientFiedText.setText(String.valueOf(Integer.parseInt(idPatientFiedText.getText()) + 1));
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Помилка");
				alert.setHeaderText("Помилка з'єднання");
				alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Не були введені необхідні дані про пацієнта");
			alert.setContentText(
					"Перегляньте чи всі необхідні дані були введені \nдля додавання пацієнта у бази даних\n"
							+ "(поля виділені червоним кольором)");
			alert.showAndWait();
		}
	}

	@FXML
	void exitNewPatientButtonAction(ActionEvent event) {
		// Створення форми і сцени
		MenuView menuView = new MenuView();
		Stage stageMainAnchorPane = menuView.starts(new Stage());
		stageMainAnchorPane.show();
		// Закриття вікна
		Stage stage = (Stage) exitNewPatientButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void initialize() {
		try {
			PreparedStatement statement = connectDB("SELECT MAX(id+1) FROM admin_bd.Patient;");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					idPatientFiedText.setText("1");
				} else {
					idPatientFiedText.setText(rs.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement statement = connectDB(
					"SELECT name, id FROM admin_bd.Workers Where concat(accessDatabaseType) LIKE '%2%';");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String s = rs.getString(1) + "," + rs.getString(2);
				doctorComboBox.getItems().add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		womanCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (womanCheckBox.isSelected() == true) {
				manCheckBox.setSelected(false);
			}
		});
		manCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (manCheckBox.isSelected() == true) {
				womanCheckBox.setSelected(false);
			}
		});

		emailPatientTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				if (emailPatientTextField.getText()
						.matches("^\\w+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}$") == false) {
					emailPatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
				} else {
					emailPatientTextField.setStyle("-fx-border-width: 1.2;");
				}
			} catch (Exception e) {
			}
		});

		numberPhonePatientTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.matches("[+]\\d{12}\\b") == false) {
				numberPhonePatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				numberPhonePatientTextField.setStyle("-fx-border-width: 1.2;");
			}
		});
		updatePatientButton.setDisable(true);
		idPatientFiedText.textProperty().addListener((observable, oldValue, newValue) -> {
			if (idPatientFiedText.getText().matches("\\b\\d+\\b") == false) {
				idPatientFiedText.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				idPatientFiedText.setStyle("-fx-border-width: 1.2");
				try {
					PreparedStatement statement = connectDB(
							"SELECT p.* , w.name FROM admin_bd.Patient p inner join admin_bd.Workers w "
									+ "On p.Workers_id = w.id where p.id = ?;");
					statement.setInt(1, Integer.parseInt(idPatientFiedText.getText()));
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						idAddNewPatientButton.setDisable(true);
						updatePatientButton.setDisable(false);
						String[] name = rs.getString(2).split(" ");
						firstNamePatientTextField.setText(name[1]);
						lastNamePatientTextField.setText(name[0]);
						fathernamePatientTextField.setText(name[2]);
						numberPhonePatientTextField.setText(rs.getString(4));
						numberPhonePatientTextField.setStyle("-fx-border-width: 1.2");
						emailPatientTextField.setText(rs.getString(5));
						emailPatientTextField.setStyle("-fx-border-width: 1.2");
						countryPatientTextField.setText(rs.getString(6));
						regionPatientTextField.setText(rs.getString(8));
						oblastPatientTextField.setText(rs.getString(7));
						cityPatientTextField.setText(rs.getString(9));
						addressPatientTextField.setText(rs.getString(10));
						housePatientTextField.setText(rs.getString(11));
						flatPatientTextField.setText(rs.getString(12));
						dopInfoPatientTextArea.setText(rs.getString(13));
						dobPatientDatePicker.setValue(LocalDate.parse(rs.getString(3)));
						if (rs.getString(15).equals("1")) {
							womanCheckBox.setSelected(true);
						} else if (rs.getString(15).equals("2")) {
							manCheckBox.setSelected(true);
						} else {
							manCheckBox.setSelected(false);
							womanCheckBox.setSelected(false);
						}
						doctorComboBox.getSelectionModel().select(rs.getString(16) + "," + rs.getString(14));
					}
					if (rs.first() == false) {
						idAddNewPatientButton.setDisable(false);
						updatePatientButton.setDisable(true);
						firstNamePatientTextField.setText("");
						firstNamePatientTextField.setStyle("-fx-border-width: 1.2");
						lastNamePatientTextField.setText("");
						lastNamePatientTextField.setStyle("-fx-border-width: 1.2");
						fathernamePatientTextField.setText("");
						fathernamePatientTextField.setStyle("-fx-border-width: 1.2");
						numberPhonePatientTextField.setText("+380");
						numberPhonePatientTextField.setStyle("-fx-border-width: 1.2");
						emailPatientTextField.setText("");
						emailPatientTextField.setStyle("-fx-border-width: 1.2");
						countryPatientTextField.setText("");
						regionPatientTextField.setText("");
						oblastPatientTextField.setText("");
						cityPatientTextField.setText("");
						addressPatientTextField.setText("");
						housePatientTextField.setText("");
						flatPatientTextField.setText("");
						dopInfoPatientTextArea.setText("");
						dobPatientDatePicker.setValue(null);
						dobPatientDatePicker.setStyle("-fx-border-width: 1.2");
						manCheckBox.setSelected(false);
						womanCheckBox.setSelected(false);
						doctorComboBox.getItems().clear();
						doctorComboBox.setStyle("-fx-border-width: 1.2");
					}
					statement.close();
				} catch (Exception e) {
					e.getMessage();
				}
			}
		});

		firstNamePatientTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (firstNamePatientTextField.getText().equals("") == true) {
				firstNamePatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				firstNamePatientTextField.setStyle("-fx-border-width: 1.2;");
			}
		});

		lastNamePatientTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (lastNamePatientTextField.getText().equals("") == true) {
				lastNamePatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				lastNamePatientTextField.setStyle("-fx-border-width: 1.2;");
			}
		});

		fathernamePatientTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (fathernamePatientTextField.getText().equals("") == true) {
				fathernamePatientTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				fathernamePatientTextField.setStyle("-fx-border-width: 1.2;");
			}
		});

		dobPatientDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
			if (dobPatientDatePicker.getValue() == null) {
				dobPatientDatePicker.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				dobPatientDatePicker.setStyle("-fx-border-width: 1.2;");
			}
		});

		doctorComboBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				String[] s = t1.split(",");
				System.out.println(s[1]);
				NewPatientController.idWorker = s[1];
			}
		});

		doctorComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (NewPatientController.idWorker == null) {
				doctorComboBox.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				doctorComboBox.setStyle("-fx-border-width: 1.2;");
			}
		});

		dobPatientDatePicker.setPromptText("DD.MM.YYYY");
		dopInfoPatientTextArea.setPromptText("Додаткова інформація про пацієнта");
		emailPatientTextField.setPromptText("example@domain.com");
	}
}