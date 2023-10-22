function editarUsuario(id) {
    $.ajax({
        method: "GET",
        url: "buscarPorId",
        data: "id=" + id,
        success: function (response) {

            $("#id").val(response.id);
            $("#nome").val(response.nome);
            $("#idade").val(response.idade);
            $("#nomeMae").val(response.nomeMae);
            $("#nomeSocial").val(response.nomeSocial);
            $("#nomePai").val(response.nomePai);
            $("#cpf").val(response.cpf);
            $("#selectSexo").val(response.sexo);
            $("#nacionalidade").val(response.nacionalidade);
            $("#naturalidade").val(response.naturalidade);
            $("#selectEstado").val(response.estado);

            $('#modalPesquisar').modal('hide');
        }
    }).fail(function (xhr, status, errorThrown) {
        alert("Erro ao buscar usuario por id: " + xhr.responseText);
    });
}