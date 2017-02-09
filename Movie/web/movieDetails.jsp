<%-- 
    Document   : movieDetails
    Created on : Dec 21, 2016, 5:23:32 PM
    Author     : LOL
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="connection.DBConnection"%>
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
    <% 
        ArrayList<MovieItem> list = (ArrayList<MovieItem>) request.getAttribute("listt");
        ArrayList<MovieItem> trailer1 = (ArrayList<MovieItem>) request.getAttribute("trailer");
        ArrayList<MovieItem> Categorys = (ArrayList<MovieItem>) request.getAttribute("Categorys");
        String name= list.get(0).getName();
        String poster = list.get(0).getPoster();
        String description = list.get(0).getOverview();
        String Actor=list.get(0).getActor();
        String  rate=list.get(0).getRate();
        String  rentalPrice=list.get(0).getRentalPrice();
        String releaseDate=list.get(0).getReleaseDate();
        String OriginalRelease=releaseDate.substring(0, 4);
        String DirectorName=list.get(0).getDirectorName();
        int MovieID= list.get(0).getId();
        int x=0;//to do session admin
    %>
    <title><%=name%> (<%=OriginalRelease%>)</title>
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
    <!-- js -->
    <script type="text/javascript">
       <%int movieid=Integer.parseInt(request.getParameter("id"));%>
           
        function edit(divName) {
           
            var value = document.getElementById(divName).innerHTML;
            
			if(!value.includes("<")){
            document.getElementById(divName).innerHTML = "<input type='text' id='Propname' name='"+ divName +"' placeholder='"+divName+"' class='form-control'> ";
         
            document.getElementById('save').innerHTML = "<button type='submit' class='btn btn-primary btn-sm' onClick='newDoc()'>Save</button>" + 
                " &#160;&#160;&#160;" +
                "<button type='submit' class='btn btn-primary btn-sm'  onClick='window.location.reload()'>Cancel</button>" + 
                "<br/> <br/>";
				}
           
        }
           function newDoc() {//to do to store update // done
           var name = document.getElementById("Propname").value;
           var divvnam = document.getElementById("Propname").name;
           var value = document.getElementById("movieid").name;
            window.location.assign("adminUpdate?id="+value+"&divName="+divvnam+"&value="+name+"")
        }
    </script>
</head><!--/head-->
<body>
    <div id="fb-root"></div>
    <script>(function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=144716315690681";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));</script>

    
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
                        User userData=(User)session.getAttribute("userData");
                        if (userData == null)   
                        {
                            response.sendRedirect("login.html");
                        }
                        if(userData.getAdmin() == 1)x = 1;
                    %>
                    
                    
                </ul>
            </div>
        </div>
    </header><!--/header-->

    <section id="title" class="emerald">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <h1><%=name%></h1>
                </div>
                <div class="col-sm-6">
                    <ul class="breadcrumb pull-right">
                        <li><a href="index.jsp">Home</a></li>
                        <li class="active"><%=name%></li>
                    </ul>
                </div>
            </div>
        </div>
    </section><!--/#title-->

    <section id="blog" class="container" >
        
        <div class="row">
            <aside class="col-sm-4 col-sm-push-8">
                

                 <li class="portfolio-item joomla bootstrap">
                     <div class="item-inner">
                         <img src="<%=poster%>" alt="">
                      <div class="overlay">
                        <a class="preview btn btn-danger" href="<%=poster%>" rel="prettyPhoto"><i class="icon-eye-open"></i></a>              
                     </div>           
                </div>           
                </li><!--/.portfolio-item-->
                 <div class="widget ads">
                    <div class="row">
                        <%
                            for(int i=0;i<trailer1.size();i++){
                                String trailler = trailer1.get(i).getLink();
                                %>
                        <div class="col-sm-6">
                            <a href="#">
                                <div class="img-responsive img-rounded" style="padding:3px;">
                                  <iframe width="180" height="150" src="<%=trailler%>" frameborder="0" allowfullscreen></iframe>
                                </div>
                            </a>
                        </div>
                        <%
                            }
                        %>
                        

                    </div>
                </div><!--/.ads-->     
                             <div class="entry-meta">
                                 <% 
                                if(userData.getAdmin() == 1)
                                {
                                    %>   
                                         <span><i class="icon-user"></i> <a href="admin.jsp">Admin</a></span>
                                <%
                                }
                                %>
                                <span><i class="icon-folder-close"></i> 
                                    <%
                                        for(int i=0;i<Categorys.size();i++){
                                            String category = Categorys.get(i).getNameOfcategory();
                                            %>
                                            <a href="#"><%=category%>,</a></span>
                                            <%
                                        }
                                    %>
                                   
                                <span><i class="icon-calendar"></i> <%=releaseDate%></span>
                                <input type="hidden" id="movieid" name="<%=movieid%>">
                                
                            </div>
                           
            </aside>        
            <div class="col-sm-8 col-sm-pull-4">
                <div class="blog">
                    <div class="blog-item">
                       
                        <div class="blog-content">

                            <h3 class="media-heading">Description</h3>
                            <%
                                if(x==1){
                                    %>
                            <p> <ul class="portfolio-filter">
                                  <li class="active" id="description" ><div id="Overview"><%=description%></div></li>
                                     <img src="images/edit1.png" style="width:15px;height:15px;" onclick="edit('Overview')"><br>
                                </ul>
                            </p>
                                    <%
                                }
                                else {%><p> <%=description%></p><%}%>
                            
                            <h3 class="media-heading">Our Price</h3>
                            <%
                                if(x==1)
                                {
                                    %>
                            <p>
                                <ul class="portfolio-filter">
                                  <li class="active" id="edit_rentalPrice" ><div id="rentalPrice"><%=rentalPrice%></div></li>
                                     <img src="images/edit1.png" style="width:15px;height:15px;" onclick="edit('rentalPrice')"><br>
                                </ul>
                            </p>
                                    <%
                                }
                                else {
                                        %>
                                        <p><%=rentalPrice%></p>
                                        <%
                                     }
                            %>
                                
                            <h3 class="media-heading">Movie Information</h3>
                            <%
                                if(x==1){
                                    %>
                                    <p>
                            <ul class="portfolio-filter">
                            <li class="active" id="name" ><b>Actors Name:</b><div id="Actor"><%=Actor%></div></li>
                                <img src="images/edit1.png" style="width:15px;height:15px;" onclick="edit('Actor')"><br>
                            <li class="active" id="date" ><b>Release Date:</b><div id="releaseDate"><%=releaseDate%></div></li>
                                <img src="images/edit1.png" style="width:15px;height:15px;" onclick="edit('releaseDate')"><br>
                                <li></li>
                                <b>Original Release:</b> <%=OriginalRelease%><br>
                                <b>Format:</b> DVD <br>
                            <li class="active" id="edit_directorName" ><b>Directed by:</b><div id="DirectorName"><%=DirectorName%></div></li>
                                <img src="images/edit1.png" style="width:15px;height:15px;" onclick="edit('DirectorName')"><br> 
                                <b>Categories:</b>
                                <%
                                    for(int i=0;i<Categorys.size();i++){
                                       String category = Categorys.get(i).getNameOfcategory();
                                       %>
                                       <%=category%>,
                                       <%
                                    }
                                %>
                                <br>
                            </p>
                            <h3 class="media-heading ">IMDB Rating</h3>
                            <p>
                                <!--<a href="http://www.imdb.com/title/tt1392190/">-->
                                <li class="active" id="edit_rate" ><b><div id="rate"><%=rate%></div></b></li>
                                <img src="images/edit1.png" style="width:15px;height:15px;" onclick="edit('rate')"><br> 
                            </p>
                            <div id="save"></div>
                            </ul>
                                    <%
                                }
                                else {
                                %>
                                <p>
                            <b>Actors Name: </b><%=Actor%><br>
                            <b>Release Date: </b><%=releaseDate%><br>
                            <b>Original Release:</b> <%=OriginalRelease%><br>
                            <b>Format:</b> DVD <br>
                            <b>Directed by:</b><%=DirectorName%><br> 
                            <b>Categories:</b>
                                <%
                                    for(int i=0;i<Categorys.size();i++){
                                       String category = Categorys.get(i).getNameOfcategory();
                                       %>
                                       <%=category%>,
                                       <%
                                    }
                                %>
                                <br>
                            </p>
                            <h3 class="media-heading ">IMDB Rating</h3>
                            <p>
                            <a href="http://www.imdb.com/title/tt1392190/"><b><%=rate%></b><br> 
                            </p>
                                <%
                                }
                            %>
                            
                            <%
                                String line;
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;
                try 
                {
                    conn = DBConnection.createConnection();
                    //System.out.print("1");
                    line = "SELECT * FROM rentingdata where MovieID = ? and IDUser = ?;";
                    stmt = conn.prepareStatement(line);
                    stmt.setInt(1, list.get(0).getId());
                    stmt.setInt(2,userData.getID());
                    rs = stmt.executeQuery();
                    if(rs.next())
                    {
                        if(rs.getInt("returned") == 0)
                        {
                            %>
                                <a class="btn btn-success btn-md btn-block" href="Extend?MovieID=<%=MovieID%>">Extend</a>
                            <%
                        }
                        else
                        {
                                %>
                                    <a class="btn btn-success btn-md btn-block" href="Extend?MovieID=<%=MovieID%>">Rent</a>
                                <%
                        }
                        
                    }
                    else
                    {
                        %>
                            <a class="btn btn-success btn-md btn-block" href="Renting?MovieID=<%=MovieID%>">Rent</a>
                        <%
                    }
                }
                catch (Exception cnfe) 
                {
                    System.out.println("Exception: " + cnfe);
                }
                                 
                                
                                
                                DBConnection.closeConnection();
                                
                                
                            %>
                            
                            
                          

                            <hr>

                            <div class="tags">
                                <i class="icon-tags"></i> Category 
                                <%
                                    for(int i=0;i<Categorys.size();i++){
                                       String category = Categorys.get(i).getNameOfcategory();
                                       %>
                                       <a class="btn btn-xs btn-primary" href="#"><%=category%></a>
                                       <%
                                    }
                                %>
                                 
                            </div>

                            <p>&nbsp;</p>

                            
                                </div>
                            </div>
                         


                           <!-- <div id="comments">
                                <div id="comments-list">
                                    <h3>3 Reviews</h3>
                                    <div class="media">
                                        <div class="pull-left">
                                            <img class="avatar img-circle" src="images/blog/avatar1.png" alt="">
                                        </div>
                                        <div class="media-body">
                                            <div class="well">
                                                <div class="media-heading">
                                                    <strong>John Doe</strong>&nbsp; <small>27 Aug 2013</small>
                                                    <a class="pull-right" href="#"><i class="icon-repeat"></i> Reply</a>
                                                </div>
                                                <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
                                            </div>
                                            <div class="media">
                                                <div class="pull-left">
                                                    <img class="avatar img-circle" src="images/blog/avatar3.png" alt="">
                                                </div>
                                                <div class="media-body">
                                                    <div class="well">
                                                        <div class="media-heading">
                                                            <strong>John Doe</strong>&nbsp; <small>27 Aug 2013</small>
                                                            <a class="pull-right" href="#"><i class="icon-repeat"></i> Reply</a>
                                                        </div>
                                                        <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante.</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="media">
                                        <div class="pull-left">
                                            <img class="avatar img-circle" src="images/blog/avatar2.png" alt="">
                                        </div>
                                        <div class="media-body">
                                            <div class="well">
                                                <div class="media-heading">
                                                    <strong>John Doe</strong>&nbsp; <small>27 Aug 2013</small>
                                                    <a class="pull-right" href="#"><i class="icon-repeat"></i> Reply</a>
                                                </div>
                                                <p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>--> 

                              <!--  <div id="comment-form">
                                    <h3>Leave a comment</h3>
                                    <form class="form-horizontal" role="form">
                                        <div class="form-group">
                                            <div class="col-sm-6">
                                                <input type="text" class="form-control" placeholder="Name">
                                            </div>
                                            <div class="col-sm-6">
                                                <input type="email" class="form-control" placeholder="Email">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-12">
                                                <textarea rows="8" class="form-control" placeholder="Comment"></textarea>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-danger btn-lg">Submit Comment</button>
                                    </form>
                                </div>
                            </div>-->
                        </div>
                    </div><!--/.blog-item-->
                </div>
            </div><!--/.col-md-8-->
        </div><!--/.row-->
    </section><!--/#blog-->

  

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
                        <li><a href="#">Contact Us</a></li>
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