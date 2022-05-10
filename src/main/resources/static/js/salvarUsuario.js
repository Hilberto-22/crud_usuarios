function salvarUsuario() {
    var id = $("#id").val();
    var nome = $("#nome").val();
    var idade = $("#idade").val();
    var nomeMae = $("#nomeMae").val();
    var nomeSocial = $("#nomeSocial").val();
    var nomePai = $("#nomePai").val();
    var cpf = $("#cpf").val();
    var sexo = $("#selectSexo").val();
    var nacionalidade = $("#nacionalidade").val();
    var naturalidade = $("#naturalidade").val();
    var estado = $("#selectEstado").val();

    $.ajax({
        method: "POST",
        url: "salvar",
        data: JSON.stringify({
            id: id,
            nome: nome,
            idade: idade,
            nomeMae: nomeMae,
            nomeSocial: nomeSocial,
            nomePai: nomePai,
            sexo: sexo,
            cpf: cpf,
            nacionalidade: nacionalidade,
            naturalidade: naturalidade,
            estado: estado
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