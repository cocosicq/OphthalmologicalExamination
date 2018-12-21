package com.main.Controller;

import javafx.scene.control.TextArea;
import java.io.File;
import java.io.FileInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.event.ChangeListener;

import com.main.DataBased.ConnectDataBased;
import com.main.View.MenuView;
import com.main.View.SearchView;
import com.mysql.jdbc.PreparedStatement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class AppointmentTreatmentController extends ConnectDataBased {
	int idWorkers = 0;
	private static String name = null;
	private static String date = null;
	private static PreparedStatement statement = null;

	@FXML
	private DatePicker dateTreatmentDatePicker;

	@FXML
	private TextField surveyIDTextField, specialistIDTextField, idTreatmentTextField;

	@FXML
	private Button exitButton, saveTreatmentButton, updateTreatmentButton;

	@FXML
	private CheckBox myoticsCheckBox, betaBlockersCheckBox, prostaglandinsCheckBox, sinusothrabuculotomyCheckBox,
			sinusotrabuculotomyWithBasaliridectomyCheckBox, rearSclerectomyCheckBox, trabeculotomyCheckBox,
			iridectomyCheckBox, applicationOflaserTreatmentCheckBox, cyclodegradationCheckBox, physiotherapyCheckBox,
			bbInjectionCheckBox, oralInjectionsCheckBox, pbInjectionCheckBox, bmInjectionCheckBox;

	@FXML
	private TextArea myoticsTextArea, betaBlockersTextArea, prostaglandinsTextArea, cyclodegradationTextArea,
			applicationOflaserTreatmentTextArea, iridectomyTextArea, trabeculotomyTextArea, rearSclerectomyTextArea,
			sinusotrabuculotomyWithBasaliridectomyTextArea, sinusothrabuculotomyTextArea, physiotherapyTextArea,
			bbInjectionTextArea, oralInjectionsTextArea, pbInjectionTextArea, bmInjectionTextArea, infoTextArea;

	public static void setSearchTextField(String searchTextField) {
		name = searchTextField;
	}

	public static String getSearchTextField() {
		return name;
	}

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

	public void addCheckBoxInDB(String nameColomn, int result) throws SQLException {
		try {
			statement = connectDB("Update admin_bd.Treatment Set " + nameColomn + " = ? Where id = ?;");
			statement.setInt(1, result);
			statement.setInt(2, Integer.parseInt(idTreatmentTextField.getText()));
			statement.executeUpdate();
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Помилка при роботі з базою даних");
			alert.setContentText("Помилка при роботі з базою даних! \n" + e.toString());
			alert.showAndWait();
		} finally {
			statement.close();
		}
	}

	public void addTextAreaInDb(String nameColomn, String text) throws SQLException {
		try {
			statement = connectDB("Update admin_bd.Treatment Set " + nameColomn + " = ? Where id = ?;");
			statement.setString(1, text);
			statement.setInt(2, Integer.parseInt(idTreatmentTextField.getText()));
			statement.executeUpdate();
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Помилка при роботі з базою даних");
			alert.setContentText("Помилка при роботі з базою даних! \n" + e.toString());
			alert.showAndWait();
		} finally {
			statement.close();
		}
	}

	@FXML
	void saveTreatmentButtonAction(ActionEvent event) throws SQLException {
		if (dateTreatmentDatePicker.getValue() != null) {
			try {
				statement = connectDB("insert into admin_bd.Treatment (id,OphthalmicStatus_idExamination,"
						+ "OphthalmicStatus_idPatient,OphthalmicStatus_idWorkers,dateOfTreatment) value (?,?,?,?,?);");
				statement.setInt(1, Integer.parseInt(idTreatmentTextField.getText()));
				statement.setInt(2, Integer.parseInt(surveyIDTextField.getText()));
				statement.setInt(3, SearchController.getIdPatientSearch());
				statement.setInt(4, idWorkers);
				statement.setDate(5, java.sql.Date.valueOf(dateTreatmentDatePicker.getValue()));
				statement.executeUpdate();
				statement.close();
				if (myoticsCheckBox.isSelected() == true) {
					addCheckBoxInDB("myotics", 1);
					if (myoticsTextArea.getText().equals("") == false) {
						addTextAreaInDb("myoticsText", myoticsTextArea.getText());
					}
				} else {
					addCheckBoxInDB("myotics", 0);
				}

				if (betaBlockersCheckBox.isSelected() == true) {
					addCheckBoxInDB("betaBlockers", 1);
					if (betaBlockersTextArea.getText().equals("") == false) {
						addTextAreaInDb("betaBlockersText", betaBlockersTextArea.getText());
					}
				} else {
					addCheckBoxInDB("betaBlockers", 0);
				}

				if (prostaglandinsCheckBox.isSelected() == true) {
					addCheckBoxInDB("prostaglandins", 1);
					if (prostaglandinsTextArea.getText().equals("") == false) {
						addTextAreaInDb("prostaglandinsText", prostaglandinsTextArea.getText());
					}
				} else {
					addCheckBoxInDB("prostaglandins", 0);
				}

				if (sinusothrabuculotomyCheckBox.isSelected() == true) {
					addCheckBoxInDB("sinusothrabuculotomy", 1);
					if (sinusothrabuculotomyTextArea.getText().equals("") == false) {
						addTextAreaInDb("sinusothrabuculotomyText", sinusothrabuculotomyTextArea.getText());
					}
				} else {
					addCheckBoxInDB("sinusothrabuculotomy", 0);
				}

				if (sinusotrabuculotomyWithBasaliridectomyCheckBox.isSelected() == true) {
					addCheckBoxInDB("sinusotrabuculotomyWithBasaliridectomy", 1);
					if (sinusotrabuculotomyWithBasaliridectomyTextArea.getText().equals("") == false) {
						addTextAreaInDb("sinusotrabuculotomyWithBasaliridectomyText",
								sinusotrabuculotomyWithBasaliridectomyTextArea.getText());
					}
				} else {
					addCheckBoxInDB("sinusotrabuculotomyWithBasaliridectomy", 0);
				}

				if (rearSclerectomyCheckBox.isSelected() == true) {
					addCheckBoxInDB("rearSclerectomy", 1);
					if (rearSclerectomyTextArea.getText().equals("") == false) {
						addTextAreaInDb("rearSclerectomyText", rearSclerectomyTextArea.getText());
					}
				} else {
					addCheckBoxInDB("rearSclerectomy", 0);
				}

				if (trabeculotomyCheckBox.isSelected() == true) {
					addCheckBoxInDB("trabeculotomy", 1);
					if (trabeculotomyTextArea.getText().equals("") == false) {
						addTextAreaInDb("trabeculotomyText", trabeculotomyTextArea.getText());
					}
				} else {
					addCheckBoxInDB("trabeculotomy", 0);
				}

				if (iridectomyCheckBox.isSelected() == true) {
					addCheckBoxInDB("iridectomy", 1);
					if (iridectomyTextArea.getText().equals("") == false) {
						addTextAreaInDb("iridectomyText", iridectomyTextArea.getText());
					}
				} else {
					addCheckBoxInDB("iridectomy", 0);
				}

				if (applicationOflaserTreatmentCheckBox.isSelected() == true) {
					addCheckBoxInDB("applicationOflaserTreatment", 1);
					if (applicationOflaserTreatmentTextArea.getText().equals("") == false) {
						addTextAreaInDb("applicationOflaserTreatmentText",
								applicationOflaserTreatmentTextArea.getText());
					}
				} else {
					addCheckBoxInDB("applicationOflaserTreatment", 0);
				}

				if (cyclodegradationCheckBox.isSelected() == true) {
					addCheckBoxInDB("cyclodegradation", 1);
					if (cyclodegradationTextArea.getText().equals("") == false) {
						addTextAreaInDb("cyclodegradationText", cyclodegradationTextArea.getText());
					}
				} else {
					addCheckBoxInDB("cyclodegradation", 0);
				}

				if (physiotherapyCheckBox.isSelected() == true) {
					addCheckBoxInDB("physiotherapy", 1);
					if (physiotherapyTextArea.getText().equals("") == false) {
						addTextAreaInDb("physiotherapyext", physiotherapyTextArea.getText());
					}
				} else {
					addCheckBoxInDB("physiotherapy", 0);
				}

				if (bbInjectionCheckBox.isSelected() == true) {
					addCheckBoxInDB("bbInjection", 1);
					if (bbInjectionTextArea.getText().equals("") == false) {
						addTextAreaInDb("bbInjectionText", bbInjectionTextArea.getText());
					}
				} else {
					addCheckBoxInDB("bbInjection", 0);
				}

				if (oralInjectionsCheckBox.isSelected() == true) {
					addCheckBoxInDB("oralInjections", 1);
					if (oralInjectionsTextArea.getText().equals("") == false) {
						addTextAreaInDb("oralInjectionsText", oralInjectionsTextArea.getText());
					}
				} else {
					addCheckBoxInDB("oralInjections", 0);
				}

				if (pbInjectionCheckBox.isSelected() == true) {
					addCheckBoxInDB("pbInjection", 1);
					if (pbInjectionTextArea.getText().equals("") == false) {
						addTextAreaInDb("pbInjectionText", pbInjectionTextArea.getText());
					}
				} else {
					addCheckBoxInDB("pbInjection", 0);
				}

				if (bmInjectionCheckBox.isSelected() == true) {
					addCheckBoxInDB("bmInjection", 1);
					if (bmInjectionTextArea.getText().equals("") == false) {
						addTextAreaInDb("bmInjectionText", bmInjectionTextArea.getText());
					}
				} else {
					addCheckBoxInDB("bmInjection", 0);
				}

				if (infoTextArea.getText().equals("") == false) {
					addTextAreaInDb("infoTextArea", infoTextArea.getText());
				}

				Alert alerts = new Alert(Alert.AlertType.INFORMATION);
				alerts.setTitle("Information");
				alerts.setHeaderText("Додано");
				alerts.setContentText("Додане лікування для пацієнта ");
				alerts.showAndWait();
				saveTreatmentButton.setDisable(true);

			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Помилка");
				alert.setHeaderText("Помилка при роботі з базою даних");
				alert.setContentText("Помилка при роботі з базою даних! \n" + e.toString());
				alert.showAndWait();
			} finally {
				statement.close();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Не була введенена дата початку лікування");
			alert.setContentText("Перегляньте чи була введенена дата початку лікування\n"
					+ "для пацієнта (поле виділені червоним кольором)");
			alert.showAndWait();
			dateTreatmentDatePicker.setStyle("-fx-border-width: 1.2; -fx-border-color: red");
		}
	}

	@FXML
	void updateTreatmentButtonAction(ActionEvent event) throws SQLException {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		try {
			if (myoticsCheckBox.isSelected() == true) {
				addCheckBoxInDB("myotics", 1);
				if (myoticsTextArea.getText().equals("") == false) {
					addTextAreaInDb("myoticsText", myoticsTextArea.getText());
				}
			} else {
				addCheckBoxInDB("myotics", 0);
			}

			if (betaBlockersCheckBox.isSelected() == true) {
				addCheckBoxInDB("betaBlockers", 1);
				if (betaBlockersTextArea.getText().equals("") == false) {
					addTextAreaInDb("betaBlockersText", betaBlockersTextArea.getText());
				}
			} else {
				addCheckBoxInDB("betaBlockers", 0);
			}

			if (prostaglandinsCheckBox.isSelected() == true) {
				addCheckBoxInDB("prostaglandins", 1);
				if (prostaglandinsTextArea.getText().equals("") == false) {
					addTextAreaInDb("prostaglandinsText", prostaglandinsTextArea.getText());
				}
			} else {
				addCheckBoxInDB("prostaglandins", 0);
			}

			if (sinusothrabuculotomyCheckBox.isSelected() == true) {
				addCheckBoxInDB("sinusothrabuculotomy", 1);
				if (sinusothrabuculotomyTextArea.getText().equals("") == false) {
					addTextAreaInDb("sinusothrabuculotomyText", sinusothrabuculotomyTextArea.getText());
				}
			} else {
				addCheckBoxInDB("sinusothrabuculotomy", 0);
			}

			if (sinusotrabuculotomyWithBasaliridectomyCheckBox.isSelected() == true) {
				addCheckBoxInDB("sinusotrabuculotomyWithBasaliridectomy", 1);
				if (sinusotrabuculotomyWithBasaliridectomyTextArea.getText().equals("") == false) {
					addTextAreaInDb("sinusotrabuculotomyWithBasaliridectomyText",
							sinusotrabuculotomyWithBasaliridectomyTextArea.getText());
				}
			} else {
				addCheckBoxInDB("sinusotrabuculotomyWithBasaliridectomy", 0);
			}

			if (rearSclerectomyCheckBox.isSelected() == true) {
				addCheckBoxInDB("rearSclerectomy", 1);
				if (rearSclerectomyTextArea.getText().equals("") == false) {
					addTextAreaInDb("rearSclerectomyText", rearSclerectomyTextArea.getText());
				}
			} else {
				addCheckBoxInDB("rearSclerectomy", 0);
			}

			if (trabeculotomyCheckBox.isSelected() == true) {
				addCheckBoxInDB("trabeculotomy", 1);
				if (trabeculotomyTextArea.getText().equals("") == false) {
					addTextAreaInDb("trabeculotomyText", trabeculotomyTextArea.getText());
				}
			} else {
				addCheckBoxInDB("trabeculotomy", 0);
			}

			if (iridectomyCheckBox.isSelected() == true) {
				addCheckBoxInDB("iridectomy", 1);
				if (iridectomyTextArea.getText().equals("") == false) {
					addTextAreaInDb("iridectomyText", iridectomyTextArea.getText());
				}
			} else {
				addCheckBoxInDB("iridectomy", 0);
			}

			if (applicationOflaserTreatmentCheckBox.isSelected() == true) {
				addCheckBoxInDB("applicationOflaserTreatment", 1);
				if (applicationOflaserTreatmentTextArea.getText().equals("") == false) {
					addTextAreaInDb("applicationOflaserTreatmentText", applicationOflaserTreatmentTextArea.getText());
				}
			} else {
				addCheckBoxInDB("applicationOflaserTreatment", 0);
			}

			if (cyclodegradationCheckBox.isSelected() == true) {
				addCheckBoxInDB("cyclodegradation", 1);
				if (cyclodegradationTextArea.getText().equals("") == false) {
					addTextAreaInDb("cyclodegradationText", cyclodegradationTextArea.getText());
				}
			} else {
				addCheckBoxInDB("cyclodegradation", 0);
			}

			if (physiotherapyCheckBox.isSelected() == true) {
				addCheckBoxInDB("physiotherapy", 1);
				if (physiotherapyTextArea.getText().equals("") == false) {
					addTextAreaInDb("physiotherapyext", physiotherapyTextArea.getText());
				}
			} else {
				addCheckBoxInDB("physiotherapy", 0);
			}

			if (bbInjectionCheckBox.isSelected() == true) {
				addCheckBoxInDB("bbInjection", 1);
				if (bbInjectionTextArea.getText().equals("") == false) {
					addTextAreaInDb("bbInjectionText", bbInjectionTextArea.getText());
				}
			} else {
				addCheckBoxInDB("bbInjection", 0);
			}

			if (oralInjectionsCheckBox.isSelected() == true) {
				addCheckBoxInDB("oralInjections", 1);
				if (oralInjectionsTextArea.getText().equals("") == false) {
					addTextAreaInDb("oralInjectionsText", oralInjectionsTextArea.getText());
				}
			} else {
				addCheckBoxInDB("oralInjections", 0);
			}

			if (pbInjectionCheckBox.isSelected() == true) {
				addCheckBoxInDB("pbInjection", 1);
				if (pbInjectionTextArea.getText().equals("") == false) {
					addTextAreaInDb("pbInjectionText", pbInjectionTextArea.getText());
				}
			} else {
				addCheckBoxInDB("pbInjection", 0);
			}

			if (bmInjectionCheckBox.isSelected() == true) {
				addCheckBoxInDB("bmInjection", 1);
				if (bmInjectionTextArea.getText().equals("") == false) {
					addTextAreaInDb("bmInjectionText", bmInjectionTextArea.getText());
				}
			} else {
				addCheckBoxInDB("bmInjection", 0);
			}
			if (infoTextArea.getText().equals("") == false) {
				addTextAreaInDb("infoTextArea", infoTextArea.getText());
			}
			alert.setTitle("Information");
			alert.setHeaderText("Оновленння!");
			alert.setContentText("Оновлене лікування для пацінта !");
			alert.showAndWait();
		} catch (Exception e) {
			alert.setTitle("Помилка");
			alert.setHeaderText("Помилка при роботі з базою даних");
			alert.setContentText("Помилка при роботі з базою даних! \n" + e.toString());
			alert.showAndWait();
		}
	}

	@FXML
	void initialize() throws SQLException {
		try {
			statement = connectDB("SELECT o.idExamination,o.DateOfSurvey,w.name, o.idWorkers "
					+ "FROM admin_bd.OphthalmicStatus o left join admin_bd.Workers w "
					+ "On o.idWorkers = w.id where o.idPatient = ?;");
			statement.setInt(1, SearchController.getIdPatientSearch());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				idWorkers = Integer.parseInt(rs.getString(4));
				surveyIDTextField.setText(SearchController.getIdExaminationSearch());
				specialistIDTextField.setText(rs.getString(3));
			}
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Помилка з'єднання");
			alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
			alert.showAndWait();
		}finally {
			statement.close();
		}
		try {
			statement = connectDB("SELECT MAX(id + 1) FROM admin_bd.Treatment;");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getString(1) == null) {
					idTreatmentTextField.setText("1");
				} else {
					idTreatmentTextField.setText(rs.getString(1));
				}
			}
			
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Помилка");
			alert.setHeaderText("Помилка з'єднання");
			alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
			alert.showAndWait();
		}finally {
			statement.close();
		}
		
		dateTreatmentDatePicker.setValue(LocalDate.now());

		idTreatmentTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				statement = connectDB("SELECT * FROM admin_bd.Treatment where id = ?;");
				statement.setInt(1, Integer.parseInt(idTreatmentTextField.getText()));
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					dateTreatmentDatePicker.setValue(LocalDate.parse(rs.getString(5)));
					surveyIDTextField.setText(rs.getString(2));

					if (rs.getString(6).equals("1")) {
						myoticsCheckBox.setSelected(true);
						myoticsCheckBox.setDisable(true);
						myoticsCheckBox.setStyle("-fx-opacity: 1");
//						myoticsTextArea.setEditable(false);
					}
					if (rs.getString(7).equals("1")) {
						betaBlockersCheckBox.setSelected(true);
						betaBlockersCheckBox.setDisable(true);
						betaBlockersCheckBox.setStyle("-fx-opacity: 1");
//						betaBlockersTextArea.setEditable(false);
					}
					if (rs.getString(8).equals("1")) {
						prostaglandinsCheckBox.setSelected(true);
						prostaglandinsCheckBox.setDisable(true);
						prostaglandinsCheckBox.setStyle("-fx-opacity: 1");
//						prostaglandinsTextArea.setEditable(false);
					}
					if (rs.getString(9).equals("1")) {
						pbInjectionCheckBox.setSelected(true);
						pbInjectionCheckBox.setDisable(true);
						pbInjectionCheckBox.setStyle("-fx-opacity: 1");
//						pbInjectionTextArea.setEditable(false);
					}
					if (rs.getString(10).equals("1")) {
						bbInjectionCheckBox.setSelected(true);
						bbInjectionCheckBox.setDisable(true);
						bbInjectionCheckBox.setStyle("-fx-opacity: 1");
//						bbInjectionTextArea.setEditable(false);
					}
					if (rs.getString(11).equals("1")) {
						bmInjectionCheckBox.setSelected(true);
						bmInjectionCheckBox.setDisable(true);
						bmInjectionCheckBox.setStyle("-fx-opacity: 1");
//						bmInjectionTextArea.setEditable(false);
					}
					if (rs.getString(12).equals("1")) {
						oralInjectionsCheckBox.setSelected(true);
						oralInjectionsCheckBox.setDisable(true);
						oralInjectionsCheckBox.setStyle("-fx-opacity: 1");
//						oralInjectionsTextArea.setEditable(false);

					}
					if (rs.getString(13).equals("1")) {
						physiotherapyCheckBox.setSelected(true);
						physiotherapyCheckBox.setDisable(true);
						physiotherapyCheckBox.setStyle("-fx-opacity: 1");
//						physiotherapyTextArea.setEditable(false);
					}
					if (rs.getString(14).equals("1")) {
						rearSclerectomyCheckBox.setSelected(true);
						rearSclerectomyCheckBox.setDisable(true);
						rearSclerectomyCheckBox.setStyle("-fx-opacity: 1");
//						rearSclerectomyTextArea.setEditable(false);
					}
					if (rs.getString(15).equals("1")) {
						sinusothrabuculotomyCheckBox.setSelected(true);
						sinusothrabuculotomyCheckBox.setDisable(true);
						sinusothrabuculotomyCheckBox.setStyle("-fx-opacity: 1");
//						sinusothrabuculotomyTextArea.setEditable(false);
					}
					if (rs.getString(16).equals("1")) {
						sinusotrabuculotomyWithBasaliridectomyCheckBox.setSelected(true);
						sinusotrabuculotomyWithBasaliridectomyCheckBox.setDisable(true);
						sinusotrabuculotomyWithBasaliridectomyCheckBox.setStyle("-fx-opacity: 1");
//						sinusotrabuculotomyWithBasaliridectomyTextArea.setEditable(false);
					}
					if (rs.getString(17).equals("1")) {
						applicationOflaserTreatmentCheckBox.setSelected(true);
						applicationOflaserTreatmentCheckBox.setDisable(true);
						applicationOflaserTreatmentCheckBox.setStyle("-fx-opacity: 1");
//						applicationOflaserTreatmentTextArea.setEditable(false);
					}
					if (rs.getString(18).equals("1")) {
						iridectomyCheckBox.setSelected(true);
						iridectomyCheckBox.setDisable(true);
						iridectomyCheckBox.setStyle("-fx-opacity: 1");
//						iridectomyTextArea.setEditable(false);
					}
					if (rs.getString(19).equals("1")) {
						trabeculotomyCheckBox.setSelected(true);
						trabeculotomyCheckBox.setDisable(true);
						trabeculotomyCheckBox.setStyle("-fx-opacity: 1");
//						trabeculotomyTextArea.setEditable(false);
					}
					if (rs.getString(20).equals("1")) {
						cyclodegradationCheckBox.setSelected(true);
						cyclodegradationCheckBox.setDisable(true);
						cyclodegradationCheckBox.setStyle("-fx-opacity: 1");
//						ді cyclodegradationTextArea.setEditable(false);
					}
					if (rs.getString(21) != null) {
						myoticsTextArea.setText(rs.getString(21));
					}
					if (rs.getString(22) != null) {
						betaBlockersTextArea.setText(rs.getString(22));
					}
					if (rs.getString(23) != null) {
						prostaglandinsTextArea.setText(rs.getString(23));
					}
					if (rs.getString(24) != null) {
						pbInjectionTextArea.setText(rs.getString(24));
					}
					if (rs.getString(25) != null) {
						bbInjectionTextArea.setText(rs.getString(25));
					}
					if (rs.getString(26) != null) {
						bmInjectionTextArea.setText(rs.getString(26));
					}
					if (rs.getString(27) != null) {
						oralInjectionsTextArea.setText(rs.getString(27));
					}
					if (rs.getString(28) != null) {
						physiotherapyTextArea.setText(rs.getString(28));
					}
					if (rs.getString(29) != null) {
						rearSclerectomyTextArea.setText(rs.getString(29));
					}
					if (rs.getString(30) != null) {
						sinusothrabuculotomyTextArea.setText(rs.getString(30));
					}
					if (rs.getString(31) != null) {
						sinusotrabuculotomyWithBasaliridectomyTextArea.setText(rs.getString(31));
					}
					if (rs.getString(32) != null) {
						applicationOflaserTreatmentTextArea.setText(rs.getString(32));
					}
					if (rs.getString(33) != null) {
						iridectomyTextArea.setText(rs.getString(33));
					}
					if (rs.getString(34) != null) {
						trabeculotomyTextArea.setText(rs.getString(34));
					}
					if (rs.getString(35) != null) {
						cyclodegradationTextArea.setText(rs.getString(35));
					}
					if (rs.getString(36) != null) {
						infoTextArea.setText(rs.getString(36));
					}

					updateTreatmentButton.setDisable(false);
				}
				if (rs.first() == false) {
					dateTreatmentDatePicker.setValue(null);

					myoticsCheckBox.setSelected(false);
					myoticsTextArea.setText(null);

					betaBlockersCheckBox.setSelected(false);
					betaBlockersTextArea.setText(null);

					prostaglandinsCheckBox.setSelected(false);
					prostaglandinsTextArea.setText(null);

					pbInjectionCheckBox.setSelected(false);
					pbInjectionTextArea.setText(null);

					bbInjectionCheckBox.setSelected(false);
					bbInjectionTextArea.setText(null);

					bmInjectionCheckBox.setSelected(false);
					bmInjectionTextArea.setText(null);

					oralInjectionsCheckBox.setSelected(false);
					oralInjectionsTextArea.setText(null);

					physiotherapyCheckBox.setSelected(false);
					physiotherapyTextArea.setText(null);

					rearSclerectomyCheckBox.setSelected(false);
					rearSclerectomyTextArea.setText(null);

					sinusothrabuculotomyCheckBox.setSelected(false);
					sinusothrabuculotomyTextArea.setText(null);

					sinusotrabuculotomyWithBasaliridectomyCheckBox.setSelected(false);
					sinusotrabuculotomyWithBasaliridectomyTextArea.setText(null);

					applicationOflaserTreatmentCheckBox.setSelected(false);
					applicationOflaserTreatmentTextArea.setText(null);

					iridectomyCheckBox.setSelected(false);
					iridectomyTextArea.setText(null);

					trabeculotomyCheckBox.setSelected(false);
					trabeculotomyTextArea.setText(null);

					cyclodegradationCheckBox.setSelected(false);
					cyclodegradationTextArea.setText(null);

					infoTextArea.setText(null);

					updateTreatmentButton.setDisable(true);
				}
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Помилка");
				alert.setHeaderText("Помилка з'єднання");
				alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
				alert.showAndWait();
			}finally {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		idTreatmentTextField.setEditable(false);
		specialistIDTextField.setEditable(false);
		surveyIDTextField.setEditable(false);

		if (SearchController.getIdTreatmentSearch() != null) {
			dateTreatmentDatePicker.setEditable(false);
			saveTreatmentButton.setDisable(true);
			idTreatmentTextField.setText(SearchController.getIdTreatmentSearch());
		} else {
			updateTreatmentButton.setDisable(true);
		}

		if (LoginController.getAccessTypeDB().contains("5")) {
			dateTreatmentDatePicker.setEditable(false);
			updateTreatmentButton.setDisable(false);
		}
		if (LoginController.getAccessTypeDB().contains("1")) {
			dateTreatmentDatePicker.setEditable(false);
			saveTreatmentButton.setDisable(true);
			updateTreatmentButton.setDisable(true);

			myoticsCheckBox.setDisable(true);
			myoticsCheckBox.setStyle("-fx-opacity: 1");
			myoticsTextArea.setEditable(false);

			betaBlockersCheckBox.setDisable(true);
			betaBlockersCheckBox.setStyle("-fx-opacity: 1");
			betaBlockersTextArea.setEditable(false);

			prostaglandinsCheckBox.setDisable(true);
			prostaglandinsCheckBox.setStyle("-fx-opacity: 1");
			prostaglandinsTextArea.setEditable(false);

			pbInjectionCheckBox.setDisable(true);
			pbInjectionCheckBox.setStyle("-fx-opacity: 1");
			pbInjectionTextArea.setEditable(false);

			bbInjectionCheckBox.setDisable(true);
			bbInjectionCheckBox.setStyle("-fx-opacity: 1");
			bbInjectionTextArea.setEditable(false);

			bmInjectionCheckBox.setDisable(true);
			bmInjectionCheckBox.setStyle("-fx-opacity: 1");
			bmInjectionTextArea.setEditable(false);

			oralInjectionsCheckBox.setDisable(true);
			oralInjectionsCheckBox.setStyle("-fx-opacity: 1");
			oralInjectionsTextArea.setEditable(false);

			physiotherapyCheckBox.setDisable(true);
			physiotherapyCheckBox.setStyle("-fx-opacity: 1");
			physiotherapyTextArea.setEditable(false);

			rearSclerectomyCheckBox.setDisable(true);
			rearSclerectomyCheckBox.setStyle("-fx-opacity: 1");
			rearSclerectomyTextArea.setEditable(false);

			sinusothrabuculotomyCheckBox.setDisable(true);
			sinusothrabuculotomyCheckBox.setStyle("-fx-opacity: 1");
			sinusothrabuculotomyTextArea.setEditable(false);

			sinusotrabuculotomyWithBasaliridectomyCheckBox.setDisable(true);
			sinusotrabuculotomyWithBasaliridectomyCheckBox.setStyle("-fx-opacity: 1");
			sinusotrabuculotomyWithBasaliridectomyTextArea.setEditable(false);

			applicationOflaserTreatmentCheckBox.setDisable(true);
			applicationOflaserTreatmentCheckBox.setStyle("-fx-opacity: 1");
			applicationOflaserTreatmentTextArea.setEditable(false);

			iridectomyCheckBox.setDisable(true);
			iridectomyCheckBox.setStyle("-fx-opacity: 1");
			iridectomyTextArea.setEditable(false);

			trabeculotomyCheckBox.setDisable(true);
			trabeculotomyCheckBox.setStyle("-fx-opacity: 1");
			trabeculotomyTextArea.setEditable(false);

			cyclodegradationCheckBox.setDisable(true);
			cyclodegradationCheckBox.setStyle("-fx-opacity: 1");
			cyclodegradationTextArea.setEditable(false);
		}

		if (myoticsCheckBox.isSelected() == true) {
			myoticsTextArea.setDisable(false);
		} else {
			myoticsTextArea.setDisable(true);
		}
		myoticsCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (myoticsCheckBox.isSelected() == true) {
				myoticsTextArea.setDisable(false);
			} else {
				myoticsTextArea.setDisable(true);
			}
		});
		if (betaBlockersCheckBox.isSelected() == true) {
			betaBlockersTextArea.setDisable(false);
		} else {
			betaBlockersTextArea.setDisable(true);
		}
		betaBlockersCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (betaBlockersCheckBox.isSelected() == true) {
				betaBlockersTextArea.setDisable(false);
			} else {
				betaBlockersTextArea.setDisable(true);
			}
		});
		if (prostaglandinsCheckBox.isSelected() == true) {
			prostaglandinsTextArea.setDisable(false);
		} else {
			prostaglandinsTextArea.setDisable(true);
		}
		prostaglandinsCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (prostaglandinsCheckBox.isSelected() == true) {
				prostaglandinsTextArea.setDisable(false);
			} else {
				prostaglandinsTextArea.setDisable(true);
			}
		});
		if (pbInjectionCheckBox.isSelected() == true) {
			pbInjectionTextArea.setDisable(false);
		} else {
			pbInjectionTextArea.setDisable(true);
		}
		pbInjectionCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (pbInjectionCheckBox.isSelected() == true) {
				pbInjectionTextArea.setDisable(false);
			} else {
				pbInjectionTextArea.setDisable(true);
			}
		});
		if (bbInjectionCheckBox.isSelected() == true) {
			bbInjectionTextArea.setDisable(false);
		} else {
			bbInjectionTextArea.setDisable(true);
		}
		bbInjectionCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (bbInjectionCheckBox.isSelected() == true) {
				bbInjectionTextArea.setDisable(false);
			} else {
				bbInjectionTextArea.setDisable(true);
			}
		});
		if (bmInjectionCheckBox.isSelected() == true) {
			bmInjectionTextArea.setDisable(false);
		} else {
			bmInjectionTextArea.setDisable(true);
		}
		bmInjectionCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (bmInjectionCheckBox.isSelected() == true) {
				bmInjectionTextArea.setDisable(false);
			} else {
				bmInjectionTextArea.setDisable(true);
			}
		});
		if (oralInjectionsCheckBox.isSelected() == true) {
			oralInjectionsTextArea.setDisable(false);
		} else {
			oralInjectionsTextArea.setDisable(true);
		}
		oralInjectionsCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (oralInjectionsCheckBox.isSelected() == true) {
				oralInjectionsTextArea.setDisable(false);
			} else {
				oralInjectionsTextArea.setDisable(true);
			}
		});
		if (physiotherapyCheckBox.isSelected() == true) {
			physiotherapyTextArea.setDisable(false);
		} else {
			physiotherapyTextArea.setDisable(true);
		}
		physiotherapyCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (physiotherapyCheckBox.isSelected() == true) {
				physiotherapyTextArea.setDisable(false);
			} else {
				physiotherapyTextArea.setDisable(true);
			}
		});
		if (rearSclerectomyCheckBox.isSelected() == true) {
			rearSclerectomyTextArea.setDisable(false);
		} else {
			rearSclerectomyTextArea.setDisable(true);
		}
		rearSclerectomyCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (rearSclerectomyCheckBox.isSelected() == true) {
				rearSclerectomyTextArea.setDisable(false);
			} else {
				rearSclerectomyTextArea.setDisable(true);
			}
		});
		if (sinusothrabuculotomyCheckBox.isSelected() == true) {
			sinusothrabuculotomyTextArea.setDisable(false);
		} else {
			sinusothrabuculotomyTextArea.setDisable(true);
		}
		sinusothrabuculotomyCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (sinusothrabuculotomyCheckBox.isSelected() == true) {
				sinusothrabuculotomyTextArea.setDisable(false);
			} else {
				sinusothrabuculotomyTextArea.setDisable(true);
			}
		});
		if (sinusotrabuculotomyWithBasaliridectomyCheckBox.isSelected() == true) {
			sinusotrabuculotomyWithBasaliridectomyTextArea.setDisable(false);
		} else {
			sinusotrabuculotomyWithBasaliridectomyTextArea.setDisable(true);
		}
		sinusotrabuculotomyWithBasaliridectomyCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (sinusotrabuculotomyWithBasaliridectomyCheckBox.isSelected() == true) {
				sinusotrabuculotomyWithBasaliridectomyTextArea.setDisable(false);
			} else {
				sinusotrabuculotomyWithBasaliridectomyTextArea.setDisable(true);
			}
		});
		if (applicationOflaserTreatmentCheckBox.isSelected() == true) {
			applicationOflaserTreatmentTextArea.setDisable(false);
		} else {
			applicationOflaserTreatmentTextArea.setDisable(true);
		}
		applicationOflaserTreatmentCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (applicationOflaserTreatmentCheckBox.isSelected() == true) {
				applicationOflaserTreatmentTextArea.setDisable(false);
			} else {
				applicationOflaserTreatmentTextArea.setDisable(true);
			}
		});
		if (iridectomyCheckBox.isSelected() == true) {
			iridectomyTextArea.setDisable(false);
		} else {
			iridectomyTextArea.setDisable(true);
		}
		iridectomyCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (iridectomyCheckBox.isSelected() == true) {
				iridectomyTextArea.setDisable(false);
			} else {
				iridectomyTextArea.setDisable(true);
			}
		});
		if (trabeculotomyCheckBox.isSelected() == true) {
			trabeculotomyTextArea.setDisable(false);
		} else {
			trabeculotomyTextArea.setDisable(true);
		}
		trabeculotomyCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (trabeculotomyCheckBox.isSelected() == true) {
				trabeculotomyTextArea.setDisable(false);
			} else {
				trabeculotomyTextArea.setDisable(true);
			}
		});
		if (cyclodegradationCheckBox.isSelected() == true) {
			cyclodegradationTextArea.setDisable(false);
		} else {
			cyclodegradationTextArea.setDisable(true);
		}
		cyclodegradationCheckBox.selectedProperty().addListener((o, oldV, newV) -> {
			if (cyclodegradationCheckBox.isSelected() == true) {
				cyclodegradationTextArea.setDisable(false);
			} else {
				cyclodegradationTextArea.setDisable(true);
			}
		});
	}
}
