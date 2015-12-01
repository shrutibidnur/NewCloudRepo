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

import com.sjsu.BO.AppPlatformDetailsBO;
import com.sjsu.BO.AppVendorDetails;
import com.sjsu.BO.ApplicationDetails;
import com.sjsu.BO.TesterDetails;
import com.sjsu.BO.AppVendorDetails;
import com.sjsu.service.IAppVendorService;


@Controller
@RequestMapping("appVendor")
public class AppVendorController {
	
	private IAppVendorService appVendorService;
	
	public IAppVendorService getAppVendorService() {
		return appVendorService;
	}

	@Autowired
	public void setAppVendorService(IAppVendorService appVendorService) {
		this.appVendorService = appVendorService;
	}
	
	@RequestMapping("/setAppProviderDetails")
	public String setAppProviderDetails(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("appVendorDetails") AppVendorDetails appVendorDetails, Model model){
		System.out.println("dashboard"+appVendorDetails);
		AppVendorDetails appVendorInfo = new AppVendorDetails();
		appVendorInfo = appVendorService.setAppProviderDetails(appVendorDetails.getUserName());
		model.addAttribute("testerDetails", appVendorInfo);
		HttpSession session = request.getSession();
		session.setAttribute("sessionAppVendorDetails", appVendorInfo);
		return "redirect:" + "showAppVendorDashboard.do";
	}
	
	@RequestMapping("/showAppVendorDashboard")
	public String showAppVendorDashboard(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("appVendorDetails") AppVendorDetails appVendorDetails, Model model){
		
		HttpSession session = request.getSession();
		//session.setAttribute("sessionAppVendorDetails", appVendorDetails);
		session.getAttribute("sessionAppVendorDetails");
		model.addAttribute("appVendorDetails", appVendorDetails);
		
		return "AppVendorDashboard";
	}
	
	@RequestMapping("/showAppVendorProfileform")
	public String showProfileform(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("appVendorDetails") AppVendorDetails appVendorDetails, Model model){
		System.out.println("HIiiiii");
		HttpSession session = request.getSession();
		appVendorDetails = (AppVendorDetails) session.getAttribute("sessionAppVendorDetails");
		System.out.println("profile"+appVendorDetails);
		
		
		model.addAttribute("appVendorDetails", appVendorDetails);
		return "AppVendorProfileForm";
	}
	
	
	@RequestMapping("/showAppVendorAssistform")
	public String showAssistform(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("appVendorDetails") AppVendorDetails appVendorDetails, Model model){
		System.out.println("HIiiiii");
		model.addAttribute("appVendorDetails", appVendorDetails);
		return "AppVendorAssistForm";
	}
	

	@RequestMapping("/showUploadAppForm.do")
	public String showBillingform(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("uploadAppBO") ApplicationDetails UploadAppBO, Model model){
		System.out.println("HIiiiii");
		model.addAttribute("UploadAppBO", UploadAppBO);
		return "UploadAppOutline";
	}
	@RequestMapping("/editAppVendorProfile")
	public ModelAndView editAppVendorProfile(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("appVendorDetails") AppVendorDetails appVendorDetails, Model model){
		System.out.println("Edit And Save App Vendor Details :: METHODNAME :: editAppVendorProfile");
		HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView();
		if (appVendorDetails.getCompanyName() == null || appVendorDetails.getCompanyName() == "") {
			modelAndView.addObject("ERROR", "Please provide Company Name. Save was unsuccessful");
			modelAndView.setViewName("/AppVendorProfileForm");
			return modelAndView;
		}
		if (appVendorDetails.getContactEmail() == null || appVendorDetails.getContactEmail() == "") {
			modelAndView.addObject("ERROR", "Please provide Company email. Save was unsuccessful");
			modelAndView.setViewName("/AppVendorProfileForm");
			return modelAndView;
		}
		System.out.println(appVendorDetails.getPassword() +"::"+appVendorDetails.getConfirmPassword());
		if (!appVendorDetails.getPassword().equals(appVendorDetails.getConfirmPassword())) {
		//if (appVendorDetails.getPassword().toString() != appVendorDetails.getConfirmPassword().toString()) {
			modelAndView.addObject("ERROR", "Passwords not matching. Save was unsuccessful");
			modelAndView.setViewName("/AppVendorProfileForm");
			return modelAndView;
		}
		
		AppVendorDetails oldAppVendorDetails = (AppVendorDetails) session.getAttribute("sessionAppVendorDetails");
		oldAppVendorDetails.setCompanyName(appVendorDetails.getCompanyName());
		oldAppVendorDetails.setContactEmail(appVendorDetails.getContactEmail());
		//oldAppVendorDetails.setAge(appVendorDetails.getPhoneNumber());
		oldAppVendorDetails.setAddress(appVendorDetails.getCompanyWebsite());
		if (appVendorDetails.getPhoneNumber() != null) {
			oldAppVendorDetails.setPhoneNumber(appVendorDetails.getPhoneNumber());
		}
		oldAppVendorDetails.setPassword(appVendorDetails.getPassword());
		System.out.println(oldAppVendorDetails);
		String result = appVendorService.editAppVendorProfile(oldAppVendorDetails);
		if (result.equalsIgnoreCase("SUCCESS")) {
			modelAndView.addObject("ERROR", "");
			modelAndView.setViewName("/AppVendorProfileForm");
			return modelAndView;
			//return "AppVendorProfileForm";
		}	
		modelAndView.addObject("ERROR", "");
		modelAndView.setViewName("/AppVendorAssistForm");
		return modelAndView;
		
		//return "AppVendorAssistForm";

	}
	
	@RequestMapping("/showAppSaveform")
	public ModelAndView showProfileform(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("uploadAppBO") ApplicationDetails uploadAppBO, Model model){
		
		System.out.println("HI shib");
		uploadAppBO.setCost(0);
		//String result = appVendorService.saveAppDetails(uploadAppBO);
		System.out.println("HIiiiii");
		AppPlatformDetailsBO appPlatformDetailsBO = new AppPlatformDetailsBO();
		model.addAttribute("appPlatformDetailsBO", appPlatformDetailsBO);
		List<String> testPlatformList = new ArrayList<String>();
		testPlatformList.add("iOS");
		testPlatformList.add("Android");
		testPlatformList.add("Windows");
		testPlatformList.add("Blackberry");
		appPlatformDetailsBO.setTestPlatformList(testPlatformList);
		
		/*appPlatformDetailsBO.setApp_Details_Application_ID(uploadAppBO);
		System.out.println("SAVED APPLICATION DETAILS ::: " + uploadAppBO);
		*/
		HttpSession session = request.getSession();
		session.setAttribute("sessionAppDetails", uploadAppBO);
		return new ModelAndView("PaymentSchemaOutline", "appPlatformDetailsBO", appPlatformDetailsBO);
//		return "PaymentSchemaForm";
	}
	
	@RequestMapping("/uploadApplication")
	public String uploadApplication(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("appPlatformDetailsBO") AppPlatformDetailsBO appPlatformDetailsBO, Model model){
		
		HttpSession session = request.getSession();
		ApplicationDetails appDetails = (ApplicationDetails) session.getAttribute("sessionAppDetails");
		
		//appPlatformDetailsBO.setApp_Details_Application_ID(appDetails);
		String testingPlatform = "";
		System.out.println("ENTERED METHOD ::: uploadApplication ::: AppPlatformDetailsBO ::: " +appPlatformDetailsBO);
		Iterator<String> iterator = appPlatformDetailsBO.getTestPlatformList().iterator();
		while (iterator.hasNext()) {
			testingPlatform = testingPlatform + iterator.next() + "#$#";
		}
		if (testingPlatform.endsWith("#$#")) {
			testingPlatform = testingPlatform.substring(0, testingPlatform.length()-3);
		}
		System.out.println(testingPlatform);
		appPlatformDetailsBO.setTestingPlatform(testingPlatform);
		appPlatformDetailsBO.setNo_of_testing_Platform(appPlatformDetailsBO.getTestPlatformList().size());
		System.out.println("AFTER CHANGES ::: " +appPlatformDetailsBO);
		
		
		String result = appVendorService.saveAppDetails(appDetails,appPlatformDetailsBO);
		
		System.out.println("saving app and platform details, result = "+result);
		
		return "SuccessPage";
	}
	
	@RequestMapping("/ajaxCalculateAmount")
	public @ResponseBody int ajaxCalculateAmount(@RequestParam("noOfTesters") int noOfTesters,@RequestParam("cost") int cost,@RequestParam("platformSelected") int platformSelected, HttpServletRequest request){
		System.out.println("Entered Ajax Method ::: METHODNAME ::: ajaxShowTestDetails");
		cost = 0;
		if(noOfTesters <= 5) {
			cost = cost + 500;
		} else if (noOfTesters <= 10) {
			cost = cost + 1000;
		} else if (noOfTesters <= 15) {
			cost = cost + 1500;
		} else {
			int extraTesters = noOfTesters - 15;
			int extraCost = extraTesters * 200;
			cost = cost + 1500 + extraCost;
		}
		
		cost = cost + platformSelected * 1000;
		HttpSession session = request.getSession();
		ApplicationDetails appDetails = (ApplicationDetails) session.getAttribute("sessionAppDetails");
		appDetails.setCost(cost);
		System.out.println("saving cost into db");
		return cost;
		
		
	}
	
	
	
}
