package com.sjsu.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.AssistanceFormBO;
import com.sjsu.BO.BugDetailsBO;
import com.sjsu.BO.LoginDetails;
import com.sjsu.BO.MappingTesterAppBO;
import com.sjsu.BO.TesterDetails;
import com.sjsu.service.ILoginService;
import com.sjsu.service.ITesterService;

@Controller
@RequestMapping("tester")
public class TesterController {
	
	private ITesterService testerService;
	 
	public ITesterService getTesterService() {
		return testerService;
	}

	@Autowired
	public void setTesterService(ITesterService testerService) {
		this.testerService = testerService;
	}
	
	@Autowired
    private JavaMailSender mailSender;
	
	@RequestMapping("/setTesterDetails")
	public String setTesterDetails(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("testerDetails") TesterDetails testerDetails, Model model){
		System.out.println("dashboard"+testerDetails);
		TesterDetails testerInfo = new TesterDetails();
		testerInfo = testerService.setTesterInformtion(testerDetails.getUserName());
		model.addAttribute("testerDetails", testerInfo);
		HttpSession session = request.getSession();
		session.setAttribute("sessionTesterDetails", testerInfo);
		return "redirect:" + "showTesterDashboard.do";
	}

	
	@RequestMapping("/showTesterDashboard")
	public String showTesterDashboard(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("testerDetails") TesterDetails testerDetails, Model model){
		System.out.println("dashboard"+testerDetails);
		HttpSession session = request.getSession();
		//session.setAttribute("sessionTesterDetails", testerDetails);
		session.getAttribute("testerDetails");
		System.out.println("SESSION DASHBOARD" +session.getAttribute("sessionTesterDetails"));
		model.addAttribute("testerDetails", testerDetails);
		
		return "TesterDashboard";
	}
	
	@RequestMapping("/showProfileform")
	public String showProfileform(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("testerDetails") TesterDetails testerDetails, Model model){
		System.out.println("HIiiiii");
		HttpSession session = request.getSession();
		testerDetails = (TesterDetails) session.getAttribute("sessionTesterDetails");
		session.setAttribute("sessionTesterDetails", testerDetails);
		System.out.println("profile"+testerDetails);
		model.addAttribute("testerDetails", testerDetails);
		return "TesterProfileForm";
	}
	
	@RequestMapping("/showAssistanceform")
	public String showAssistanceform(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("assistanceForm") AssistanceFormBO assistanceForm, Model model,@RequestParam String appId){
		System.out.println("METHODNAME ::: showAssistanceform");
		HttpSession session = request.getSession();
		TesterDetails testerDetails = (TesterDetails) session.getAttribute("sessionTesterDetails");
		session.setAttribute("sessionTesterDetails", testerDetails);
		System.out.println("ASSISTANCE"+testerDetails);
		model.addAttribute("testerDetails", testerDetails);
		String appVendorEmail = testerService.getAppVendorEmail(appId);
		assistanceForm.setToEmail(appVendorEmail);
		assistanceForm.setTesterusername(testerDetails.getUserName());
		System.out.println("ASSITANCE FORM: "+assistanceForm);
		return "TesterAssistanceForm";
	}
	
	@RequestMapping("/sendAssistanceQuery")
	public ModelAndView sendAssistanceQuery(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("assistanceForm") AssistanceFormBO assistanceForm, Model model){
		System.out.println("METHODNAME ::: sendAssistanceQuery");
		Date assistanceDate = new Date();
		SimpleDateFormat assistanceDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
//		HttpSession session = request.getSession();
//		TesterDetails testerDetails = (TesterDetails) session.getAttribute("sessionTesterDetails");
//		session.setAttribute("sessionTesterDetails", testerDetails);
		ModelAndView modelAndView = new ModelAndView();
		if (assistanceForm.getSubject() == null || assistanceForm.getSubject() == "") {
			modelAndView.addObject("ERROR", "Please provide Subject.");
			modelAndView.setViewName("/TesterAssistanceForm");
			return modelAndView;
		}
		if (assistanceForm.getDescription() == null || assistanceForm.getDescription() == "") {
			modelAndView.addObject("ERROR", "Please provide Description.");
			modelAndView.setViewName("/TesterAssistanceForm");
			return modelAndView;
		}
		System.out.println("ASSISTANCE: "+assistanceForm);
		SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(assistanceForm.getToEmail());
        email.setSubject(assistanceForm.getSeverity()+" : "+assistanceForm.getSubject());
        email.setText(assistanceForm.getDescription());
        
        mailSender.send(email);
        
        String DateToStr = assistanceDateFormat.format(assistanceDate);
        System.out.println(DateToStr);
        assistanceForm.setStartdate(DateToStr);
        try{
        String result = testerService.sendAssistanceQuery(assistanceForm);
        }
        catch(Exception e) {
        	modelAndView.addObject("ERROR", "");
			modelAndView.setViewName("/TesterAssistanceForm");
			return modelAndView;
        }
		//model.addAttribute("assistanceForm", assistanceForm);
		//System.out.println("ASSITANCE FORM: "+assistanceForm);
        modelAndView.addObject("ERROR", "Email sent sucessfully.");
		modelAndView.setViewName("/TesterAssistanceForm");
		return modelAndView;
		//return "TesterAssistanceForm";
	}
	
	
	@RequestMapping("/showBillingform")
	public String showBillingform(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("testerDetails") TesterDetails testerDetails, Model model){
		System.out.println("HIiiiii");
		HttpSession session = request.getSession();
		testerDetails = (TesterDetails) session.getAttribute("sessionTesterDetails");
		session.setAttribute("sessionTesterDetails", testerDetails);
		System.out.println(testerDetails);
		model.addAttribute("testerDetails", testerDetails);
		return "TesterBillingForm";
	}
	
	@RequestMapping("/showAssistform")
	public String showAssistform(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("testerDetails") TesterDetails testerDetails, Model model){
		System.out.println("HIiiiii");
		model.addAttribute("testerDetails", testerDetails);
		return "TesterAssistForm";
	}
	
	@RequestMapping("/editTesterProfile")
	public ModelAndView editTesterProfile(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("testerDetails") TesterDetails testerDetails, Model model){
		System.out.println("Edit And Save TESTER Details :: METHODNAME :: editTesterProfile");
		HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ERROR", "");
		TesterDetails oldTesterDetails = (TesterDetails) session.getAttribute("sessionTesterDetails");
		if (testerDetails.getFirstName() == null || testerDetails.getFirstName() == "") {
			modelAndView.addObject("ERROR", "Please provide First name. Save was unsuccessful.");
			modelAndView.setViewName("/TesterProfileForm");
			return modelAndView;
		}
		if (testerDetails.getEducation() == null || testerDetails.getEducation() == "") {
			modelAndView.addObject("ERROR", "Please provide Education. Save was unsuccessful.");
			modelAndView.setViewName("/TesterProfileForm");
			return modelAndView;
		}
		if (testerDetails.getOccupation() == null || testerDetails.getOccupation() == "") {
			modelAndView.addObject("ERROR", "Please provide Occupation. Save was unsuccessful.");
			modelAndView.setViewName("/TesterProfileForm");
			return modelAndView;
		}
		if ( testerDetails.getAge() != (int)testerDetails.getAge()) {
			modelAndView.addObject("ERROR", "Age should be integer value. Save was unsuccessful.");
			modelAndView.setViewName("/TesterProfileForm");
			return modelAndView;
		}
		if (!testerDetails.getPassword().equals(testerDetails.getConfirmPassword())) {
			//if (appVendorDetails.getPassword().toString() != appVendorDetails.getConfirmPassword().toString()) {
				modelAndView.addObject("ERROR", "Passwords not matching. Save was unsuccessful");
				modelAndView.setViewName("/TesterProfileForm");
				return modelAndView;
		}	
		oldTesterDetails.setFirstName(testerDetails.getFirstName());
		oldTesterDetails.setLastName(testerDetails.getLastName());
		oldTesterDetails.setAge(testerDetails.getAge());
		oldTesterDetails.setAddress(testerDetails.getAddress());
		//if (testerDetails.getPhoneNumber() != null) {
			oldTesterDetails.setPhoneNumber(testerDetails.getPhoneNumber());
		//}
		oldTesterDetails.setPassword(testerDetails.getPassword());
		oldTesterDetails.setEducation(testerDetails.getEducation());
		oldTesterDetails.setOccupation(testerDetails.getOccupation());
		System.out.println("Before save:::"+oldTesterDetails);
	

		String result = testerService.editTesterProfile(oldTesterDetails);
		System.out.println(result);
		if (result.equalsIgnoreCase("SUCCESS")) {
			System.out.println("Success");
			modelAndView.addObject("ERROR", "");
			modelAndView.setViewName("/TesterProfileForm");
			return modelAndView;
			
			//return "TesterProfileForm";
		}
		modelAndView.addObject("ERROR", result);
		System.out.println(result);
		//modelAndView.addObject("testerDetails", oldTesterDetails);
		modelAndView.addObject("testerDetails", oldTesterDetails);
		modelAndView.setViewName("/TesterProfileForm");
		return modelAndView;
		//return "TesterAssistForm";
	}
	
	@RequestMapping("/ajaxShowTestDetails")
	public @ResponseBody JSONArray ajaxShowTestDetails(@RequestParam("userName") String userName){
		System.out.println("Entered Ajax Method ::: METHODNAME ::: ajaxShowTestDetails");
		System.out.println(userName);
		List<ApplicationDetails> applicationDetailList = new ArrayList<ApplicationDetails>(); 
		applicationDetailList = testerService.retreiveTesterDetails(userName); 
		
		// converting to JSON format from Java list 
		JSONObject responseDetailsJson = new JSONObject();
	    JSONArray jsonArray = new JSONArray();
	    
	    for(ApplicationDetails p : applicationDetailList) {
	    	System.out.println(p.getApplicationID()+ "   " +p.getAppName());
	    	JSONObject formDetailsJson = new JSONObject();
	    	formDetailsJson.put("appId" , p.getAppVendorUsername().getUserName());
	        formDetailsJson.put("appName" , p.getAppName());
	        formDetailsJson.put("description", p.getDescription());
	        formDetailsJson.put("testType" , p.getTestType());
	    //    formDetailsJson.put("productOS", p.getProductOS());
	        formDetailsJson.put("testDeadline" , p.getTestDeadLine());
	        jsonArray.add(formDetailsJson);
	    }
	    
	    // Debug messages 
	    Iterator i = jsonArray.iterator();
	                // take each value from the json array separately
	         while (i.hasNext()) {
	             JSONObject innerObj = (JSONObject) i.next();
	                }

		return jsonArray;
		
	}

	@RequestMapping("/showViewApplications")
	public String showViewApplications(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("testerDetails") TesterDetails testerDetails, Model model){
		System.out.println("HI ::: showViewApplications");
		HttpSession session = request.getSession();
		testerDetails = (TesterDetails) session.getAttribute("sessionTesterDetails");
		System.out.println(testerDetails.getPreferredTestLang());
		List<ApplicationDetails> appDetailsList = new ArrayList<ApplicationDetails>();
		
		session.setAttribute("appDetailsList", appDetailsList);
		appDetailsList = testerService.getMatchedAppDetails(testerDetails.getPreferredTestLang());
		System.out.println("Matched Applications: " +appDetailsList);
		model.addAttribute("appDetailsList", appDetailsList);
		return "ViewApplications";
	}

	
	@RequestMapping("/testThisApp")
	public ModelAndView testThisApplication(HttpServletRequest request,
			HttpServletResponse response, Model model,@RequestParam int appID){
		System.out.println("METHODNAME ::: testThisApplication ::: Asign Application to tester Addition in mapping table");
		HttpSession session = request.getSession();
	    int applicationId = appID;
		MappingTesterAppBO mappingTesterApp = new MappingTesterAppBO();
		ApplicationDetails testApplicationDetails = new ApplicationDetails();
		testApplicationDetails.setApplicationID(appID);
		mappingTesterApp.setApplicationId(testApplicationDetails);
		TesterDetails testerDetails = (TesterDetails) session.getAttribute("sessionTesterDetails");
		mappingTesterApp.setTesterUsername(testerDetails.getUserName());
		mappingTesterApp.setStatus("ASSIGNED");
		System.out.println(mappingTesterApp);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ERROR","");
		List<ApplicationDetails> appDetailsList = new ArrayList<ApplicationDetails>();
		appDetailsList = (List<ApplicationDetails>) session.getAttribute("appDetailsList");
		//appDetailsList = modelAndView.getClass()
			String result = testerService.testThisApplication(mappingTesterApp);
			if(result.equalsIgnoreCase("success")){
				model.addAttribute("appDetailsList", appDetailsList);
				modelAndView.addObject("appDetailsList", appDetailsList);
				System.out.println(appDetailsList);
				session.setAttribute("appDetailsList", appDetailsList);
			modelAndView.addObject("ERROR", "You have succesfully registered to test this application . Please check pending tasks or profile test details.");
			modelAndView.setViewName("/ViewApplications");
			//modelAndView.addObject("testerDetails", testerDetails)
			return modelAndView;
			}
			else{
				modelAndView.addObject("appDetailsList", appDetailsList);
				System.out.println(appDetailsList);
				model.addAttribute("appDetailsList", appDetailsList);
				session.setAttribute("appDetailsList", appDetailsList);
			modelAndView.addObject("ERROR", "You have already registered to test this application. Please check pending tasks or profile test details.");
			modelAndView.setViewName("/ViewApplications");
			return modelAndView;
		}
		//return "SUCCESS";
	}
	
	@RequestMapping("/showAssignedApplications")
	public String showAssignedApplications(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("testerDetails") TesterDetails testerDetails, Model model){
		System.out.println("SHOW THE ASSIGNED APPLICATIONS ::: METHODNAME ::: showAssignedApplications");
		HttpSession session = request.getSession();
		testerDetails = (TesterDetails) session.getAttribute("sessionTesterDetails");
		System.out.println(testerDetails.getUserName());
		List<MappingTesterAppBO> appDetailsList = new ArrayList<MappingTesterAppBO>();
		
		appDetailsList = testerService.getAssignedAppDetails(testerDetails.getUserName());
		System.out.println("Matched Applications: " +appDetailsList);
		model.addAttribute("appDetailsList", appDetailsList);
		return "ViewAssignedApplications";
	}
	
	@RequestMapping("/showReportBugsPage")
	public String showReportBugsPage(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("bugDetails") BugDetailsBO bugDetails, Model model,@RequestParam int appID,@RequestParam String appName){
		System.out.println("SHOW REPORT BUGS ::: METHODNAME ::: showReportBugsPage");
		
		System.out.println("Application ID: "+appID+" Application Name: "+appName);
		ApplicationDetails appDetails = new ApplicationDetails();
		appDetails.setApplicationID(appID);
		appDetails.setAppName(appName);
		bugDetails.setAppDetails(appDetails);
		//model.addAttribute("appDetailsList", appDetailsList);
		return "ReportBugsPage";
	}
	
	@RequestMapping("/sendBugDetails")
	public ModelAndView sendBugDetails(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("bugDetails") BugDetailsBO bugDetails, Model model){
		System.out.println("REPORT BUGS ::: METHODNAME ::: sendBugDetails");
		HttpSession session = request.getSession();
		TesterDetails testerDetails = (TesterDetails) session.getAttribute("sessionTesterDetails");
		bugDetails.setTesterDetails(testerDetails);
		
		Date bugDate = new Date();
		SimpleDateFormat assistanceDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		ModelAndView modelAndView = new ModelAndView();
		if (bugDetails.getSeverity() == null || bugDetails.getSeverity() == "") {
			modelAndView.addObject("ERROR", "Please provide bug severity. Save was unsuccessful.");
			modelAndView.setViewName("/ReportBugsPage");
			return modelAndView;
		}
		if (bugDetails.getBugDetails() == null || bugDetails.getBugDetails() == "") {
			modelAndView.addObject("ERROR", "Please provide bug description. Save was unsuccessful.");
			modelAndView.setViewName("/ReportBugsPage");
			return modelAndView;
		}
		
		String DateToStr = assistanceDateFormat.format(bugDate);
        System.out.println(DateToStr);
        bugDetails.setDetectedDate(DateToStr);
        bugDetails.setBugStatus("SUBMITTED");
        try{
		String result = testerService.sendBugDetails(bugDetails);
		modelAndView.addObject("ERROR", "Bug reported sucessfully");
		modelAndView.setViewName("/ReportBugsPage");
		return modelAndView;
        }
        catch(Exception e){
        	//String firstWord = null;
        	String msg = e.getCause().toString();
        	if(msg.contains("Exception")){
        	   msg= msg.substring(msg.indexOf("Exception"),msg.length()); 
        	}
        	session.setAttribute("sessionTesterDetails", testerDetails);
		modelAndView.addObject("ERROR",msg );
		modelAndView.setViewName("/ReportBugsPage");
		return modelAndView;
		//return "ReportBugsPage";
		}
	}
	
	@RequestMapping("/showViewBugsPage")
	public ModelAndView showViewBugsPage(HttpServletRequest request,
			HttpServletResponse response,@ModelAttribute("bugDetails") BugDetailsBO bugDetails, Model model,@RequestParam int appID){
		System.out.println("SHOW REPORTED BUGS ::: METHODNAME ::: showViewBugsPage");
		
//		Set Application Details
		System.out.println("Application ID: "+appID);
		ApplicationDetails appDetails = new ApplicationDetails();
		appDetails.setApplicationID(appID);
		bugDetails.setAppDetails(appDetails);
		
//		Set Tester Details
		HttpSession session = request.getSession();
		TesterDetails testerDetails = (TesterDetails) session.getAttribute("sessionTesterDetails");
		bugDetails.setTesterDetails(testerDetails);
		
//		Fetch the Bug Details
		List<BugDetailsBO> bugList = new ArrayList<BugDetailsBO>();
		bugList = testerService.fetchBugList(testerDetails.getUserName(),appID);
		
		
//		Return the result Page
		return new ModelAndView ("TesterBugDetails","bugList",bugList);
	}
}
