package com.sjsu.BO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="BUG_DETAILS")
public class BugDetailsBO {
	
	@Id
	@Column(name = "BUG_ID", nullable = false)
	@SequenceGenerator(name="bug_seq", sequenceName="bugID_generator")
	@GeneratedValue(strategy=GenerationType.AUTO , generator="bug_seq")
	private String bugId;
	
	@Column(name = "SEVERITY", nullable = false)
	private String severity;
	
	@Column(name = "BUG_DETAILS", nullable = false)
	private String bugDetails;
	
	@Column(name = "DETECTED_DATE", nullable = false)
	private String detectedDate;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "TESTER_DETAILS_USERNAME")
	private TesterDetails testerDetails;
	
	@ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "APP_DETAILS_APPLICATION_ID")
	private ApplicationDetails appDetails;
	
	@Column(name = "BUG_STATUS", nullable = false)
	private String bugStatus;

	public String getBugId() {
		return bugId;
	}

	public void setBugId(String bugId) {
		this.bugId = bugId;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getBugDetails() {
		return bugDetails;
	}

	public void setBugDetails(String bugDetails) {
		this.bugDetails = bugDetails;
	}

	public String getDetectedDate() {
		return detectedDate;
	}

	public void setDetectedDate(String detectedDate) {
		this.detectedDate = detectedDate;
	}

	public TesterDetails getTesterDetails() {
		return testerDetails;
	}

	public void setTesterDetails(TesterDetails testerDetails) {
		this.testerDetails = testerDetails;
	}

	public ApplicationDetails getAppDetails() {
		return appDetails;
	}

	public void setAppDetails(ApplicationDetails appDetails) {
		this.appDetails = appDetails;
	}
	
	public String getBugStatus() {
		return bugStatus;
	}

	public void setBugStatus(String bugStatus) {
		this.bugStatus = bugStatus;
	}

	@Override
	public String toString() {
		return "BugDetailsBO [bugId=" + bugId + ", severity=" + severity
				+ ", bugDetails=" + bugDetails + ", detectedDate="
				+ detectedDate + ", testerDetails=" + testerDetails
				+ ", appDetails=" + appDetails + "]";
	}
	

}
