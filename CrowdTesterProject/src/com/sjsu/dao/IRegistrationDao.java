package com.sjsu.dao;

import org.hibernate.HibernateException;

import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.TesterDetails;

public interface IRegistrationDao {

	void saveTesterDetails  (TesterDetails testerDetails) throws HibernateException;

	void saveAppVendorDetails(AppVendorDetails appVendorDetails)throws HibernateException;
}

