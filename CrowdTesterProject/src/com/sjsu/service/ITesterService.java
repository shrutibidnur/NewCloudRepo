package com.sjsu.service;

import java.util.List;

import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.MappingTesterAppBO;
import com.sjsu.BO.TesterDetails;

public interface ITesterService {

	String editTesterProfile(TesterDetails testerDetails);

	List<ApplicationDetails> retreiveTesterDetails(String userName);

	List<ApplicationDetails> getMatchedAppDetails(String preferredTestLang);

	TesterDetails setTesterInformtion(String userName);

	String testThisApplication(MappingTesterAppBO mappingTesterApp);

	String getAppVendorUsername(String appId);

}