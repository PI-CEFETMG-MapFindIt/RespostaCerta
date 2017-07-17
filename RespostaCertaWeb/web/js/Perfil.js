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
