package com.sjsu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




//import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.TesterDetails;
import com.sjsu.dao.ILoginDao;
//import com.sjsu.dao.IAppVendorDao;
import com.sjsu.dao.IAppVendorDao;


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
	public String saveAppDetails(ApplicationDetails uploadAppBO) {
		String result = appVendorDao.saveAppDetails(uploadAppBO);
		return result;
	}

}



	