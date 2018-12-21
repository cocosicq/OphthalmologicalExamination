package com.main.Controller;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.main.DataBased.ConnectDataBased;
import com.main.View.AppointmentTreatmentView;
import com.main.View.MenuView;
import com.main.View.OphthalmicStatusView;
import com.main.View.ReviewOftalmView;
import com.main.View.ReviewPatientView;
import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SearchController extends ConnectDataBased {
	static ResultSet rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8;
	private ObservableList<Patient> usersData = FXCollections.observableArrayList();
	private ObservableList<Patient> specialistData = FXCollections.observableArrayList();
	private static int idPatientSearch;
	private static String searchTextField;
	private static String searchDatePicker;
	private static String idTreatmentSearch;
	private static String idExaminationSearch;

	public static String getIdTreatmentSearch() {
		return idTreatmentSearch;
	}

	public static void setIdTreatmentSearch(String idTreatmentSearch) {
		SearchController.idTreatmentSearch = idTreatmentSearch;
	}

	public static String getIdExaminationSearch() {
		return idExaminationSearch;
	}

	public static void setIdExaminationSearch(String idExaminationSearch) {
		SearchController.idExaminationSearch = idExaminationSearch;
	}

	@FXML
	private Button searchButton;

	@FXML
	private TableView<Patient> infoTableView;

	@FXML
	private TableColumn<Patient, Integer> idTableColumn;

	@FXML
	private TableColumn<Patient, String> nameTableColumn;

	@FXML
	private TableColumn<Patient, String> specialistTableColumn;

	@FXML
	private TableColumn<Patient, String> dateTableColumn;

	@FXML
	private TableColumn<Patient, String> dateTreatmentTableColumn;

	@FXML
	private TableColumn<Patient, String> idTreatmentTableColumn;

	@FXML
	private TableColumn<Patient, String> idExaminationTableColumn;

	@FXML
	private TableView<Patient> infoSpecialistTableView;

	@FXML
	private TableColumn<Patient, Integer> idSpecialistTableColumn;

	@FXML
	private TableColumn<Patient, String> idPatientSpecialistTableColumn;

	@FXML
	private TableColumn<Patient, String> nameSpecialistTableColumn;

	@FXML
	private TableColumn<Patient, String> dateExaminationSpecialistTableColumn;

	@FXML
	private TextField nameTextField;

	@FXML
	private DatePicker dobDatePicker;

	@FXML
	private TextField idPatientTextField;

	@FXML
	private TextField nameSpecialistTextField;

	@FXML
	private DatePicker dateExaminationDatePicker;

	@FXML
	private TextField idSpecialistTextField;

	public static int getIdPatientSearch() {
		return idPatientSearch;
	}

	public void setIdPatientSearch(int idPatientSearch) {
		this.idPatientSearch = idPatientSearch;
	}

	public void setSearchTextField(String searchTextField) {
		SearchController.searchTextField = searchTextField;
	}

	public String getSearchTextField() {
		return searchTextField;
	}

	public String getSearchDatePicker() {
		return searchDatePicker;
	}

	public void setSearchDatePicker(String searchDatePicker) {
		SearchController.searchDatePicker = searchDatePicker;
	}

	@FXML
	void searchButtonAction(ActionEvent event) {
		OphthalmicStatusPatientController.setSearchTextField(null);
		OphthalmicStatusPatientController.setSearchDatePicker(null);
		AppointmentTreatmentController.setSearchTextField(null);
		AppointmentTreatmentController.setSearchDatePicker(null);
		// Створення форми і сцени
		MenuView menuView = new MenuView();
		Stage stageMainAnchorPane = menuView.starts(new Stage());
		stageMainAnchorPane.show();
		// Закриття вікна
		Stage stage = (Stage) searchButton.getScene().getWindow();
		stage.close();
	}

	@SuppressWarnings("restriction")
	@FXML
	void initialize() {
		nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			setSearchTextField(newValue);
			try {
				usersData.clear();
				if (LoginController.getAccessTypeDB().contains("2") && newValue.equals("") == false && newValue.length() > 2) {
					PreparedStatement statement = connectDB(
							"SELECT p.id, p.name, w.name, o.DateOfSurvey, t.dateOfTreatment, t.id , o.idExamination  \n"
									+ "From admin_bd.OphthalmicStatus o left join  admin_bd.Patient p ON o.idPatient = p.id \n"
									+ "inner join admin_bd.Workers w On o.idWorkers = w.id \n"
									+ "left join admin_bd.Treatment t On o.idExamination = t.OphthalmicStatus_idExamination \n"
									+ "where concat(p.name) LIKE (select pi.name from  admin_bd.Patient pi inner join  \n"
									+ "admin_bd.Workers wi ON pi.Workers_id = wi.id where concat(pi.name) LIKE ? and wi.id = ?)\n"
									+ "or concat(p.id) LIKE (select pi.id from  admin_bd.Patient pi inner join  \n"
									+ "admin_bd.Workers wi ON pi.Workers_id = wi.id where concat(pi.id) LIKE ? and wi.id = ?);");

					if (newValue.equals("") || newValue.equals(" ")) {
						statement.setString(1, "-");
					} else {
						if (newValue.matches("\\d+")) {
							statement.setString(1, "");
							statement.setInt(2, 0);
							statement.setInt(3, Integer.parseInt(newValue));
							statement.setInt(4, Integer.parseInt(LoginController.getIdWorker()));
						} else {
							statement.setString(1, "%" + newValue + "%");
							statement.setInt(2, Integer.parseInt(LoginController.getIdWorker()));
							statement.setInt(3, 0);
							statement.setInt(4, 0);
						}
					}
					rs1 = statement.executeQuery();
					while (rs1.next()) {
						usersData
								.add(new Patient(Integer.parseInt(rs1.getString(1)), rs1.getString(2), rs1.getString(3),
										rs1.getString(7), rs1.getString(4), rs1.getString(6), rs1.getString(5)));
					}

					if (rs1.first() == false && LoginController.getAccessTypeDB().contains("5") == false
							&& LoginController.getAccessTypeDB().contains("3") == false
							&& LoginController.getAccessTypeDB().contains("1") == false) {
						usersData.clear();
					}
					rs1.close();
					statement.close();
				}
				if (LoginController.getAccessTypeDB().contains("3") && newValue.equals("") == false && newValue.length() > 2) {
					PreparedStatement statementS = connectDB(
							"SELECT id, name From admin_bd.Patient where concat(name) LIKE ? or concat(id) LIKE ?");

					if (newValue.equals("") || newValue.equals(" ")) {
						statementS.setString(1, "-");
						statementS.setInt(2, 0);
					} else {
						if (newValue.matches("\\d+")) {
							statementS.setString(1, "");
							statementS.setInt(2, Integer.parseInt(newValue));
						} else {
							statementS.setString(1, "%" + newValue + "%");
							statementS.setInt(2, 0);
						}
					}
					rs2 = statementS.executeQuery();
					while (rs2.next()) {
						usersData.add(
								new Patient(Integer.parseInt(rs2.getString(1)), rs2.getString(2), "", "", "", "", ""));
					}

					PreparedStatement statement = connectDB(
							"SELECT p.id, p.name, w.name, o.DateOfSurvey, o.idExamination \n"
									+ "From admin_bd.OphthalmicStatus o left join  admin_bd.Patient p ON o.idPatient = p.id \n"
									+ "inner join admin_bd.Workers w On o.idWorkers = w.id \n"
									+ "where concat(p.name) LIKE ? or concat(p.id) LIKE ?;");

					if (newValue.equals("") || newValue.equals(" ")) {
						statement.setString(1, "-");
						statement.setInt(2, 0);
					} else {
						if (newValue.matches("\\d+")) {
							statement.setString(1, "");
							statement.setInt(2, Integer.parseInt(newValue));
						} else {
							statement.setString(1, "%" + newValue + "%");
							statement.setInt(2, 0);
						}
					}
					rs3 = statement.executeQuery();
					while (rs3.next()) {
						usersData.add(new Patient(Integer.parseInt(rs3.getString(1)), rs3.getString(2),
								rs3.getString(3), rs3.getString(5), rs3.getString(4), "", ""));
					}
					if (rs2.first() == false && rs3.first() == false
							&& LoginController.getAccessTypeDB().contains("5") == false
							&& LoginController.getAccessTypeDB().contains("2") == false
							&& LoginController.getAccessTypeDB().contains("1") == false) {
						usersData.clear();
					}

					if (rs3.first() == false && rs2.first() == false
							&& LoginController.getAccessTypeDB().contains("5") == false
							&& LoginController.getAccessTypeDB().contains("2") == false
							&& LoginController.getAccessTypeDB().contains("1") == false) {
						usersData.clear();
					}

					rs2.close();
					rs3.close();
					statement.close();
					statementS.close();
				}

				if ((LoginController.getAccessTypeDB().contains("5") || LoginController.getAccessTypeDB().contains("1"))
						&& newValue.equals("") == false && newValue.length() > 2) {
					PreparedStatement statement = connectDB(
							"SELECT p.id, p.name, w.name, o.DateOfSurvey, t.dateOfTreatment, t.id, o.idExamination \n"
									+ "From admin_bd.OphthalmicStatus o left join  admin_bd.Patient p ON o.idPatient = p.id  \n"
									+ "inner join admin_bd.Workers w On o.idWorkers = w.id\n"
									+ "inner join admin_bd.Treatment t On o.idExamination = t.OphthalmicStatus_idExamination\n"
									+ "where concat(p.name) LIKE ? or concat(p.id) LIKE ?");

					if (newValue.equals("") || newValue.equals(" ")) {
						statement.setString(1, "-");
					} else {
						if (newValue.matches("\\d+")) {
							statement.setString(1, "");
							statement.setInt(2, Integer.parseInt(newValue));
						} else {
							statement.setString(1, "%" + newValue + "%");
							statement.setInt(2, 0);
						}
					}

					rs4 = statement.executeQuery();
					while (rs4.next()) {
						usersData
								.add(new Patient(Integer.parseInt(rs4.getString(1)), rs4.getString(2), rs4.getString(3),
										rs4.getString(7), rs4.getString(4), rs4.getString(6), rs4.getString(5)));
					}
					if (rs4.first() == false && LoginController.getAccessTypeDB().contains("3") == false
							&& LoginController.getAccessTypeDB().contains("2") == false) {
						usersData.clear();
					}
					rs4.close();
					statement.close();
				}
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Помилка");
				alert.setHeaderText("Помилка при роботі з базою даних");
				alert.setContentText(e.toString());
				alert.showAndWait();
			}
			if (usersData.size() > 0) {
				for (int i = 0; i < usersData.size(); i++) {
					for (int j = 0; j < usersData.size(); j++) {
						if (usersData.get(i).getIdExamination().equals(usersData.get(j).getIdExamination())
								&& usersData.get(i).getIdExamination().equals("") == false && i != j) {
							if (usersData.get(i).getIdTreatment().equals("")) {
								usersData.remove(i);
								i--;
							}
						}
					}
				}
			}

		});

		infoTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			try {
				setIdTreatmentSearch(newSelection.getIdTreatment());
				setIdPatientSearch(newSelection.getId());
				setIdExaminationSearch(newSelection.getIdExamination());
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Помилка");
				alert.setHeaderText("Помилка з'єднання");
				alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
				alert.showAndWait();
			}
		});

		infoTableView.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {

			if (LoginController.getAccessTypeDB().contains("2")) {
				if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY) {
					AppointmentTreatmentView appointmentTreatmentView = new AppointmentTreatmentView();
					Stage stageMainAnchorPane = appointmentTreatmentView.starts(new Stage());
					stageMainAnchorPane.show();
					// Закриття вікна
					Stage stage = (Stage) searchButton.getScene().getWindow();
					stage.close();
				}
				if (e.getButton() == MouseButton.SECONDARY) {
					ReviewOftalmView reviewOftalmView = new ReviewOftalmView();
					Stage stageMainAnchorPane = reviewOftalmView.starts(new Stage());
					stageMainAnchorPane.show();
					// Закриття вікна
					Stage stage = (Stage) searchButton.getScene().getWindow();
					stage.close();
				}
			}
			if (LoginController.getAccessTypeDB().contains("3")) {
				if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY) {
					OphthalmicStatusView ophthalmicStatusView = new OphthalmicStatusView();
					Stage stageMainAnchorPane = ophthalmicStatusView.starts(new Stage());
					stageMainAnchorPane.show();
					// Закриття вікна
					Stage stage = (Stage) searchButton.getScene().getWindow();
					stage.close();
				}
			}
			if ((LoginController.getAccessTypeDB().contains("5") || LoginController.getAccessTypeDB().contains("1"))
					&& SearchController.getIdExaminationSearch().equals("") == false) {
				if (e.getButton() == MouseButton.SECONDARY) {
					ReviewOftalmView reviewOftalmView = new ReviewOftalmView();
					Stage stageMainAnchorPane = reviewOftalmView.starts(new Stage());
					stageMainAnchorPane.show();
					// Закриття вікна
					Stage stage = (Stage) searchButton.getScene().getWindow();
					stage.close();
				}
			}
			if (e.getButton() == MouseButton.MIDDLE) {
				ReviewPatientView reviewPatientView = new ReviewPatientView();
				Stage stageMainAnchorPane = reviewPatientView.starts(new Stage());
				stageMainAnchorPane.show();
			}

		});

		dobDatePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
			setSearchDatePicker(newValue.toString());
			try {
				usersData.clear();
				if (LoginController.getAccessTypeDB().contains("2") && newValue.equals("") == false ) {
					PreparedStatement statement = connectDB(
							"SELECT p.id, p.name, w.name, o.DateOfSurvey, t.dateOfTreatment, t.id , o.idExamination  \n"
									+ "From admin_bd.OphthalmicStatus o left join  admin_bd.Patient p ON o.idPatient = p.id \n"
									+ "inner join admin_bd.Workers w On o.idWorkers = w.id \n"
									+ "left join admin_bd.Treatment t On o.idExamination = t.OphthalmicStatus_idExamination \n"
									+ "where concat(p.dateOfBirth) LIKE (select pi.dateOfBirth from  admin_bd.Patient pi inner join  \n"
									+ "admin_bd.Workers wi ON pi.Workers_id = wi.id where concat(pi.dateOfBirth) LIKE ? and wi.id = ?);");
					statement.setString(1, dobDatePicker.getValue().toString());
					statement.setInt(2, Integer.parseInt(LoginController.getIdWorker()));
					rs5 = statement.executeQuery();
					while (rs5.next()) {
						usersData
								.add(new Patient(Integer.parseInt(rs5.getString(1)), rs5.getString(2), rs5.getString(3),
										rs5.getString(7), rs5.getString(4), rs5.getString(6), rs5.getString(5)));
					}

					if (rs5.first() == false && LoginController.getAccessTypeDB().contains("5") == false
							&& LoginController.getAccessTypeDB().contains("3") == false
							&& LoginController.getAccessTypeDB().contains("1") == false) {
						usersData.clear();
					}
					rs5.close();
					statement.close();
				}
				if (LoginController.getAccessTypeDB().contains("3") && newValue.equals("") == false)  {
					PreparedStatement statementS = connectDB(
							"SELECT id, name " + " From  admin_bd.Patient  where concat(dateOfBirth) LIKE ?;");
					statementS.setString(1, dobDatePicker.getValue().toString());
					rs6 = statementS.executeQuery();
					while (rs6.next()) {
						usersData.add(
								new Patient(Integer.parseInt(rs6.getString(1)), rs6.getString(2), "", "", "", "", ""));
					}
					
					PreparedStatement statement = connectDB(
							"SELECT p.id, p.name, w.name, o.DateOfSurvey, o.idExamination \n"
									+ "From admin_bd.OphthalmicStatus o left join  admin_bd.Patient p ON o.idPatient = p.id \n"
									+ "inner join admin_bd.Workers w On o.idWorkers = w.id \n"
									+ "where concat(p.dateOfBirth) LIKE ?;");
					statement.setString(1, dobDatePicker.getValue().toString());
					
					rs7 = statement.executeQuery();
					while (rs7.next()) {
						usersData.add(new Patient(Integer.parseInt(rs7.getString(1)), rs7.getString(2),
								rs7.getString(3), rs7.getString(5), rs7.getString(4), "", ""));
					}
					if (rs6.first() == false && rs7.first() == false
							&& LoginController.getAccessTypeDB().contains("5") == false
							&& LoginController.getAccessTypeDB().contains("2") == false
							&& LoginController.getAccessTypeDB().contains("1") == false) {
						usersData.clear();
					}

					if (rs7.first() == false && rs6.first() == false
							&& LoginController.getAccessTypeDB().contains("5") == false
							&& LoginController.getAccessTypeDB().contains("2") == false
							&& LoginController.getAccessTypeDB().contains("1") == false) {
						usersData.clear();
					}

					rs6.close();
					rs7.close();
					statement.close();
					statementS.close();
					
				}
				if ((LoginController.getAccessTypeDB().contains("5") || LoginController.getAccessTypeDB().contains("1"))
						&& newValue.equals("") == false) {
					PreparedStatement statement = connectDB(
							"SELECT p.id, p.name, w.name, o.DateOfSurvey, t.dateOfTreatment,t.id , o.idExamination \n"
									+ "From admin_bd.OphthalmicStatus o left join  admin_bd.Patient p ON o.idPatient = p.id  \n"
									+ "inner join admin_bd.Workers w On o.idWorkers = w.id\n"
									+ "inner join admin_bd.Treatment t On o.idExamination = t.OphthalmicStatus_idExamination\n"
									+ "where concat(p.dateOfBirth) LIKE ?");
					statement.setString(1, dobDatePicker.getValue().toString());
					rs7 = statement.executeQuery();
					while (rs7.next()) {
						usersData.add(new Patient(Integer.parseInt(rs7.getString(1)), rs7.getString(2), rs7.getString(3),
								rs7.getString(7), rs7.getString(4), rs7.getString(6), rs7.getString(5)));
					}
					
					if (rs7.first() == false && LoginController.getAccessTypeDB().contains("3") == false
							&& LoginController.getAccessTypeDB().contains("2") == false) {
						usersData.clear();
					}
					rs7.close();
					statement.close();
				}
				
			} catch (Exception e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Помилка");
				alert.setHeaderText("Помилка з'єднання");
				alert.setContentText("Неможливо з'єднатись з базою даних! \nПеревірте  підключеня до інтернету");
				alert.showAndWait();
			}
			
			if (usersData.size() > 0) {
				for (int i = 0; i < usersData.size(); i++) {
					for (int j = 0; j < usersData.size(); j++) {
						if (usersData.get(i).getIdExamination().equals(usersData.get(j).getIdExamination())
								&& usersData.get(i).getIdExamination().equals("") == false && i != j) {
							if (usersData.get(i).getIdTreatment().equals("")) {
								usersData.remove(i);
								i--;
							}
						}
					}
				}
			}
		});

		if (OphthalmicStatusPatientController.getSearchTextField() != null) {
			nameTextField.setText(OphthalmicStatusPatientController.getSearchTextField());
		}
		if (OphthalmicStatusPatientController.getSearchDatePicker() != null) {
			dobDatePicker.setValue(LocalDate.parse(OphthalmicStatusPatientController.getSearchDatePicker()));
		}
		if (AppointmentTreatmentController.getSearchTextField() != null) {
			nameTextField.setText(AppointmentTreatmentController.getSearchTextField());
		}
		if (AppointmentTreatmentController.getSearchDatePicker() != null) {
			dobDatePicker.setValue(LocalDate.parse(AppointmentTreatmentController.getSearchDatePicker()));
		}
		if (ReviewOftalmController.getSearchTextField() != null) {
			nameTextField.setText(ReviewOftalmController.getSearchTextField());
		}
		if (ReviewOftalmController.getSearchDatePicker() != null) {
			dobDatePicker.setValue(LocalDate.parse(ReviewOftalmController.getSearchDatePicker()));
		}

		dobDatePicker.setPromptText("dd.MM.YYYY");
		idTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
		nameTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
		specialistTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("specialist"));
		dateTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("dateExamination"));
		idTreatmentTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("idTreatment"));
		dateTreatmentTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("dateTreatment"));
		idExaminationTableColumn.setCellValueFactory(new PropertyValueFactory<Patient, String>("idExamination"));

		infoTableView.setItems(usersData);
	}
}