<!DOCTYPE html>
<html>
<head>
<title>API Hunter</title>
<script src="js/jquery.min.js"></script>
<script src="filecount.js?v=<%=System.currentTimeMillis()%>"></script>
 <script>
 $(document).ready(function () {
 $("#header").load("header.html");

 $("#required-docs-content").html(temp.filesCount.RequirementDocs);
 $("#business-processes-content").html(temp.filesCount.BusinessProcesses);
 $("#refresh_btn").hide();
 $("#results_btn").hide();

 <%
 String cmd = request.getParameter("cmd");
 if(cmd != null && cmd.equals("hunt"))
 { 
 %>
 	enableProgressBar();
 	$.get('HunterLauncher?cmd=run', {}, function(result) { 	 });

<% } else { %>

	pollResults();

<% } %>

 });
 


 function enableProgressBar()
 {
	 var content = '';
		content +='<div id="hunt-in-progress">HUNTING IN PROGRESS</div>';
		content +='<div class="progress" style="width: 80%;margin-left:20px;margin-top: 20px;">';
		content +='<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:100%">';
		content +='<span class="sr-only">40% Complete</span>';
		content +='</div>';
		content +='</div>';
		$("#flag-content").html(content); 
		$("#refresh_btn").show();
		$("#results_btn").hide();
 }
 function pollResults()
 {
	//$("#refresh_btn").hide();
	//$("#results_btn").hide();
		
	 $.get('Processing_Status.json?v=<%=System.currentTimeMillis()%>',{},function(responseText) { 
//	 		alert(JSON.stringify(responseText));
			respStat = responseText.status;			
			if(respStat['Processing'] == "0"){
//				alert(responseText.number);
				var content = '';
				content +='<div id="api-count">'+respStat['Api Candidates']+'</div>';
				content +='<div>APIs FOUND</div>';
				$("#flag-content").html(content);
				$("#refresh_btn").hide();
				$("#results_btn").show();


			}else{
				enableProgressBar();

			}
	 });
 }
</script>
</head>
<style>
div.container_sm
{
  width: 1000px;
  display: block;
  margin-left: auto;
  margin-right: auto;
  color: #505050;
}

/* Added by Saravanakumar */
.my-panel{
	margin-top:100px;
	border: 1px solid #bbb;
	background-color: #f9f9f9;
	height: 320px;
}

#required-docs,#business-processes{
	float: left;
	width: 200px;
	height:80px;
	margin-bottom: 50px;
	margin-left: 30px;
}

#required-docs-content,#business-processes-content{
	color:#D40000;
	font-size:50px;
}

#upload-files-button{
	margin-bottom: 40px;
}

#results-button{
	margin-bottom: 40px;
	margin-top: 20px;
}

.font-red{
	color:#D40000;
}

#api-count{
	color:#D40000;
	font-size:80px;
}

#api-cand-main{
	height: 120px;
}

.flag-content-no-data{
	margin-top: 50px;
	font-size: 18px;
}
</style>
    
<body>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <div id="header"></div>
        
        <div class="section">
      <div class="container_sm">
        <div class="row">
          <div class="col-md-6">
          	<div class="my-panel">
	          	<div align="center">
		            <h1 class="font-red">API Hunting Sources</h1>
		            <p></p>
		            <p>Requirement specification documents, spread sheets,<br>
		             business processes in BPMN format, etc</p>
		 
		            <p></p>
	            </div>
	            
	            <div align="center">
		            <div id="required-docs">
		            	<div id="required-docs-content"></div>
		            	<div>REQUIREMENT DOCS</div>
		            </div>
		            
		            <div id="business-processes">
		            	<div id="business-processes-content"></div>
		            	<div>BUSINESS PROCESSES</div>
		            </div>
	            </div>
	            
	            <div id="upload-files-button" align="center">
	            	<a class="btn btn-primary" href="upload.jsp">Upload Files</a>
	            </div>
            </div>
            
          </div>
          <div class="col-md-6">
          	<div class="my-panel">
	          	<div align="center">
		            <h1 class="font-red">API Candidates</h1>
		        </div>
		        
		        <div align="center" id="api-cand-main">
		        
		        	<div id="flag-content"><div class="flag-content-no-data">No Data</div></div>

		        </div>
		        <p>&nbsp;</p>
		        <p>&nbsp;</p>
		         <div id="refresh-button" align="center">
		            <a id="refresh_btn" class="btn btn-primary" onClick="pollResults()">Refresh</a>
		      
		            <a id="results_btn" class="btn btn-primary" href="hunt-results.jsp">Explore</a>
		        </div>
            </div>
          </div>
        </div>
      </div>
    </div>
	    
</body>
</html>