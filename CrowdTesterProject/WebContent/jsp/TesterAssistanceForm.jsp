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
	 <div id="assist-page-wrapper">
		<h1><a>Assistance Form</a></h1>
		<form:form id="AssistanceFormID" method="post" modelAttribute="assistanceForm" action="sendQuery.do">
					<div class="form_description">
			<h2>Assistance Form</h2>
			<p>Please describe your query in detail & send</p>
		</div>						
			<ul >
			
					<li id="li_1" >
		<label class="description" for="element_1">To </label>
		<div>
			<form:input path="toEmail" />
			<!-- <input id="element_1" name="element_1" class="element text medium" type="text" maxlength="255" value=""/>  -->
		</div> 
		</li>		<li id="li_2" >
		<label class="description" for="element_2">Subject </label>
		<div>
			<form:input path="subject" />
			<!-- <input id="element_2" name="element_2" class="element text large" type="text" maxlength="255" value=""/>  -->
		</div> 
		</li>		<li id="li_4" >
		<label class="description" for="element_4">Severity </label>
		<div>
		
		<form:select path ="severity">
                         <form:option value="Low">LOW</form:option>
                         <form:option value="Medium">MEDIUM</form:option>
                         <form:option value="High">HIGH</form:option>
                         <form:option value="Critical">CRITICAL</form:option>
                    </form:select>
		
		<!-- <select class="element select medium" id="element_4" name="element_4"> 
			<option value="1" selected="selected">Low</option>
<option value="2" >Medium</option>
<option value="3" >High</option>
<option value="4" >Critical</option>

		</select> -->
		</div> 
		</li>		<li id="li_3" >
		<label class="description" for="element_3">Description </label>
		<div>
		<form:textarea path="description" />
			<!-- <textarea id="element_3" name="element_3" class="element textarea medium"></textarea>  -->
		</div> 
		</li>
			
					<li class="buttons">
			    <input type="hidden" name="form_id" value="1076669" />
			    
				<input id="saveForm" class="button_text" type="submit" name="submit" value="Send Email" />
		</li>
			</ul>
		</form:form>	
		
	</div>
	<img id="bottom" src="../images/bottom.png" alt=""/>
	
</div>	
	 <!-- jQuery -->
    <script src="../javascript/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../javascript/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../javascript/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../javascript/sb-admin-2.js"></script>
	
	</body>
</html>