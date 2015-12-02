<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Assistance Form</title>
<!-- <link rel="stylesheet" type="text/css" href="../css/view.css" media="all"/> -->
<script type="text/javascript" src="../javascript/view.js"></script>

<!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet"/>

    <!-- MetisMenu CSS -->
    <link href="../css/metisMenu.min.css" rel="stylesheet"/>

    <!-- Custom CSS -->
    <link href="../css/sb-admin-2.css" rel="stylesheet"/> 

    <!-- Custom Fonts -->
    <link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    
    <link rel="stylesheet" type="text/css" href="../css/Registration.css"/>
    <!-- <link id="theme" rel="stylesheet" type="text/css" href="../css/profileHeaderStyle.css" title="theme" /> -->
    <link rel="stylesheet" type="text/css" href="../css/view.css" media="all"/>
<script type="text/javascript" src="../javascript/calendar.js"></script>

</head>
 <body id="main_body" > 
<!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">CrowdTester</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        
                        
                        <li><a href="../login/showLogin.do"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <!-- CUT AND PASTER IN SEPERATE JSP -->
            <jsp:include page="TesterSideBar.jsp" flush="true" />
            <!-- /.navbar-static-side -->
        </nav>
	
	<img id="top" src="../images/top.png" alt=""/>
	<div id="form_container">
	
		<h1><a>Bug Details Form</a></h1>
		<form:form id="BugFormID" method="post" modelAttribute="bugDetails" action="sendBugDetails.do">
		
					<div class="form_description">
			<h2>Bug Details Form</h2>
			<p></p>
		</div>						
			<ul >
			<li>
				<label class="appName" for="element_3">Application Name:  </label>
				 <form:input path="appDetails.appName" readonly="true"/> 
				 <form:hidden path="appDetails.applicationID" />
			</li>
			
					<li id="li_3" >
		<label class="description" for="element_3">Bug Severity * </label>
		<span>
		
		<form:radiobutton path="severity" value="Low"/><label class="choice" for="element_3_1">Low</label>
		<form:radiobutton path="severity" value="Medium"/><label class="choice" for="element_3_2">Medium</label>
		<form:radiobutton path="severity" value="High"/><label class="choice" for="element_3_3">High</label>
		<form:radiobutton path="severity" value="Critical"/><label class="choice" for="element_3_4">Critical</label>
		
		
		<!-- 	<input id="element_3_1" name="element_3" class="element radio" type="radio" value="1" />
<label class="choice" for="element_3_1">Low</label>
<input id="element_3_2" name="element_3" class="element radio" type="radio" value="2" />
<label class="choice" for="element_3_2">Medium</label>
<input id="element_3_3" name="element_3" class="element radio" type="radio" value="3" />
<label class="choice" for="element_3_3">High</label>
<input id="element_3_4" name="element_3" class="element radio" type="radio" value="4" />
<label class="choice" for="element_3_4">Critical</label> -->

		</span> 
		</li>		
				<li id="li_1" >
		<label class="description" for="element_1">Bug Description *	 </label>
		<div>
			<form:textarea id="element_1" name="element_1" class="element textarea medium" path="bugDetails" />
			<!-- <textarea id="element_1" name="element_1" class="element textarea medium"></textarea> --> 
		</div> 
		</li>
			
					<li class="buttons">
			    <input type="hidden" name="form_id" value="1077547" />
			    
				<input id="saveForm" class="button_text" type="submit" name="submit" value="Submit" />
		</li>
			</ul>
			<div align="center"><span class="error" style="color:red" >${ERROR}</span></div>
		</form:form>
	</div>
	<img id="bottom" src="../images/bottom.png" alt=""/>
	</body>
</html>