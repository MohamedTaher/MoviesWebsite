<%-- 
    Document   : transactions
    Created on : Dec 26, 2016, 4:57:25 PM
    Author     : mohamed2
--%>

<%@page import="connection.DBConnection"%>
<%@page import="java.sql.*"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Admin</title>
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
    //System.out.print("2");
    try
    {
        currentUser = (User)session.getAttribute("userData");
        if(currentUser.getAdmin() == 0)
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
                <a class="navbar-brand" href="index.html"><img src="images/logo.png" alt="logo"></a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="Movie">Movies</a></li>
                    <li class="active"><a href="index.jsp">Log out</a></li>
                </ul>
            </div>
        </div>
    </header><!--/header-->

    <section id="title" class="emerald">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <h1>Admin Page</h1>
                </div>
                <div class="col-sm-6">
                    <ul class="breadcrumb pull-right">
                        <li class="active"><a href="transactions.jsp">transactions</a></li>
                        <li><a href="admin.jsp">Add new movie</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </section><!--/#title--> 

    <section id="portfolio" class="container">
        <ul class="portfolio-filter">
            <h3>Current Transactions</h3>
        </ul><!--/#portfolio-filter-->
		
                <div class="form-group">
                    
                    <table border="1" style="width:600px;">
                        <% 
                
                String line;
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try 
                {
                    conn = DBConnection.createConnection();
                    //System.out.print("1");
                    line = "SELECT * FROM rentingdata;";
                    stmt = conn.prepareStatement(line);
                    
                    rs = stmt.executeQuery();
                }
                catch (Exception cnfe) 
                {
                    System.out.println("Exception: " + cnfe);
                }
                        int i = 1,movieNum = 0, userID  = 0, returned  = 0, sendMail= 0;
                        String startTime,expiredTime;
                            while(rs.next())
                            {
                                if(rs.getInt("returned") == 1)continue;
                                movieNum = rs.getInt("MovieID");
                                userID = rs.getInt("IDUser");
                                startTime = rs.getString("StartTime");
                                expiredTime = rs.getString("ExpiredTime");
                                
                            %>
                            <tr>
                            <form action="transactions" >
                                <td>
                                    <input  id="movieID<%=i%>" value="<%=movieNum%>" name="movieID" readonly="true">     
                                </td>
                                <td>
                                    <input  id="userID<%=i%>" value="<%=userID%>" name="IDUser" readonly="true">
                                    
                                </td>
                                <td>
                                    <input type="text" id="startTime<%=i%>" value="<%=startTime%>" name="startTime" readonly="true">
                                    
                                </td>
                                <td>
                                    <input  id="expiredTime<%=i%>" value="<%=expiredTime%>" name="expiredTime" readonly="true">
                                    
                                </td>
                                <td>
                                    
                                    <input type="submit" value="restore" name="restore">
        
                                </td>
                                <td>
                                    <%
                                        if(rs.getInt("mailSend") == 0)
                                        {
                                            %>
                                                <input type="submit" value="sendMail" name="sendMail">
                                            <%
                                        }
                                        else
                                        {
                                            %>
                                            <input type="submit" value="sendMail" disabled="true" name="sendMail">
                                            <%
                                        }
                                    
                                    %>
                                  
                                </td>
                                
                            </form>
                            </tr>
                            <%
                                        i++;
                            }
                         DBConnection.closeConnection();
                        %>
                    </table>
                    
                </div>
	
    </section><!--/#portfolio-->

    
    <br><br><br><br><br><br><br><br><br><br>
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


