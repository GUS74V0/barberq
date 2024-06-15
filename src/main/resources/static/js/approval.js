document.addEventListener('DOMContentLoaded', function() {
    const requestTableBody = document.querySelector('.dados_tabela');

    function aprovarSolicitacao(id) {
        fetch(`/barbeiros/aprovar/${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(err => { throw new Error(err.message); });
                }
                return response.json().catch(() => { return {}; }); // Trata resposta vazia
            })
            .then(data => {
                carregarSolicitacoes();
                alert("Cadastro do barbeiro aprovado");
            })
            .catch(error => {
                console.error('Erro:', error);
                alert(error.message);
            });
    }

    function rejeitarSolicitacao(id) {
        fetch(`/barbeiros/rejeitar/${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(err => { throw new Error(err.message); });
                }
                return response.json().catch(() => { return {}; }); // Trata resposta vazia
            })
            .then(data => {
                carregarSolicitacoes();
                alert("Cadastro do barbeiro rejeitado");
            })
            .catch(error => {
                console.error('Erro:', error);
                alert(error.message);
            });
    }

    function carregarSolicitacoes() {
        fetch('/barbeiros/solicitacoes')
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

        dados.forEach(function(solicitacao) {
            const tr = document.createElement('tr');

            const tdSelecionar = document.createElement('td');
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            tdSelecionar.appendChild(checkbox);

            const tdNome = document.createElement('td');
            tdNome.textContent = solicitacao.nome;

            const tdEmail = document.createElement('td');
            tdEmail.textContent = solicitacao.email;

            const tdBarbearia = document.createElement('td');
            tdBarbearia.textContent = solicitacao.barbeariaNome;

            const tdStatus = document.createElement('td');
            tdStatus.textContent = solicitacao.status;

            const tdAcoes = document.createElement('td');
            const btnAprovar = document.createElement('button');
            btnAprovar.textContent = "Aprovar";
            btnAprovar.onclick = function() {
                aprovarSolicitacao(solicitacao.id);
            };
            const btnRejeitar = document.createElement('button');
            btnRejeitar.textContent = "Rejeitar";
            btnRejeitar.onclick = function() {
                rejeitarSolicitacao(solicitacao.id);
            };
            tdAcoes.appendChild(btnAprovar);
            tdAcoes.appendChild(btnRejeitar);

            tr.appendChild(tdSelecionar);
            tr.appendChild(tdNome);
            tr.appendChild(tdEmail);
            tr.appendChild(tdBarbearia);
            tr.appendChild(tdStatus);
            tr.appendChild(tdAcoes);

            requestTableBody.appendChild(tr);
        });
    }

    carregarSolicitacoes();
});
