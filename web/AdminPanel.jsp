<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>   
    <head>  
        <meta charset="utf-8"> 
        <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />

        <link rel="stylesheet" href="js/bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" href="js/bootstrap/css/bootstrap-responsive.css">
        <link rel="stylesheet" href="css/siteStyle.css">

    </head>
    <body id="body">
        <div class="clearfix" id="wrapper">
            <!--Header Pocetok-->
            <header>
                <!--Slika Pocetok-->
                <div id="bannerSlika">
                    <img src ="img/banner/banner2.jpg" />
                </div>
                <!--Slika Kraj-->
                <!-- Navigacija Pocetok-->
                <div class="navbar navbar-inverse">

                    <div class="navbar-inner">
                        <jsp:include page="webParts/navKopcinja.jsp"/>  
                    </div>

                </div>

                <!-- Navigacija Kraj-->	
            </header>
            <!-- Header Kraj -->
            <!---Glavna Sodrzin- Pocetok-->
            <div class ="clearfix" id="content">
                <div class="alert alert-success  fade in" id="Uspesno" style="display: none;">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    Успешно додаден !
                </div>
                <div class="alert alert-error fade in" id="Error" style="display: none;" >
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Грешка во системот!.!</strong> Не се додаде ништо во системо.
                </div>

                <div class ="clearfix" id="glavnaSodrzina">
                    <div class="accordion" id="accordion2">
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle text-center" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                                    Внеси Јазик
                                </a>
                            </div>
                            <div id="collapseOne" class="accordion-body collapse">
                                <div class="accordion-inner">
                                    <jsp:include page="webParts/dodadiJazik.jsp" />
                                </div>
                            </div>
                        </div>
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle text-center" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                                    Внеси Код
                                </a>
                            </div>
                            <div id="collapseTwo" class="accordion-body collapse">
                                <div class="accordion-inner">
                                    <jsp:include page="webParts/dodadiKod.jsp" />
                                </div>
                            </div>
                        </div>
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle text-center" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">
                                    Внеси предмет
                                </a>
                            </div>
                            <div id="collapseThree" class="accordion-body collapse">
                                <div class="accordion-inner">
                                    <jsp:include page="webParts/vnesiPredmet.jsp"/> 
                                </div>
                            </div>
                        </div>

                    </div>

                </div> 
                <div class ="clearfix" id="infoBar">
                    <jsp:include page="webParts/Novosti.jsp"/>
                </div>
            </div>
            <!---Glavna Sodrzina Kraj-->
            <!---Footer Sodrzia Pocetok-->
            <footer class="clearfix">
                Finki-CodeVision all rights reserved.
            </footer>
            <!---Footer Sodrzia Kraj-->
        </div>
        <script src="js/jquery/jquery.js"></script>
        <script src="js/Scripti.js"></script>
        <script src="js/bootstrap/js/bootstrap.js"></script>
        <script>
            <%
                String actionResult = (String) request.getAttribute("ActionResult");
                if ("Successfull".equals(actionResult)) {
            %>
              $("#Uspesno").fadeIn().delay(5000).fadeOut();
            <%                        } else if ("Failed".equals(actionResult)) {
            %>
              $("#Error").fadeIn().delay(5000).fadeOut();
            <%                            }
            %>

              $("[rel='tooltip']").tooltip({
                  html: true
              });

        </script>  

    </body>
</html>  