package com.sjsu.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.AssistanceFormBO;
import com.sjsu.BO.BugDetailsBO;
import com.sjsu.BO.MappingTesterAppBO;
import com.sjsu.BO.TesterDetails;

@Repository
public class TesterDaoImpl implements ITesterDao{

	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}
	
	@Override
	public String editTesterProfile(TesterDetails testerDetails) {
		System.out.println("EDIT AND SAVE TESTER DETAILS ::: In DAO class..");
		Session session = getSessionFactory().getCurrentSession();
		String result = "SUCCESS";
		try {
		
		session.beginTransaction();
		
		session.saveOrUpdate(testerDetails);
		session.getTransaction().commit();
	     } catch(Exception e) {
		System.out.println(e);
		session.getTransaction().rollback();
		result = e.getMessage();
		return result;
	}
		return result;
	}

	@Override
	public List<ApplicationDetails> retreiveTesterDetails(String userName) {
		Session session = getSessionFactory().getCurrentSession();
		List<ApplicationDetails> applicationDetailsList = new ArrayList<ApplicationDetails>();
		System.out.println("IN AJAX DAO" + userName);
		session.beginTransaction();
		Query query = session.createSQLQuery(
				"select mapping.APPLICATION_ID from MAPPING_TESTER_APP mapping where mapping.TESTER_USERNAME = :userName")
				.setParameter("userName",userName);
				List appList = query.list();
				System.out.println(appList);
		
		if(!appList.isEmpty()) {

		Criteria criteria = session.createCriteria(ApplicationDetails.class);
		Disjunction disjunction = Restrictions.disjunction();
		Iterator<String> iterator = appList.iterator();
		while (iterator.hasNext()) {
			//System.out.println(iterator.next());
			disjunction.add(Restrictions.eq("applicationID",iterator.next()));
		}
		criteria.add(disjunction);
        applicationDetailsList = (List<ApplicationDetails>) criteria.list();
		}
        System.out.println(applicationDetailsList);
        session.getTransaction().commit();
		return applicationDetailsList;
	}

	@Override
	public List<ApplicationDetails> getMatchedAppDetails(String preferredTestLang) {
		Session session = getSessionFactory().getCurrentSession();
		List<ApplicationDetails> applicationDetailsList = new ArrayList<ApplicationDetails>();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(ApplicationDetails.class);
		applicationDetailsList = (List<ApplicationDetails>) criteria.list();
		session.getTransaction().commit();
		return applicationDetailsList;
	}

	@Override
	public TesterDetails setTesterInformtion(String userName) {
		System.out.println("FETCHING TESTER INFORMATION");
		Session session = getSessionFactory().getCurrentSession();
		TesterDetails testerInformation = new TesterDetails();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(TesterDetails.class);
		criteria.add(Restrictions.eq("userName",userName).ignoreCase());
		testerInformation = (TesterDetails) criteria.uniqueResult();
		session.getTransaction().commit();
		return testerInformation;
	}

	@Override
	public String testThisApplication(MappingTesterAppBO mappingTesterApp) {
		System.out.println("Assign Application to Tester");
		Session session = getSessionFactory().getCurrentSession();
		String result = "SUCCESS";
		try {
		
		session.beginTransaction();
		
		session.saveOrUpdate(mappingTesterApp);
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
	public String getAppVendorEmail(String appId) {
		System.out.println("Fetch Application ID for Assistance Form: " +appId);
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(AppVendorDetails.class);
		criteria.add(Restrictions.eq("userName",appId).ignoreCase());
		AppVendorDetails appVendorDetails = (AppVendorDetails) criteria.uniqueResult();
		session.getTransaction().commit();
		String appVendorEmail = appVendorDetails.getContactEmail();
	   
		return appVendorEmail;
	}

	@Override
	public String sendAssistanceQuery(AssistanceFormBO assistanceForm) {
		System.out.println("SAVE TESTER QUERY DETAILS ::: METHODNAME ::: sendAssistanceQuery");
		String result = "SUCCESS";
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(assistanceForm);
		session.getTransaction().commit();
		return result;
	}

	@Override
	public List<MappingTesterAppBO> getAssignedAppDetails(String userName) {
		Session session = getSessionFactory().getCurrentSession();
		List<MappingTesterAppBO> assignedApplicationList = new ArrayList<MappingTesterAppBO>();
		session.beginTransaction();
		
//		Query query = session.createSQLQuery(
//				"select mapping.APPLICATION_ID from MAPPING_TESTER_APP mapping where mapping.TESTER_USERNAME = :userName")
//				.setParameter("userName",userName);
//				List assignedAppList = query.list();
//				System.out.println(assignedAppList);
				
		Criteria criteria = session.createCriteria(MappingTesterAppBO.class);
//		Disjunction disjunction = Restrictions.disjunction();
//		Iterator<String> iterator = assignedAppList.iterator();
//		while (iterator.hasNext()) {
//			//System.out.println(iterator.next());
//			disjunction.add(Restrictions.eq("applicationID",iterator.next()));
//		}
//		criteria.add(disjunction);
		criteria.add(Restrictions.eq("testerUsername",userName).ignoreCase());
		
		assignedApplicationList = (List<MappingTesterAppBO>) criteria.list();
        System.out.println(assignedApplicationList);
		session.getTransaction().commit();
		
		return assignedApplicationList;
	}

	@Override
	public String sendBugDetails(BugDetailsBO bugDetails) {
		System.out.println("SAVE BUG DETAILS ::: METHODNAME ::: sendBugDetails");
		String result = "SUCCESS";
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(bugDetails);
		session.getTransaction().commit();
		return result;
	}

	@Override
	public List<BugDetailsBO> fetchBugList(String userName, int appID) {
		System.out.println("FETCH BUG DETAILS ::: METHODNAME ::: fetchBugList");
		List< BugDetailsBO> bugList = new ArrayList<BugDetailsBO>();
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
//		Query to fetch the bug Details
		Criteria criteria = session.createCriteria(BugDetailsBO.class);
		Criterion username = Restrictions.eq("testerDetails.userName",userName).ignoreCase();
        Criterion applicationId = Restrictions.eq("appDetails.applicationID",appID);
        LogicalExpression Exp = Restrictions.and(username,applicationId);
        criteria.add(Exp);
        bugList = (List<BugDetailsBO>) criteria.list();
        
        System.out.println(bugList);
		session.getTransaction().commit();
		
		return bugList;
		
		
	}

	@Override
	public String updateCreditRanking(TesterDetails sessionTesterDetails) {
		System.out.println("UPDATE credits and Ranking :: METHOD NAME :: updateCreditRanking");
	
		int accumulatedCredit = 0;
		
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(TesterDetails.class);
		Criterion username = Restrictions.eq("userName",sessionTesterDetails.getUserName()).ignoreCase();
        criteria.add(username);
        TesterDetails testerDetails = (TesterDetails) criteria.uniqueResult();
        accumulatedCredit = testerDetails.getAccumulatedCredit();
        accumulatedCredit = accumulatedCredit + sessionTesterDetails.getCredit();
        testerDetails.setAccumulatedCredit(accumulatedCredit);
        testerDetails.setCredit(0);
        
        if(accumulatedCredit>=300) {
        	testerDetails.setRanking(1);
        } else if (accumulatedCredit>=250) {
			testerDetails.setRanking(2);
		} else if (accumulatedCredit>=200) {
			testerDetails.setRanking(3);
		} else {
			testerDetails.setRanking(0);
		}
        try {
		
		session.saveOrUpdate(testerDetails);
		return "SUCCESS";
		}
        catch(Exception e){
		return "Fail";
        }
		
	}

	@Override
	public TesterDetails setRefreshedTesterData(TesterDetails testerDetails) {
		System.out.println("Get refreshed Tester Details :: methodname :: setRefreshedTesterData");
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(TesterDetails.class);
		TesterDetails refreshedTesterDetails = (TesterDetails) criteria.uniqueResult();
		
		return refreshedTesterDetails;
	}

}
