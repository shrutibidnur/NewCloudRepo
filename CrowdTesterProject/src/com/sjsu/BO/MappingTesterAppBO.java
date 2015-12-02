package com.sjsu.BO;

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
@Table(name ="MAPPING_TESTER_APP")
public class MappingTesterAppBO {
	
	@Id
	@Column(name = "MAPPING_ID", nullable = false)
	@SequenceGenerator(name="mapping_seq", sequenceName="mappingID_generator")
	@GeneratedValue(strategy=GenerationType.AUTO , generator="mapping_seq")
	private int mappingID;
	
	@Column(name = "TESTER_USERNAME", nullable = false)
	private String testerUsername;
	
//	@Column(name = "APPLICATION_ID", nullable = false)
	
	@ManyToOne
    @JoinColumn(name = "APPLICATION_ID")
	private ApplicationDetails applicationId;
	
	@Column(name = "STATUS", nullable = false)
	private String status;

	public int getMappingID() {
		return mappingID;
	}

	public void setMappingID(int mappingID) {
		this.mappingID = mappingID;
	}

	public String getTesterUsername() {
		return testerUsername;
	}

	public void setTesterUsername(String testerUsername) {
		this.testerUsername = testerUsername;
	}

	public ApplicationDetails getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(ApplicationDetails applicationId) {
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
