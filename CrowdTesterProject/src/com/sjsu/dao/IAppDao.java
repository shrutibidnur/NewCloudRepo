package com.sjsu.dao;

import com.sjsu.BO.ApplicationDetails;


public interface IAppDao {

	//String editTesterProfile(UploadAppBO uploadAppBO);

	String saveAppProfile(ApplicationDetails uploadAppBO);

}
