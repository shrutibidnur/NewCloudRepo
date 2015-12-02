package com.sjsu.service;

import java.util.List;

import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.AssistanceFormBO;
import com.sjsu.BO.BugDetailsBO;
import com.sjsu.BO.MappingTesterAppBO;
import com.sjsu.BO.TesterDetails;

public interface ITesterService {

	String editTesterProfile(TesterDetails testerDetails);

	List<ApplicationDetails> retreiveTesterDetails(String userName);

	List<ApplicationDetails> getMatchedAppDetails(String preferredTestLang);

	TesterDetails setTesterInformtion(String userName);

	String testThisApplication(MappingTesterAppBO mappingTesterApp);

	String getAppVendorEmail(String appId);

	String sendAssistanceQuery(AssistanceFormBO assistanceForm);

	List<MappingTesterAppBO> getAssignedAppDetails(String userName);

	String sendBugDetails(BugDetailsBO bugDetails);

	List<BugDetailsBO> fetchBugList(String userName, int appID);

	String updateCreditRanking(TesterDetails sessionTesterDetails);

	TesterDetails setRefreshedTesterData(TesterDetails testerDetails);

}
