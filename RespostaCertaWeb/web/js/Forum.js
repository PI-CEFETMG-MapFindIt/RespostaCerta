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