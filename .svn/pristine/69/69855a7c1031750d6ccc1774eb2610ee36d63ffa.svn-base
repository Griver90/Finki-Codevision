<%@page import="classes.Konstanti"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form class="forma" method="POST" action="doDodadiPredmet">
    <div class ='kontrolaRed'>
        <label>
            Име на предмет:
        </label>
        <input class="desno" type="text" id="predmet" name="<%=Konstanti.predmetImeString%>" 
               required title="Името на предметот е задолжително" placeholder="Внесете име на предмет"  maxlength="40"/>
    </div>
    <div class ='kontrolaRed'>
        <label>
            Семестар:
        </label>  
        <select name="<%=Konstanti.predmetSemestarInt%>">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>

        </select>
    </div>
    <div class ='kontrolaRed'>
        <label>
            Опис за предметот
        </label> 
        <textarea name="<%=Konstanti.predmetOpisString%>" rows="10" cols="50" required title="Содржината е задолжителна" placeholder="Внесете содржина">
        </textarea>

    </div>
   <input type="submit" class="btn btn-primary" value="Додади" name="isprati"/>
    <input type="reset" class="btn btn-danger" value="Исчити полиња"/>
</form>
