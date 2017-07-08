function salvarDisciplina(){
    nivel=2;
    $('#fecharDisciplina').click();
    $('#modalDisciplina').removeClass('in');
    $('.modal-backdrop').remove()
    $('#disciplina').append(`<option>${$('#nomeDisciplina').val()}</option>`);
    $('#disciplina').val($('#nomeDisciplina').val()).change();
    $('#idtNovo').val(1);
    $('#novaDisciplina').val($('#nomeDisciplina').val());
}
