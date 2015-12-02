package com.sjsu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;











//import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.BugDetailsBO;
import com.sjsu.BO.TesterDetails;
import com.sjsu.dao.ILoginDao;
//import com.sjsu.dao.IAppVendorDao;
import com.sjsu.dao.IAppVendorDao;
import com.sjsu.BO.AppPlatformDetailsBO;

@Component
public class AppVendorServiceImpl implements IAppVendorService{

private IAppVendorDao appVendorDao;
	
	public IAppVendorDao getAppVendorDao() {
		return appVendorDao;
	}

	@Autowired
	public void setAppVendorDao(IAppVendorDao appVendorDao) {
		this.appVendorDao = appVendorDao;
	}

	@Override
	public String editAppVendorProfile(AppVendorDetails appVendorDetails) {
		String result = appVendorDao.editAppVendorProfile(appVendorDetails);
		return result;
	}

	@Override
	public AppVendorDetails setAppProviderDetails(String userName) {
		AppVendorDetails appVendorInfo = appVendorDao.setAppProviderDetails(userName);
		return appVendorInfo;
	}

	@Override
	public String saveAppDetails(ApplicationDetails uploadAppBO, AppPlatformDetailsBO appPlatformDetailsBO) {
		String result = appVendorDao.saveAppDetails(uploadAppBO);
		String result1 = appVendorDao.saveAppPlatformDetails(appPlatformDetailsBO);
		System.out.println("service class app + platform");
		return result+result1;
	}

	@Override
	public List<ApplicationDetails> getApplicationDetails(
			AppVendorDetails appVendorDetails) {
		List<ApplicationDetails> appDetailsList = new ArrayList<ApplicationDetails>();
		appDetailsList = appVendorDao.getApplicationDetails(appVendorDetails);
		return appDetailsList;
	}

	@Override
	public List<BugDetailsBO> viewBugsForAppProvider(
			ApplicationDetails testApplicationDetails) {
		List<BugDetailsBO> bugList = new ArrayList<BugDetailsBO>();
		bugList = appVendorDao.viewBugsForAppProvider(testApplicationDetails);
		return bugList;
	}

	@Override
	public String changeBugStatus(int bugId, String status, String detectedBy) {
		String result = appVendorDao.changeBugStatus(bugId, status, detectedBy);
		return result;
	}

	@Override
	public String deleteAssignedBugs(int bugId, String status,
			String detectedBy) {
		String result = appVendorDao.deleteAssignedBugs(bugId, status, detectedBy);
		return result;
	}

	@Override
	public List<TesterDetails> viewTesterDetailsAssigned(String appID) {
		List<TesterDetails> testerDetailsList = new ArrayList<TesterDetails>();
		testerDetailsList = appVendorDao.viewTesterDetailsAssigned(appID);
		return testerDetailsList;
	}

}



	