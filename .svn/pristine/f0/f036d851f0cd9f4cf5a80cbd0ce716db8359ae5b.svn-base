/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var Predmet = {
        Editor: {}
    };
Predmet.Editor = {
    
    savePredmet: function() {
            // Se citaat soodvenite parametri
          var x = $("#UlListaPredmeti li[class='active']");
          var selektor = ".tab-content div[class='tab-pane active'] #opisPredmet";
          var novOpis = $(selektor).val();
          var idPredmet = x.find("a").attr("href").substring(1);
          var selektor = ".tab-content div[class='tab-pane active'] #imepredmet";
          var imePredmet = $(selektor).val();
          var selektor = ".tab-content div[class='tab-pane active'] #semestarP";
          var Semestar = $(selektor).val();
            var request = $.ajax({
                type: "POST",
                url: "doSmeniPredmet",
                dataType: "json",
                data: {predmetName:imePredmet, opisPredmet: novOpis, ID:idPredmet, semestar:Semestar}
            });
            // Dokolku nastane greska korisnikot se izvestuva
            request.error(function(httpObj, textStatus) {
                    $(".label-success").fadeIn().delay(3000).fadeOut('slow');
            });
        },
                
    deletePredmet: function(){
          var x = $("#UlListaPredmeti li[class='active']");
          var idPredmet = x.find("a").attr("href").substring(1);
            var request = $.ajax({
                type: "GET",
                url: "doSmeniPredmet",
                dataType: "json",
                data: {ID:idPredmet}
            });
            location.reload();
            // Dokolku nastane greska korisnikot se izvestuva
            request.error(function(httpObj, textStatus) {
                    $(".label-success").fadeIn().delay(3000).fadeOut('slow');
            });
    }
};

