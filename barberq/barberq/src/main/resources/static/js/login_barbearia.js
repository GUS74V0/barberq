document.addEventListener('DOMContentLoaded', function() {
    const btnTogglePassword = document.querySelector('.fa-eye');
    const inputSenha = document.querySelector('#senha');
    const btnEntrar = document.querySelector('#entrarBtn');
    const inputEmail = document.querySelector('#email');
    const msgError = document.querySelector('#msgError');
    const emailLabel = document.querySelector('#emailLabel');
    const senhaLabel = document.querySelector('#senhaLabel');

    btnTogglePassword.addEventListener('click', () => {
        const type = inputSenha.getAttribute('type') === 'password' ? 'text' : 'password';
        inputSenha.setAttribute('type', type);
    });

    btnEntrar.addEventListener('click', (event) => {
        event.preventDefault();
    
        const email = inputEmail.value;
        const senha = inputSenha.value;

        if (email === '' || senha === '') {
            showErrorMessage('Por favor, preencha todos os campos.');
            return;
        }
    
        fetch('/login_barbearia', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email: email, senha: senha })
        })
        .then(response => {
            if (!response.ok) {
                return response.json().then(data => {
                    throw new Error(data.message || 'Credenciais inválidas. Verifique o e-mail e a senha.');
                });
            }
            return response.json();
        })
        .then(data => {
            if (data.id) {
                localStorage.setItem('token', data.id);
                localStorage.setItem('userType', 'barbearia');
                window.location.href = './home_barbearia.html';
            } else {
                showErrorMessage('Login falhou. Tente novamente.');
            }
        })
        .catch(error => {
            showErrorMessage(error.message || 'Erro ao efetuar login.');
        });
    });
    
    function showErrorMessage(message) {
        emailLabel.style.color = 'red';
        inputEmail.style.borderColor = 'red';
        senhaLabel.style.color = 'red';
        inputSenha.style.borderColor = 'red';
        msgError.textContent = message;
        msgError.style.display = 'block';
        inputEmail.focus();
    }
});