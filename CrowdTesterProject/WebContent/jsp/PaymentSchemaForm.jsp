<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<title>Payment Scheme</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<script type="text/javascript" language="javascript" src="../javascript/jquery-1.8.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/view.css" media="all"/>
<script type="text/javascript" src="../javascript/view.js"></script>
<link rel="stylesheet" type="text/css" href="../css/Registration.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>


</head>

<body id="main_body" >
<form:form method="post" modelAttribute="appPlatformDetailsBO" action="../appVendor/uploadApplication.do" class="register">

	<img id="top" src="../images/top.png" alt=""/>
	<div id="form_container">
	
		<h1><a>Payment Scheme</a></h1>
		<form  class="appnitro"  method="post" action="">
					<div class="form_description">
			<h2>Payment Scheme</h2>
			<!-- <p>Please describe your query in detail.</p> -->
		</div>						
			<ul >
			
		<li id="li_1" >
		<label class="description" for="element_1">No. of Testers </label>
		<div>
			<form:input path="no_of_Testers" id="noOfTestersID" name="element_1" class="element text medium" type="text" maxlength="255"/> 
		</div>
		<p class="guidelines" id="guide_1"><small>*Number of Testers should not exceed 20</small></p> 
		</li>		
		
		<li id="li_3" >
		<label class="description">Select Platforms </label>
		<span>
		<c:forEach items="${appPlatformDetailsBO.testPlatformList}" var="platform">
			<form:checkbox id="platformCheckbox" path="testPlatformList" value="${platform}" /><c:out value="${platform}"></c:out> <br/><br/>
		
		</c:forEach>
		

		</span><p class="guidelines" id="guide_3"><small>*Offer:Select 3 platform, you will get 1 platform free!</small></p> 
		</li>		<li id="li_2" >
		<label class="description" for="element_2">Total Cost </label>
		<span class="symbol">$</span>
		<span>
			<form:input path="app_Details_Application_ID.cost" id="costId"/>
			<!-- <input id="element_2_1" name="element_2_1" class="element text currency" size="10" value="" type="text" /> . -->		
			<label for="element_2_1">Dollars</label>
		</span>
		<!-- <span>
			<input id="element_2_2" name="element_2_2" class="element text" size="2" maxlength="2" value="" type="text" />
			<label for="element_2_2">Cents</label>
		</span> -->
		 
		</li>
			
					<li class="buttons">
					<button type="button" id="calculateAmount" value="calculateAmount">Calculate Amount</button>
					<!-- <input id="calculateAmount" class="button_text" type="submit" name="submit" value="Calculate Payment" /> -->
        <form:button id="saveProfileData" value="SAVE" disabled="true">PROCEED PAYMENT</form:button>
			    
				
		</li>
			</ul>
		</form>	
		
	</div>
	<img id="bottom" src="bottom.png" alt=""/>
	</form:form>
	</body>
</html>
 