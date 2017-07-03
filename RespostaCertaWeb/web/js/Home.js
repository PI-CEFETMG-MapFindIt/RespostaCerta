$(document).ready(function() {
    $('#topicos').DataTable( {
        'pagingType': "simple",
        'iDisplayLength': 5,
        'ordering': false,
        'lengthChange': false,
        'info': false,
        'bFilter': false,
        'language':{
            "emptyTable":     "Sem dados",
            "loadingRecords": "Carregando...",
            "processing":     "Processando...",
            "search":         "Procurar:",
            "zeroRecords":    "Sem resultados",
            "paginate": {
                "first": "Primeiro",
                "last": "Ultimo",
                "next": " Próximo ",
                "previous": " Anterior "
            }
        },
        "bJQueryUI": true,
        "sDom": 'bfrtip'
    } );
} );