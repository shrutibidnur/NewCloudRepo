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
@Table(name ="APP_Details")
public class ApplicationDetails {
	
	@Id
	@Column(name = "APPLICATION_ID", nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO )
	private int applicationID;
	
	@Column(name = "APP_NAME", nullable = false)
	private String appName;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Column(name = "TEST_TYPE", nullable = true)
	private String testType;
	
	@Column(name = "TEST_DEADLINE", nullable = false)
	private String testDeadLine;
	
	@Column(name = "DOWNLOAD_LINK", nullable = false)
	private String downloadLink;
	
	@Column(name = "APP_SIZE", nullable = true)
	private String appSize;
	
	@Column(name = "APP_DOCUMENT", nullable = true)
	private String appDocument;
	
	public AppVendorDetails getAppVendorUsername() {
		return appVendorUsername;
	}

	public void setAppVendorUsername(AppVendorDetails appVendorUsername) {
		this.appVendorUsername = appVendorUsername;
	}

	//@Column(name = "APP_VENDOR_DETAILS_USERNAME")
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name= "APP_VENDOR_DETAILS_USERNAME")
	private AppVendorDetails appVendorUsername;
	
	@Column(name = "APP_LANGUAGE", nullable = true)
	private String appLanguage;
	
//	@Column(name = "REPORTING_LANGUAGE", nullable = false)
//	private String reportingLanguage;
//	
	@Column(name = "COST", nullable = false)
	private int cost;

	public int getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	/*public String getProductOS() {
		return productOS;
	}

	public void setProductOS(String productOS) {
		this.productOS = productOS;
	}

	public String getTesting() {
		return testing;
	}

	public void setTesting(String testing) {
		this.testing = testing;
	}
*/
	public String getTestDeadLine() {
		return testDeadLine;
	}

	public void setTestDeadLine(String testDeadLine) {
		this.testDeadLine = testDeadLine;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public String getAppSize() {
		return appSize;
	}

	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}

	public String getAppDocument() {
		return appDocument;
	}

	public void setAppDocument(String appDocument) {
		this.appDocument = appDocument;
	}

//	public String getAppProvider() {
//		return appProvider;
//	}
//
//	public void setAppProvider(String appProvider) {
//		this.appProvider = appProvider;
//	}


	public String getAppLanguage() {
		return appLanguage;
	}

	public void setAppLanguage(String appLanguage) {
		this.appLanguage = appLanguage;
	}

	/*public String getReportingLanguage() {
		return reportingLanguage;
	}

	public void setReportingLanguage(String reportingLanguage) {
		this.reportingLanguage = reportingLanguage;
	}
*/
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "ApplicationDetails [applicationID=" + applicationID
				+ ", appName=" + appName + ", description=" + description
//				+ ", testType=" + testType + ", productOS=" + productOS
	//			+ ", testing=" + testing + ", testDeadLine=" + testDeadLine
				+ ", downloadLink=" + downloadLink + ", appSize=" + appSize
				+ ", appDocument=" + appDocument
				+ ", appVendorUsername=" + appVendorUsername
				+ ", appLanguage=" + appLanguage 
		//		+ reportingLanguage
				+ ", cost=" + cost + "]";
	}
	
	

}
