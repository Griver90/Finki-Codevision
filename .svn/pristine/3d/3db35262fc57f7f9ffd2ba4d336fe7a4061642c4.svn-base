<%-- 
    Document   : testuvanje
    Created on : Mar 1, 2013, 5:16:26 PM
    Author     : Aleksandar
--%>

<%@page import="classes.Kod"%>
<%@page import="java.util.List"%>
<%@page import="org.json.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <input type="button" value="TEST" onclick="kliknime()"/>

        <div class="accordion-group" id="kod">

        </div>
        <form action="doSomething" method="POST">
            <input type="submit" value="ASD"/>
        </form>

        <script>
                function kliknime()
                {
                    $.ajax({
                        type: "GET",
                        url: "doDodadiKod",
                        dataType: 'json',
                        success: function(data) {
                            i = 0;
                            while (data[i] != null)
                            {
                                listajKod(i, data[i]);

                                i++;
                            }
                        },
                        error: function(e) {
                            console.log(e.message);
                        }
                    });
                }

                function listajKod(i, data)
                {
                    $("#kod").append("<div class='accordion-heading'>"
                            + "<a class='accordion-toggle text-center' data-toggle='collapse' data-parent='#accordion2' href='#collapseOne" + i + "'>" +
                            +data.Naslov +
                            "</a></div><div id='collapseOne" + i + "' class='accordion-body collapse'><div class='accordion-inner'>" +
                            data.Opis
                            + "</div></div>");
                }
        </script>
        <script src="js/jquery/jquery.js"></script>
        <script src="js/Scripti.js"></script>
        <script src="js/bootstrap/js/bootstrap.js"></script>
    </body>
</html>
