package com.sjsu.BO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="tester_assistance_data")
public class AssistanceFormBO {
	
	@Id
	@Column(name = "Query_ID", nullable = false)
	@SequenceGenerator(name="mapping_seq", sequenceName="mappingID_generator")
	@GeneratedValue(strategy=GenerationType.AUTO , generator="mapping_seq")
	private String id;
	
	@Column(name = "TO_EMAIL", nullable = false)
	private String toEmail;

	@Column(name = "Query_Description", nullable = false)
	private String description;
	
	@Column(name = "Query_Severity", nullable = true)
	private String severity;
	
	@Column(name = "Query_Subject", nullable = true)
	private String subject;
	
	
	@Column(name = "Query_StartDate", nullable = false)
	private String startdate;
	
	@Column(name = "Tester_Details_UserName", nullable = false)
	private String testerusername;

	

	public String getDescription() {
		return description;
	}



	public String getTesterusername() {
		return testerusername;
	}



	public void setTesterusername(String testerusername) {
		this.testerusername = testerusername;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getSeverity() {
		return severity;
	}



	public void setSeverity(String severity) {
		this.severity = severity;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getStartdate() {
		return startdate;
	}



	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}



	public String getToEmail() {
		return toEmail;
	}



	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}



	@Override
	public String toString() {
		return "AssistanceFormBO [id=" + id + ", toEmail=" + toEmail
				+ ", description=" + description + ", severity=" + severity
				+ ", subject=" + subject + ", startdate=" + startdate
				+ ", testerusername=" + testerusername + "]";
	}



	



}
