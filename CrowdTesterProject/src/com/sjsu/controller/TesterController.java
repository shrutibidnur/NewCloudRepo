package com.sjsu.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
		String appVendorEmail = testerService.getAppVendorUsername(appId);
		assistanceForm.setToEmail(appVendorEmail);
		assistanceForm.setTesterusername(testerDetails.getUserName());
		System.out.println("ASSITANCE FORM: "+assistanceForm);
		return "TesterAssistanceForm";
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
	public String editTesterProfile(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("testerDetails") TesterDetails testerDetails, Model model){
		System.out.println("Edit And Save TESTER Details :: METHODNAME :: editTesterProfile");
		HttpSession session = request.getSession();
		TesterDetails oldTesterDetails = (TesterDetails) session.getAttribute("sessionTesterDetails");
		oldTesterDetails.setFirstName(testerDetails.getFirstName());
		oldTesterDetails.setLastName(testerDetails.getLastName());
		oldTesterDetails.setAge(testerDetails.getAge());
		oldTesterDetails.setAddress(testerDetails.getAddress());
		if (testerDetails.getPhoneNumber() != null) {
			oldTesterDetails.setPhoneNumber(testerDetails.getPhoneNumber());
		}
		oldTesterDetails.setPassword(testerDetails.getPassword());
		oldTesterDetails.setEducation(testerDetails.getEducation());
		oldTesterDetails.setOccupation(testerDetails.getOccupation());
		System.out.println(oldTesterDetails);
		String result = testerService.editTesterProfile(oldTesterDetails);
		if (result.equalsIgnoreCase("SUCCESS")) {
			return "TesterProfileForm";
		}
		return "TesterAssistForm";
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
	    	formDetailsJson.put("appId" , p.getAppVendorUsername());
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
		
		appDetailsList = testerService.getMatchedAppDetails(testerDetails.getPreferredTestLang());
		System.out.println("Matched Applications: " +appDetailsList);
		model.addAttribute("appDetailsList", appDetailsList);
		return "ViewApplications";
	}

	
	@RequestMapping("/testThisApp")
	public String testThisApplication(HttpServletRequest request,
			HttpServletResponse response, Model model,@RequestParam String appID){
		System.out.println("METHODNAME ::: testThisApplication ::: Asign Application to tester Addition in mapping table");
		HttpSession session = request.getSession();
	    String applicationId = appID;
		MappingTesterAppBO mappingTesterApp = new MappingTesterAppBO();
		mappingTesterApp.setApplicationId(applicationId);
		TesterDetails testerDetails = (TesterDetails) session.getAttribute("sessionTesterDetails");
		mappingTesterApp.setTesterUsername(testerDetails.getUserName());
		mappingTesterApp.setStatus("ASSIGNED");
		System.out.println(mappingTesterApp);
		String result = testerService.testThisApplication(mappingTesterApp);
		
		return result;
	}
}
