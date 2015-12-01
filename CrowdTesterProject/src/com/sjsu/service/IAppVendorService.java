package com.sjsu.service;

import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.AppPlatformDetailsBO;
import com.sjsu.BO.ApplicationDetails;

public interface IAppVendorService {

	String editAppVendorProfile(AppVendorDetails appVendorDetails);

	AppVendorDetails setAppProviderDetails(String userName);

	String saveAppDetails(ApplicationDetails uploadAppBO, AppPlatformDetailsBO appPlatformDetailsBO);
}
