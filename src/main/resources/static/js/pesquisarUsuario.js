$(document).ready(function() {
    $('#modal').click(function() {
        $('#modalPesquisar').modal('show');
        limparTabela();

        $('#btnBuscar').click(function() {
            pesquisa();
        });
    });

    function pesquisa() {
        var nomeCampo = $('#buscaUsuario').val();
        $.ajax({
            url: '/usuarios/buscarPorNome/',
            type: 'GET',
            data: { name: nomeCampo },
            success: function(data) {
				limparTabela();
                addRows(data);
                reloadPage()
            },
            error: function() {
                console.error('erro');
            }
        });
    }
    
    function reloadPage(){
		 $('#btnFechar').click(function(){
		 location.reload();
		 })
	}
    
    
    function limparTabela() {
    $('#tabelaLista > tbody > tr').remove();
}

    function addRows(dados) {
        var tabela = $('#tabelaLista');
        dados.forEach(dado => {
            let row = $('<tr>').append(
				$('<td>').text(dado.id),
                $('<td>').text(dado.nome),
                $('<td>').text(dado.dataNascimento).change(),
                $('<td>').text(dado.idade),
                $('<td>').append(
                $('<button>').text('Editar').addClass('btn btn-primary').click(function() {
                    editarUsuario(dado.id);
					$('#modalPesquisar').modal('hide');
                })
            ),
            	$('<td>').append(
                $('<button>').addClass('btn btn-danger').text('Excluir')
                    .on('click', function() {
                        deleteUsuario(dado.id);
                    })
            )
            );
            tabela.append(row);
        });
    }
});
