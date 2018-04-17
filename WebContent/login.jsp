<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css"
    rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/theme.css">
<link rel="stylesheet" href="css/styles.css">
<title>API Hunter</title>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/image-scale.min.js" type="text/javascript"></script>
<script src="js/common.js"></script>
<script src="js/login.js"></script>


<style type="text/css">
#footerContent{
	border-top: none;
}

#login-main-content{
	float:left;
	width: 100%;
 	height: auto;
	background: url(img/banner.png);
}

#login-form{
	float: left;
 	margin: 550px;
}

.login-fields{
	margin-top: 10px;
	height: 30px;
	width: 250px;
}

.login-fields input[type=text], .login-fields input[type=password]{
	width: 250px;
	height: 25px;
	-webkit-appearance: none;
  	background: #fff;
  	border: 1px solid #d9d9d9;
  	border-top: 1px solid #c0c0c0;
  	border-radius: 2px;
  	padding: 0 8px;
  	box-sizing: border-box;
  	-moz-box-sizing: border-box;
}

.login-fields input[type=submit]{
	float: right;
	width: 150px;
	padding: 3px;
}
.container {
	margin-top: 15px; 
	margin-left: auto;
	max-width: 600px;
}

body {
//  overflow:hidden;
  margin:0px;
  //background-image: url('img/banner.png'); width:1440px; 
}
</style>
</head>
<body>
<div class="image-container" style="width:100%; height:540px; overflow:hidden;">
<img src="img/banner.png" width="100%"/> 
</div>
<div id="login_panel" style="margin: auto; width:100%;  background: white; horizontal-align:center ">

      <div class="container">
        <div class="column">
          <div class="col-md-12">
            <form role="form" action="home.jsp" method="GET">
              <div class="form-group">
                <input class="form-control" id="exampleInputEmail1"
                placeholder="Username" type="text">
              </div>
              <div class="form-group">
                <input class="form-control" id="exampleInputPassword1"
                placeholder="Password" type="password">
              </div>
              <button type="submit" class="btn btn-default">Login</button>
            </form>
          </div>
        </div>
      </div>
    </div>


<!--  <div id="test_login-main-content">
		<div id="login-form">
			<div class="login-fields">
				<input type="text" name="userId" id="userId" placeholder="User Id">
			</div>
			<div class="login-fields">
				<input type="password" name="password" id="password" placeholder="Password">
			</div>
			<div class="login-fields">
				<input type="submit" name="login" value="Login">
			</div>
	    </div>
	</div>
</div>
-->
	


	
</body>
</html>