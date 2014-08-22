<%-- 
    Document   : listaPredmet
    Created on : Mar 5, 2013, 1:58:20 PM
    Author     : Riste
--%>

<%@page import="classes.User"%>
<%@page import="classes.Konstanti"%>
<%@page import="java.util.List"%>
<%@page import="classes.Predmet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Predmet> listapredmeti = db.helpers.dbPredmet.zemiSitePredmeti();
    User u = (User) session.getAttribute("User");;
    String editable;
    if (u!=null && u.getRole().equals("Administrator")) {
        editable = "";
    } else {
        editable = "disabled";
    }
%>
<!--- Lista na predmeti od leva strana na stranicata --->
<div class="tabbable tabs-left">
    <div class="predmetList span3">
        <ul class="nav nav-pills nav-stacked " id="UlListaPredmeti">

            <% for (Predmet p : listapredmeti) {%>

            <li><a href="#<%=p.getIdPredmet()%>" data-toggle="tab"><%=p.getImePredmet()%></a></li>

            <% }%>
        </ul>
    </div> 
    <!-- Pokazuvanje na sodrzinata na selektiraniot predmet -->
    <div class="tab-content" id="tabContent" >
        <%for (Predmet p : listapredmeti) {%>

        <div style="margin:10px;" class="tab-pane" id="<%=p.getIdPredmet()%>">

            <h4 id="imepredmet"><%=p.getImePredmet()%></h4>
            <input type="hidden" value="<%=p.getSemestadPredmet()%>" id="semestarP"/>
            <input type="hidden" value="<%=p.getIdPredmet()%>" id="ID"/>
            <div >
                <textarea id="opisPredmet"  <%=editable%> style="min-height: 350px; width: 99%; overflow-y:auto; cursor:pointer;resize: none;" ><%=p.getOpisPredmet()%></textarea>
                <button class ="btn btn-primary dropdown-toggle" id="editMode" onClick="window.location = 'Kod.jsp#kodZa=predmet&predmetID=<%=p.getIdPredmet()%>'"editMode="True">Пример задачи за предметот</button>

            </div>
        </div>
        <% }%>

        <% if (u!=null && u.getRole().equals("Administrator")) {%>
        <div class="editCodеArea" id="editPanel" style="visibility: hidden">
            <button class ="btn btn-primary dropdown-toggle" id="editPredmet" editMode="True">Зачувај</button>
            <button class ="btn btn-danger dropdown-toggle" id="delPredmet" editMode="True">Избриши</button>
            <span class="label label-success" style="display: none">Success</span>
            <span class="label label-warning" style="display: none">Warning</span>
        </div>
        <% }%>
    </div>

</div>                  
<script>

                   
</script>

