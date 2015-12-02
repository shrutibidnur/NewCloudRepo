package com.sjsu.service;

import java.util.List;

import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.AppPlatformDetailsBO;
import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.BugDetailsBO;
import com.sjsu.BO.TesterDetails;

public interface IAppVendorService {

	String editAppVendorProfile(AppVendorDetails appVendorDetails);

	AppVendorDetails setAppProviderDetails(String userName);

	String saveAppDetails(ApplicationDetails uploadAppBO, AppPlatformDetailsBO appPlatformDetailsBO);

	List<ApplicationDetails> getApplicationDetails(
			AppVendorDetails appVendorDetails);

	List<BugDetailsBO> viewBugsForAppProvider(ApplicationDetails testApplicationDetails);

	String changeBugStatus(String bugId, String status, String detectedBy);

	String deleteAssignedBugs(String bugId, String status, String detectedBy);

	List<TesterDetails> viewTesterDetailsAssigned(String appID);
}
