<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>API Hunter</title>
<script src="js/common.js"></script>
<script src="data.js"></script>
<script src="js/welcome.js"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
</head>
<body>
	<div>
	
		<div id="header">
			<div id="wrapper">
				<div id="headerContent">
					<a id="logo" href="#">
						<img src="images/logo.png" alt="API Hunter"/>
					</a>
				</div>
			</div>
		</div>
		
		<div id="breadCrumps" class="breadCrum">
			<span>&nbsp;</span>
		</div>

		<div id="mainContent">
			<div style="float: left;width: 45%;border: 1px solid;height: 300px;margin-right: 10px;"></div>
			<div style="float: left;width: 45%;border: 1px solid;height: 300px;" id="api-candidate">
				<h3>Api Candidates</h3>
				<div id="api-candidate-content"></div>
			</div>
	     </div>
	        

		
	</div>
	
	
</body>
</html>