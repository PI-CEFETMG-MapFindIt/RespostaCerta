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
}