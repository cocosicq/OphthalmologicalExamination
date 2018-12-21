package com.main.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.main.DataBased.ConnectDataBased;
import com.main.View.SearchView;
import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;

public class ReviewPatientController extends ConnectDataBased{
	@FXML
	private Label labelPatientRegion;

	@FXML
	private Label labelPatientMobilePhone;

	@FXML
	private Label labelPatientCountry;

	@FXML
	private Label labelPatientSurName;

	@FXML
	private Label labelPatientFlat;

	@FXML
	private Label labelPatientStreet;

	@FXML
	private Label labelPatientEmail;

	@FXML
	private Label labelPatientID;

	@FXML
	private Label labelPatientFirstName;

	@FXML
	private Label labelPatientLastName;
	
	@FXML
	private Label labelNameDoctor;
	
	@FXML
	private Button exitButton;

	@FXML
	private Label labelPatientDob;

	@FXML
	private Label labelPatientGender;

	@FXML
	private Label labelPatientCity;

	@FXML
	private Label labelPatientHouse;

	@FXML
	private Label labelPatientRayon;

	@FXML
	private TextArea dopInfoPatientTextArea;

	@FXML
	void exitButtonAction(ActionEvent event) {
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}
	
	@SuppressWarnings("restriction")
	@FXML
	void initialize() {
		try {
			PreparedStatement statement = connectDB("Select p.*, w.name From admin_bd.Patient p inner join admin_bd.Workers w "
					+ "On p.Workers_id = w.id Where p.id = ?;");
			statement.setInt(1, SearchController.getIdPatientSearch());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				labelPatientID.setText(rs.getString(1));
				String[] name = rs.getString(2).split(" ");
				labelPatientLastName.setText(name[0]);
				labelPatientFirstName.setText(name[1]);
				labelPatientSurName.setText(name[2]);
				labelPatientDob.setText(rs.getString(3));
				labelPatientMobilePhone.setText(rs.getString(4));
				labelPatientEmail.setText(rs.getString(5));
				labelPatientCountry.setText(rs.getString(6));
				labelPatientRegion.setText(rs.getString(7));
				labelPatientRayon.setText(rs.getString(8));
				labelPatientCity.setText(rs.getString(9));
				labelPatientStreet.setText(rs.getString(10));
				labelPatientHouse.setText(rs.getString(11));
				labelPatientFlat.setText(rs.getString(12));
				dopInfoPatientTextArea.setText(rs.getString(13));
				
				if(rs.getString(15).equals("1")) {
					labelPatientGender.setText("Жінка");
				}else {
					labelPatientGender.setText("Чоловік");
				}
				labelNameDoctor.setText(rs.getString(16));
			}
			statement.close();
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Помилка з'єднання");
			alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
			alert.showAndWait();
		} 
		dopInfoPatientTextArea.setEditable(false);
		
	}
}
