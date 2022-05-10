function deleteUsuario(id) {
    if (confirm('Deseja deletar o usuario?')) {
        $.ajax({
            method: "DELETE",
            url: "delete",
            data: "iduser=" + id,
            success: function (response) {

                $('#' + id).remove();
                alert(response);
            }
        }).fail(function (xhr, status, errorThrown) {
            alert("Erro ao deletar usuario por id: " + xhr.responseText);
        });
    }
}