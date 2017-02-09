package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.User;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <meta name=\"description\" content=\"\">\n");
      out.write("    <meta name=\"author\" content=\"\">\n");
      out.write("    <title>Home</title>\n");
      out.write("    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("    <link href=\"css/font-awesome.min.css\" rel=\"stylesheet\">\n");
      out.write("    <link href=\"css/prettyPhoto.css\" rel=\"stylesheet\">\n");
      out.write("    <link href=\"css/animate.css\" rel=\"stylesheet\">\n");
      out.write("    <link href=\"css/main.css\" rel=\"stylesheet\">\n");
      out.write("    <!--[if lt IE 9]>\n");
      out.write("    <script src=\"js/html5shiv.js\"></script>\n");
      out.write("    <script src=\"js/respond.min.js\"></script>\n");
      out.write("    <![endif]-->       \n");
      out.write("    <link rel=\"shortcut icon\" href=\"images/ico/favicon.ico\">\n");
      out.write("    <link rel=\"apple-touch-icon-precomposed\" sizes=\"144x144\" href=\"images/ico/apple-touch-icon-144-precomposed.png\">\n");
      out.write("    <link rel=\"apple-touch-icon-precomposed\" sizes=\"114x114\" href=\"images/ico/apple-touch-icon-114-precomposed.png\">\n");
      out.write("    <link rel=\"apple-touch-icon-precomposed\" sizes=\"72x72\" href=\"images/ico/apple-touch-icon-72-precomposed.png\">\n");
      out.write("    <link rel=\"apple-touch-icon-precomposed\" href=\"images/ico/apple-touch-icon-57-precomposed.png\">\n");
      out.write("    \n");
      out.write("</head><!--/head-->\n");
      out.write("<body>\n");
      out.write("    <header class=\"navbar navbar-inverse navbar-fixed-top wet-asphalt\" role=\"banner\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"navbar-header\">\n");
      out.write("                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\n");
      out.write("                    <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                    <span class=\"icon-bar\"></span>\n");
      out.write("                </button>\n");
      out.write("                <a class=\"navbar-brand\" href=\"index.html\"><img src=\"images/logo.png\" alt=\"logo\"></a>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"collapse navbar-collapse\">\n");
      out.write("                <ul class=\"nav navbar-nav navbar-right\">\n");
      out.write("                    <li class=\"active\"><a href=\"index.jsp\">Home</a></li>\n");
      out.write("                    <li><a href=\"Movie\">Movies</a></li>\n");
      out.write("                    ");

                        model.User userData=(User)session.getAttribute("userData");
                        if (userData == null) {
                         //   out.print("session.getAttribute(\"userData\")");
                            
      out.write("\n");
      out.write("                                <li><a href=\"login.html\">Login</a></li>\n");
      out.write("                            ");

                        } else {
                                String username = userData.getFname();
                                
                            
      out.write("\n");
      out.write("                                <li><a href=\"profile.jsp\">");
      out.print(username);
      out.write("</a></li>\n");
      out.write("                                    ");

                                         if(userData.getAdmin() == 1)
                                            {
                                         
      out.write("\n");
      out.write("                                                <li> <a href=\"admin.jsp\">admin page</a></li>\n");
      out.write("                                          ");

                                        }
                                    
      out.write("\n");
      out.write("                                <li> <a href=\"logout\">logout</a></li>\n");
      out.write("                                <!--<li><form action=\"logout\" method=\"POST\"><input type=\"submit\" value=\"logout\"/></form></li>-->\n");
      out.write("                           ");

                        }
                    
      out.write("\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </header><!--/header-->\n");
      out.write("    <section id=\"main-slider\" class=\"no-margin\">\n");
      out.write("        <div class=\"carousel slide wet-asphalt\">\n");
      out.write("            <ol class=\"carousel-indicators\">\n");
      out.write("                <li data-target=\"#main-slider\" data-slide-to=\"0\" class=\"active\"></li>\n");
      out.write("                <li data-target=\"#main-slider\" data-slide-to=\"1\"></li>\n");
      out.write("                <li data-target=\"#main-slider\" data-slide-to=\"2\"></li>\n");
      out.write("            </ol>\n");
      out.write("            <div class=\"carousel-inner\">\n");
      out.write("                <div class=\"item active\" style=\"background-image: url(http://image.tmdb.org/t/p/w1280/lubzBMQLLmG88CLQ4F3TxZr2Q7N.jpg)\">\n");
      out.write("                    <div class=\"container\">\n");
      out.write("                        <div class=\"row\">\n");
      out.write("                            <div class=\"col-sm-12\">\n");
      out.write("                                <div class=\"carousel-content centered\">\n");
      out.write("                                    <h2 class=\"animation animated-item-1\">The Secret Life of Pets</h2>\n");
      out.write("                                    <p class=\"animation animated-item-2\">The quiet life of a terrier named Max is upended when his owner takes in Duke, a stray whom Max instantly dislikes.</p>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div><!--/.item-->\n");
      out.write("                <div class=\"item\" style=\"background-image: url(images/slider/bg2.jpg)\">\n");
      out.write("                    <div class=\"container\">\n");
      out.write("                        <div class=\"row\">\n");
      out.write("                            <div class=\"col-sm-12\">\n");
      out.write("                                <div class=\"carousel-content center centered\">\n");
      out.write("                                    <h2 class=\"boxed animation animated-item-1\">Clean, Crisp, Powerful and Responsive Web Design</h2>\n");
      out.write("                                    <p class=\"boxed animation animated-item-2\">Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</p>\n");
      out.write("                                    <br>\n");
      out.write("                                   \n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div><!--/.item-->\n");
      out.write("                <div class=\"item\" style=\"background-image: url(http://image.tmdb.org/t/p/w1280/z09QAf8WbZncbitewNk6lKYMZsh.jpg)\">\n");
      out.write("                    <div class=\"container\">\n");
      out.write("                        <div class=\"row\">\n");
      out.write("                            <div class=\"col-sm-6\">\n");
      out.write("                                <div class=\"carousel-content centered\">\n");
      out.write("                                    <h2 class=\"animation animated-item-1\">Finding Dory</h2>\n");
      out.write("                                    <p class=\"animation animated-item-2\">\\\"Finding Dory\\\" reunites Dory with friends Nemo and Marlin on a search for answers about her past. What can she remember? Who are her parents? And where did she learn to speak Whale?</p>\n");
      out.write("                                    \n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"col-sm-6 hidden-xs animation animated-item-4\">\n");
      out.write("                                <div class=\"centered\">\n");
      out.write("                                    <div class=\"embed-container\">\n");
      out.write("                                        <iframe frameborder=\"0\" width=\"480\" height=\"270\" src=\"//www.dailymotion.com/embed/video/x3d7coe\" allowfullscreen></iframe><br /><a href=\"http://www.dailymotion.com/video/x3d7coe_finding-dory-official-movie-trailer-1-ellen-degeneres-idris-elba-dominic-west-animation-2016-full-hd_shortfilms\" target=\"_blank\"></a> <i> <a href=\"http://www.dailymotion.com/80PoundGames\" target=\"_blank\">80PoundGames</a></i>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div><!--/.item-->\n");
      out.write("            </div><!--/.carousel-inner-->\n");
      out.write("        </div><!--/.carousel-->\n");
      out.write("        <a class=\"prev hidden-xs\" href=\"#main-slider\" data-slide=\"prev\">\n");
      out.write("            <i class=\"icon-angle-left\"></i>\n");
      out.write("        </a>\n");
      out.write("        <a class=\"next hidden-xs\" href=\"#main-slider\" data-slide=\"next\">\n");
      out.write("            <i class=\"icon-angle-right\"></i>\n");
      out.write("        </a>\n");
      out.write("    </section><!--/#main-slider-->\n");
      out.write("\n");
      out.write("   \n");
      out.write("    </section><!--/#services-->\n");
      out.write("\n");
      out.write("  \n");
      out.write("\n");
      out.write("    <section id=\"testimonial\" class=\"alizarin\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-lg-12\">\n");
      out.write("                    <div class=\"center\">\n");
      out.write("                        <h2>What our website do</h2>\n");
      out.write("                        <p>It is a website to rent movies.</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"gap\"></div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-md-6\">\n");
      out.write("                            <blockquote>\n");
      out.write("                                <p>Task #1</p>\n");
      out.write("                                <small>description <cite title=\"Source Title\">task #1</cite></small>\n");
      out.write("                            </blockquote>\n");
      out.write("                            <blockquote>\n");
      out.write("                                <p>Task #3</p>\n");
      out.write("                                <small>description <cite title=\"Source Title\">task #1</cite></small>\n");
      out.write("                            </blockquote>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"col-md-6\">\n");
      out.write("                            <blockquote>\n");
      out.write("                                <p>Task #2</p>\n");
      out.write("                                <small>description <cite title=\"Source Title\">task #1</cite></small>\n");
      out.write("                            </blockquote>\n");
      out.write("                            <blockquote>\n");
      out.write("                                <p>Task #4</p>\n");
      out.write("                                <small>description <cite title=\"Source Title\">task #1</cite></small>\n");
      out.write("                            </blockquote>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </section><!--/#testimonial-->\n");
      out.write("\n");
      out.write("    <footer id=\"footer\" class=\"midnight-blue\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-sm-6\">\n");
      out.write("                    &copy; 2013 <a target=\"_blank\" href=\"http://shapebootstrap.net/\" title=\"Free Twitter Bootstrap WordPress Themes and HTML templates\">ShapeBootstrap</a>. All Rights Reserved.\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-sm-6\">\n");
      out.write("                    <ul class=\"pull-right\">\n");
      out.write("                        <li><a href=\"index.jsp\">Home</a></li>\n");
      out.write("                        <li><a href=\"#\">About Us</a></li>\n");
      out.write("                        <li><a href=\"contact-us.html\">Contact Us</a></li>\n");
      out.write("                        <li><a id=\"gototop\" class=\"gototop\" href=\"#\"><i class=\"icon-chevron-up\"></i></a></li><!--#gototop-->\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </footer><!--/#footer-->\n");
      out.write("\n");
      out.write("    <script src=\"js/jquery.js\"></script>\n");
      out.write("    <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("    <script src=\"js/jquery.prettyPhoto.js\"></script>\n");
      out.write("    <script src=\"js/main.js\"></script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
