function salvarUsuario() {
    var id = $("#id").val();
    var nome = $("#nome").val();
    var idade = $("#idade").val();

    $.ajax({
        method: "POST",
        url: "salvar",
        data: JSON.stringify({
            id: id,
            nome: nome,
            idade: idade,
        }),
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            $("#id").val(response.id);
            alert("Salvou com sucesso!");
        }
    }).fail(function (xhr, status, errorThrown) {
        alert("Erro ao salvar usuario: " + xhr.responseText);
    });
}