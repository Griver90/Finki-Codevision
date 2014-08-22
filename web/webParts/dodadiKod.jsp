<%@page import="classes.Predmet"%>
<%@page import="db.helpers.dbPredmet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="classes.Jazik"%>
<%@page import="db.helpers.dbJazik" %>
<%@page import="classes.Konstanti" %>
<%@page import="classes.Predmet"%>
<form class="forma" action="Kod" method="POST">
    <div class ='span4'>
        <label>Јазик: </label>
        <%
            List<Jazik> listaJazici = dbJazik.zemiSiteJazici();
            List<Predmet> listaPredmeti = dbPredmet.zemiSitePredmeti();
        %>
        <select name="<%=Konstanti.kodJazik%>">
            <%
                if (listaJazici != null) {
                    for (Jazik c : listaJazici) {
            %>
            <option value="<%=c.getIdJazik()%>">
                <%=c.getImeJazik()%>
            </option>
            <%
                    }
                }
            %>
        </select>
    </div>
    <div class ='span4'>
        <label>Број на страни:</label>
        <select id="kod_broj_strani_input" name="<%=Konstanti.kodBrStraniInt%>"  required>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>

    </div>
    <div class ='span4'>
        <%  for (Predmet pr : listaPredmeti) {
        %>
        <input type="checkbox" name="<%=Konstanti.kodPredmeti%>" value="<%=pr.getIdPredmet()%>"> 
        <%=pr.getImePredmet()%>
        <br/>
        <%
            }
        %>

    </div>
    <div class ='span4'>

        <label>Наслов:</label>
        <input type="text" id="naslovkod" name="<%=Konstanti.kodNaslovString%>" 
               required title="Насловот на кодот е задолжително" placeholder="Внесете наслов на код"  maxlength="100"/>
    </div>
    <div class ="span4">
        <label> Опис:</label>
        <textarea name="<%=Konstanti.kodOpisString%>" class="opis"
                  required title="Описот е задолжителен" placeholder="Внесете опис"></textarea>
    </div>
    <div class="span6">          
        <div class="tabbable tab-height"> <!-- Only required for left/right tabs -->
            <ul class="nav nav-tabs" id='kopcinjaStranaKod'>
                <li class="active"><a href="#tab1" data-toggle="tab">Страна 1</a></li>
            </ul>
            <div class="tab-content" id='polinjaZaKod'>
                <div class="tab-pane active" id="tab1">
                    <textarea class="code" name="<%=Konstanti.kodSodrzinaIntNiza%>1"
                              required title="Описот е задолжителен" placeholder="Внесете Код"></textarea>
                </div>
            </div>
        </div>
    </div>  
    <div class="span4">
        <input type="submit" class="btn btn-primary" value="Додади" name="isprati"/>
        <input type="reset" class="btn btn-danger" value="Исчити полиња"/>
    </div>
</form>
