package com.sjsu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.AssistanceFormBO;
import com.sjsu.BO.BugDetailsBO;
import com.sjsu.BO.MappingTesterAppBO;
import com.sjsu.BO.TesterDetails;
import com.sjsu.dao.ILoginDao;
import com.sjsu.dao.ITesterDao;

@Component
public class TesterServiceImpl implements ITesterService{

	private ITesterDao testerDao;
	
	public ITesterDao getTesterDao() {
		return testerDao;
	}

	@Autowired
	public void setTesterDao(ITesterDao testerDao) {
		this.testerDao = testerDao;
	}

	@Override
	public String editTesterProfile(TesterDetails testerDetails) {
		String result = testerDao.editTesterProfile(testerDetails);
		return result;
	}

	@Override
	public List<ApplicationDetails> retreiveTesterDetails(String userName) {
		//TesterDetails testerDetails = testerDao.retreiveTesterDetails(userName);
		return testerDao.retreiveTesterDetails(userName); 
	}

	@Override
	public List<ApplicationDetails> getMatchedAppDetails(
			String preferredTestLang) {
		List<ApplicationDetails> appDetailsList = testerDao.getMatchedAppDetails(preferredTestLang);
		return appDetailsList;
	}

	@Override
	public TesterDetails setTesterInformtion(String userName) {
		TesterDetails testerInformation = testerDao.setTesterInformtion(userName);
		return testerInformation;
	}

	@Override
	public String testThisApplication(MappingTesterAppBO mappingTesterApp) {
		String result = testerDao.testThisApplication(mappingTesterApp);
		return result;
	}

	@Override
	public String getAppVendorEmail(String appId) {
		String appVendorEmail = testerDao.getAppVendorEmail(appId);
		return appVendorEmail;
	}

	@Override
	public String sendAssistanceQuery(AssistanceFormBO assistanceForm) {
		String result = testerDao.sendAssistanceQuery(assistanceForm);
		return result;
	}

	@Override
	public List<MappingTesterAppBO> getAssignedAppDetails(String userName) {
		List<MappingTesterAppBO> assignedAppDetailsList = testerDao.getAssignedAppDetails(userName);
		return assignedAppDetailsList;
	}

	@Override
	public String sendBugDetails(BugDetailsBO bugDetails) {
		String result = testerDao.sendBugDetails(bugDetails);
		return result;
	}

	@Override
	public List<BugDetailsBO> fetchBugList(String userName, int appID) {
		List<BugDetailsBO> bugList = new ArrayList<BugDetailsBO>();
		bugList = testerDao.fetchBugList(userName,appID);
		return bugList;
	}

}
