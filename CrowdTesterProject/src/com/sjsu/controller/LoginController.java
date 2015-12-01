package com.sjsu.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sjsu.BO.LoginDetails;
import com.sjsu.BO.TesterDetails;
import com.sjsu.BO.UserTypeBO;
import com.sjsu.service.ILoginService;


@Controller
@RequestMapping("login")
public class LoginController {

	private ILoginService loginService;
	 
	public ILoginService getLoginService() {
		return loginService;
	}

	@Autowired
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping("/showHomePage")
	public ModelAndView showHomePage(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("loginDetails") LoginDetails loginDetails, Model model){
		
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("HIiiiii");
		modelAndView.setViewName("/HomePage");;
		return modelAndView;
	}
	
	@RequestMapping("/showLogin")
	public String showLogin(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("loginDetails") LoginDetails loginDetails, Model model){
		System.out.println("HIiiiii");
		model.addAttribute("loginDetails", loginDetails);
		return "LoginPage";
	}
	
	@RequestMapping("/authenticate")
	public ModelAndView helloWorld(HttpServletRequest request,
			HttpServletResponse response,/* @Valid*/ @ModelAttribute("loginDetails") LoginDetails loginDetails, BindingResult result, Model model, final RedirectAttributes redirectAttributes) throws IOException {
		System.out.println(loginDetails.getUserName());
		System.out.println(loginDetails.getPassword());
		ModelAndView modelAndView = new ModelAndView();
		String userName = loginDetails.getUserName();
		String password = loginDetails.getPassword();
		if (userName == null || userName == "") {
			modelAndView.addObject("ERROR", "Please provide Username.");
			modelAndView.setViewName("/LoginPage");
			return modelAndView;
		}
		if (password == null || password == "") {
			modelAndView.addObject("ERROR", "Please provide Password.");
			modelAndView.setViewName("/LoginPage");
			return modelAndView;
		}
		
		UserTypeBO userTypeBO = loginService.authenticateUser(loginDetails);
		
		if (userTypeBO.getUserType().equalsIgnoreCase("NONE")) {
			modelAndView.addObject("ERROR", "Invalid Username / Password.");
			modelAndView.setViewName("/LoginPage");
			return modelAndView;
		} 
		else if (userTypeBO.getUserType().equalsIgnoreCase("TESTER")) {
			System.out.println("CONTROLLER::::" + userTypeBO.getTesterDetails());
			model.addAttribute("testerDetails", userTypeBO.getTesterDetails());
			redirectAttributes.addFlashAttribute("testerDetails", userTypeBO.getTesterDetails());
			modelAndView.setViewName("redirect:" + "../tester/setTesterDetails.do");
			return modelAndView;
			//return "redirect:" + "../tester/showTesterDashboard.do";
		} else {
			System.out.println("Login CONTROLLER::::" + userTypeBO.getAppVendorDetails());
			model.addAttribute("appVendorDetails", userTypeBO.getAppVendorDetails());
			redirectAttributes.addFlashAttribute("appVendorDetails", userTypeBO.getAppVendorDetails());
			modelAndView.setViewName("redirect:" + "../appVendor/setAppProviderDetails.do");
			return modelAndView;
		}
	}

	
	
	
	
	@RequestMapping("/showForgotPasswordPage")
	public String showForgotPasswordPage(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("testerDetails") TesterDetails testerDetails, Model model){
		System.out.println("::::::showForgotPasswordPage:::::");
		model.addAttribute("testerDetails", testerDetails);
		return "ForgotPasswordPage";
	}
	
	@RequestMapping("/verifyEmail")
	public String verifyEmail(HttpServletRequest request,
			HttpServletResponse response, @ModelAttribute("testerDetails") TesterDetails testerDetails, Model model){
		System.out.println("::::::verifyEmail:::::");
		System.out.println(testerDetails.getEmail());
		return "SuccessPage";
	}
	
	
	

}

