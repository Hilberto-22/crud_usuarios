function editarUsuario(id) {
    $.ajax({
        url: '/usuarios/buscarPorId/',
        data: {id: id},
        type: 'GET',
        success: function(detalhesUsuario) {
			console.log(detalhesUsuario)
            // Preencher o modal com os detalhes do usuário
            $('#modalEditar').modal('show');
            $('#nomeEdit').val(detalhesUsuario.nome);
            $('#dataNascimentoEdit').val(detalhesUsuario.dataNascimento);
            $('#idadeEdit').val(detalhesUsuario.idade);

            $('#btnAtualizar').off('click').on('click', function() {
                salvarEdicaoUsuario(id);
            });
        },
        error: function() {
            console.error('Erro ao obter detalhes do usuário para edição');
        }
    });
}

function salvarEdicaoUsuario(id) {
    
    // Obter os novos valores dos campos do modal
    var novoNome = $('#nomeEdit').val();
    var novaDataNascimento = $('#dataNascimentoEdit').val();
    var novaIdade = $('#idadeEdit').val();

    var dadosAtualizados = {
        nome: novoNome,
        dataNascimento: novaDataNascimento,
        idade: novaIdade
    };

    $.ajax({
        url: '/usuarios/atualizar/' + id,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(dadosAtualizados),
        success: function(response) {
			alert("Usuario atualizado")
			window.location.reload();
        },
        error: function(error) {
            console.error('Erro ao atualizar usuário:', error);
        }
    });
    
    $('#modalEditar').modal('hide');
}


































