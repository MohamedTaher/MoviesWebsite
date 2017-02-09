<%-- 
    Document   : profile
    Created on : Dec 25, 2016, 7:39:45 PM
    Author     : taher
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>profile page</title>
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
        <%
            User user = new User();
            user = (User) session.getAttribute("userData");
        %>
        <script type="text/javascript">
            function edit(divName) {

                var value = document.getElementById(divName).innerHTML;
                if (!value.includes("<")) {
                    document.getElementById(divName).innerHTML = "<input type='text' name='U" + divName + "' placeholder='" + value + "' class='form-control'> ";
                    document.getElementById('save').innerHTML = "<button type='submit' value = 'submit'  class='btn btn-primary btn-sm' >Save</button>" +
                            " &#160;&#160;&#160;" +
                            "<button class='btn btn-primary btn-sm'  onClick='window.location.reload()'>Cancel</button>" +
                            "<br/> <br/>";
                }
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
                    <li class="active"><a href="index.jsp">Home</a></li>
                    <li><a href="Movie">Movies</a></li>
                    <%
                        model.User userData=(User)session.getAttribute("userData");
                        if (userData == null) 
                        {
                         //   out.print("session.getAttribute(\"userData\")");
                            %>
                                <li><a href="login.html">Login</a></li>
                            <%
                        } else 
                        {
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

        <section id="title" class="emerald">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <h1>Profile</h1><br>

                        <p></p>
                    </div>
                    <div class="col-sm-6">
                        <ul class="breadcrumb pull-right">
                            <li><a href="index.jsp">Home</a></li>
                            <li class="active">Profile</li> <br>
                            <li><a href="ChangePassword.html">Edit Profile</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </section><!--/#title--> 

        <section id="portfolio" class="container">
            <form role="form" action="ProfileServlet" method="get" name="formform"/>

            <ul class="portfolio-filter">
                <% String st1 = user.getFname();
                    String st2 = user.getLname();
                    String name = "";
                    if (st1 == null) {
                        out.print("st1 is null");

                    } else if (st2 == null) {
                        out.print("st2 is null");
                    } else {
                        name = st1 + " " + st2;
                    }
                %>
                <li class="active" id="name">
                    <h3>Name:
                    <div id="edit_name"><%=name%></div>
                    </h3>
                </li> 
                <img src="images/update.jpg" style="width:15px;height:15px;" onclick="edit('edit_name')"><br>

                <%String email = user.getEmail();
                    if (email == null) {
                        out.print("email is null");
                    }

                %>
                
                    <li class="active" id="email">
                       <h3>E-mail:
                        <div id="edit_email"><%=email%></div>
                     </h3>
                       </li>
                
                <img src="images/update.jpg" style="width:15px;height:15px;" onclick="edit('edit_email')"> <br>



                <%String phone = user.getPhoneNumber();
                    if (phone == null) {
                        out.print("phone number is null");
                    }
                %>
                
                <li class="active" id="phoneNumber">
                    <h3>Phone-Number:
                    <div id="edit_phoneNumber"><%=phone%></div>
                    </h3>
                    </li>
                
                <img src="images/update.jpg" style="width:15px;height:15px;" onclick="edit('edit_phoneNumber')"> <br>


                <%String Address = user.getAddress();
                    if (Address == null) {
                        out.print("Address is null");
                    }
                %> 
                
                <li class="active" id="Address">
                    <h3>Address:
                    <div id="edit_Address"><%=Address%></div>
                    </h3>
                    </li> 
                
            
                <img src="images/update.jpg" style="width:15px;height:15px;" onclick="edit('edit_Address')"> <br><br>
                <div id="save" ></div>
                
            </ul><!--/#portfolio-filter-->
           
            
            <%
                
                ArrayList<model.MovieItem> list = model.MovieItem.selectRentMovies(user.getID());
                
            %>
 
            <ul class="portfolio-items col-5">
                <%
                    for (int i = 0; i < list.size(); i++) {
 
                        String url = list.get(i).getPoster();
                        String mName = list.get(i).getName();
                        int id = list.get(i).getId();
 
                        String cat = "";
                        //ArrayList<String> temp = list.get(i).getCategory();
                        /*
                        for (int f = 0; f < temp.size(); f++) {
                            String string = temp.get(f);
                            string = Character.toLowerCase(string.charAt(0)) + string.substring(1);
                            cat += string + " ";
                        }
 */
 
                %>
                <li class="portfolio-item <%=cat%>">
                    <div class="item-inner">
                        <img src="<%=url%>" alt=""/>
                        <h5><%=mName%></h5>
                        <div class="overlay">
                            <a  class="preview btn btn-danger" href="movieDetails?id=<%=id%>"><i class="icon-eye-open"></i></a> <br> <br> <br> 
                            <a  class="preview btn btn-danger" ><i> Extend Rent Period</i></a>  
                        </div>           
                    </div>
                </li><!--/.portfolio-item-->
                <%
                    }
                %>
            </ul>
            
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
