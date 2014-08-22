/*
 * Alatka za prikazuvanje na kod so oznacena sintaska i broj linii
 * @type CodeMirror
 * 
 */
window.CodeView = (function() {
    var Code = {
        Editor: {}
    };
    // Editor objekt so site funkcii za CodeMirror
    Code.Editor = {
        //Init
        init: function(textArea, mode, readOnly) {
            // Parametri za CodeMirror
            this.codeEditor = CodeMirror.fromTextArea(textArea, {
                lineNumbers: true,
                matchBrackets: true,
                mode: mode,
                readOnly: readOnly,
                theme: 'lesser-dark',
                autofocus: true,
                lineWrapping: true
            });
            // Po default se krie sekcijata za prikazuvanje kod
            $("#sodrzinaKod").css("display", "none");
        },
        // Pomosni funkcii 
        // Za menuvanje na tema
        setTheme: function(theme) {
            this.codeEditor.setOption("theme", theme);
        },
        // Za  postavuvanje na nacinot na koj sto ke se
        // prikazuva cod, c/c++, javascript, html , clike itn
        setMode: function(mode) {
            this.codeEditor.setMode("mode", mode);
        },
        // Vraka sodrzina na CodeEditorot
        getCode: function() {
            return this.codeEditor.getValue();
        },
        // postavuva sodrzina na code editorot
        // @param code - sodrzina kako string
        setCode: function(code) {

            this.codeEditor.setValue(code);
            this.codeEditor.focus();
        },
        //Funkcija koja zema kod od textarea i ja menuva visinata na
        //CodeMirror objektot spored visinata na textArea
        setCodeFromTextArea: function(id) {
            Code.Editor.setCode($("#codeSrc" + id).val());
            $(".codeSrc" + id).css("display", "block");
            $(".codeSrc" + id).css("visibility", "hidden");
            var height = $(".codeSrc" + id).prop("scrollHeight");
            $(".codeSrc" + id).css("display", "none");
            if (height > 700) {
                height = 700;
            } else if (height < 200)
                height = 200;
            Code.Editor.codeEditor.setSize(null, height);
        },
        // Funkcija za zacuvuanje na kod
        // Se praka POST ajax baranje na server 
        // so ime ne fajlot i sodrzinata sto treba da se dodade
        saveCode: function() {
            // Se citaat soodvenite parametri
            var kodID = getValueFromHash("kodID");
            var jazik = $("#kodPregledJazik").val();
            var sodrzinaKod = htmlUnescape(this.getCode());
            var page = $("#kodPregledStrana").val();

            var filename = jazik + "-" + kodID + "-" + page + ".txt";
            var request = $.ajax({
                type: "POST",
                url: "SmeniKod",
                dataType: "json",
                data: {fileName: filename, sourceCode: sodrzinaKod}
            });
            // Dokolku nastane greska korisnikot se izvestuva
            request.error(function(httpObj, textStatus) {
                if (httpObj.status === 200) {
                    $(".label-success").fadeIn().delay(3000).fadeOut('slow');
                    $(".codeSrc" + page).html(htmlUnescape(Code.Editor.getCode()));
                }
                else {
                    $(".label-success").fadeIn().delay(3000).fadeOut('slow');
                    Code.Editor.setCode(kodID);
                }
            });
        },
        //Brisenje na kod od server\
        // Se praka ID na kod taka sto se brise od baza
        deleteCode: function() {
            var kodID = getValueFromHash("kodID");
            var request = $.ajax({
                type: "POST",
                url: "deleteCode",
                datatype: "json",
                data: {codeID: kodID},
                success:function(){
                    $(document).delay(2000);
                    console.log("waiting");
                    removeParamFromHash("&kodID");
                    processHash();
                    skrijSodrzinaKod();
                }
            });

            request.error(function(httpObj, textStatus) {
                if (httpObj.status === 200) {
                    console.log('deled succ');
                    $(".label-success").fadeIn().delay(3000).fadeOut('slow');
                      
                  
                } else {
                    $(".label-success").fadeIn().delay(3000).fadeOut('slow');
                }
            });
        }
    };
    return Code;
})();

