 function deleteUsuario(id) {
    if (confirm('Deseja deletar o usuário?')) {
        $.ajax({
            method: "DELETE",
            url: "delete",
            data: { id: id },
            success: function (response) {
                handleDeleteSuccess(id, response);
            },
            error: function (xhr, status, errorThrown) {
                handleDeleteError(xhr);
            }
        });
    }
}

function handleDeleteSuccess(id, response) {
    $('#' + id).remove();
    alert(response);
    window.location.reload();
}

function handleDeleteError(xhr) {
    alert("Erro ao deletar usuário por ID: " + xhr.responseText);
}
