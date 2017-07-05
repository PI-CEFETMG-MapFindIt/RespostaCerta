$('input[name="altQuestao"]').on('change', function(e) {
    let inputs = document.getElementsByName("altQuestao");
    for(let i=0; i<inputs.length; i++){
	inputs[i].parentNode.className="col-md-12";
    }
    e.target.parentNode.className="col-md-12 alert alert-success";
});

$('#aberta').on('change', function(e){
	$('#divAlternativas').addClass('hidden');
});

$('#fechada').on('change', function(e){
	$('#divAlternativas').removeClass('hidden');
});
//Identifica se o usuario criou modulo ou disciplina
var nivel=0;

function salvarModulo(){
    nivel=1;
    $('#fecharModulo').click();
    $('.modal-backdrop').remove()
    $('#modulo').append(`<option>${$('#nomeModulo').val()}</option>`);
    $('#modulo').val($('#nomeModulo').val()).change();
    $('#idtNovo').val(nivel);
    $('#novoModuloIdDisciplina').val($('#disciplina').val());
    $('#novoModulo').val($('#nomeModulo').val());
}

function salvarDisciplina(){
    nivel=2;
    $('#fecharModulo').click();
    $('#fecharDisciplina').click();
    $('#modalDisciplina').removeClass('in');
    $('.modal-backdrop').remove()
    $('#disciplina').append(`<option>${$('#nomeDisciplina').val()}</option>`);
    $('#disciplina').val($('#nomeDisciplina').val()).change();
    $('#modulo').append(`<option>${$('#nomeModulo').val()}</option>`);
    $('#modulo').val($('#nomeModulo').val()).change();
    $('#idtNovo').val(nivel);
    $('#novoModulo').val($('#nomeModulo').val());
    $('#novaDisciplina').val($('#nomeDisciplina').val());
}

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
