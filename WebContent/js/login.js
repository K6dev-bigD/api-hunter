
$(document).ready(function(){

	$.ajaxSetup ({
	    // Disable caching of AJAX responses
	    cache: false
	});
		
	$("#userId").focus();
	$("#login-menus").remove();


});  //End of document.ready function

function loginValidate() {
	var userId = $("#userId").val();
	var password = $("#password").val();
	
	var loginFlag = true;
	var loginError ='';
	
	if(userId == '' || password == ''){
		loginError +='<p>Email/Password cannot be blank</p>';
		loginFlag = false;
	}else if(password.length < 4){
		loginError +='<p>Invalid Credentials</p>';
		loginFlag = false;
	}else{
		var emailRegexStr = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		var isvalid = emailRegexStr.test(userId);
		if (!isvalid) {
			loginError +='<p>Invalid Credentials</p>';
			loginFlag = false;
		}	
	}

	if(loginFlag == false){
		$("#loginError").html(loginError);
		$("#userId").focus();
		return false;
	}else{
		return true;
	}
	
}