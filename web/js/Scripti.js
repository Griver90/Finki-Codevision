$(document.title).ready(function(){
     $(".aktivna-strana").html(document.title );
});
$(function() {
    
    var pull = $('#prikazi');
    menu = $('#infoBarContent');
    menuHeight = menu.height();

    //Kopce za prikazuvanje na poleto so novosti na ekrani pod 770px 
    $(pull).on('click', function(e) {
        e.preventDefault();
        menu.slideToggle('slow', function() {
            scroll("#infoBar");
        });
    });
    //Event handler za koga se menuva golemina na prozor
    $(window).resize(function() {
        var w = $(window).width();
        menuHeight = menu.height();
        if (w > 770 && menu.is(':hidden')) {
            menu.slideToggle();
        }
    });
    //event hander za sekoj li a element od lista kodovi
    $("#listaKod li a").live("click", function() {
        zemiKodSoID($(this).attr('id'));
        updateHash("kodID=" + $(this).attr('id'));       
        
    });


    $(function() {
        $("#UlListaPredmeti li a").on("click", function() {
            $("#editPanel").css("visibility", "visible");
        });
    });
    // event sto se izvrsuva koga korisnokot ke odbere
    // programski jazik ili nekoj predmet
    $(".kod").change(function() {

        var predmetID = "predmetID";
        var jazikID = "jazikID";

        // dokolku selektirano e "defdault"
        // znaci deka ne e selektirano nisto i se krijat site elementi
        if ($(this).val() === 'def') {
            $("#listaKod").slideUp(200).html("");
            skrijSodrzinaKod();
            return;
        }

        if ($(this).attr('id') === 'izborKodLista') {
            $("#izborPredmetLista").select(0);
            polniListaSoKod(jazikID, $(this).val());
            updateHash("jazikID=" + $(this).val());
        } else if ($(this).attr('id') === 'izborPredmetLista') {
            polniListaSoKod(predmetID, $(this).val());
            updateHash("predmetID=" + $(this).val());
        }
        skrijSodrzinaKod();

    });

    //event za koga korisnikot ke klikne na 
    // prikazuvanje na meni sproed predmet ili jazik
    $('[name="filterKod"]').change(function() {
        //dokolku e selektirano spored jazik
        if ($(this).val() === 'sporedJazik') {
            // se dodava vrednosta vo linkt (hash)
            updateHash("kodZa=jazik");
            // se prikazuva lista za jazik i se sleketira default vrednost
            prikaziListaJazik();
            $("#izborKodLista").val("def");
        } else {
            updateHash("kodZa=predmet");
            //istoto dokolku se selektira predmet
            prikaziListaPredmeti();
            $("#izborPredmetLista").val("def");
        }
    });
    // event za kopceto Zacuvaj 
    $("#editCode").click(function() {
        CodeView.Editor.saveCode();
    });
    //event za kopceto zacuvaj kaj predmet
    $("#editPredmet").click(function() {
        Predmet.Editor.savePredmet();
    });
    //event za kopceto Izbrisi
    $("#deleteCode").click(function() {
        CodeView.Editor.deleteCode();
    });
    $("#delPredmet").click(function(){
        Predmet.Editor.deletePredmet();
    });

    //event za tab na kod so poveke strani
    // pri klik na "i" tiot tab se prikazuva kodot od "i" toto textArea
    $("#kopcinjaStranaKod li a").live("click", function() {
        var id = $(this).attr("href").split("#tab")[1];

        CodeView.Editor.setCodeFromTextArea(id);
        $("#kodPregledStrana").val(id);
        scroll("#tab1");
    });





});
// funkcija koja skrola do elementot so id
function scroll(id) {
    $('html, body').animate({
        scrollTop: $(id).offset().top - 50
    }, 400);
}

/* Kreiranje na input polinja za vnesuvanje na sodrzina na kod */
$(function() {
    // funkcija koja sto ke se izvrsuva sekogas koga ke se sluci koga ke se odbere  
    // nov broj na strani
    var brojStrani = parseInt($('#kod_broj_strani_input').val());
    $('#kod_broj_strani_input').change(function() {
        // Se cita brojot na strani sto e selektirano
        var brojStraniNovo = parseInt($('#kod_broj_strani_input').val());
        // Dokolku se dodadeni novi strani, na istata sodrzina se dodavaat uste tolku strani
        if (brojStrani < brojStraniNovo) {

            for (var i = (parseInt(brojStrani) + 1); i <= brojStraniNovo; i++) {
                // so append na veke postoecki div se dodava nova sodrzina na krajot
                genenirajTab("#kopcinjaStranaKod", i);
                $("#polinjaZaKod").append(
                        "<div class='tab-pane' id='tab" + i + "'>" +
                        "<textarea class='code' name='kod_sodrzina_input" + i +
                        "' required title = 'Кодот е задолжителен' placeholder = 'Внесете Код' ></textarea></div>"

                        );
            }
        } else {
            for (var i = parseInt(brojStrani); i > brojStraniNovo; i--) {
                $("#kopcinjaStranaKod li:last-child").remove();
                $("#polinjaZaKod div:last-child").remove();
            }

        }
        brojStrani = brojStraniNovo;

    }
    );
});

// generira N tab za kod pri dodavanje ili prikazuvanje kod
// element - id ili class na elementot kade da se dodade noviot tab
// id - id na tabot
function genenirajTab(element, id) {
    $(element).append(
            "<li><a href='#tab" + id + "' data-toggle='tab'>Страна " + id + "</a></li>"
            );
}

// generira N tabovi za  kod
// element - ima na elementot za kade da se dodadat tabovi
// m - broj na tabovi
function genenirajTabN(element, n) {
    $(element).html("");
    for (var i = 0; i < n; i++)
        $(element).append(
                "<li><a href='#tab" + (i + 1) + "' data-toggle='tab'>Страна " + (i + 1) + "</a></li>"
                );
}

//Se polni lista za kodovi
// ime param -  moze da e predmetID = id ili jazikID = jazikID
// taka sto se prikazuvaat site kodovi za toj predmet ili toj jazik
// 
function polniListaSoKod(imeParam, id) {
    var data = imeParam + '=' + id;
    $.ajax({
        // Se praka get baranje
        type: "GET",
        // baranjeto se praka na doDodadiKodServletot
        url: "Kod",
        //Tipot na podatocite sto ke gi dobieme se vo json format
        dataType: 'json',
        // se praka jazikId
        data: data,
        // Ako baranje uspesno se prati i podatocite se uspesno prevzemeni
        // Se izvrsuva slednata funkcija
        success: function(data) {
            // Vo data imame JSONArray so eval() se pretvara vo javascript array od objekti
            var lista = eval(data);
            // Sodrzinata na listata se stava da e prazen
            //alert("fade out");
            $("#listaKod").slideUp(200, function() {
                $(this).html("");
                for (var i = 0; i < lista.length; i++) {
                    var tab = 'tab' + (i + 1);
                    $("#listaKod").append(
                            "<li><a href='#" + tab + "' id='" + lista[i].ID + "' data-toggle='tab'>" + lista[i].Naslov + "</a></li>"
                            );
                }
                $("#listaKod").slideDown(200);
            });
        }
    });
}

// Praka ajax baranje do serverot i vraka
// kod so toj ID
function zemiKodSoID(kod_id) {
    //funkcija dokolku baranjeto e uspesno
    var success = function(data) {
        // se krie loading animacijata
        $(".loading").fadeOut();
        var kod = eval(data);
        if (kod.error !== undefined) {
            alert(kod.error);
        } else {
            // se polniat osnovnite informacii za kodot
            $("#sodrzinaKod h2").html(kod.naslov);
            $("#sodrzinaKod p").html(kod.opis);
            $("#kodPregledJazik").val(kod.jazik);
            $("#kodPregledStrana").val(1);
            var obj = eval(kod.kodStrana);
            // se citaat site strani za kodot
            for (var i = 0; i < kod.kodStrana.length; i++) {

                $("#sodrzinaKod .codeSrc" + (i + 1)).html(obj[i].kod);

            }
            // se generiraat tabovi za sekoja strana
            genenirajTabN("#kopcinjaStranaKod", kod.kodStrana.length);
            $("#kopcinjaStranaKod :first-child").addClass("active");
            var selector = "#listaKod li a[id|=" + kod_id + "]";
            // od lisata na kodovi se selektira linkot so kod_id kako sto e i kodot sto se prikazuva
            $(selector).parent().addClass("active");
            prikaziSodrzinaKod();
        }
    };
    // pred da se prati baranje se prikazuva loading
    // i se krie sodrzina na kod
    var beforeSend = function() {
        skrijSodrzinaKod();
        $(".loading").fadeIn();
    };
    // ajax baranje
    $.ajax({
        url: "Kod",
        data: {kodID: kod_id},
        success: success,
        beforeSend: beforeSend,
        dataType: "json"
    }).done(function() {

        CodeView.Editor.setCodeFromTextArea(1);
    });

}

function htmlEscape(str) {
    return String(str)
            .replace(/&/g, '&amp;')
            .replace(/"/g, '&quot;')
            .replace(/'/g, '&#39;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;');
}

function htmlUnescape(value) {
    return String(value)
            .replace(/&quot;/g, '"')
            .replace(/&#39;/g, "'")
            .replace(/&lt;/g, '<')
            .replace(/&gt;/g, '>')
            .replace(/&amp;/g, '&');
}

//pomosni funkcii za krienje i prikazuvanje odredeni delovi od stranat 
function prikaziSodrzinaKod() {
    $("#sodrzinaKod").slideDown();
}

function skrijSodrzinaKod() {
    $("#sodrzinaKod").slideUp();
}

function prikaziListaKod() {
    $("#listaKod").slideDown();
}

function skrijListraKod() {
    $("#listaKod").slideUp();
}

function prikaziListaJazik() {
    $("#izborPredmet").fadeOut('fast', function() {
        $("#izborKod").fadeIn('fast');
    });
}

function prikaziListaPredmeti() {
    $("#izborKod").fadeOut('fast', function() {
        $("#izborPredmet").fadeIn('fast');
    });
}

/////////////////////////////////////////////
/*
 function updateHash(hash) {
 var origHash = parent.location.hash;
 var res = "";
 
 if (hash.split("=")[0] === "kodZa") {
 res = hash;
 $("#listaKod").slideUp();
 $("#sodrzinaKod").slideUp();
 
 } else {
 if (origHash.split("kodZa")[0] === undefined) {
 updateHash("kodZa=jazik");
 
 }
 if (hash.split("=")[0] === "jazikID" || hash.split("=")[0] === "predmetID" || hash.split("=")[0] === "kodID") {
 if (origHash.indexOf(hash.split("=")[0]) > 0)
 res = origHash.replace(hash.split("=")[0] + "=" + origHash.split(hash.split("=")[0] + "=")[1], hash);
 else {
 res = origHash + "&" + hash;
 }
 }
 }
 parent.location.hash = res;
 
 }
 */

//Funkcija dodavanje na parametar vo hash linkot
function updateHash(hash) {
    var origHash = parent.location.hash;
    var paramAddOrUp = hash.split("=")[0];
    var value = hash.split("=")[1];
    var res = "";
    // dokolku se dodade parametar kodZa
    // se drugo se brise bidejki toj  vlijae i na drugite parametri
    if (paramAddOrUp === "kodZa") {
        res = hash;
        skrijSodrzinaKod();
        skrijListraKod();
    } else if (paramAddOrUp === "jazikID" || paramAddOrUp === "predmetID") {
        // dokolku se dodava jazikID ili predmetID treba da ima kodID i posle samiot parametar
        res += "kodZa=" + paramAddOrUp.substring(0, paramAddOrUp.length - 2) + "&" + hash;
        skrijSodrzinaKod();
        skrijListraKod();
    } else if (paramAddOrUp === "kodID") {
        //Dokolku se dodava kodID
        // se proveruva dali postoi
        // ako postoi samo se apdejtira
        if (origHash.indexOf("kodID") >= 0) {
            res = origHash.split("kodID=")[0] + "kodID=" + value;
        } else {
            res = origHash + "&" + hash;
        }

    }
    parent.location.hash = res;

}


// funkcija koja sto procesira hash linkot
// prikazuva, krie elementi i povikuva funkcii za prikazuvanje na listakod, predmeti ili sodrzinna na kod
function processHash() {

    var link = parent.location.hash;
    if (link !== undefined) {
        var queryString = link.split("#")[1];
        if (queryString !== undefined) {
            queryString = queryString.split("&");
            // Se izminuvaat site patameri vo hash linkot
            // i se povikuvaat soodvetni funkcii
            for (var i = 0; i < queryString.length; i++) {
                var param = queryString[i].split("=");
                // dokolku parametarot e jaziKID Ili predmet ID
                if (param[0] === "jazikID" || param[0] === "predmetID") {
                    if (param[0] === "jazikID") {
                        $("#izborKodLista").val(param[1]);
                    } else {
                        $("#izborPredmetLista").val(param[1]);
                    }
                    // se polni lista so kod
                    polniListaSoKod(param[0], param[1]);
                }
                // za prikazuvanje na izbor za kod spored predmet ili jazik
                if (param[0] === "kodZa") {
                    if (param[1] === "predmet") {
                        prikaziListaPredmeti();
                        $('#optionsRadios2').prop('checked', true);
                    }
                    else {
                        prikaziListaJazik();
                        $('#optionsRadios1').prop("checked", true);
                    }
                }
                if (param[0] === "kodID") {
                    zemiKodSoID(param[1]);
                }
            }
        }
    }
}

// prima paramter (kodID, jazikID, predmetID itn )
// i ja vraka vrednosta na toj parametar
function getValueFromHash(val) {
    var link = parent.location.hash;
    if (link !== undefined) {
        var res = link.split(val + "=")[1].split("&")[0];
        return res;
    } else {
        return undefined;
    }
}

// brise parametar od hash funkcija
function removeParamFromHash(param) {
    var origNash = parent.location.hash;
    var index = -1;
    if (param !== undefined) {
        index = origNash.indexOf(param);
        if (index >= 0) {
            origNash = origNash.substring(0, index);
            parent.location.hash = origNash;
        }
    }

}

$('document body').ready(function() {
    processHash();
});
/*
 $(window).bind('hashchange', processHash);
 
 */

