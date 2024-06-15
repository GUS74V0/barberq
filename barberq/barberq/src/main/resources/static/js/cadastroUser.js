document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('#registrationForm');
    const nome = document.querySelector('#nome');
    const usuario = document.querySelector('#usuario');
    const senha = document.querySelector('#senha');
    const confirmSenha = document.querySelector('#confirmSenha');
    const msgError = document.querySelector('#msgError');
    const msgSuccess = document.querySelector('#msgSuccess');
    const btnVerSenha = document.querySelector('#verSenha');
    const btnVerConfirmSenha = document.querySelector('#verConfirmSenha');

    function toggleVisibility(input, icon) {
        const type = input.getAttribute('type') === 'password' ? 'text' : 'password';
        input.setAttribute('type', type);
        icon.classList.toggle('fa-eye-slash');
    }

    btnVerSenha.addEventListener('click', () => toggleVisibility(senha, btnVerSenha));
    btnVerConfirmSenha.addEventListener('click', () => toggleVisibility(confirmSenha, btnVerConfirmSenha));

    form.addEventListener('submit', (event) => {
        console.log('Formulário submetido');
        event.preventDefault();
        if (validarFormulario()) {
            verificarDuplicidadecliente();
        } else {
            msgError.style.display = 'block';
            msgError.innerHTML = '<strong>Preencha todos os campos corretamente antes de cadastrar</strong>';
            msgSuccess.style.display = 'none';
        }
    });

    function validarFormulario() {
        let valid = true;

        if (nome.value.trim() === "") valid = false;
        if (usuario.value.trim() === "") valid = false;
        if (senha.value.trim() === "" || senha.value !== confirmSenha.value) valid = false;

        return valid;
    }

    function verificarDuplicidadecliente() {
        const clienteData = {
            nome: nome.value,
            email: usuario.value,
        };

        fetch('/clientes/check', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(clienteData)
        })
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    msgError.style.display = 'block';
                    msgError.innerHTML = '<strong>cliente já cadastrado com esses dados</strong>';
                    msgSuccess.style.display = 'none';
                } else {
                    enviarFormulario(clienteData);
                }
            })
            .catch(error => {
                msgError.style.display = 'block';
                msgError.innerHTML = '<strong>Erro ao verificar duplicidade</strong>';
                msgSuccess.style.display = 'none';
            });
    }

    function enviarFormulario(clienteData) {
        clienteData.senha = senha.value;

        fetch('/clientes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(clienteData)
        })
            .then(response => response.json())
            .then(data => {
                msgSuccess.style.display = 'block';
                msgSuccess.innerHTML = '<strong>cliente cadastrado com sucesso!</strong>';
                msgError.style.display = 'none';

                setTimeout(() => {
                    window.location.href = './login.html';
                }, 3000);

            })
            .catch(error => {
                msgError.style.display = 'block';
                msgError.innerHTML = '<strong>Erro ao cadastrar cliente</strong>';
                msgSuccess.style.display = 'none';
            });
    }


});