package com.sjsu.BO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="MAPPING_TESTER_APP")
public class MappingTesterAppBO {
	
	@Id
	@Column(name = "MAPPING_ID", nullable = false)
	@SequenceGenerator(name="mapping_seq", sequenceName="mappingID_generator")
	@GeneratedValue(strategy=GenerationType.AUTO , generator="mapping_seq")
	private String mappingID;
	
	@Column(name = "TESTER_USERNAME", nullable = false)
	private String testerUsername;
	
	@Column(name = "APPLICATION_ID", nullable = false)
	private String applicationId;
	
	@Column(name = "STATUS", nullable = false)
	private String status;

	public String getMappingID() {
		return mappingID;
	}

	public void setMappingID(String mappingID) {
		this.mappingID = mappingID;
	}

	public String getTesterUsername() {
		return testerUsername;
	}

	public void setTesterUsername(String testerUsername) {
		this.testerUsername = testerUsername;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MappingTesterAppBO [mappingID=" + mappingID
				+ ", testerUsername=" + testerUsername + ", applicationId="
				+ applicationId + ", status=" + status + "]";
	}
	
	
	
	

}
