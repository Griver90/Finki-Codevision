<%-- 
    Document   : Novosti
    Created on : Feb 24, 2013, 6:21:48 PM
    Author     : Muhamed
--%>

<%@page import="classes.Novost"%>
<%@page import="java.util.List"%>
<%@page import="db.helpers.dbNovost"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Novost> lista = dbNovost.zemiNovost(-1);

    String[] zbor;
    String naslov;
    String akcija;
    String tipSodrzina;
    String boja = null;
    String slika = null;
    String link = null;
    String sodrzinaNovost = null;

%>
<div  id="prikazi">
    Новости
</div>
<aside id="infoBarContent">
    <ul>
        <%
            for (Novost n : lista) {
                sodrzinaNovost = n.getSodrzinaNovost();
                naslov = sodrzinaNovost.split(" : ")[1];
                zbor = sodrzinaNovost.split(" ");
                akcija = zbor[0];
                tipSodrzina = zbor[1];
                if ("Нов".equals(akcija)) {
                    boja = "sina";
                } else {
                    boja = "crvena";
                }

                if ("код".equals(tipSodrzina)) {
                    slika = "img/sodrzina/infobar/kod.png";
                    String jazikID = n.getUrlInfo().split("-")[1];
                    String kodID = n.getUrlInfo().split("-")[2];
                    link = "Kod.jsp#kodZa=jazik&jazikID=" + jazikID + "&kodID=" + kodID;
                } else {
                    slika = "img/sodrzina/infobar/predmet.png";

                    link = "Predmeti.jsp";
                }

                if (sodrzinaNovost.length() > 40) {
                    sodrzinaNovost = sodrzinaNovost.substring(0, 40);
                }

        %>
            
        <a href="<%=link%>" rel="tooltip" title="<%=naslov%> <br/>Време: <%=n.getDatumNovost()%>" >
            <li class="<%=boja%> novost clearfix">
                <img src="<%=slika%>"/>
                <span><%=sodrzinaNovost%></span>
            </li>          
        </a>
        <%
            }
        %>
        <ul/>
</aside>
