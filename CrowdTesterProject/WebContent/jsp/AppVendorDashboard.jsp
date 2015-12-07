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

    <title>CLIENT DASHBOARD</title>



    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="../css/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="../css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="../css/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="../css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <link href="../css/tableStyle.css" rel="stylesheet">
    
    <link href="../css/Registration.css" rel="stylesheet">
    
    <link rel="stylesheet" href="../css/main.css">
    
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
                <a class="navbar-brand" href="AppVendorDashboard.jsp">CrowdTester</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                
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
               
            </ul>
            <!-- /.navbar-top-links -->
		<!-- CUT AND PASTER IN SEPERATE JSP -->
            <jsp:include page="AppVendorSideBar.jsp" flush="true" />
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                 <div class="col-lg-3 col-md-6">
                    <h3 class="page-header">Client Dashboard</h3>
               </div>
                <!-- /.col-lg-12 -->
                
                <div class="col-lg-3 col-md-6">
                   
                </div>
                <div class="col-lg-3 col-md-6">
                    
                </div>
                <div class="col-lg-3 col-md-6">
                    <h3 class="page-header"><button type="button" class="btn btn-primary">Create a new Project</button></h3>
                </div>
            </div>
            
        <div>
       
        
        <div id="box">
  <h3>Your Applications</h3>
  <form:form id="viewApplications" method="post" modelAttribute="appDetailsList" action="#">
  <div class="CSSTableGenerator" >
  <table width="100%" class='order-table table'>
      <tr height="70px">
        <td>App ID</td>
        <td>App Name</td>
        <td>Description</td>
        <td>Test DeadLine</td>
        <td>Download Link</td>
        <td>Cost Paid</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
   
      <c:forEach var="applications" items="${appDetailsList}">
      	<tr height="50px">
      		<td><c:out value="${applications.applicationID}"></c:out></td>
			<td><c:out value="${applications.appName}"></c:out></td>
			<td><c:out value="${applications.description}"></c:out></td>
			<td><c:out value="${applications.testDeadLine}"></c:out></td>
			<td><a href="#"><c:out value="${applications.downloadLink}"></c:out></a></td>
			<td><c:out value="${applications.cost}"></c:out></td>
			<td><a href="viewBugsForAppProvider.do?appID=${applications.applicationID}">View Bugs</a></td>
			<td><a href="viewTesters.do?appID=${applications.applicationID}">View Testers</a></td>
			</tr>
      </c:forEach>
    
  </table>
  </div>
  </form:form>
</div>
        
        </div>    

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="../javascript/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../javascript/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="../javascript/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="../javascript/raphael-min.js"></script>
    <script src="../javascript/morris.min.js"></script>
    <script src="../javascript/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="../javascript/sb-admin-2.js"></script>
</div>
</body>

 <footer class="clearFix">
  
</footer>
</html>