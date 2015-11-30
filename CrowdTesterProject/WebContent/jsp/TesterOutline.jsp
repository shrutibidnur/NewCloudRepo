<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tester Dashboard</title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->  
    <link href="../css/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <link rel="stylesheet" type="text/css" href="../css/Registration.css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

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
			          <jsp:include page="TesterSideBar.jsp"  />
			          <!-- /.navbar-static-side -->
			      </nav>
			      
			      
			      
			      </div>
			      </body>
			      </html>