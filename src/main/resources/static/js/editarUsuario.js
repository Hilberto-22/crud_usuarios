function editarUsuario(id) {
    $.ajax({
        method: "GET",
        url: "buscarPorId",
        data: "id=" + id,
        success: function (response) {
		console.log(response)
            $("#dataNascimento").val(response.dataNascimento).change();
            $("#nome").val(response.nome);
            $("#idade").val(response.idade);
            $('#modalPesquisar').modal('hide');
        }
    }).fail(function (xhr, status, errorThrown) {
        alert("Erro ao buscar usuario por id: " + xhr.responseText);
    });
}