document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('#registrationForm');
    const descricao = document.querySelector('#descricao');
    const preco = document.querySelector('#preco');
    const msgError = document.querySelector('#msgError');
    const msgSuccess = document.querySelector('#msgSuccess');

    form.addEventListener('submit', (event) => {
        event.preventDefault();
        if (!validarFormulario()) {
            msgError.style.display = 'block';
            msgError.innerHTML = '<strong>Preencha todos os campos corretamente antes de cadastrar</strong>';
            msgSuccess.style.display = 'none';
        } else {
            enviarFormulario();
        }
    });

    function validarFormulario() {
        return descricao.value.trim() !== '' && preco.value.trim() !== '';
    }

    function enviarFormulario() {
        const servicoData = {
            descricao: descricao.value,
            preco: preco.value,
            status: 'PENDENTE'
        };

        fetch('/servicos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(servicoData)
        })
            .then(response => {
                if (response.status === 409) {
                    throw new Error('Serviço já existe');
                }
                if (!response.ok) {
                    return response.json().then(error => {
                        throw new Error(error.message || 'Erro ao cadastrar serviço');
                    });
                }
                return response.json();
            })
            .then(data => {
                msgSuccess.style.display = 'block';
                msgSuccess.innerHTML = '<strong>Serviço cadastrado com sucesso!</strong>';
                msgError.style.display = 'none';
            })
            .catch(error => {
                msgError.style.display = 'block';
                msgError.innerHTML = `<strong>${error.message}</strong>`;
                msgSuccess.style.display = 'none';
            });
    }
});
