<%-- 
    Document   : movies
    Created on : Dec 21, 2016, 2:18:46 PM
    Author     : taher
--%>

<%@page import="com.sun.xml.rpc.processor.modeler.j2ee.xml.string"%>
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.MovieItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Movies</title>

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

        <script type="text/javascript">
            function searchByYear()
            {
                var val = document.getElementById("year").value;
                location.href = "/Movie/MoviesByYear?year=" + val;
            }

            function searchByRate()
            {
                var val = document.getElementById("rate").value;
                location.href = "/Movie/MovieByRate?rate=" + val;
            }

            function searchByNameOrActor()
            {
                var val = document.getElementById("nameOrActor").value;
                var txt = document.getElementById("text").value;
                if (val == "name") {
                    location.href = "/Movie/MovieByName?name=" + txt;
                } else if (val == "actor") {
                    location.href = "/Movie/MovieByActor?actor=" + txt;
                }
            }

        </script>

    </head><!--/head-->
    <body>
        <%
            ArrayList<MovieItem> list = (ArrayList<MovieItem>) request.getAttribute("list");
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
                    <a class="navbar-brand" href="index.html"><img src="images/logo.png" alt="logo"></a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                    <li><a href="index.jsp">Home</a></li>
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

        <section id="portfolio" class="container" >
            <div class="widget search">

                <div class="row">
                    <div class="select-style col-md-1">
                        <select name="type" id="nameOrActor">
                            <option selected disabled>Search By</option>
                            <option value="name">Name</option>
                            <option value="actor">Actor</option>
                        </select>
                    </div>

                    <div class="input-group col-md-11">
                        <input name="text" id="text" type="text" class="form-control" autocomplete="off" placeholder="Search">

                        <span class="input-group-btn">
                            <button class="btn btn-danger" onclick="searchByNameOrActor()"><i class="icon-search"></i></button>
                        </span>
                    </div>
                </div>


                <div class="row">
                    <div class="searchCategories">
                        <div class="input-group">
                            <label class="col-md-2" style="padding: 6px 22px;">Year</label>
                            <div class="col-md-4">
                                <div class="select-style" >

                                    <%
                                        String year = request.getParameter("year");
                                        String rate = request.getParameter("rate");
                                    %>

                                    <select name="year" id = "year" onchange="searchByYear()" >
                                        <option value="2016">2016</option>
                                        <option <%= (year != null && year.equals("2015")) ? "selected" : ""%> value="2015">2015</option>
                                        <option <%= (year != null && year.equals("2014")) ? "selected" : ""%> value="2014">2014</option>
                                        <option <%= (year != null && year.equals("2013")) ? "selected" : ""%> value="2013">2013</option>
                                        <option <%= (year != null && year.equals("2012")) ? "selected" : ""%> value="2012">2012</option>
                                        <option <%= (year != null && year.equals("2011")) ? "selected" : ""%> value="2011">2011</option>
                                        <option <%= (year != null && year.equals("2010")) ? "selected" : ""%> value="2010">2010</option>
                                        <option <%= (year != null && year.equals("2009")) ? "selected" : ""%> value="2009">2009</option>
                                        <option <%= (year != null && year.equals("2001")) ? "selected" : ""%> value="2001">2001</option>
                                    </select>
                                </div> 
                            </div>

                            <label class="col-md-2" style="padding: 6px 10px;">Rate</label>
                            <div class="col-md-4">
                                <div class="select-style">
                                    <select name="rate" id = "rate" onchange="searchByRate()">
                                        <option value="0">+0</option>
                                        <option <%= (rate != null && rate.equals("5")) ? "selected" : ""%> value="5">+5</option>
                                        <option <%= (rate != null && rate.equals("4")) ? "selected" : ""%> value="4">+4</option>
                                        <option <%= (rate != null && rate.equals("3")) ? "selected" : ""%> value="3">+3</option>
                                        <option <%= (rate != null && rate.equals("2")) ? "selected" : ""%> value="2">+2</option>
                                        <option <%= (rate != null && rate.equals("1")) ? "selected" : ""%> value="1">+1</option>
                                    </select>
                                </div> 
                            </div> 
                        </div>
                    </div>
                </div>

            </div><!--/.search-->        

            <ul class="portfolio-filter">
                <li><a class="btn btn-default active" href="#" data-filter="*">All</a></li>
                <li><a class="btn btn-default" href="#" data-filter=".action">Action</a></li>
                <li><a class="btn btn-default" href="#" data-filter=".animation">Animation</a></li>
                <li><a class="btn btn-default" href="#" data-filter=".comedy">Comedy</a></li>
                <li><a class="btn btn-default" href="#" data-filter=".family">Family</a></li>
                <li><a class="btn btn-default" href="#" data-filter=".drama">Drama</a></li>
                <li><a class="btn btn-default" href="#" data-filter=".history">History</a></li>

            </ul><!--/#portfolio-filter-->

            <ul class="portfolio-items col-5">

                <%
                    for (int i = 0; i < list.size(); i++) {

                        String url = list.get(i).getPoster();
                        String name = list.get(i).getName();
                        int id = list.get(i).getId();

                        String cat = "";
                        ArrayList<String> temp = list.get(i).getCategory();
                        for (int f = 0; f < temp.size(); f++) {
                            String string = temp.get(f);
                            string = Character.toLowerCase(string.charAt(0)) + string.substring(1);
                            cat += string + " ";
                        }
                        
                        

                %>
                <li class="portfolio-item <%=cat%>">
                    <div class="item-inner">
                        <img src="<%=url%>" alt="">
                        <h5><%=name%></h5>
                        <div class="overlay">
                            <a  class="preview btn btn-danger" href="movieDetails?id=<%=id%>"><i class="icon-eye-open"></i></a>             
                        </div>           
                    </div>           
                </li><!--/.portfolio-item-->
                <%
                    }
                %>
            </ul>
            
            <%
                if (list.size() == 0) {
                    %>
                        <section id="error" class="container">
                            <h1>No Results</h1>
                        </section>
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
        <script src="js/jquery.isotope.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>
