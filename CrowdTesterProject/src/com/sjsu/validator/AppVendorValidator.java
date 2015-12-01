package com.sjsu.validator;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.sjsu.BO.AppVendorDetails;;

@Component
public class AppVendorValidator implements Validator {

	
	
	private static final String NULL = null;

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("hi bin1");
    		AppVendorDetails appVendorDetails = (AppVendorDetails) target;
    		
    		/*ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    		Properties properties = new Properties();
    		try {
				properties.load(classLoader.getResourceAsStream("validation.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
    		/*ModelAndView mav= new ModelAndView();
    		mav.addAllObjects(((ModelAndView) errors).getModel());
    		*/
    		
    		System.out.println("hi bin");
    		
    		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "companyName");
    		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "phoneNumber");
    		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address");
    		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyWebsite", "companyWebsite");
    		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactEmail", "contactEmail");
    		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName");
    		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password");
    		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","confirmPassword");
    		
    		
    		final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
    				"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
    				"(\\.[A-Za-z]{2,})$";

    		Pattern pattern;
    		Matcher matcher;
    		 pattern = Pattern.compile(EMAIL_PATTERN);
    		 if(appVendorDetails.getContactEmail() == null || appVendorDetails.getContactEmail().length() != 0) 
    		 {
    		 matcher = pattern.matcher(appVendorDetails.getContactEmail());
    			if(!matcher.matches()){
    					
    				errors.rejectValue("contactEmail", "Pattern.appVendorDetails.email");
    			}
    		 }
    		
    			//String expression = "^(?=.{7,32}$)(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*((\\s?x\\s?|ext\\s?|extension\\s?)\\d{1,5}){0,1}$";  
    			

    			
    			 if(appVendorDetails.getPassword()!= NULL)
        		 {
        		 
    				 if (!appVendorDetails.getPassword().equals(appVendorDetails.getConfirmPassword())) {
    					 errors.rejectValue("confirmPassword", "Diff.appVendor.confirmPassword");
    				 }
        
        		 }
	

		}
	}
