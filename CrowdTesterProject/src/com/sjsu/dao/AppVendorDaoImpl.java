package com.sjsu.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.sjsu.BO.AppPlatformDetailsBO;
import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.TesterDetails;

@Repository
public class AppVendorDaoImpl implements IAppVendorDao{
	private SessionFactory sessionFactory;
	private int appID;
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}
	
	@Override
	public String editAppVendorProfile(AppVendorDetails appVendorDetails) {
		System.out.println("EDIT AND SAVE AppVendor DETAILS ::: In DAO class..");
		Session session = getSessionFactory().getCurrentSession();
		String result = "SUCCESS";
		try {
		
		session.beginTransaction();
		
		session.saveOrUpdate(appVendorDetails);
		session.getTransaction().commit();
	     } catch(Exception e) {
		System.out.println(e);
		session.getTransaction().rollback();
		result = "FAIL";
		return result;
	     }
		return result;
	}

	@Override
	public AppVendorDetails setAppProviderDetails(String userName) {
		System.out.println("FETCHING APP PROVIDER INFORMATION");
		Session session = getSessionFactory().getCurrentSession();
		AppVendorDetails appProviderInfo = new AppVendorDetails();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(AppVendorDetails.class);
		criteria.add(Restrictions.eq("userName",userName).ignoreCase());
		appProviderInfo = (AppVendorDetails) criteria.uniqueResult();
		session.getTransaction().commit();
		return appProviderInfo;
	}

	@Override
	public String saveAppDetails(ApplicationDetails uploadAppBO) {
			System.out.println("SAVE APP DETAILS ::: In DAO class..");
			Session session = getSessionFactory().getCurrentSession();
			String result = "SUCCESS";
			//ModelAndView modelAndView = new ModelAndView();
			session.beginTransaction();
			try{
			session.save(uploadAppBO);
			appID = uploadAppBO.getApplicationID();
			session.getTransaction().commit();
			//modelAndView.addObject("appID", uploadAppBO.getApplicationID());
			}
			 catch(Exception e) {
				System.out.println(e);
				session.getTransaction().rollback();
				result = "FAIL";
				return result;
			}
			return result;
	}

	@Override
	public String saveAppPlatformDetails(AppPlatformDetailsBO appPlatformDetailsBO) {
		// TODO Auto-generated method stub
		System.out.println("SAVE APP platform DETAILS ::: In DAO class..");
		Session session = getSessionFactory().getCurrentSession();
		String result = "SUCCESS";
		ModelAndView modelAndView = new ModelAndView();
		
		session.beginTransaction();
		try{
		appPlatformDetailsBO.setApp_Details_Application_ID(appID);
		session.save(appPlatformDetailsBO);
		session.getTransaction().commit();
		}catch(Exception e) {
			System.out.println(e);
			session.getTransaction().rollback();
			result = "FAIL";
			return result;
		}
		return result;
	}

}


	