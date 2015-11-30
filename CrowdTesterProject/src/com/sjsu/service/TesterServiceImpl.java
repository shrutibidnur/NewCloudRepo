package com.sjsu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sjsu.BO.ApplicationDetails;
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
	public String getAppVendorUsername(String appId) {
		String appVendorEmail = testerDao.getAppVendorUsername(appId);
		return appVendorEmail;
	}

}
