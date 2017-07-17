document.getElementById("btnDesempenho").onclick = function () {
    location.href = "/RespostaCerta/ControllerServlet?control=Desempenho";
};

document.getElementById("btnDeletaConta").onclick = function () {
    location.href = "/RespostaCerta/ControllerServlet?control=DeletaConta";
};

document.getElementById("btnAlteraDados").onclick = function () {
    document.getElementById("alteraDados").submit();
};

document.getElementById("btnAlteraImagem").onclick = function(){
    document.getElementById("alteraImagem").submit();
};

//bloqueia Submit on Enter, para forçar verificação
function checkEnter(e){
 e = e || event;
 var txtArea = /textarea/i.test((e.target || e.srcElement).tagName);
 return txtArea || (e.keyCode || e.which || e.charCode || 0) !== 13;
}
document.getElementById("alteraSenha").onkeypress = checkEnter;


//verifica se senhas são iguais
document.getElementById("btnAlteraSenha").onclick = function(){
    if(document.getElementById("novaSenhaUsuario").value===document.getElementById("repitaSenha").value){
        if(document.getElementById("senhaAtualUsuario").value === "" ||
                document.getElementById("novaSenhaUsuario").value === "" ||
                document.getElementById("repitaSenha").value === ""){
            document.getElementById("statusSenha").innerHTML = "Campos não podem ficar vazios";
        } else {
            document.getElementById("alteraSenha").submit();
        }
    } else {
        document.getElementById("statusSenha").innerHTML = "Senhas devem ser iguais!";
    }
};

//Função para mudar a imagem do Crop
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#novaImg').attr('src', e.target.result);
            $('#novaImg').cropper({
                aspectRatio: 1/1,
                crop: function(e) {
                }
            });
        }
        reader.readAsDataURL(input.files[0]);
    }
}
$("#imgInp").change(function(){
    readURL(this);
    setTimeout(function(){
        mudarImagem();
    }, 1000);
});

function mudarImagem(){
    //Obtem a imagem cropped em blob
    $("#novaImg").cropper('getCroppedCanvas').toBlob(function (blob) {
        //Envia o blob para o back-end
        var reader = new window.FileReader();
        reader.readAsDataURL(blob);
        reader.onloadend = function() {
            base64data = reader.result;
            $("#blob").val(base64data);
        }
    });

}