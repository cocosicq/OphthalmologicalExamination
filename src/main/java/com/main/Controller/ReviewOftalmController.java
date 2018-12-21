package com.main.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.main.DataBased.ConnectDataBased;
import com.main.View.AppointmentTreatmentView;
import com.main.View.MenuView;
import com.main.View.ReviewOftalmView;
import com.main.View.SearchView;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;

public class ReviewOftalmController extends ConnectDataBased {
	@FXML
	private Label labelNameSpecialist, labelDateOftalm, labelNamePatient, labelNameDoctor;

	@FXML
	private Button buttonJustDoIt, exitButton;

	@FXML
	private TextField textFieldVizusOD, textFieldVisusOS, textFieldFosfenOD, textFieldFosfenOS, textFieldBotOD,
			textFieldBotOS;

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
			buttonHicknessOfCorneaOS4,buttonHicknessOfCorneaOU;

	@FXML
	private Button buttonMaculaOd3D1, buttonMaculaOd3D2, buttonMaculaOd3D3, buttonMaculaOd3D4;
	@FXML
	private Button buttonMaculaOs3D2, buttonMaculaOs3D1, buttonMaculaOs3D3, buttonMaculaOs3D4;

	@FXML
	private Button buttonMaculaOD1, buttonMaculaOD2, buttonMaculaOD3, buttonMaculaOD4;
	@FXML
	private Button buttonMaculaOS1, buttonMaculaOS2, buttonMaculaOS3, buttonMaculaOS4;

	@FXML
	private Button buttonMaculaOU;

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
	private Button buttonPoleZoruOS, buttonAtlasOU, buttonFieldViewOU;

	public String getFileAndSave(int indexFile, int idFormat) {
		String filePath = null;
		try {
			PreparedStatement statement = connectDB(
					"SELECT * FROM admin_bd.OphthalmicStatus where idExamination = ? ;");
			statement.setInt(1, Integer.parseInt(SearchController.getIdExaminationSearch()));
			ResultSet rs = statement.executeQuery();
			final int BUFFER_SIZE = 4096;
			while (rs.next()) {
				filePath = "resource\\file" + rs.getString(idFormat);
				InputStream inputStream = rs.getBlob(indexFile).getBinaryStream();
				OutputStream outputStream = new FileOutputStream(filePath);

				int bytesRead = -1;
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				inputStream.close();
				outputStream.close();
			}
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Помилка з'єднання");
			alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
			alert.showAndWait();
		}

		return filePath;
	}

	public void openFileForWatching(String filePath) throws IOException {
		File file = new File(filePath);
		// first check if Desktop is supported by Platform or not
		if (!Desktop.isDesktopSupported()) {
			System.out.println("Desktop is not supported");
			return;
		}

		Desktop desktop = Desktop.getDesktop();
		if (file.exists()) {
			desktop.open(file);
		}
	}

	@FXML
	void buttonHicknessOfCorneaOUAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(99, 100));
	}
	
	@FXML
	void buttonAtlasOUAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(51, 95));
	}

	@FXML
	void buttonMaculaOUAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(52, 96));
	}

	@FXML
	void buttonHPTOUAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(53, 97));
	}

	@FXML
	void buttonFieldViewOUAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(54, 98));
	}

	@FXML
	void buttonHicknessOfCorneaOD1Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(13, 57));
	}

	@FXML
	void buttonHicknessOfCorneaOD2Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(14, 58));
	}

	@FXML
	void buttonHicknessOfCorneaOD3Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(15, 59));
	}

	@FXML
	void buttonHicknessOfCorneaOD4Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(16, 60));
	}

	@FXML
	void buttonHicknessOfCorneaOS1Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(17, 61));
	}

	@FXML
	void buttonHicknessOfCorneaOS2Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(18, 62));
	}

	@FXML
	void buttonHicknessOfCorneaOS3Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(19, 63));
	}

	@FXML
	void buttonHicknessOfCorneaOS4Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(20, 64));
	}

	@FXML
	void buttonHamfriODAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(7, 55));
	}

	@FXML
	void buttonHamfriOSAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(8, 56));
	}

	@FXML
	void buttonAtlasODAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(21, 65));
	}

	@FXML
	void buttonAtlasOSAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(22, 66));
	}

	@FXML
	void buttonCameraODAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(23, 67));
	}

	@FXML
	void buttonCameraOSAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(24, 68));
	}

	@FXML
	void buttonCamera3dODAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(25, 69));
	}

	@FXML
	void buttonCamera3dOSAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(26, 70));
	}

	@FXML
	void buttonCorneeNODAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(27, 71));
	}

	@FXML
	void buttonCorneeNOSAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(28, 72));
	}

	@FXML
	void buttonGDXODAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(29, 73));
	}

	@FXML
	void buttonGDXOSAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(30, 74));
	}

	@FXML
	void buttonHrtODAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(31, 75));
	}

	@FXML
	void buttonHrtOSAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(32, 76));
	}

	@FXML
	void buttonMaculaOD1Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(33, 77));
	}

	@FXML
	void buttonMaculaOD2Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(34, 78));
	}

	@FXML
	void buttonMaculaOD3Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(35, 79));
	}

	@FXML
	void buttonMaculaOD4Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(36, 80));
	}

	@FXML
	void buttonMaculaOS1Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(37, 81));
	}

	@FXML
	void buttonMaculaOS2Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(38, 82));
	}

	@FXML
	void buttonMaculaOS3Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(39, 83));
	}

	@FXML
	void buttonMaculaOS4Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(40, 84));
	}

	@FXML
	void buttonMaculaOd3D1Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(41, 85));
	}

	@FXML
	void buttonMaculaOd3D2Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(42, 86));
	}

	@FXML
	void buttonMaculaOd3D3Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(43, 87));
	}

	@FXML
	void buttonMaculaOd3D4Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(44, 88));
	}

	@FXML
	void buttonMaculaOs3D1Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(45, 89));
	}

	@FXML
	void buttonMaculaOs3D2Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(46, 90));
	}

	@FXML
	void buttonMaculaOs3D3Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(47, 91));
	}

	@FXML
	void buttonMaculaOs3D4Action(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(48, 92));
	}

	@FXML
	void buttonPoleZoruODAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(49, 93));
	}

	@FXML
	void buttonPoleZoruOSAction(ActionEvent event) throws IOException {
		openFileForWatching(getFileAndSave(50, 94));
	}

	@FXML
	void buttonJustDoItAction(ActionEvent event) {
		// Створення форми і сцени
		AppointmentTreatmentView appointmentTreatmentView = new AppointmentTreatmentView();
		Stage stageMainAnchorPane = appointmentTreatmentView.starts(new Stage());
		stageMainAnchorPane.show();
		// Закриття вікна
		Stage stage = (Stage) buttonJustDoIt.getScene().getWindow();
		stage.close();
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
		
		SearchView searchView = new SearchView();
		Stage stageMainAnchorPane = searchView.starts(new Stage());
		stageMainAnchorPane.show();
		try {
			// Закриття вікна
			Stage stage = (Stage) exitButton.getScene().getWindow();
			stage.close();
		}catch (Exception e) {
		}
	}

	@SuppressWarnings("restriction")
	@FXML
	void initialize() {
		buttonJustDoIt.setDisable(true);
		if(LoginController.getIdWorker().contains("2")) {
			buttonJustDoIt.setDisable(false);
		}else {
			if(SearchController.getIdTreatmentSearch() == null) {
				buttonJustDoIt.setDisable(true);
			}else {
				buttonJustDoIt.setDisable(false);
			}
		}
		new File("resource").mkdir();
		if (SearchController.getIdExaminationSearch().equals("")) {
			exitButton.fire();
		} else {
			try {
				PreparedStatement statement = connectDB("SELECT p.name, w.name, o.* \n"
						+ "From admin_bd.OphthalmicStatus o left join  admin_bd.Patient p ON o.idPatient = p.id \n"
						+ "inner join admin_bd.Workers w On o.idWorkers = w.id \n"
						+ "where concat(o.idExamination) LIKE ?;");
				statement.setInt(1, Integer.parseInt(SearchController.getIdExaminationSearch()));
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					labelNamePatient.setText(rs.getString(1));
					labelNameSpecialist.setText(rs.getString(2));
					labelDateOftalm.setText(rs.getString(6));
					textFieldVizusOD.setText(rs.getString(7));
					textFieldVisusOS.setText(rs.getString(8));
					textFieldFosfenOD.setText(rs.getString(11));
					textFieldFosfenOS.setText(rs.getString(12));
					textFieldBotOD.setText(rs.getString(13));
					textFieldBotOS.setText(rs.getString(14));
					if (rs.getString(9) == null) {
						buttonHamfriOD.setDisable(true);
					}
					if (rs.getString(10) == null) {
						buttonHamfriOS.setDisable(true);
					}
					if (rs.getString(15) == null) {
						buttonHicknessOfCorneaOD1.setDisable(true);
					}
					if (rs.getString(16) == null) {
						buttonHicknessOfCorneaOD2.setDisable(true);
					}
					if (rs.getString(17) == null) {
						buttonHicknessOfCorneaOD3.setDisable(true);
					}
					if (rs.getString(18) == null) {
						buttonHicknessOfCorneaOD4.setDisable(true);
					}
					if (rs.getString(19) == null) {
						buttonHicknessOfCorneaOS1.setDisable(true);
					}
					if (rs.getString(20) == null) {
						buttonHicknessOfCorneaOS2.setDisable(true);
					}
					if (rs.getString(21) == null) {
						buttonHicknessOfCorneaOS3.setDisable(true);
					}
					if (rs.getString(22) == null) {
						buttonHicknessOfCorneaOS4.setDisable(true);
					}
					if (rs.getString(23) == null) {
						buttonAtlasOD.setDisable(true);
					}
					if (rs.getString(24) == null) {
						buttonAtlasOS.setDisable(true);
					}
					if (rs.getString(25) == null) {
						buttonCameraOD.setDisable(true);
					}
					if (rs.getString(26) == null) {
						buttonCameraOS.setDisable(true);
					}
					if (rs.getString(27) == null) {
						buttonCamera3dOD.setDisable(true);
					}
					if (rs.getString(28) == null) {
						buttonCamera3dOS.setDisable(true);
					}
					if (rs.getString(29) == null) {
						buttonCorneeNOD.setDisable(true);
					}
					if (rs.getString(30) == null) {
						buttonCorneeNOS.setDisable(true);
					}
					if (rs.getString(31) == null) {
						buttonGDXOD.setDisable(true);
					}
					if (rs.getString(32) == null) {
						buttonGdXOS.setDisable(true);
					}
					if (rs.getString(33) == null) {
						buttonHrtOD.setDisable(true);
					}
					if (rs.getString(34) == null) {
						buttonHrtOS.setDisable(true);
					}
					// -----------------------------
					if (rs.getString(35) == null) {
						buttonMaculaOD1.setDisable(true);
					}
					if (rs.getString(36) == null) {
						buttonMaculaOD2.setDisable(true);
					}
					if (rs.getString(37) == null) {
						buttonMaculaOD3.setDisable(true);
					}
					if (rs.getString(38) == null) {
						buttonMaculaOD4.setDisable(true);
					}
					if (rs.getString(39) == null) {
						buttonMaculaOS1.setDisable(true);
					}
					if (rs.getString(40) == null) {
						buttonMaculaOS2.setDisable(true);
					}
					if (rs.getString(41) == null) {
						buttonMaculaOS3.setDisable(true);
					}
					if (rs.getString(42) == null) {
						buttonMaculaOS4.setDisable(true);
					}
					// -------------------
					if (rs.getString(43) == null) {
						buttonMaculaOd3D1.setDisable(true);
					}
					if (rs.getString(44) == null) {
						buttonMaculaOd3D2.setDisable(true);
					}
					if (rs.getString(45) == null) {
						buttonMaculaOd3D3.setDisable(true);
					}
					if (rs.getString(46) == null) {
						buttonMaculaOd3D4.setDisable(true);
					}
					if (rs.getString(47) == null) {
						buttonMaculaOs3D1.setDisable(true);
					}
					if (rs.getString(48) == null) {
						buttonMaculaOs3D2.setDisable(true);
					}
					if (rs.getString(49) == null) {
						buttonMaculaOs3D3.setDisable(true);
					}
					if (rs.getString(50) == null) {
						buttonMaculaOs3D4.setDisable(true);
					}
					// **************************
					if (rs.getString(51) == null) {
						buttonPoleZoruOD.setDisable(true);
					}
					if (rs.getString(52) == null) {
						buttonPoleZoruOS.setDisable(true);
					}
					if (rs.getString(53) == null) {
						buttonAtlasOU.setDisable(true);
					}
					if (rs.getString(54) == null) {
						buttonMaculaOU.setDisable(true);
					}
					if (rs.getString(55) == null) {
						buttonHPTOU.setDisable(true);
					}
					if (rs.getString(56) == null) {
						buttonFieldViewOU.setDisable(true);
					}
					if (rs.getString(101) == null) {
						buttonHicknessOfCorneaOU.setDisable(true);
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
			try {
				PreparedStatement statement = connectDB(
						"Select w.name From admin_bd.Patient p inner join admin_bd.Workers w "
								+ "On p.Workers_id = w.id Where p.id = ?;");
				statement.setInt(1, SearchController.getIdPatientSearch());
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					labelNameDoctor.setText(rs.getString(1));
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

		textFieldVizusOD.setEditable(false);
		textFieldVisusOS.setEditable(false);
		textFieldFosfenOD.setEditable(false);
		textFieldFosfenOS.setEditable(false);
		textFieldBotOD.setEditable(false);
		textFieldBotOS.setEditable(false);
	}
}
