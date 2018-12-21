package com.main.Controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.omg.CORBA.portable.ApplicationException;

import com.main.DataBased.ConnectDataBased;
import com.main.View.MenuView;
import com.main.View.SearchView;
import com.mysql.jdbc.PreparedStatement;

import javafx.application.Application;
import javafx.application.HostServices;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class OphthalmicStatusPatientController extends ConnectDataBased {
	private static Map dictionary = new HashMap();

	@FXML
	private TextField idExaminationTextField;

	@FXML
	private TextField spesialistTextField;

	@FXML
	private TextField doctorTextField;

	@FXML
	private TextField patientTextField;

	@FXML
	private DatePicker dayOfExaminationDatePicker;

	@FXML
	private TextField textFieldVizusOD;
	@FXML
	private TextField textFieldVisusOS;

	@FXML
	private TextField textFieldFosfenOD;
	@FXML
	private TextField textFieldFosfenOS;

	@FXML
	private TextField textFieldBotOD;
	@FXML
	private TextField textFieldBotOS;

	@FXML
	private TextField textFieldThicknessOfCorneaOD;
	@FXML
	private TextField textFieldThicknessOfCorneaOS;

	@FXML
	private Button buttonHamfriOD;
	@FXML
	private Button buttonHamfriOS;

	@FXML
	private Button buttonAtlasOD;
	@FXML
	private Button buttonAtlasOS;

	@FXML
	private Button buttonCameraOD;
	@FXML
	private Button buttonCameraOS;

	@FXML
	private Button buttonCamera3dOD;
	@FXML
	private Button buttonCamera3dOS;

	@FXML
	private Button buttonCorneeNOD;
	@FXML
	private Button buttonCorneeNOS;

	@FXML
	private Button buttonGDXOD;
	@FXML
	private Button buttonGdXOS;

	@FXML
	private Button buttonHicknessOfCorneaOD1, buttonHicknessOfCorneaOD2, buttonHicknessOfCorneaOD3,
			buttonHicknessOfCorneaOD4;
	@FXML
	private Button buttonHicknessOfCorneaOS1, buttonHicknessOfCorneaOS2, buttonHicknessOfCorneaOS3,
			buttonHicknessOfCorneaOS4;

	@FXML
	private Button buttonMaculaOd3D1, buttonMaculaOd3D2, buttonMaculaOd3D3, buttonMaculaOd3D4;
	@FXML
	private Button buttonMaculaOs3D2, buttonMaculaOs3D1, buttonMaculaOs3D3, buttonMaculaOs3D4;

	@FXML
	private Button buttonMaculaOD1, buttonMaculaOD2, buttonMaculaOD3, buttonMaculaOD4;

	@FXML
	private Button buttonMaculaOS1, buttonMaculaOS2, buttonMaculaOS3, buttonMaculaOS4;

	@FXML
	private Button buttonMaculaOD, buttonMaculaOU;
	@FXML
	private Button buttonMaculaOS;

	@FXML
	private Button buttonMacula3dOD;
	@FXML
	private Button buttonMacula3dOS;

	@FXML
	private Button buttonHrtOD, buttonHPTOU;
	@FXML
	private Button buttonHrtOS;

	@FXML
	private Button buttonPoleZoruOD;
	@FXML
	private Button buttonPoleZoruOS, buttonAtlasOU, buttonFieldViewOU,buttonHicknessOfCorneaOU;

	@FXML
	private Button saveExaminationButton, exitButton, updateOftalmButton;

	@FXML
	void buttonHamfriODAction(ActionEvent event) {
		try {
			selectedFile(7, buttonHamfriOD);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonHamfriOSAction(ActionEvent event) throws Exception {
		try {
			selectedFile(8, buttonHamfriOS);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonHicknessOfCorneaOD1Action(ActionEvent event) {
		try {
			selectedFile(13, buttonHicknessOfCorneaOD1);
			System.out.println("buttonHicknessOfCorneaOD1");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonHicknessOfCorneaOD2Action(ActionEvent event) {
		try {
			selectedFile(14, buttonHicknessOfCorneaOD2);
			System.out.println("buttonHicknessOfCorneaOD2");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonHicknessOfCorneaOD3Action(ActionEvent event) {
		try {
			selectedFile(15, buttonHicknessOfCorneaOD3);
			System.out.println("buttonHicknessOfCorneaOD3");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonHicknessOfCorneaOD4Action(ActionEvent event) {
		try {
			selectedFile(16, buttonHicknessOfCorneaOD4);
			System.out.println("buttonHicknessOfCorneaOD4");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonHicknessOfCorneaOS1Action(ActionEvent event) {
		try {
			selectedFile(17, buttonHicknessOfCorneaOS1);
			System.out.println("buttonHicknessOfCorneaOS1");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonHicknessOfCorneaOS2Action(ActionEvent event) {
		try {
			selectedFile(18, buttonHicknessOfCorneaOS2);
			System.out.println("buttonHicknessOfCorneaOS2");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonHicknessOfCorneaOS3Action(ActionEvent event) {
		try {
			selectedFile(19, buttonHicknessOfCorneaOS3);
			System.out.println("buttonHicknessOfCorneaOS3");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonHicknessOfCorneaOS4Action(ActionEvent event) {
		try {
			selectedFile(20, buttonHicknessOfCorneaOS4);
			System.out.println("buttonHicknessOfCorneaOS4");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonAtlasODAction(ActionEvent event) throws Exception {
		try {
			selectedFile(21, buttonAtlasOD);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonAtlasOSAction(ActionEvent event) throws Exception {
		try {
			selectedFile(22, buttonAtlasOS);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonCameraODAction(ActionEvent event) {
		try {
			selectedFile(23, buttonCameraOD);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonCameraOSAction(ActionEvent event) {
		try {
			selectedFile(24, buttonCameraOS);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonCamera3dODAction(ActionEvent event) {
		try {
			selectedFile(25, buttonCamera3dOD);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonCamera3dOSAction(ActionEvent event) {
		try {
			selectedFile(26, buttonCamera3dOS);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonCorneeNODAction(ActionEvent event) {
		try {
			selectedFile(27, buttonCorneeNOD);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonCorneeNOSAction(ActionEvent event) {
		try {
			selectedFile(28, buttonCorneeNOS);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonGDXODAction(ActionEvent event) {
		try {
			selectedFile(29, buttonGDXOD);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonGDXOSAction(ActionEvent event) {
		try {
			selectedFile(30, buttonGdXOS);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonHrtODAction(ActionEvent event) {
		try {
			selectedFile(31, buttonHrtOD);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonHrtOSAction(ActionEvent event) {
		try {
			selectedFile(32, buttonHrtOS);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOD1Action(ActionEvent event) {
		try {
			selectedFile(33, buttonMaculaOD1);
			System.out.println("buttonMaculaOD1");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOD2Action(ActionEvent event) {
		try {
			selectedFile(34, buttonMaculaOD2);
			System.out.println("buttonMaculaOD2");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOD3Action(ActionEvent event) {
		try {
			selectedFile(35, buttonMaculaOD3);
			System.out.println("buttonMaculaOD3");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOD4Action(ActionEvent event) {
		try {
			selectedFile(36, buttonMaculaOD4);
			System.out.println("buttonMaculaOD4");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOS1Action(ActionEvent event) {
		try {
			selectedFile(37, buttonMaculaOS1);
			System.out.println("buttonMaculaOS1");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOS2Action(ActionEvent event) {
		try {
			selectedFile(38, buttonMaculaOS2);
			System.out.println("buttonMaculaOS2");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOS3Action(ActionEvent event) {
		try {
			selectedFile(39, buttonMaculaOS3);
			System.out.println("buttonMaculaOS3");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOS4Action(ActionEvent event) {
		try {
			selectedFile(40, buttonMaculaOS4);
			System.out.println("buttonMaculaOS4");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOd3D1Action(ActionEvent event) {
		try {
			selectedFile(41, buttonMaculaOd3D1);
			System.out.println("buttonMaculaOd3D1");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOd3D2Action(ActionEvent event) {
		try {
			selectedFile(42, buttonMaculaOd3D2);
			System.out.println("buttonMaculaOd3D2");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOd3D3Action(ActionEvent event) {
		try {
			selectedFile(43, buttonMaculaOd3D3);
			System.out.println("buttonMaculaOd3D3");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOd3D4Action(ActionEvent event) {
		try {
			selectedFile(44, buttonMaculaOd3D4);
			System.out.println("buttonMaculaOd3D4");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOs3D1Action(ActionEvent event) {
		try {
			selectedFile(45, buttonMaculaOs3D1);
			System.out.println("buttonMaculaOs3D1");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOs3D2Action(ActionEvent event) {
		try {
			selectedFile(46, buttonMaculaOs3D2);
			System.out.println("buttonMaculaOs3D2");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOs3D3Action(ActionEvent event) {
		try {
			selectedFile(47, buttonMaculaOs3D3);
			System.out.println("buttonMaculaOs3D3");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOs3D4Action(ActionEvent event) {
		try {
			selectedFile(48, buttonMaculaOs3D4);
			System.out.println("buttonMaculaOs3D4");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonPoleZoruODAction(ActionEvent event) {
		try {
			selectedFile(49, buttonPoleZoruOD);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonPoleZoruOSAction(ActionEvent event) {
		try {
			selectedFile(50, buttonPoleZoruOS);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonAtlasOUAction(ActionEvent event) {
		try {
			selectedFile(51, buttonAtlasOU);
			System.out.println("buttonAtlasOU");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonMaculaOUAction(ActionEvent event) {
		try {
			selectedFile(52, buttonMaculaOU);
			System.out.println("buttonMaculaOU");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonHPTOUAction(ActionEvent event) {
		try {
			selectedFile(53, buttonHPTOU);
			System.out.println("buttonHPTOU");
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@FXML
	void buttonFieldViewOUAction(ActionEvent event) {
		try {
			selectedFile(54, buttonFieldViewOU);
			System.out.println("buttonFieldViewOU");
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@FXML
	void buttonHicknessOfCorneaOUAction(ActionEvent event) {
		try {
			selectedFile(99, buttonHicknessOfCorneaOU);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void selectedFile(int key, Button b) throws Exception {
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(null);

		if (selectedFile != null) {
			dictionary.put(key, selectedFile);
			b.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
		} else {
			System.out.println("File selection cancelled.");
		}
	}

	public void addFileInDB(String nameColomn, int key) throws Exception {
		PreparedStatement statement = null;
		try {
			File f = (File) dictionary.get(key);
			String[] formatFile = f.getName().split("[.]");
			FileInputStream inputSelectedFile = new FileInputStream(f);

			statement = connectDB("Update admin_bd.OphthalmicStatus Set " + nameColomn + " = ?, formatFile_"
					+ nameColomn + " = ? Where idExamination = ?;");
			statement.setBlob(1, inputSelectedFile);

			statement.setString(2, "." + formatFile[1]);
			statement.setInt(3, Integer.parseInt(idExaminationTextField.getText()));
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			statement.close();
		}
	}

	public void addTextFieldInDB(String nameColomn, String textField) throws Exception {
		try {
			PreparedStatement statement = connectDB(
					"Update admin_bd.OphthalmicStatus Set " + nameColomn + " = ? Where idExamination = ?;");
			statement.setString(1, textField);
			statement.setInt(2, Integer.parseInt(idExaminationTextField.getText()));
			statement.executeUpdate();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void updateOftalmButtonAction(ActionEvent event) throws Exception {
		try {
		if (textFieldVizusOD.getText() != null) {
			addTextFieldInDB("vizusOD", textFieldVizusOD.getText());
		}
		if (textFieldVisusOS.getText()  != null) {
			addTextFieldInDB("vizusOS", textFieldVisusOS.getText());
		}
		if (textFieldFosfenOD.getText() != null) {
			addTextFieldInDB("fosfenOD", textFieldFosfenOD.getText());
		}
		if (textFieldFosfenOS.getText() != null) {
			addTextFieldInDB("fosfenOS", textFieldFosfenOS.getText());
		}
		if (textFieldBotOD.getText() != null) {
			addTextFieldInDB("botOD", textFieldBotOD.getText());
		}
		if (textFieldBotOS.getText() != null) {
			addTextFieldInDB("botOS", textFieldBotOS.getText());
		}
		if (dictionary.get(7) != null) {
			addFileInDB("humphreyOD", 7);
		}
		if (dictionary.get(8) != null) {
			addFileInDB("humphreyOS", 8);
		}

		if (dictionary.get(13) != null) {
			addFileInDB("hicknessOfCorneaOD_1", 13);
		}

		if (dictionary.get(14) != null) {
			addFileInDB("hicknessOfCorneaOD_2", 14);
		}

		if (dictionary.get(15) != null) {
			addFileInDB("hicknessOfCorneaOD_3", 15);
		}

		if (dictionary.get(16) != null) {
			addFileInDB("hicknessOfCorneaOD_4", 16);
		}

		if (dictionary.get(17) != null) {
			addFileInDB("hicknessOfCorneaOS_1", 17);
		}

		if (dictionary.get(18) != null) {
			addFileInDB("hicknessOfCorneaOS_2", 18);
		}

		if (dictionary.get(19) != null) {
			addFileInDB("hicknessOfCorneaOS_3", 19);
		}

		if (dictionary.get(20) != null) {
			addFileInDB("hicknessOfCorneaOS_4", 20);
		}

		if (dictionary.get(21) != null) {
			addFileInDB("atlasOD", 21);
		}
		if (dictionary.get(22) != null) {
			addFileInDB("atlasOS", 22);
		}

		if (dictionary.get(23) != null) {
			addFileInDB("cameraOD", 23);
		}
		if (dictionary.get(24) != null) {
			addFileInDB("cameraOS", 24);
		}
		if (dictionary.get(25) != null) {
			addFileInDB("cameraOD3D", 25);
		}
		if (dictionary.get(26) != null) {
			addFileInDB("cameraOS3D", 26);
		}

		if (dictionary.get(27) != null) {
			addFileInDB("corneeN_OD", 27);
		}
		if (dictionary.get(28) != null) {
			addFileInDB("corneeN_OS", 28);
		}
		if (dictionary.get(29) != null) {
			addFileInDB("gdxOD", 29);
		}
		if (dictionary.get(30) != null) {
			addFileInDB("gdxOS", 30);
		}
		if (dictionary.get(31) != null) {
			addFileInDB("hrtOD", 31);
		}
		if (dictionary.get(32) != null) {
			addFileInDB("hrtOS", 32);
		}

		if (dictionary.get(33) != null) {
			addFileInDB("maculaOD_1", 33);
		}

		if (dictionary.get(34) != null) {
			addFileInDB("maculaOD_2", 34);
		}

		if (dictionary.get(35) != null) {
			addFileInDB("maculaOD_3", 35);
		}

		if (dictionary.get(36) != null) {
			addFileInDB("maculaOD_4", 36);
		}

		if (dictionary.get(37) != null) {
			addFileInDB("maculaOS_1", 37);
		}

		if (dictionary.get(38) != null) {
			addFileInDB("maculaOS_2", 38);
		}

		if (dictionary.get(39) != null) {
			addFileInDB("maculaOS_3", 39);
		}

		if (dictionary.get(40) != null) {
			addFileInDB("maculaOS_4", 40);
		}

		if (dictionary.get(41) != null) {
			addFileInDB("maculaOD3D_1", 41);
		}

		if (dictionary.get(42) != null) {
			addFileInDB("maculaOD3D_2", 42);
		}

		if (dictionary.get(43) != null) {
			addFileInDB("maculaOD3D_3", 43);
		}

		if (dictionary.get(44) != null) {
			addFileInDB("maculaOD3D_4", 44);
		}

		if (dictionary.get(45) != null) {
			addFileInDB("maculaOS3D_1", 45);
		}

		if (dictionary.get(46) != null) {
			addFileInDB("maculaOS3D_2", 46);
		}

		if (dictionary.get(47) != null) {
			addFileInDB("maculaOS3D_3", 47);
		}

		if (dictionary.get(48) != null) {
			addFileInDB("maculaOS3D_4", 48);
		}

		if (dictionary.get(49) != null) {
			addFileInDB("sightOD", 49);
		}
		if (dictionary.get(50) != null) {
			addFileInDB("sightOS", 50);
		}

		if (dictionary.get(51) != null) {
			addFileInDB("ATLAS_OU", 51);
		}

		if (dictionary.get(52) != null) {
			addFileInDB("Macula_OU", 52);
		}

		if (dictionary.get(53) != null) {
			addFileInDB("HPT_OU", 53);
		}

		if (dictionary.get(54) != null) {
			addFileInDB("fieldView_OU", 54);
		}
		if (dictionary.get(99) != null) {
			addFileInDB("hicknessOfCorneaOU", 99);
		}
		Alert alertS = new Alert(Alert.AlertType.INFORMATION);
		alertS.setTitle("Додавання");
		alertS.setHeaderText("Додавання обстеження пацієнта");
		alertS.setContentText("Успішно додано обстеження пацієнта!");
		alertS.showAndWait();
		}catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Помилка при роботі");
			alert.setContentText(
					"Помилка при роботі з базою даних! \n" + e.getMessage());
			System.out.println(e.getMessage());
			alert.showAndWait();
		}

	}

	@FXML
	void saveExaminationButtonAction(ActionEvent event) throws Exception {
		if (dayOfExaminationDatePicker.getValue() == null) {
			dayOfExaminationDatePicker.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
		} else {
			dayOfExaminationDatePicker.setStyle("-fx-border-width: 1.2;");
			try {
				PreparedStatement statement = connectDB(
						"insert into admin_bd.OphthalmicStatus (idExamination, idPatient, idWorkers,DateOfSurvey)"
								+ "value(?,?,?,?);");
				statement.setInt(1, Integer.parseInt(idExaminationTextField.getText()));
				statement.setInt(2, SearchController.getIdPatientSearch());
				statement.setInt(3, Integer.parseInt(LoginController.getIdWorker()));
				statement.setDate(4, java.sql.Date.valueOf(dayOfExaminationDatePicker.getValue()));
				statement.executeUpdate();

				if (textFieldVizusOD.getText().equals("") == false) {
					addTextFieldInDB("vizusOD", textFieldVizusOD.getText());
				}
				if (textFieldVisusOS.getText().equals("") == false) {
					addTextFieldInDB("vizusOS", textFieldVisusOS.getText());
				}
				if (textFieldFosfenOD.getText().equals("") == false) {
					addTextFieldInDB("fosfenOD", textFieldFosfenOD.getText());
				}
				if (textFieldFosfenOS.getText().equals("") == false) {
					addTextFieldInDB("fosfenOS", textFieldFosfenOS.getText());
				}
				if (textFieldBotOD.getText().equals("") == false) {
					addTextFieldInDB("botOD", textFieldBotOD.getText());
				}
				if (textFieldBotOS.getText().equals("") == false) {
					addTextFieldInDB("botOS", textFieldBotOS.getText());
				}
				if (dictionary.get(7) != null) {
					addFileInDB("humphreyOD", 7);
				}
				if (dictionary.get(8) != null) {
					addFileInDB("humphreyOS", 8);
				}

				if (dictionary.get(13) != null) {
					addFileInDB("hicknessOfCorneaOD_1", 13);
				}

				if (dictionary.get(14) != null) {
					addFileInDB("hicknessOfCorneaOD_2", 14);
				}

				if (dictionary.get(15) != null) {
					addFileInDB("hicknessOfCorneaOD_3", 15);
				}

				if (dictionary.get(16) != null) {
					addFileInDB("hicknessOfCorneaOD_4", 16);
				}

				if (dictionary.get(17) != null) {
					addFileInDB("hicknessOfCorneaOS_1", 17);
				}

				if (dictionary.get(18) != null) {
					addFileInDB("hicknessOfCorneaOS_2", 18);
				}

				if (dictionary.get(19) != null) {
					addFileInDB("hicknessOfCorneaOS_3", 19);
				}

				if (dictionary.get(20) != null) {
					addFileInDB("hicknessOfCorneaOS_4", 20);
				}

				if (dictionary.get(21) != null) {
					addFileInDB("atlasOD", 21);
				}
				if (dictionary.get(22) != null) {
					addFileInDB("atlasOS", 22);
				}

				if (dictionary.get(23) != null) {
					addFileInDB("cameraOD", 23);
				}
				if (dictionary.get(24) != null) {
					addFileInDB("cameraOS", 24);
				}
				if (dictionary.get(25) != null) {
					addFileInDB("cameraOD3D", 25);
				}
				if (dictionary.get(26) != null) {
					addFileInDB("cameraOS3D", 26);
				}

				if (dictionary.get(27) != null) {
					addFileInDB("corneeN_OD", 27);
				}
				if (dictionary.get(28) != null) {
					addFileInDB("corneeN_OS", 28);
				}
				if (dictionary.get(29) != null) {
					addFileInDB("gdxOD", 29);
				}
				if (dictionary.get(30) != null) {
					addFileInDB("gdxOS", 30);
				}
				if (dictionary.get(31) != null) {
					addFileInDB("hrtOD", 31);
				}
				if (dictionary.get(32) != null) {
					addFileInDB("hrtOS", 32);
				}

				if (dictionary.get(33) != null) {
					addFileInDB("maculaOD_1", 33);
				}

				if (dictionary.get(34) != null) {
					addFileInDB("maculaOD_2", 34);
				}

				if (dictionary.get(35) != null) {
					addFileInDB("maculaOD_3", 35);
				}

				if (dictionary.get(36) != null) {
					addFileInDB("maculaOD_4", 36);
				}

				if (dictionary.get(37) != null) {
					addFileInDB("maculaOS_1", 37);
				}

				if (dictionary.get(38) != null) {
					addFileInDB("maculaOS_2", 38);
				}

				if (dictionary.get(39) != null) {
					addFileInDB("maculaOS_3", 39);
				}

				if (dictionary.get(40) != null) {
					addFileInDB("maculaOS_4", 40);
				}

				if (dictionary.get(41) != null) {
					addFileInDB("maculaOD3D_1", 41);
				}

				if (dictionary.get(42) != null) {
					addFileInDB("maculaOD3D_2", 42);
				}

				if (dictionary.get(43) != null) {
					addFileInDB("maculaOD3D_3", 43);
				}

				if (dictionary.get(44) != null) {
					addFileInDB("maculaOD3D_4", 44);
				}

				if (dictionary.get(45) != null) {
					addFileInDB("maculaOS3D_1", 45);
				}

				if (dictionary.get(46) != null) {
					addFileInDB("maculaOS3D_2", 46);
				}

				if (dictionary.get(47) != null) {
					addFileInDB("maculaOS3D_3", 47);
				}

				if (dictionary.get(48) != null) {
					addFileInDB("maculaOS3D_4", 48);
				}

				if (dictionary.get(49) != null) {
					addFileInDB("sightOD", 49);
				}
				if (dictionary.get(50) != null) {
					addFileInDB("sightOS", 50);
				}

				if (dictionary.get(51) != null) {
					addFileInDB("ATLAS_OU", 51);
				}

				if (dictionary.get(52) != null) {
					addFileInDB("Macula_OU", 52);
				}

				if (dictionary.get(53) != null) {
					addFileInDB("HPT_OU", 53);
				}

				if (dictionary.get(54) != null) {
					addFileInDB("fieldView_OU", 54);
				}
				
				if (dictionary.get(99) != null) {
					addFileInDB("hicknessOfCorneaOU", 99);
				}

				statement.close();
				Alert alertS = new Alert(Alert.AlertType.INFORMATION);
				alertS.setTitle("Додавання");
				alertS.setHeaderText("Додавання обстеження пацієнта");
				alertS.setContentText("Успішно додано обстеження пацієнта!");
				alertS.showAndWait();
				saveExaminationButton.setDisable(true);
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Помилка");
				alert.setHeaderText("Помилка при роботі");
				alert.setContentText(
						"Помилка при роботі з базою даних! \nПеревірте  підключеня до інтернету\n" + e.getMessage());
				alert.showAndWait();
			}
		}
	}

	private static String name = null;

	public static void setSearchTextField(String searchTextField) {
		name = searchTextField;
	}

	public static String getSearchTextField() {
		return name;
	}

	private static String date = null;

	public static void setSearchDatePicker(String searchDatePicker) {
		date = searchDatePicker;
	}

	public static String getSearchDatePicker() {
		return date;
	}

	@FXML
	void exitButtonAction(ActionEvent event) {
		SearchController sc = new SearchController();
		setSearchTextField(sc.getSearchTextField());
		setSearchDatePicker(sc.getSearchDatePicker());

		// Створення форми і сцени
		SearchView searchView = new SearchView();
		Stage stageMainAnchorPane = searchView.starts(new Stage());
		stageMainAnchorPane.show();
		// Закриття вікна
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void initialize() {
		if (SearchController.getIdExaminationSearch().equals("") == false) {
			idExaminationTextField.setText(SearchController.getIdExaminationSearch());
			try {
				saveExaminationButton.setDisable(true);
				PreparedStatement statement = connectDB(
						"SELECT * FROM admin_bd.OphthalmicStatus Where idExamination = ?;");
				statement.setInt(1, Integer.parseInt(SearchController.getIdExaminationSearch()));
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					textFieldVizusOD.setText(rs.getString(5));
					textFieldVisusOS.setText(rs.getString(6));
					textFieldFosfenOD.setText(rs.getString(9));
					textFieldFosfenOS.setText(rs.getString(10));
					textFieldBotOD.setText(rs.getString(11));
					textFieldBotOS.setText(rs.getString(12));
					if (rs.getString(7) == null) {
						buttonHamfriOD.setDisable(false);
					} else {
						buttonHamfriOD.setDisable(true);
						buttonHamfriOD.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(8) == null) {
						buttonHamfriOS.setDisable(false);
					} else {
						buttonHamfriOS.setDisable(true);
						buttonHamfriOS.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(13) == null) {
						buttonHicknessOfCorneaOD1.setDisable(false);
					} else {
						buttonHicknessOfCorneaOD1.setDisable(true);
						buttonHicknessOfCorneaOD1.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(14) == null) {
						buttonHicknessOfCorneaOD2.setDisable(false);
					} else {
						buttonHicknessOfCorneaOD2.setDisable(true);
						buttonHicknessOfCorneaOD2.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(15) == null) {
						buttonHicknessOfCorneaOD3.setDisable(false);
					} else {
						buttonHicknessOfCorneaOD3.setDisable(true);
						buttonHicknessOfCorneaOD3.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(16) == null) {
						buttonHicknessOfCorneaOD4.setDisable(false);
					} else {
						buttonHicknessOfCorneaOD4.setDisable(true);
						buttonHicknessOfCorneaOD4.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(17) == null) {
						buttonHicknessOfCorneaOS1.setDisable(false);
					} else {
						buttonHicknessOfCorneaOS1.setDisable(true);
						buttonHicknessOfCorneaOS1.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(18) == null) {
						buttonHicknessOfCorneaOS2.setDisable(false);
					} else {
						buttonHicknessOfCorneaOS2.setDisable(true);
						buttonHicknessOfCorneaOS2.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(19) == null) {
						buttonHicknessOfCorneaOS3.setDisable(false);
					} else {
						buttonHicknessOfCorneaOS3.setDisable(true);
						buttonHicknessOfCorneaOS3.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(20) == null) {
						buttonHicknessOfCorneaOS4.setDisable(false);
					} else {
						buttonHicknessOfCorneaOS4.setDisable(true);
						buttonHicknessOfCorneaOS4.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(21) == null) {
						buttonAtlasOD.setDisable(false);
					} else {
						buttonAtlasOD.setDisable(true);
						buttonAtlasOD.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(22) == null) {
						buttonAtlasOS.setDisable(false);
					} else {
						buttonAtlasOS.setDisable(true);
						buttonAtlasOS.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(23) == null) {
						buttonCameraOD.setDisable(false);
					} else {
						buttonCameraOD.setDisable(true);
						buttonCameraOD.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(24) == null) {
						buttonCameraOS.setDisable(false);
					} else {
						buttonCameraOS.setDisable(true);
						buttonCameraOS.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(25) == null) {
						buttonCamera3dOD.setDisable(false);
					} else {
						buttonCamera3dOD.setDisable(true);
						buttonCamera3dOD.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(26) == null) {
						buttonCamera3dOS.setDisable(false);
					} else {
						buttonCamera3dOS.setDisable(true);
						buttonCamera3dOS.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(27) == null) {
						buttonCorneeNOD.setDisable(false);
					} else {
						buttonCorneeNOD.setDisable(true);
						buttonCorneeNOD.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(28) == null) {
						buttonCorneeNOS.setDisable(false);
					} else {
						buttonCorneeNOS.setDisable(true);
						buttonCorneeNOS.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(29) == null) {
						buttonGDXOD.setDisable(false);
					} else {
						buttonGDXOD.setDisable(true);
						buttonGDXOD.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(30) == null) {
						buttonGdXOS.setDisable(false);
					} else {
						buttonGdXOS.setDisable(true);
						buttonGdXOS.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(31) == null) {
						buttonHrtOD.setDisable(false);
					} else {
						buttonHrtOD.setDisable(true);
						buttonHrtOD.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(32) == null) {
						buttonHrtOS.setDisable(false);
					} else {
						buttonHrtOS.setDisable(true);
						buttonHrtOS.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					// -----------------------------
					if (rs.getString(33) == null) {
						buttonMaculaOD1.setDisable(false);
					} else {
						buttonMaculaOD1.setDisable(true);
						buttonMaculaOD1.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(34) == null) {
						buttonMaculaOD2.setDisable(false);
					} else {
						buttonMaculaOD2.setDisable(true);
						buttonMaculaOD2.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(35) == null) {
						buttonMaculaOD3.setDisable(false);
					} else {
						buttonMaculaOD3.setDisable(true);
						buttonMaculaOD3.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(36) == null) {
						buttonMaculaOD4.setDisable(false);
					} else {
						buttonMaculaOD4.setDisable(true);
						buttonMaculaOD4.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(37) == null) {
						buttonMaculaOS1.setDisable(false);
					} else {
						buttonMaculaOS1.setDisable(true);
						buttonMaculaOS1.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(38) == null) {
						buttonMaculaOS2.setDisable(false);
					} else {
						buttonMaculaOS2.setDisable(true);
						buttonMaculaOS2.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(39) == null) {
						buttonMaculaOS3.setDisable(false);
					} else {
						buttonMaculaOS3.setDisable(true);
						buttonMaculaOS3.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(40) == null) {
						buttonMaculaOS4.setDisable(false);
					} else {
						buttonMaculaOS4.setDisable(true);
						buttonMaculaOS4.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					// -------------------
					if (rs.getString(41) == null) {
						buttonMaculaOd3D1.setDisable(false);
					} else {
						buttonMaculaOd3D1.setDisable(true);
						buttonMaculaOd3D1.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(42) == null) {
						buttonMaculaOd3D2.setDisable(false);
					} else {
						buttonMaculaOd3D2.setDisable(true);
						buttonMaculaOd3D2.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(43) == null) {
						buttonMaculaOd3D3.setDisable(false);
					} else {
						buttonMaculaOd3D3.setDisable(true);
						buttonMaculaOd3D3.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(44) == null) {
						buttonMaculaOd3D4.setDisable(false);
					} else {
						buttonMaculaOd3D4.setDisable(true);
						buttonMaculaOd3D4.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(45) == null) {
						buttonMaculaOs3D1.setDisable(false);
					} else {
						buttonMaculaOs3D1.setDisable(true);
						buttonMaculaOs3D1.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(46) == null) {
						buttonMaculaOs3D2.setDisable(false);
					} else {
						buttonMaculaOs3D2.setDisable(true);
						buttonMaculaOs3D2.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(47) == null) {
						buttonMaculaOs3D3.setDisable(false);
					} else {
						buttonMaculaOs3D3.setDisable(true);
						buttonMaculaOs3D3.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(48) == null) {
						buttonMaculaOs3D4.setDisable(false);
					} else {
						buttonMaculaOs3D4.setDisable(true);
						buttonMaculaOs3D4.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					// **************************
					if (rs.getString(49) == null) {
						buttonPoleZoruOD.setDisable(false);
					} else {
						buttonPoleZoruOD.setDisable(true);
						buttonPoleZoruOD.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(50) == null) {
						buttonPoleZoruOS.setDisable(false);
					} else {
						buttonPoleZoruOS.setDisable(true);
						buttonPoleZoruOS.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(51) == null) {
						buttonAtlasOU.setDisable(false);
					} else {
						buttonAtlasOU.setDisable(true);
						buttonAtlasOU.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(52) == null) {
						buttonMaculaOU.setDisable(false);
					} else {
						buttonMaculaOU.setDisable(true);
						buttonMaculaOU.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(53) == null) {
						buttonHPTOU.setDisable(false);
					} else {
						buttonHPTOU.setDisable(true);
						buttonHPTOU.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(54) == null) {
						buttonFieldViewOU.setDisable(false);
					} else {
						buttonFieldViewOU.setDisable(true);
						buttonFieldViewOU.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
					if (rs.getString(99) == null) {
						buttonHicknessOfCorneaOU.setDisable(false);
					} else {
						buttonHicknessOfCorneaOU.setDisable(true);
						buttonHicknessOfCorneaOU.setStyle("-fx-border-width: 0 0 3 0; -fx-border-color: green");
					}
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				updateOftalmButton.setDisable(true);
				PreparedStatement statement = connectDB(
						"SELECT MAX(idExamination + 1) FROM admin_bd.OphthalmicStatus;");
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					if (rs.getString(1) == null) {
						idExaminationTextField.setText("1");
					} else {
						idExaminationTextField.setText(rs.getString(1));
					}
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			PreparedStatement statement = connectDB("select p.name ,w.name  "
					+ "FROM admin_bd.Patient p inner join admin_bd.Workers w ON p.Workers_id = w.id AND p.id = ?;");
			statement.setInt(1, SearchController.getIdPatientSearch());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				patientTextField.setText(rs.getString(1));
				doctorTextField.setText(rs.getString(2));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dayOfExaminationDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
			if (dayOfExaminationDatePicker.getValue() == null) {
				dayOfExaminationDatePicker.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
			} else {
				dayOfExaminationDatePicker.setStyle("-fx-border-width: 1.2;");
			}
		});

		spesialistTextField.setText(
				LoginController.getSurname() + " " + LoginController.getName() + " " + LoginController.getFathername());
		// -------------------------------------------------------------------------------------------
		// -------------------------------------------------------------------------------------------
		idExaminationTextField.setEditable(false);
		dayOfExaminationDatePicker.setValue(LocalDate.now());
		// -------------------------------
		spesialistTextField.setEditable(false);
		doctorTextField.setEditable(false);
		patientTextField.setEditable(false);

	}
}