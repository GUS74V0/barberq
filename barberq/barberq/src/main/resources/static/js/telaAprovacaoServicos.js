document.addEventListener('DOMContentLoaded', function () {
    const requestTableBody = document.querySelector('.dados_tabela');

    function aprovarSolicitacao(id) {
        fetch(`/servicos/aprovar/${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao aprovar serviço');
                }
                return response.json();
            })
            .then(data => {
                carregarSolicitacoes();
                alert("Serviço aprovado com sucesso");
            })
            .catch(error => {
                console.error('Erro:', error);
                alert(error.message);
            });
    }

    function rejeitarSolicitacao(id) {
        fetch(`/servicos/rejeitar/${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Erro ao rejeitar serviço');
                }
                return response.json();
            })
            .then(data => {
                carregarSolicitacoes();
                alert("Serviço rejeitado com sucesso");
            })
            .catch(error => {
                console.error('Erro:', error);
                alert(error.message);
            });
    }

    function carregarSolicitacoes() {
        fetch('/servicos/solicitacoes')
            .then(response => response.json())
            .then(data => {
                gerarDadosNaTabela(data);
            })
            .catch(error => {
                console.error('Erro ao carregar solicitações:', error);
            });
    }

    function gerarDadosNaTabela(dados) {
        requestTableBody.innerHTML = '';

        dados.forEach(function (servico) {
            const tr = document.createElement('tr');

            const tdSelecionar = document.createElement('td');
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            tdSelecionar.appendChild(checkbox);

            const tdDescricao = document.createElement('td');
            tdDescricao.textContent = servico.descricao;

            const tdPreco = document.createElement('td');
            tdPreco.textContent = servico.preco;

            const tdStatus = document.createElement('td');
            tdStatus.textContent = servico.status;

            const tdAcoes = document.createElement('td');
            const btnAprovar = document.createElement('button');
            btnAprovar.textContent = "Aprovar";
            btnAprovar.onclick = function () {
                aprovarSolicitacao(servico.id);
            };
            const btnRejeitar = document.createElement('button');
            btnRejeitar.textContent = "Rejeitar";
            btnRejeitar.onclick = function () {
                rejeitarSolicitacao(servico.id);
            };
            tdAcoes.appendChild(btnAprovar);
            tdAcoes.appendChild(btnRejeitar);

            tr.appendChild(tdSelecionar);
            tr.appendChild(tdDescricao);
            tr.appendChild(tdPreco);
            tr.appendChild(tdStatus);
            tr.appendChild(tdAcoes);

            requestTableBody.appendChild(tr);
        });
    }

    carregarSolicitacoes();
});
