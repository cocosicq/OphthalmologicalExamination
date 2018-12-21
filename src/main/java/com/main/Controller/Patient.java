package com.main.Controller;

public class Patient {
	private int id;
	private String name;
	private String specialist;
	private String idExamination;
	private String dateExamination;
	private String idTreatment;
	private String dateTreatment;
	
	Patient(int id, String name, String specialist, String idExamination,String dateExamination, String idTreatment, String dateTreatment){
		this.id = id;
		this.name = name;
		this.specialist = specialist;
		this.dateExamination = dateExamination;
		this.idTreatment = idTreatment;
		this.dateTreatment = dateTreatment;
		this.idExamination = idExamination;
	}
	
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", specialist=" + specialist + ", idExamination="
				+ idExamination + ", dateExamination=" + dateExamination + ", idTreatment=" + idTreatment
				+ ", dateTreatment=" + dateTreatment + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialist() {
		return specialist;
	}
	public void setSpecialist(String specialist) {
		this.specialist = specialist;
	}
	public String getIdExamination() {
		return idExamination;
	}
	public void setIdExamination(String idExamination) {
		this.idExamination = idExamination;
	}
	public String getIdTreatment() {
		return idTreatment;
	}
	public void setIdTreatment(String idTreatment) {
		this.idTreatment = idTreatment;
	}
	public String getDateExamination() {
		return dateExamination;
	}
	public void setDateExamination(String dateExamination) {
		this.dateExamination = dateExamination;
	}
	public String getDateTreatment() {
		return dateTreatment;
	}

	public void setDateTreatment(String isTreatment) {
		this.dateTreatment = isTreatment;
	}
}
