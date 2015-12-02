package com.sjsu.dao;

import java.util.List;

import com.sjsu.BO.AppPlatformDetailsBO;
import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.BugDetailsBO;
import com.sjsu.BO.TesterDetails;

public interface IAppVendorDao {
	
	String editAppVendorProfile(AppVendorDetails appVendorDetails);

	AppVendorDetails setAppProviderDetails(String userName);

	String saveAppDetails(ApplicationDetails uploadAppBO);

	String saveAppPlatformDetails(AppPlatformDetailsBO appPlatformDetailsBO);

	List<ApplicationDetails> getApplicationDetails(
			AppVendorDetails appVendorDetails);

	List<BugDetailsBO> viewBugsForAppProvider(
			ApplicationDetails testApplicationDetails);

	String changeBugStatus(int bugId, String status, String detectedBy);

	String deleteAssignedBugs(int bugId, String status, String detectedBy);

	List<TesterDetails> viewTesterDetailsAssigned(String appID);
}
