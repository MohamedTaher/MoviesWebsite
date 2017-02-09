<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Registration</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    <%@ page import="model.User" %>
    <script type="text/javascript">
        function saveForm()
        {
           document.getElementById("FieldofCode").innerHTML="<input name=\"code\" id=\"code\" type=\"password\" placeholder=\"ValidationCode\" required/>"
        }
        function emailCheck(){
            var email = document.getElementById('email').value;
            
            var xmlhttp=new XMLHttpRequest();
            var params = "email="+email;
            
            xmlhttp.open("POST","emailcheck",true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.setRequestHeader("Content-length", params.length);
            xmlhttp.setRequestHeader("Connection", "close");

            xmlhttp.onreadystatechange=function()
              {
              if(xmlhttp.readyState==4 && xmlhttp.status==200)
                {
                 if(xmlhttp.responseText == '0'){
                    
                    document.getElementById('msg').innerHTML='Email already exists.';
                 }
                 else{
                     document.getElementById('msg').innerHTML='Email valid.';
                     document.getElementById('tnc').style.display='block';
                 }
                }
                
              }
            xmlhttp.send(params);
             
        }
        function checkPassword()
        {
            var password=document.getElementById("password").value;
            var confirmPassword=document.getElementById("password_confirm").value;
            if(password!=confirmPassword)
            {
                document.getElementById("passwordText").innerHTML="password and confirm password the same";
                var value=document.getElementById("password_confirm");
                value.value=value.defaultValue;
            }
            else{
                document.getElementById("passwordText").innerHTML="";
                
            }
        }
        
        function emailSyntaxValid()
        {
            
             if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(document.getElementById("email").value))
              {
                  emailCheck();
                    return (true);
              }
                document.getElementById('msg').innerHTML="You have entered an invalid email address!";
                return (false);
        }
        
        
    
    </script>
</head><!--/head-->
<body>
    <header class="navbar navbar-inverse navbar-fixed-top wet-asphalt" role="banner">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"><img src="images/logo.png" alt="logo"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="Movie">Movies</a></li>
                    <li><a href="login.html">Login</a></li>
                </ul>
            </div>
        </div>
    </header><!--/header-->

    <section id="title" class="emerald">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <h1>Registration</h1>
                </div>
                <div class="col-sm-6">
                    <ul class="breadcrumb pull-right">
                        <li><a href="index.jsp">Home</a></li>
                        <li class="active">Registration</li>
                    </ul>
                </div>
            </div>
        </div>
    </section><!--/#title-->     

    <section id="registration" class="container">
        <form class="center" role="form" method="POST" action="register" >
            <fieldset class="registration-form">
<!--                <div class="form-group">
                    <input type="text" id="username" name="username" placeholder="Username" class="form-control">
                </div>-->
				<div class="form-group">
                    <input type="text" id="first_name" name="first_name" required placeholder="First name" class="form-control">
                </div>
				<div class="form-group">
                                    <input type="text" id="last_name" name="last_name" placeholder="Last name" class="form-control" required>
                </div>
				
				<div class="form-group">
                                    <input type="text" id="address" name="address" placeholder="address" class="form-control" required>
                </div>
				
				<div class="form-group">
                                    <input type="number" id="phone_number" name="phone_number" placeholder="(+02)0000000" class="form-control" required>
                </div>
                <div class="form-group">
                    <input type="text" id="email" name="email" placeholder="E-mail" class="form-control" onblur="emailSyntaxValid()" required><div id="msg"> </div><br>
                    <div id="tnc" style="display: none;">
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="password" placeholder="Password" class="form-control" required>
                </div>
                <div class="form-group">
                    <input type="password" id="password_confirm" name="password_confirm" placeholder="Password (Confirm)" class="form-control" required onblur="checkPassword()">
                </div>
                    
                    <div class="form-group"id="passwordText">
                        
                    </div>
                    <br/>
                <div class="form-group">
                    <input class="btn btn-success btn-md btn-block" type="checkbox" id="adminCode" name="adminCode" value="asadmin" onclick="saveForm()"/>
                </div>

                <div class="from-group" id="FieldofCode">
                    
                </div>
                <br/>
                <div class="form-group">
                    <button class="btn btn-success btn-md btn-block" onclick="register">Register</button>
                </div>
            </fieldset>
        </form>
    </section><!--/#registration-->

    <footer id="footer" class="midnight-blue">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    &copy; 2013 <a target="_blank" href="http://shapebootstrap.net/" title="Free Twitter Bootstrap WordPress Themes and HTML templates">ShapeBootstrap</a>. All Rights Reserved.
                </div>
                <div class="col-sm-6">
                    <ul class="pull-right">
                        <li><a href="index.html">Home</a></li>
                        <li><a href="#">About Us</a></li>
                        <li><a href="contact-us.html">Contact Us</a></li>
                        <li><a id="gototop" class="gototop" href="#"><i class="icon-chevron-up"></i></a></li><!--#gototop-->
                    </ul>
                </div>
            </div>
        </div>
    </footer><!--/#footer-->

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script>
    <script src="js/main.js"></script>
</body>
</html>