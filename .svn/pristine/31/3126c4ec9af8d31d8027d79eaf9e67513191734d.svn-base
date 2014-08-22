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

                <div class ="clearfix" id="adminPanel">
                    <div id="loginPanel">
                        <form method="POST" action="AdminLogin">
                            <label for="username">Корисничко име:</label>
                            <input type="text" id ="username" name="username" required="Внесете Корисчно Име"/>
                            Лозинка
                            <input type="password" name="password" required="Внесете лозинка"/>

                            <div id="submitLine">
                                <input class="btn" type="submit" value="Најави се"/>
                                <div id="save">
                                    <input type="checkbox"  id="remember"name="remember"value="remember" />
                                    <label for="remember">Запомни ме</label>
                                </div>

                            </div>


                        </form>
                    </div>
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
            $("[rel='tooltip']").tooltip({
                html: true
            });
        </script>
    </body>
</html>  