package com.sjsu.dao;

import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.ApplicationDetails;

public interface IAppVendorDao {

	String editAppVendorProfile(AppVendorDetails appVendorDetails);

	AppVendorDetails setAppProviderDetails(String userName);

	String saveAppDetails(ApplicationDetails uploadAppBO);
}
