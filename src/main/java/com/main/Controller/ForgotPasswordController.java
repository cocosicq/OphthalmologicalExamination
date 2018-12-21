package com.main.Controller;

import java.sql.ResultSet;

import com.main.DataBased.ConnectDataBased;
import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ForgotPasswordController extends ConnectDataBased {
	@FXML
	private TextField loginWorkerTextField;

	@FXML
	private Button restorePasswordButton;

	@SuppressWarnings("restriction")
	@FXML
	void restorePasswordButtonAction(ActionEvent event) {
		try {
			PreparedStatement statement = connectDB(
					"SELECT r.*, w.eMail, w.name FROM admin_bd.RegistrationData r inner join admin_bd.Workers w On r.id = w.id where login = ?;");
			statement.setString(1, loginWorkerTextField.getText());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Sender tlsSender = new Sender("medicalifua@gmail.com", "medicalifuamedicalifua");
				tlsSender.send("Відновлення пароля від облікового запису", "\tВІДНОВЛЕННЯ !\nКористувач : " + rs.getString(2) + " , " +
						rs.getString(5) + ", Ви намагаєтесь відновити пароль від облікового запису в медичній програмі,\nякщо це так, то в програму можна "
								+ "зайти під логіном і паролем який Ви бачите нище: \nЛогін : " + rs.getString(2) + "\nПароль : " + rs.getString(3) + ".\n\nЯкщо це не Ви намагались відновити пароль, в "
										+ "межах безпеки Ваших даних та даних Ваших пацієнтів змініть пароль в медичній програмі.\nДякуємо!\nАдміністрація медичної програми." , 
						"medicalifua@gmail.com", rs.getString(4));
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Надіслано");
				alert.setContentText("Пароль було надіслано Вам на \nелектронну пошту : \n" + rs.getString(4));
				alert.showAndWait();
			}
			if (rs.first() == false) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Помилка");
				alert.setHeaderText("Не знайдено логін");
				alert.setContentText("Неможливо знайти введений логін! \nПеревірте чи правильно введений логін!");
				alert.showAndWait();
			}
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Помилка з'єднання");
			alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
			alert.showAndWait();
		}
	}
}
