<%-- 
    Document   : rentMovie
    Created on : Dec 25, 2016, 3:36:53 PM
    Author     : mohamed2
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>rent movie</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/main.css" rel="stylesheet">
    <link href="css/extra.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
  


</head><!--/head-->
<body>
    
    <%
        
    User currentUser = new User();
    
    try
    {
        currentUser = (User)session.getAttribute("userData");
        if(currentUser == null)
        {
            response.sendRedirect("404.html");
        }
    }
    catch(NullPointerException e)
    {
        response.sendRedirect("404.html");
    }
        
    %>
    <header class="navbar navbar-inverse navbar-fixed-top wet-asphalt" role="banner">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp"><img src="images/logo.png" alt="logo"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="Movie">Movies</a></li>
                    <li class="active"><a href="logout">Log out</a></li>
                </ul>
            </div>
        </div>
    </header><!--/header-->

    <section id="title" class="emerald">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <h1>rental page</h1>
                </div>
                <div class="col-sm-6">
                    <ul class="breadcrumb pull-right">
                        <li><a href="profile.jsp">profile</a></li>
                        <li class="active">rental page</li>
                    </ul>
                </div>
            </div>
        </div>
    </section><!--/#title--> 

    <section id="portfolio" class="container">
        <!--
        <ul class="portfolio-filter">
     
        </ul>--><!--/#portfolio-filter-->

                <div class="form-group">
                    <h2>Movie name</h2><br>
                        <h3 >
                            <%= request.getParameter("Name")%>
                        </h3>
                </div>
                
                <div class="form-group">
                        <h2>Price</h2><br>
                        <h3 >
                            <%= request.getParameter("RentalPrice")%>
                        </h3>
                </div>
                
                <br/>
                
                <%
                    if(request.getParameter("check").equals("1"))
                    {
                        %>
                            
                                <br>
                                <h1>Successfully, you will receive the product to your address ^_^</h1>
                          
                        <%
                    }
                    else if (request.getParameter("check").equals("2"))
                    {
                        %>
                        <form class="form-group">
                            <br>
                            <h1>you can not rent this movie</h1>
                        </form>
                        <%
                    }
                    else if (request.getParameter("check").equals("3"))
                    {
                        %>
                        <form class="form-group">
                            <br>
                            <h1>currently there is no CDs to this movie</h1>
                        </form>
                        <%
                    }
                %>
		
        
    </section><!--/#portfolio-->

    

    <footer id="footer" class="midnight-blue">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    &copy; 2013 <a target="_blank" href="http://shapebootstrap.net/" title="Free Twitter Bootstrap WordPress Themes and HTML templates">ShapeBootstrap</a>. All Rights Reserved.
                </div>
                <div class="col-sm-6">
                    <ul class="pull-right">
                        <li><a href="index.jsp">Home</a></li>
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
    <script src="js/jquery.isotope.min.js"></script>
    <script src="js/main.js"></script>
    
	
</body>
</html>
