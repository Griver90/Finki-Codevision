<%-- 
    Document   : listaKod
    Created on : Mar 5, 2013, 1:57:16 PM
    Author     : Muhamed
--%>

<%@page import="classes.Predmet"%>
<%@page import="java.util.List"%>
<%@page import="classes.Jazik"%>
<%
    List<Jazik> listaJazik = db.helpers.dbJazik.zemiSiteJazici();
    List<Predmet> listaPredmeti = db.helpers.dbPredmet.zemiSitePredmeti();


%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="span3">
    <label for="kodZaPregled">Прикажи избор на код според: </label>
    <label>
        <input type="radio" name="filterKod" id="optionsRadios1" value="sporedJazik" checked>
        Програмски јазик
    </label>

    <label>
        <input type="radio" name="filterKod" id="optionsRadios2" value="sporedPredmet">
        Предмет
    </label>


</div>
<div class="span4" id="izborKod">
    <label for="kodZaPregled">Прегледај ги кодовите за програмскиoт јазик:</label>
    <select class="kod" id="izborKodLista">
        <option value="def" selected="true">Одбери јазик</option> 
        <%
            for (Jazik jazik : listaJazik) {
        %>
        <option value="<%=jazik.getIdJazik()%>"><%=jazik.getImeJazik()%></option> 
        <%
            }
        %>
    </select>
</div>
<div class="span4" id="izborPredmet" style="display: none">
    <label for="kodZaPregled">Прегледај ги кодовите за предметот::</label>
    <select class="kod" id="izborPredmetLista">

        <option value="def" selected="true">Одбери Предмет</option> 

        <%
            for (Predmet predmet : listaPredmeti) {
        %>

        <option value="<%=predmet.getIdPredmet()%>"><%=predmet.getImePredmet()%></option> 
        <%
            }
        %>
    </select>

</div>