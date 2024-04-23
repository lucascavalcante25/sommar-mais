/*!
	* Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
	* Copyright 2013-2023 Start Bootstrap
	* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
	*/
// 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

	// Toggle the side navigation
	const sidebarToggle = document.body.querySelector('#sidebarToggle');
	if (sidebarToggle) {
		// Uncomment Below to persist sidebar toggle between refreshes
		// if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
		//     document.body.classList.toggle('sb-sidenav-toggled');
		// }
		sidebarToggle.addEventListener('click', event => {
			event.preventDefault();
			document.body.classList.toggle('sb-sidenav-toggled');
			localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
		});
	}

});

$(document).ready(function() {
	$("#btnCadastrar").on("click", function() {
		$(this).hide();
		$("#formularioContainer").show();
		$("#btnSairCadastro").show();
	});

	$("#btnSairCadastro").on("click", function() {
		$(this).hide();
		$("#formularioContainer").hide();
		$("#btnCadastrar").show();
	});

	$("form").submit(function(event) {
		event.preventDefault(); // Evitar o comportamento padrão do formulário

		var formData = new FormData($(this)[0]); // Crie um objeto FormData com os dados do formulário

		$.ajax({
			type: "POST",
			url: $(this).attr("action"),
			data: formData, // Use o objeto FormData como dados
			contentType: false, // Não defina o tipo de conteúdo, deixe o jQuery decidir
			processData: false, // Não processe os dados, deixe o FormData lidar com isso
			success: function(response) {
				console.log(response);

				// Limpar o formulário
				$("form")[0].reset();

				// Ocultar o formulário
				$("#formularioContainer").hide();

				// Exibir o botão "Cadastrar cliente" novamente
				$("#btnCadastrar").show();

				// Atualizar a tabela de clientes
				// Este código assume que a resposta contém a tabela HTML completa
				$("#tabelaclientes").html(
					$(response).find("#tabelaclientes").html());
			},
			error: function(error) {
				console.log(error);

				// Exibir mensagem de erro (opcional)
				alert("Erro ao cadastrar cliente");
			}
		});
	});
});


$(document).ready(function() {
	$("#myInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#myTable tr").filter(function() {
			$(this).toggle(
				$(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
});


$(document).ready(function() {
	$('#pesquisarCNPJ').click(function() {
		var cnpj = $('#cnpj').val(); // Obtém o valor do campo CNPJ
		// Mostra o spinner ou a mensagem de carregamento
		$('#loadingSpinner').show();
		// Faz a chamada AJAX para o endpoint /consultar-cnpj
		$.ajax({
			type: "GET",
			url: "/consultar-cnpj",
			data: { cnpj: cnpj },
			timeout: 10000, // Tempo limite de 10 segundos
			success: function(response) {
				// Remove as barras invertidas do JSON usando JSON.parse()
				var parsedResponse = JSON.parse(response);
				// Formata o JSON
				var formattedResponse = formatarJSON(parsedResponse);
				// Exibe o JSON formatado no modal
				$('#dadosCNPJModal').html(formattedResponse);
				// Abre o modal
				$('#resultadoModal').modal('show');
			},
			error: function(xhr, status, error) {
				// Exibe uma mensagem de erro personalizada
				var errorMessage;
				if (xhr.status === 429) {
					errorMessage = "Muitas solicitações - Tente novamente mais tarde";
				} else if (xhr.status === 504) {
					errorMessage = "Tempo de espera excedido - Verifique sua conexão de internet";
				} else {
					errorMessage = "Erro na requisição AJAX: " + xhr.responseText;
				}
				$('#mensagemErro').text(errorMessage);
				$('#mensagemErro').show();
			},
			complete: function() {
				// Esconde o spinner ou a mensagem de carregamento quando a requisição estiver completa
				$('#loadingSpinner').hide();
			}
		});
	});
});


function formatarJSON(json) {
	var formattedJSON = '<div id="dadosCNPJ">';
	formattedJSON += '<ul>';

	for (var key in json) {
		if (Array.isArray(json[key])) {
			formattedJSON += '<li><strong>' + key.replace(/_/g, ' ').toUpperCase() + ':</strong></li>';
			formattedJSON += '<ul>';
			json[key].forEach(function(item) {
				for (var subKey in item) {
					// Substitui 'code' por 'Código' e 'text' por 'Descrição'
					var subKeyFormatted = subKey.replace('code', 'Código').replace('text', 'Descrição');
					formattedJSON += '<li><strong>' + subKeyFormatted.replace(/_/g, ' ').toUpperCase() + ':</strong> ' + item[subKey] + '</li>';
				}
			});
			formattedJSON += '</ul>';
		} else {
			formattedJSON += '<li><strong>' + key.replace(/_/g, ' ').toUpperCase() + ':</strong> ' + json[key] + '</li>';
		}
	}

	formattedJSON += '</ul>';
	formattedJSON += '</div>';

	return formattedJSON;
}

$(document).ready(function() {
	// Evento de clique para o botão "Cadastrar Manual"
	$("#cadastrarManual").on("click", function() {
		// Mostra os campos para cadastro manual
		$("#cadastroManual").show();
	});
});

$(document).ready(function() {
	document.getElementById('formCadastroManual').addEventListener('submit', function(event) {
		event.preventDefault(); // Evita o envio padrão do formulário

		// Aqui você pode enviar os dados do formulário para o servidor
		var formData = new FormData(this);

		console.log('Enviando dados do formulário para o servidor:', formData);

		fetch('/salvar-cliente-manual', {
			method: 'POST',
			body: formData
		})
			.then(response => {
				// Verifica se a resposta foi bem-sucedida
				console.log('Status da resposta:', response.status);
				if (!response.ok) {
					throw new Error('Erro ao salvar os dados do cliente');
				}
				return response.json();
			})
			.then(data => {
				// Aqui você pode lidar com a resposta do servidor
				console.log('Dados do cliente salvos com sucesso:', data);
				// Redirecionar ou atualizar a página, se necessário
				// window.location.href = '/pagina-de-sucesso';
			})
			.catch(error => {
				// Trata erros de requisição
				console.error('Erro ao salvar os dados do cliente:', error);
				// Exibir uma mensagem de erro na tela, se necessário
				// document.getElementById('mensagemErro').textContent = 'Erro ao salvar os dados do cliente';
			});
	});
});








