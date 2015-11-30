package com.sjsu.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.TesterDetails;

@Repository
public class AppVendorDaoImpl implements IAppVendorDao{
	private SessionFactory sessionFactory;
	 
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
			
			session.beginTransaction();
			
			session.save(uploadAppBO);
			session.getTransaction().commit();
			 /*catch(SQLException e) {
				System.out.println(e);
				session.getTransaction().rollback();
				result = "FAIL";
				return result;
			}*/
			return result;
	}

}


	