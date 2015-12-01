package com.sjsu.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.TesterDetails;
import com.sjsu.dao.IRegistrationDao;

@Component
public class RegistrationServiceImpl implements IRegistrationService {

	private IRegistrationDao registrationDao;
	
	public IRegistrationDao getRegistrationDao() {
		return registrationDao;
	}
	
	@Autowired
	public void setRegistrationDao(IRegistrationDao registrationDao) {
		this.registrationDao = registrationDao;
	}

	@Override
	public void saveTesterDetails(TesterDetails testerDetails) throws HibernateException{
		//String result = registrationDao.saveTesterDetails(testerDetails);
		
		registrationDao.saveTesterDetails(testerDetails);
		//return result;
	}

	@Override
	public void saveAppVendorDetails(AppVendorDetails appVendorDetails)throws HibernateException {
		 registrationDao.saveAppVendorDetails(appVendorDetails);
		//return result;
	}

}
