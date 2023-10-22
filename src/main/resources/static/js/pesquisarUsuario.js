$(document).ready(function() {
	$('#modal').click(function() {
		$('#modalPesquisar').modal('show');

		$('#btnBuscar').click(function() {
			pesquisarUsuario();
		})
	})

	function pesquisarUsuario() {
		var nomeCampo = $('#buscaUsuario').val();
		if (nomeCampo != null && nomeCampo.trim() != '') {

			$.ajax({
				method: "GET",
				url: "buscarPorNome",
				data: "name=" + nomeCampo,
				success: function(response) {
					$('#tabelaLista > tbody > tr').remove();
					for (var i = 0; i < response.length; i++) {
						$('#tabelaLista > tbody')
							.append('<tr><td>'
								+ response[i].id + '</td><td>'
								+ response[i].nome + '</td><td>'
								+ response[i].idade  + '</td><td><button type="button" class="btn btn-primary" onclick="editarUsuario('
								+ response[i].id + ')">Editar</button></td><td><button type="button" class="btn btn-danger" onclick="deleteUsuario('
								+ response[i].id + ')">Excluir</button></td></tr>');
					}
				}
			}).fail(function(xhr, status, errorThrown) {
				alert("Erro ao salvar usuario: " + xhr.responseText);
			});
		}
	}
})
