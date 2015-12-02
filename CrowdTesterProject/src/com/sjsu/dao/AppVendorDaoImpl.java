package com.sjsu.dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import com.sjsu.BO.AppPlatformDetailsBO;
import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.BugDetailsBO;
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

	@Override
	public List<ApplicationDetails> getApplicationDetails(
			AppVendorDetails appVendorDetails) {
		System.out.println("Get Registered APP DETAILS ::: In DAO class ::: getApplicationDetails");
		List<ApplicationDetails> appDetailsList = new ArrayList<ApplicationDetails>();
		Session session = getSessionFactory().getCurrentSession();
		String result = "SUCCESS";
		session.beginTransaction();
		Criteria criteria = session.createCriteria(ApplicationDetails.class);
		criteria.add(Restrictions.eq("appVendorUsername.userName",appVendorDetails.getUserName()).ignoreCase());
		appDetailsList = (List<ApplicationDetails>) criteria.list();
		session.getTransaction().commit();
		return appDetailsList;
	}

	@Override
	public List<BugDetailsBO> viewBugsForAppProvider(
			ApplicationDetails testApplicationDetails) {
		System.out.println("View Bug Details assigned ::: In DAO class ::: viewBugsForAppProvider");
		List<BugDetailsBO> bugDetailsList = new ArrayList<BugDetailsBO>();
		Session session = getSessionFactory().getCurrentSession();
		String result = "SUCCESS";
		session.beginTransaction();
		Criteria criteria = session.createCriteria(BugDetailsBO.class);
		criteria.add(Restrictions.eq("appDetails.applicationID",testApplicationDetails.getApplicationID()));
		bugDetailsList = (List<BugDetailsBO>) criteria.list();
		
		session.getTransaction().commit();
		return bugDetailsList;
	}

	@Override
	public String changeBugStatus(int bugId, String status, String detectedBy) {
		System.out.println("Changing BUG status in DataBase ::: METHODNAME ::: changeBugStatus");
		String result = "SUCCESS";
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println(bugId + "   "+ status);
		BugDetailsBO newBugDetails = new BugDetailsBO();
		
		Criteria criteria = session.createCriteria(BugDetailsBO.class);
		criteria.add(Restrictions.eq("bugId",bugId));
		newBugDetails = (BugDetailsBO) criteria.uniqueResult();
		
//		Change Status if it is not Accepted
		if(!newBugDetails.getBugStatus().equalsIgnoreCase("ACCEPTED")) {
			newBugDetails.setBugStatus(status);
			System.out.println("Bug Details to be Updated: "+newBugDetails);
			session.saveOrUpdate(newBugDetails);
			session.getTransaction().commit();
			 session = getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			String severity = newBugDetails.getSeverity();
			TesterDetails testerDetailsToEdit = new TesterDetails();
			criteria = session.createCriteria(TesterDetails.class);
			criteria.add(Restrictions.eq("userName",detectedBy));
			testerDetailsToEdit = (TesterDetails) criteria.uniqueResult();
			System.out.println("Tester details to edit: "+testerDetailsToEdit);
			
//			Set Credit based on bug severity
			int oldCredits = testerDetailsToEdit.getCredit();
			if(severity.equalsIgnoreCase("CRITICAL")) {

				int newCredit = 1 * 10 + oldCredits;
				testerDetailsToEdit.setCredit(newCredit);
			} else if(severity.equalsIgnoreCase("HIGH")) {

				int newCredit = 1 * 5 + oldCredits;
				testerDetailsToEdit.setCredit(newCredit);
			} else if(severity.equalsIgnoreCase("MEDIUM")) {

				int newCredit = 1 * 3 + oldCredits;
				testerDetailsToEdit.setCredit(newCredit);
			} else {
				int newCredit = 1 * 2 + oldCredits;
				testerDetailsToEdit.setCredit(newCredit);
			}
			
//			Set Ranking based on credit
			if (testerDetailsToEdit.getAccumulatedCredit() >= 300) {
				testerDetailsToEdit.setRanking(1);
			} else if (testerDetailsToEdit.getAccumulatedCredit() >= 250) {
				testerDetailsToEdit.setRanking(2);
			} else if(testerDetailsToEdit.getAccumulatedCredit() >= 200) {
				testerDetailsToEdit.setRanking(3);
			} else {
				testerDetailsToEdit.setRanking(0);
			}
			
			System.out.println("NEW TESTER DETAILS TO BE STORED WITH CREDIT AND RANKING: " +testerDetailsToEdit);
			session.saveOrUpdate(testerDetailsToEdit);	
			session.getTransaction().commit();
		}
				
//		
		
				return result;
	}

	@Override
	public String deleteAssignedBugs(int bugId, String status,
			String detectedBy) {
		System.out.println("Soft Deleting BUG Details in DataBase ::: METHODNAME ::: changeBugStatus");
		String result = "SUCCESS";
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		System.out.println(bugId + "   "+ status);
		
		Query query = session.createQuery(
				"update BugDetailsBO set bugStatus= :qstatus where bugId= :qbugId");
		query.setParameter("qstatus", status);
		query.setParameter("qbugId", bugId);
				int res = query.executeUpdate();
				System.out.println(res);
				if (res == 0){
					result = "FAIL";
				} else {
					session.getTransaction().commit();
				}
		return result;
		
	}

	@Override
	public List<TesterDetails> viewTesterDetailsAssigned(String appID) {
		System.out.println("View Assigned Tester Details assigned ::: In DAO class ::: viewTesterDetailsAssigned");
		List<TesterDetails> testerDetailsList = new ArrayList<TesterDetails>();
		Session session = getSessionFactory().getCurrentSession();
		String result = "SUCCESS";
		session.beginTransaction();
		
		Query query = session.createSQLQuery(
				"select mapping.TESTER_USERNAME from MAPPING_TESTER_APP mapping where mapping.APPLICATION_ID = :appId")
				.setParameter("appId",appID);
				List testerUsernameList = query.list();
				System.out.println(testerUsernameList);
		
		Iterator<String> iterator = testerUsernameList.iterator();
		TesterDetails testerDetails = new TesterDetails();
		while (iterator.hasNext()) {
			Criteria criteria = session.createCriteria(TesterDetails.class);
			criteria.add(Restrictions.eq("userName",iterator.next()).ignoreCase());
			testerDetails = (TesterDetails) criteria.uniqueResult();
			testerDetailsList.add(testerDetails);
		}
		System.out.println(testerDetailsList);
		session.getTransaction().commit();
		return testerDetailsList;
	}

}


	