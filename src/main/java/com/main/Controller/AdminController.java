package com.main.Controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import com.main.DataBased.ConnectDataBased;
import com.main.View.LoginView;
import com.mysql.jdbc.PreparedStatement;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminController extends ConnectDataBased {

	private static String accessType = "";

	@FXML
	private TextField idWorkerFiedText;

	@FXML
	private TextField lastNameWorkerTextField;

	@FXML
	private TextField firstNameWorkerTextField;

	@FXML
	private TextField fatherNameWorkerTextField;

	@FXML
	private DatePicker dobWorkerDatePicker;

	@FXML
	private TextField hospitalTextField;

	@FXML
	private TextField positionTextField;

	@FXML
	private TextField telephoneTextField;

	@FXML
	private TextField eMailTextField;

	@FXML
	private TextField loginTextField;

	@FXML
	private DatePicker subscriptionToAccountDatePicker;

	@FXML
	private Button saveDataWorkerButton;

	@FXML
	private Button deleteDataWorkerButton;

	@FXML
	private Button exitWorkerButton;

	@FXML
	private Button updateDataWorkerButton;

	@FXML
	private CheckBox checkBoxOnlyRead;

	@FXML
	private CheckBox checkBoxDoctor;

	@FXML
	private CheckBox checkBoxAddNewPatient;

	@FXML
	private CheckBox checkBoxSuperUser;

	@FXML
	private CheckBox checkBoxSpecialist;

	@FXML
	void exitWorkerButtonAction(ActionEvent event) {
		// Створення форми і сцени
		LoginView loginView = new LoginView();
		Stage stageMainAnchorPane = loginView.starts(new Stage());
		stageMainAnchorPane.show();
		// Закриття вікна
		Stage stage = (Stage) exitWorkerButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void saveDataWorkerButtonAction(ActionEvent event) {
		boolean correctField = true;
		if (idWorkerFiedText.getText().equals("")) {
			idWorkerFiedText.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}

		if (checkBoxAddNewPatient.isSelected() == false && checkBoxOnlyRead.isSelected() == false
				&& checkBoxDoctor.isSelected() == false && checkBoxSpecialist.isSelected() == false
				&& checkBoxSuperUser.isSelected() == false) {
			checkBoxAddNewPatient.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			checkBoxOnlyRead.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			checkBoxDoctor.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			checkBoxSpecialist.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			checkBoxSuperUser.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}

		if (firstNameWorkerTextField.getText().equals("")) {
			firstNameWorkerTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (lastNameWorkerTextField.getText().equals("")) {
			lastNameWorkerTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (fatherNameWorkerTextField.getText().equals("")) {
			fatherNameWorkerTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (dobWorkerDatePicker.getValue() == null) {
			dobWorkerDatePicker.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (telephoneTextField.getText().equals("")) {
			telephoneTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}

		if (hospitalTextField.getText().equals("")) {
			hospitalTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (positionTextField.getText().equals("")) {
			positionTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (eMailTextField.getText().equals("")) {
			eMailTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (loginTextField.getText().equals("")) {
			loginTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}

		if (subscriptionToAccountDatePicker.getValue() == null) {
			subscriptionToAccountDatePicker.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (checkBoxOnlyRead.isSelected() == true) {
			accessType += "1";
		} else {
			checkBoxOnlyRead.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
		}

		if (checkBoxDoctor.isSelected() == true) {
			accessType += "2";
		} else {
			checkBoxDoctor.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
		}

		if (checkBoxSpecialist.isSelected() == true) {
			accessType += "3";
		} else {
			checkBoxSpecialist.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
		}

		if (checkBoxAddNewPatient.isSelected() == true) {
			accessType += "4";
		} else {
			checkBoxAddNewPatient.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
		}

		if (checkBoxSuperUser.isSelected() == true) {
			accessType += "5";
		} else {
			checkBoxSuperUser.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
		}
		if (correctField) {
			int leftLimit = 97; // letter 'a'
			int rightLimit = 122; // letter 'z'
			int targetStringLength = 10;
			Random random = new Random();
			StringBuilder buffer = new StringBuilder(targetStringLength);
			for (int i = 0; i < targetStringLength; i++) {
				int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
				buffer.append((char) randomLimitedInt);
			}
			String generatedPassword = buffer.toString();
			try {
				PreparedStatement statementL = connectDB(
						"insert into admin_bd.RegistrationData (id, login, password) " + "Values (?,?,?);");
				statementL.setInt(1, Integer.parseInt(idWorkerFiedText.getText()));
				statementL.setString(2, loginTextField.getText());
				statementL.setString(3, generatedPassword);
				statementL.executeUpdate();
				statementL.close();

				Sender tlsSender = new Sender("medicalifua@gmail.com", "medicalifuamedicalifua");
				tlsSender.send("Додавання облікового запису до медичної програми", "\tВітаємо !\nКористувач : "
						+ loginTextField.getText() + " , " + lastNameWorkerTextField.getText() + " "
						+ firstNameWorkerTextField.getText() + " " + fatherNameWorkerTextField.getText()
						+ ", Вас додано в базу даних працівників медичної програми.\nДля подальшої роботи Вам було надіслано згенерований пароль, який ви бачите знизу\n"
						+ "для авторизації у програмі : \nЛогін : " + loginTextField.getText() + "\nПароль : "
						+ generatedPassword + "\n\nВ "
						+ "межах безпеки Ваших даних та даних Ваших пацієнтів радимо змініть пароль у медичній програмі на зручніший, який Ви будете пам'ятати.\nДякуємо!\nАдміністрація медичної програми.",
						"medicalifua@gmail.com", eMailTextField.getText());

				PreparedStatement statement = connectDB("insert into admin_bd.Workers (id,name ,dateOfBirth,"
						+ "institution,position,telephone ,eMail,accessDatabaseType,accountStatus) "
						+ "Values (?,?,?,?,?,?,?,?,?);");
				statement.setInt(1, Integer.parseInt(idWorkerFiedText.getText()));
				statement.setString(2, lastNameWorkerTextField.getText() + " " + firstNameWorkerTextField.getText()
						+ " " + fatherNameWorkerTextField.getText());
				statement.setDate(3, java.sql.Date.valueOf(dobWorkerDatePicker.getValue().toString()));
				statement.setString(4, hospitalTextField.getText());
				statement.setString(5, positionTextField.getText());
				statement.setString(6, telephoneTextField.getText());
				statement.setString(7, eMailTextField.getText());
				statement.setInt(8, Integer.parseInt(accessType));
				statement.setDate(9, Date.valueOf(subscriptionToAccountDatePicker.getValue()));

				statement.executeUpdate();
				statement.close();
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Додано");
				alert.setContentText("Додані дані про працівника у базу даних");
				alert.showAndWait();
				idWorkerFiedText.setText(String.valueOf(Integer.parseInt(idWorkerFiedText.getText()) + 1));
			} catch (Exception e) {
				System.out.println(e.getMessage());
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Помилка");
				alert.setHeaderText("Помилка при роботі з базою даних");
				alert.setContentText("Помилка при роботі з базою даних стався збій\n" + e.getMessage());
				alert.showAndWait();
				accessType = "";
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Не були введені необхідні дані про працівника");
			alert.setContentText(
					"Перегляньте чи всі необхідні дані були введені \nдля додавання працівника у бази даних\n"
							+ "(поля виділені червоним кольором)");
			alert.showAndWait();
		}
	}

	@FXML
	void updateDataWorkerButtonAction(ActionEvent event) {

		boolean correctField = true;
		if (idWorkerFiedText.getText().equals("")) {
			idWorkerFiedText.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}

		if (checkBoxAddNewPatient.isSelected() == false && checkBoxOnlyRead.isSelected() == false
				&& checkBoxDoctor.isSelected() == false && checkBoxSpecialist.isSelected() == false
				&& checkBoxSuperUser.isSelected() == false) {
			checkBoxAddNewPatient.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			checkBoxOnlyRead.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			checkBoxDoctor.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			checkBoxSpecialist.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			checkBoxSuperUser.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}

		if (firstNameWorkerTextField.getText().equals("")) {
			firstNameWorkerTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (lastNameWorkerTextField.getText().equals("")) {
			lastNameWorkerTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (fatherNameWorkerTextField.getText().equals("")) {
			fatherNameWorkerTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (dobWorkerDatePicker.getValue() == null) {
			dobWorkerDatePicker.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (telephoneTextField.getText().equals("")) {
			telephoneTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}

		if (hospitalTextField.getText().equals("")) {
			hospitalTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (positionTextField.getText().equals("")) {
			positionTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (eMailTextField.getText().equals("")) {
			eMailTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (loginTextField.getText().equals("")) {
			loginTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (subscriptionToAccountDatePicker.getValue() == null) {
			subscriptionToAccountDatePicker.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			correctField = false;
		}
		if (checkBoxOnlyRead.isSelected() == true) {
			accessType += "1";
		}
		if (checkBoxDoctor.isSelected() == true) {
			accessType += "2";
		}

		if (checkBoxSpecialist.isSelected() == true) {
			accessType += "3";
		}

		if (checkBoxAddNewPatient.isSelected() == true) {
			accessType += "4";
		}

		if (checkBoxSuperUser.isSelected() == true) {
			accessType += "5";
		}
		if(accessType.equals("")) {
			correctField = false;
			checkBoxOnlyRead.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			checkBoxDoctor.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			checkBoxSpecialist.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			checkBoxAddNewPatient.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			checkBoxSuperUser.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
		}
		if (correctField) {
			try {
				PreparedStatement statementD = connectDB("DELETE FROM admin_bd.Workers WHERE id = ?;");
				statementD.setInt(1, Integer.parseInt(idWorkerFiedText.getText()));
				statementD.executeUpdate();

				PreparedStatement statementL = connectDB("UPDATE admin_bd.RegistrationData \n" + 
						"SET login = ? WHERE id = ?");
				statementL.setInt(2, Integer.parseInt(idWorkerFiedText.getText()));
				statementL.setString(1, loginTextField.getText());
				statementL.executeUpdate();
				statementL.close();

				PreparedStatement statement = connectDB("insert into admin_bd.Workers (id,name,dateOfBirth,"
						+ "institution,position,telephone ,eMail,accessDatabaseType,accountStatus) "
						+ "Values (?,?,?,?,?,?,?,?,?);");
				statement.setInt(1, Integer.parseInt(idWorkerFiedText.getText()));
				statement.setString(2, lastNameWorkerTextField.getText() + " " + firstNameWorkerTextField.getText()
						+ " " + fatherNameWorkerTextField.getText());
				statement.setDate(3, java.sql.Date.valueOf(dobWorkerDatePicker.getValue().toString()));
				statement.setString(4, hospitalTextField.getText());
				statement.setString(5, positionTextField.getText());
				statement.setString(6, telephoneTextField.getText());
				statement.setString(7, eMailTextField.getText());
				statement.setInt(8, Integer.parseInt(accessType));
				statement.setDate(9, Date.valueOf(subscriptionToAccountDatePicker.getValue()));

				statement.executeUpdate();
				statement.close();
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Оновлено!");
				alert.setContentText("Оновлено дані про працівника у базі даних");
				alert.showAndWait();
				accessType = "";
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Помилка");
				alert.setHeaderText("Помилка при роботі з базою даних");
				alert.setContentText("Помилка при роботі з базою даних! \n" + e.getMessage());
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Не були введені необхідні дані про працівника");
			alert.setContentText(
					"Перегляньте чи всі необхідні дані були введені \nдля додавання працівника у бази даних\n"
							+ "(поля виділені червоним кольором)");
			alert.showAndWait();
		}
	}
/*
	@FXML
	void deleteDataWorkerButtonAction(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Видалення");
		alert.setHeaderText("Видалення працівника");
		alert.setContentText("Ви впевнені, що хочете видалити працівника ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				PreparedStatement statement = connectDB("DELETE FROM admin_bd.Workers WHERE id = ?;");
				statement.setInt(1, Integer.parseInt(idWorkerFiedText.getText()));
				statement.executeUpdate();
				loginTextField.setText("");
				loginTextField.setStyle("-fx-border-width: 1.2");
				statement.close();

				PreparedStatement statementL = connectDB("DELETE FROM admin_bd.RegistrationData WHERE id = ?;");
				statementL.setInt(1, Integer.parseInt(idWorkerFiedText.getText()));
				statementL.executeUpdate();
				firstNameWorkerTextField.setText("");
				firstNameWorkerTextField.setStyle("-fx-border-width: 1.2");
				lastNameWorkerTextField.setText("");
				lastNameWorkerTextField.setStyle("-fx-border-width: 1.2");
				fatherNameWorkerTextField.setText("");
				fatherNameWorkerTextField.setStyle("-fx-border-width: 1.2");
				dobWorkerDatePicker.setValue(null);
				dobWorkerDatePicker.setStyle("-fx-border-width: 1.2");
				hospitalTextField.setText("");
				hospitalTextField.setStyle("-fx-border-width: 1.2");
				positionTextField.setText("");
				positionTextField.setStyle("-fx-border-width: 1.2");
				subscriptionToAccountDatePicker.getEditor().clear();
				subscriptionToAccountDatePicker.setStyle("-fx-border-width: 1.2");
				eMailTextField.setText("");
				eMailTextField.setStyle("-fx-border-width: 1.2");
				telephoneTextField.setText("");
				telephoneTextField.setStyle("-fx-border-width: 1.2");
				checkBoxAddNewPatient.setSelected(false);
				checkBoxAddNewPatient.setStyle("-fx-border-width: 1.2");
				checkBoxDoctor.setSelected(false);
				checkBoxDoctor.setStyle("-fx-border-width: 1.2");
				checkBoxOnlyRead.setSelected(false);
				checkBoxOnlyRead.setStyle("-fx-border-width: 1.2");
				checkBoxSpecialist.setSelected(false);
				checkBoxSpecialist.setStyle("-fx-border-width: 1.2");
				checkBoxSuperUser.setSelected(false);
				checkBoxSuperUser.setStyle("-fx-border-width: 1.2");
				statementL.close();
				Alert alertS = new Alert(Alert.AlertType.INFORMATION);
				alertS.setTitle("Видалення");
				alertS.setHeaderText("Видалення працівника");
				alertS.setContentText("Успішно видалено працівника!");
				alertS.showAndWait();
			} catch (Exception e) {
				Alert alertS = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Помилка");
				alert.setHeaderText("Помилка з'єднання");
				alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
				alert.showAndWait();
			}
		} else {
			alert.close();
		}

	}
*/
	@FXML
	void initialize() {
		try {
			PreparedStatement statement = connectDB("SELECT MAX(id+1) FROM admin_bd.Workers;");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					idWorkerFiedText.setText("2");
				} else {
					idWorkerFiedText.setText(rs.getString(1));
				}
			}
			statement.close();
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Помилка з'єднання");
			alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
			alert.showAndWait();
		}

		idWorkerFiedText.textProperty().addListener((observable, oldValue, newValue) -> {
			if (idWorkerFiedText.getText().matches("\\d+") == false) {
				idWorkerFiedText.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				idWorkerFiedText.setStyle("-fx-border-width: 1.2;");
				try {
					PreparedStatement statement = connectDB(
							"SELECT * FROM admin_bd.Workers w, admin_bd.RegistrationData r Where w.id = r.id and w.id = ?;");
					statement.setInt(1, Integer.parseInt(idWorkerFiedText.getText()));
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						String[] name = rs.getString(2).split(" ");
						lastNameWorkerTextField.setText(name[0]);
						firstNameWorkerTextField.setText(name[1]);
						fatherNameWorkerTextField.setText(name[2]);
						dobWorkerDatePicker.setValue(LocalDate.parse(rs.getString(3)));
						hospitalTextField.setText(rs.getString(4));
						positionTextField.setText(rs.getString(5));
						telephoneTextField.setText(rs.getString(6));
						eMailTextField.setText(rs.getString(7));
						subscriptionToAccountDatePicker.setValue(LocalDate.parse(rs.getString(9)));
						loginTextField.setText(rs.getString(11));
						if(rs.getString(8).contains("1")) {
							checkBoxOnlyRead.setSelected(true);
						}else {
							checkBoxOnlyRead.setSelected(false);
						}
						if(rs.getString(8).contains("2")) {
							checkBoxDoctor.setSelected(true);
						}else {
							checkBoxDoctor.setSelected(false);
						}
						if(rs.getString(8).contains("3")) {
							checkBoxSpecialist.setSelected(true);
						}else {
							checkBoxSpecialist.setSelected(false);
						}
						if(rs.getString(8).contains("4")) {
							checkBoxAddNewPatient.setSelected(true);
						}else {
							checkBoxAddNewPatient.setSelected(false);
						}
						if(rs.getString(8).contains("5")) {
							checkBoxSuperUser.setSelected(true);
						}else {
							checkBoxSuperUser.setSelected(false);
						}
						
						updateDataWorkerButton.setDisable(false);
					}
					if (rs.first() == false) {
						saveDataWorkerButton.setDisable(false);
						updateDataWorkerButton.setDisable(true);
						loginTextField.setText("");
						loginTextField.setStyle("-fx-border-width: 1.2");
						firstNameWorkerTextField.setText("");
						firstNameWorkerTextField.setStyle("-fx-border-width: 1.2");
						lastNameWorkerTextField.setText("");
						lastNameWorkerTextField.setStyle("-fx-border-width: 1.2");
						fatherNameWorkerTextField.setText("");
						fatherNameWorkerTextField.setStyle("-fx-border-width: 1.2");
						dobWorkerDatePicker.setValue(null);
						dobWorkerDatePicker.setStyle("-fx-border-width: 1.2");
						hospitalTextField.setText("");
						hospitalTextField.setStyle("-fx-border-width: 1.2");
						positionTextField.setText("");
						positionTextField.setStyle("-fx-border-width: 1.2");
						subscriptionToAccountDatePicker.setValue(null);
						subscriptionToAccountDatePicker.setStyle("-fx-border-width: 1.2");
						eMailTextField.setText("");
						eMailTextField.setStyle("-fx-border-width: 1.2");
						telephoneTextField.setText("+380");
						telephoneTextField.setStyle("-fx-border-width: 1.2");

						checkBoxAddNewPatient.setSelected(false);
						checkBoxAddNewPatient.setStyle("-fx-border-width: 1.2");
						checkBoxDoctor.setSelected(false);
						checkBoxDoctor.setStyle("-fx-border-width: 1.2");
						checkBoxOnlyRead.setSelected(false);
						checkBoxOnlyRead.setStyle("-fx-border-width: 1.2");
						checkBoxSpecialist.setSelected(false);
						checkBoxSpecialist.setStyle("-fx-border-width: 1.2");
						checkBoxSuperUser.setSelected(false);
						checkBoxSuperUser.setStyle("-fx-border-width: 1.2");
					}
					statement.close();
				} catch (Exception e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Помилка");
					alert.setHeaderText("Помилка з'єднання");
					alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
					alert.showAndWait();
				}
			}
		});
		dobWorkerDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
			if (dobWorkerDatePicker.getValue() == null) {
				dobWorkerDatePicker.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				dobWorkerDatePicker.setStyle("-fx-border-width: 1.2;");
			}
		});

		eMailTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (eMailTextField.getText().matches("^\\w+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}$") == false) {
				eMailTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				eMailTextField.setStyle("-fx-border-width: 1.2;");
			}
		});
		telephoneTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (telephoneTextField.getText().matches("[+]\\d{12}\\b") == false) {
				telephoneTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				telephoneTextField.setStyle("-fx-border-width: 1.2;");
			}
		});
		firstNameWorkerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (firstNameWorkerTextField.getText().equals("") == true) {
				firstNameWorkerTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				firstNameWorkerTextField.setStyle("-fx-border-width: 1.2;");
			}
		});
		lastNameWorkerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (lastNameWorkerTextField.getText().equals("") == true) {
				lastNameWorkerTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				lastNameWorkerTextField.setStyle("-fx-border-width: 1.2;");
			}
		});
		fatherNameWorkerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (fatherNameWorkerTextField.getText().equals("") == true) {
				fatherNameWorkerTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				fatherNameWorkerTextField.setStyle("-fx-border-width: 1.2;");
			}
		});
		hospitalTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (hospitalTextField.getText().equals("") == true) {
				hospitalTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				hospitalTextField.setStyle("-fx-border-width: 1.2;");
			}
		});
		positionTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (positionTextField.getText().equals("") == true) {
				positionTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				positionTextField.setStyle("-fx-border-width: 1.2;");
			}
		});
		loginTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (loginTextField.getText().equals("") == true) {
				loginTextField.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				try {
					PreparedStatement statement = connectDB("SELECT * FROM admin_bd.RegistrationData where login = ?;");
					statement.setString(1, loginTextField.getText());
					ResultSet rs = statement.executeQuery();
					String s1 = "";
					while (rs.next()) {
						s1 = rs.getString(1);
					}
					PreparedStatement statementL = connectDB("SELECT * FROM admin_bd.Workers where id = ?;");
					statementL.setString(1, idWorkerFiedText.getText());
					ResultSet rsL = statementL.executeQuery();
					String s2 = "";
					while (rsL.next()) {
						s2 = rsL.getString(1);
					}
					if (rsL.first() == false && rs.first() == false) {
						saveDataWorkerButton.setDisable(false);
						updateDataWorkerButton.setDisable(true);
					}
					if (rsL.first() == true && rs.first() == false) {
						saveDataWorkerButton.setDisable(true);
						updateDataWorkerButton.setDisable(false);
					}
					if (rsL.first() == false && rs.first() == true) {
						saveDataWorkerButton.setDisable(true);
						updateDataWorkerButton.setDisable(true);
					}
					if (rsL.first() == true && rs.first() == true && s1.equals(s2)) {
						saveDataWorkerButton.setDisable(true);
						updateDataWorkerButton.setDisable(false);
					}
					if (rsL.first() == true && rs.first() == true && s1.equals(s2) == false) {
						saveDataWorkerButton.setDisable(true);
						updateDataWorkerButton.setDisable(true);
					}

				} catch (Exception e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Помилка");
					alert.setHeaderText("Помилка з'єднання");
					alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
					alert.showAndWait();
				}
				loginTextField.setStyle("-fx-border-width: 1.2;");
			}

		});
		subscriptionToAccountDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
			if (subscriptionToAccountDatePicker.getValue() == null) {
				subscriptionToAccountDatePicker.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				subscriptionToAccountDatePicker.setStyle("-fx-border-width: 1.2;");
			}
		});
		
		checkBoxOnlyRead.selectedProperty().addListener((o, oldV, newV) -> {
			if (checkBoxOnlyRead.isSelected() == true) {
				checkBoxAddNewPatient.setStyle("-fx-border-width: 1.2;");
				checkBoxDoctor.setStyle("-fx-border-width: 1.2;");
				checkBoxSpecialist.setStyle("-fx-border-width: 1.2;");
				checkBoxSuperUser.setStyle("-fx-border-width: 1.2;");
			} else {
				checkBoxOnlyRead.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			}
		});

		checkBoxDoctor.selectedProperty().addListener((o, oldV, newV) -> {
			if (checkBoxDoctor.isSelected() == true) {
				checkBoxSpecialist.setSelected(false);

				checkBoxAddNewPatient.setStyle("-fx-border-width: 1.2;");
				checkBoxOnlyRead.setStyle("-fx-border-width: 1.2;");
				checkBoxSpecialist.setStyle("-fx-border-width: 1.2;");
				checkBoxSuperUser.setStyle("-fx-border-width: 1.2;");
			} else {
				checkBoxDoctor.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			}
		});

		checkBoxSpecialist.selectedProperty().addListener((o, oldV, newV) -> {
			if (checkBoxSpecialist.isSelected() == true) {
				checkBoxDoctor.setSelected(false);

				checkBoxOnlyRead.setStyle("-fx-border-width: 1.2;");
				checkBoxDoctor.setStyle("-fx-border-width: 1.2;");
				checkBoxAddNewPatient.setStyle("-fx-border-width: 1.2;");
				checkBoxSuperUser.setStyle("-fx-border-width: 1.2;");
			} else {
				checkBoxSpecialist.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			}
		});

		checkBoxAddNewPatient.selectedProperty().addListener((o, oldV, newV) -> {
			if (checkBoxAddNewPatient.isSelected() == true) {

				checkBoxOnlyRead.setStyle("-fx-border-width: 1.2;");
				checkBoxDoctor.setStyle("-fx-border-width: 1.2;");
				checkBoxSpecialist.setStyle("-fx-border-width: 1.2;");
				checkBoxSuperUser.setStyle("-fx-border-width: 1.2;");
			} else {
				checkBoxAddNewPatient.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			}
		});

		checkBoxSuperUser.selectedProperty().addListener((o, oldV, newV) -> {
			if (checkBoxSuperUser.isSelected() == true) {
				checkBoxSpecialist.setSelected(false);

				checkBoxOnlyRead.setStyle("-fx-border-width: 1.2;");
				checkBoxDoctor.setStyle("-fx-border-width: 1.2;");
				checkBoxSpecialist.setStyle("-fx-border-width: 1.2;");
				checkBoxAddNewPatient.setStyle("-fx-border-width: 1.2;");
			} else {
				checkBoxSuperUser.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			}
		});
		
		updateDataWorkerButton.setDisable(true);
		dobWorkerDatePicker.setPromptText("DD.MM.YYYY");
		eMailTextField.setPromptText("example@domain.com");
	}
}