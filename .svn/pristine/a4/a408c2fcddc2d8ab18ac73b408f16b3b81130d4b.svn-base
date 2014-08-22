<%-- 
    Document   : pregledKod
    Created on : Mar 12, 2013, 10:07:26 PM
    Author     : Muhamed
--%>

<%@page import="classes.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h2></h2>
<p>

</p>
<%
    User u = (User) session.getAttribute("User");
%>
<input id ="kodPregledJazik"type="hidden" value="null"/>
<input id ="kodPregledStrana"type="hidden" value="null"/>
<div class="tabbable tab-height">
    <ul class="nav nav-tabs" id='kopcinjaStranaKod'>
    </ul>
    <div class="tab-content" id='polinjaZaKod'>
        <div class="tab-pane active" id="tab1">
            <textarea id="codeSrc1" class="codeSrc1" style="display: none"></textarea>
            <textarea id="codeSrc2" class="codeSrc2" style="display: none"></textarea>
            <textarea id="codeSrc3" class="codeSrc3" style="display: none"></textarea>
            <textarea id="codeSrc4" class="codeSrc4" style="display: none" ></textarea>
            <textarea id="codeSrc5" class="codeSrc5" style="display: none"></textarea>
        </div>
    </div>
</div>
<%
    if (u != null && u.getRole().equals("Administrator")) {
%>
<div class="editCodеArea">
    <button class ="btn btn-primary dropdown-toggle" id="editCode" editMode="True">Зачувај</button>
    <button class ="btn btn-danger dropdown-toggle" id="deleteCode" editMode="True">Избриши</button>
    <span class="label label-success" style="display: none">Success</span>
    <span class="label label-warning" style="display: none">Warning</span>

</div>

<%}%>
<div class="fb-like" data-href="http://localhost:8084/finki_codevision/Kod.jsp" data-width="450" data-show-faces="true" data-send="true"></div>