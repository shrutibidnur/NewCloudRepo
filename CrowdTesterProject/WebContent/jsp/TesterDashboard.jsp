<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<script>
			function myFunction()
            {
				if (confirm('Are you sure you want to encash your points?')) {
				   //alert('You have encashed your points');
				   return true;
				} 
				return false;
   			 }
			</script>
		<jsp:include page="TesterOutline.jsp"  />	
			
			
			      <div id="page-wrapper">
			      <form:form id="testerDashboardID" method="post" modelAttribute="testerDetails" action="#">
				          <div class="row">
				              <div class="col-lg-12">
				                  <h1 class="page-header">Dashboard</h1>
				              </div>
				              
				          </div>
				          
				          <div class="row">
				               <div class="col-lg-3 col-md-6">
				                    <div class="panel panel-green">
				                        <div class="panel-heading">
				                            <div class="row">
				                                <div class="col-xs-3">
				                                    <i class="fa fa-tasks fa-5x"></i>
				                                </div>
				                                <div class="col-xs-9 text-right">
				                                    <div class="huge"></div>
				                                    <div>New Tasks!</div>
				                                </div>
				                            </div>
				                        </div>
					                        <a href="showViewApplications.do">
					                            <div class="panel-footer">
					                                <span class="pull-left">View Details</span>
					                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					                                <div class="clearfix"></div>
					                            </div>
					                        </a>
					                    </div>
					                </div>
					                
					           <div class="col-lg-3">
					                    <div class="panel panel-success">
					                        <div class="panel-heading">
					                            New Tasks
					                        </div>
					                        <div class="panel-body">
					                            <p>Click on New Tasks to view the tasks Assigned to You</p>
					                        </div>
					                        
					                    </div>
	                				</div>
	                				
	                		   <div class="col-lg-3 col-md-6">
				                    <div class="panel panel-yellow">
				                        <div class="panel-heading">
				                            <div class="row">
				                                <div class="col-xs-3">
				                                    <i class="fa fa-tasks fa-5x"></i>
				                                </div>
				                                <div class="col-xs-9 text-right">
				                                    <div class="huge"></div>
				                                    <div>Pending Tasks!</div>
				                                </div>
				                            </div>
				                        </div>
				                        <a href="showAssignedApplications.do">
				                            <div class="panel-footer">
				                                <span class="pull-left">View Details</span>
				                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
				                                <div class="clearfix"></div>
				                            </div>
				                        </a>
				                    </div>
	                			</div>
	                				
	                		   <div class="col-lg-3">
				                    <div class="panel panel-warning">
				                        <div class="panel-heading">
				                            Pending Tasks
				                        </div>
				                        <div class="panel-body">
				                            <p>Click on Pending Tasks to view your pending tasks</p>
				                        </div>
				                        
				                    </div>
	                		  </div>	
	                				
				             
				          </div>
				         
				         
						   <div class="row">
						   
								   <div class="col-lg-3 col-md-6">
					                    <div class="panel panel-primary">
					                        <div class="panel-heading">
					                            <div class="row">
					                                <div class="col-xs-3">
					                                    <i class="fa fa-support fa-5x"></i>
					                                </div>
					                                <div class="col-xs-9 text-right">
					                                    <div class="huge"></div>
					                                    <div>Redeem Credit points!</div><br/>
					                                    <div align="center" style="font-size: x-large;"><c:out value="${testerDetails.credit}"></c:out></div>
					                                </div>
					                            </div>
					                        </div>
					                        <a href="showPaymentSuccessPage.do"  onclick="return myFunction();">
					                            <div class="panel-footer">
					                                <span class="pull-left">Redeem your Credits</span>
					                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					                                <div class="clearfix"></div>
					                            </div>
					                        </a>
					                    </div>
			                	</div>
			
					               <div class="col-lg-3">
					                    <div class="panel panel-info">
					                        <div class="panel-heading">
					                            Redeem Points
					                        </div>
					                        <div class="panel-body">
					                            <p>Click on Redeem your Credits to encash your earned points.</p>
					                            <p>You can encash $ <c:out value="${testerDetails.credit * 3}"></c:out></p>
					                        </div>
					                        
					                    </div>
					                </div>
								   
								   
								   <div class="col-lg-3 col-md-6">
					                    <div class="panel panel-red">
					                        <div class="panel-heading">
					                            <div class="row">
					                                <div class="col-xs-3">
					                                    <i class="fa fa-support fa-5x"></i>
					                                </div>
					                                <div class="col-xs-9 text-right">
					                                    <div class="huge"></div>
					                                    <div>Ranking!</div>
					                                    <div align="center" style="font-size: x-large;"><c:out value="${testerDetails.ranking}"></c:out></div>
					                                </div>
					                            </div>
					                        </div>
					                        
					                            <div class="panel-footer">
					                                <span class="pull-left"></span>
					                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					                                <div class="clearfix"></div>
					                            </div>
					                       
					                    </div>
			                		</div>
			
					               <div class="col-lg-3">
			                    <div class="panel panel-danger">
			                        <div class="panel-heading">
			                            Your Rank
			                        </div>
			                        <div class="panel-body">
			                        <c:choose>
			                        	
			                        	<c:when test="${testerDetails.ranking == 1}">
			                        		<p>Congratulations!!! Keep your good work going.</p>
			                        	</c:when>
			                        	<c:when test="${testerDetails.ranking == 2}">
			                        		<p>Good Work. More testing for more profit!!</p>
			                        	</c:when>
			                        	<c:when test="${testerDetails.ranking == 3}">
			                        		<p>Welcome... Climb the ladder of success by testing.</p>
			                        	</c:when>
			                        	<c:otherwise>
			                        		<p>Earn more credits to get a Ranking!!!</p>
			                        	</c:otherwise>
			                        </c:choose>
			                            
			                        </div>
			                        
			                    </div>
			                </div>
						   
						   
						   </div>			         
			        </form:form> 
			      </div>
       

    <!-- /#wrapper -->

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