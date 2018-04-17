
$(document).ready(function(){

	$.ajaxSetup ({
	    // Disable caching of AJAX responses
	    cache: false
	});
		
	alert(JSON.stringify(temp));
	
	
	$.get('ReadApiCandidate',{},function(responseText) { 
//		alert(JSON.stringify(responseText));
		if(responseText.flagFirst == "true"){
//			alert(responseText.number);
			$("#api-candidate-content").html(responseText.number+" Apis found");
		}else{
			var progress = '';
			progress +='<div class="progress" style="width: 80%;margin-left:30px;">';
			progress +='<div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:60%">';
			progress +='<span class="sr-only">40% Complete</span>';
			progress +='</div>';
			progress +='</div>';
			
			$("#api-candidate-content").html(progress);
		}
    });


});  //End of document.ready function
