<%-- 
    Document   : index
    Created on : Dec 21, 2016, 12:01:22 PM
    Author     : taher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home</title>
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
                    <li class="active"><a href="index.jsp">Home</a></li>
                    <li><a href="Movie">Movies</a></li>
                    <%
                        model.User userData=(User)session.getAttribute("userData");
                        if (userData == null) {
                         //   out.print("session.getAttribute(\"userData\")");
                            %>
                                <li><a href="login.html">Login</a></li>
                            <%
                        } else {
                                String username = userData.getFname();
                                
                            %>
                                <li><a href="profile.jsp"><%=username%></a></li>
                                    <%
                                         if(userData.getAdmin() == 1)
                                            {
                                         %>
                                                <li> <a href="admin.jsp">admin page</a></li>
                                          <%
                                        }
                                    %>
                                <li> <a href="logout">logout</a></li>
                                <!--<li><form action="logout" method="POST"><input type="submit" value="logout"/></form></li>-->
                           <%
                        }
                    %>
                    
                    
                </ul>
            </div>
        </div>
    </header><!--/header-->
    <section id="main-slider" class="no-margin">
        <div class="carousel slide wet-asphalt">
            <ol class="carousel-indicators">
                <li data-target="#main-slider" data-slide-to="0" class="active"></li>
                <li data-target="#main-slider" data-slide-to="1"></li>
                <li data-target="#main-slider" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="item active" style="background-image: url(http://image.tmdb.org/t/p/w1280/lubzBMQLLmG88CLQ4F3TxZr2Q7N.jpg)">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="carousel-content centered">
                                    <h2 class="animation animated-item-1">The Secret Life of Pets</h2>
                                    <p class="animation animated-item-2">The quiet life of a terrier named Max is upended when his owner takes in Duke, a stray whom Max instantly dislikes.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--/.item-->
                <div class="item" style="background-image: url(images/slider/bg2.jpg)">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="carousel-content center centered">
                                    <h2 class="boxed animation animated-item-1">Clean, Crisp, Powerful and Responsive Web Design</h2>
                                    <p class="boxed animation animated-item-2">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</p>
                                    <br>
                                   
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--/.item-->
                <div class="item" style="background-image: url(http://image.tmdb.org/t/p/w1280/z09QAf8WbZncbitewNk6lKYMZsh.jpg)">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="carousel-content centered">
                                    <h2 class="animation animated-item-1">Finding Dory</h2>
                                    <p class="animation animated-item-2">\"Finding Dory\" reunites Dory with friends Nemo and Marlin on a search for answers about her past. What can she remember? Who are her parents? And where did she learn to speak Whale?</p>
                                    
                                </div>
                            </div>
                            <div class="col-sm-6 hidden-xs animation animated-item-4">
                                <div class="centered">
                                    <div class="embed-container">
                                        <iframe frameborder="0" width="480" height="270" src="//www.dailymotion.com/embed/video/x3d7coe" allowfullscreen></iframe><br /><a href="http://www.dailymotion.com/video/x3d7coe_finding-dory-official-movie-trailer-1-ellen-degeneres-idris-elba-dominic-west-animation-2016-full-hd_shortfilms" target="_blank"></a> <i> <a href="http://www.dailymotion.com/80PoundGames" target="_blank">80PoundGames</a></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div><!--/.item-->
            </div><!--/.carousel-inner-->
        </div><!--/.carousel-->
        <a class="prev hidden-xs" href="#main-slider" data-slide="prev">
            <i class="icon-angle-left"></i>
        </a>
        <a class="next hidden-xs" href="#main-slider" data-slide="next">
            <i class="icon-angle-right"></i>
        </a>
    </section><!--/#main-slider-->

   
    </section><!--/#services-->

  

    <section id="testimonial" class="alizarin">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="center">
                        <h2>What our website do</h2>
                        <p>It is a website to rent movies.</p>
                    </div>
                    <div class="gap"></div>
                    <div class="row">
                        <div class="col-md-6">
                            <blockquote>
                                <p>Task #1</p>
                                <small>description <cite title="Source Title">task #1</cite></small>
                            </blockquote>
                            <blockquote>
                                <p>Task #3</p>
                                <small>description <cite title="Source Title">task #1</cite></small>
                            </blockquote>
                        </div>
                        <div class="col-md-6">
                            <blockquote>
                                <p>Task #2</p>
                                <small>description <cite title="Source Title">task #1</cite></small>
                            </blockquote>
                            <blockquote>
                                <p>Task #4</p>
                                <small>description <cite title="Source Title">task #1</cite></small>
                            </blockquote>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section><!--/#testimonial-->

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
    <script src="js/main.js"></script>
</body>
</html>