package com.sjsu.BO;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

	@Entity
	@Table(name ="app_platform_details")
	public class AppPlatformDetailsBO {
		
		
		/*@Column(name = "PLATFORM_DETAILS_ID", nullable = false)
		@SequenceGenerator(name="platform_seq", sequenceName="platformDetailsID_generator")
		@GeneratedValue(strategy=GenerationType.AUTO , generator="platform_seq")
		private String platformDetailsId ;
		*/
		@Column(name = "NO_OF_TESTING_PLATFORM", nullable = false)
		private int no_of_testing_Platform;
		
		@Column(name = "TESTING_PLATFORM", nullable = false)
		private String testingPlatform;
		
		@Column(name = "No_of_Testers", nullable = false)
		private int no_of_Testers;
		
		//@Column(name = "App_Details_Application_ID", nullable = false)
		@Id
		@JoinColumn(name = "App_Details_Application_ID", referencedColumnName = "APPLICATION_ID")
		@ManyToOne
		private String app_Details_Application_ID;
		
		@Transient
		private List<String> testPlatformList;
		
		@Transient
		private int cost;
		
		
		
		/*public String getPlatformDetailsId() {
			return platformDetailsId;
		}



		public void setPlatformDetailsId(String platformDetailsId) {
			this.platformDetailsId = platformDetailsId;
		}
*/


		public int getCost() {
			return cost;
		}



		public void setCost(int cost) {
			this.cost = cost;
		}



		public int getNo_of_testing_Platform() {
			return no_of_testing_Platform;
		}



		public void setNo_of_testing_Platform(int no_of_testing_Platform) {
			this.no_of_testing_Platform = no_of_testing_Platform;
		}



		public String getTestingPlatform() {
			return testingPlatform;
		}



		public void setTestingPlatform(String testingPlatform) {
			this.testingPlatform = testingPlatform;
		}



		public int getNo_of_Testers() {
			return no_of_Testers;
		}



		public void setNo_of_Testers(int no_of_Testers) {
			this.no_of_Testers = no_of_Testers;
		}



		public String getApp_Details_Application_ID() {
			return app_Details_Application_ID;
		}


/*
		public void setApp_Details_Application_ID(ApplicationDetails app_Details_Application_ID) {
			this.app_Details_Application_ID = app_Details_Application_ID;
		}*/		
		

		public void setApp_Details_Application_ID(String appID) {
			// TODO Auto-generated method stub
			this.app_Details_Application_ID = appID;
		}

		
		public List<String> getTestPlatformList() {
			return testPlatformList;
		}



		public void setTestPlatformList(List<String> testPlatformList) {
			this.testPlatformList = testPlatformList;
		}



		@Override
		public String toString() {
			return "AppPlatformDetailsBO [platformDetailsId="
					+ no_of_testing_Platform + ", testingPlatform="
					+ testingPlatform + ", no_of_Testers=" + no_of_Testers
					+ ", app_Details_Application_ID="
					+ app_Details_Application_ID + ", testPlatformList="
					+ testPlatformList + "]";
		}



		
				
	}


