$('document').ready(function(){
jQuery("#dataNascimento").mask("99/99/9999");
});
        function minCaracSenha(senha){
        return senha.match(/[a-zA-Z]/g) && senha.match(/[0-9]/g);
        }

var erroEmail;
        var erroSenhaConf;
        var erroSenha;
        var erroTipo;
        var erroTermo;
        var erroData;
        var erroEmailExiste;
        function validateCadastro(){
        let email = $('#emailCad');
                let emailConf = $('#emailConf');
                let senha = $('#password');
                let senhaConf = $('#password_confirmation');
                let data = $('#dataNascimento');
                let aluno = $('#aluno');
                let prof = $('#prof');
                let termos = $('#aceito');
                let retorno = true;
                if (email.val() != emailConf.val()) {
        emailConf.parent().addClass('has-error');
                if ($('#erroEmail').length === 0) {
        erroEmail = $('<span id="erroEmail" class="help-block">Os e-mails não correspondem</span>').appendTo(emailConf.parent());
        }
        retorno = false;
        } else{
        emailConf.parent().removeClass('has-error');
                if (erroEmail){
        erroEmail.remove();
        }
        }
        if (senha.val() != senhaConf.val()) {
        senhaConf.parent().addClass('has-error');
                if ($('#erroSenhaConf').length === 0) {
        erroSenhaConf = $('<span id="erroSenhaConf" class="help-block">As senhas não correspondem</span>').appendTo(senhaConf.parent());
        }
        retorno = false;
        } else{
        senhaConf.parent().removeClass('has-error');
                if (erroSenhaConf){
        erroSenhaConf.remove();
        }
        }
        if (senha.val().length < 6 || !minCaracSenha(senha.val())){
        senha.parent().addClass('has-error');
                if ($('#erroSenha').length === 0) {
        erroSenha = $('<span id="erroSenha" class="help-block">A senha deve ter ao menos 6 caracteres e deve conter números e letras</span>').appendTo(senha.parent());
        }
        retorno = false;
        } else{
        senha.parent().removeClass('has-error');
                if (erroSenha){
        erroSenha.remove();
        }
        }
        let newdate = data.val().split("/").reverse().join("-");
                let dataValidacao = new Date(newdate);
                if (dataValidacao.toString() == "Invalid Date" || dataValidacao.getFullYear() < 1900){
        data.parent().addClass('has-error');
                if ($("#erroData").length === 0){
        erroData = $('<span id="erroData" class="help-block">Data Inválida</span>').appendTo(data.parent());
        }
        retorno = false;
        } else{
        data.parent().removeClass('has-error');
                if (erroData){
        erroData.remove();
        }
        }
        if (!aluno.is(':checked') && !prof.is(':checked')){
        if ($("#erroTipo").length === 0){
        erroTipo = $('<div class="has-error"><span id="erroTipo" class="help-block">Nada Selecionado</span></div>').appendTo(sexoM.parent().parent());
        }
        retorno = false;
        } else{
        if (erroTipo){
        erroSexo.remove();
        }
        }
        if (!termos.is(':checked')){
        if ($('#erroTermo').length === 0){
        erroTermo = $('<div class="has-error"><span id="erroTermo" class="help-block">Você deve aceitar os termos e condições para se cadastrar</span></div>').appendTo(termos.parent());
        }
        retorno = false;
        } else{
        if (erroTermo){
        erroTermo.remove();
        }
        }
        if (retorno){
            $('#formCadastro').submit();
        }
    }
