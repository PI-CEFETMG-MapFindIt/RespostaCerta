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
    $('#modalModulo').modal('hide');
}

function salvarDisciplina(){
    nivel=2;
    $('#modalModulo').modal('hide');
    $('#modalDisciplina').modal('hide');
}

function cadastrar(){
    $('#idtNovo').val(nivel);
}