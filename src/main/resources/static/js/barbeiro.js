document.addEventListener('DOMContentLoaded', function() {
  const form = document.querySelector('#registrationForm');
  const nome = document.querySelector('#nome');
  const usuario = document.querySelector('#usuario');
  const senha = document.querySelector('#senha');
  const confirmSenha = document.querySelector('#confirmSenha');
  const barbeariaSelect = document.querySelector('#barbeariaSelect');
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
      verificarDuplicidadeBarbeiro();
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
    if (barbeariaSelect.value === "") valid = false;

    return valid;
  }

  function verificarDuplicidadeBarbeiro() {
    const barbeiroData = {
      nome: nome.value,
      email: usuario.value,
      barbearia: { id: barbeariaSelect.value }
    };

    fetch('/barbeiros/check', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(barbeiroData)
    })
        .then(response => response.json())
        .then(data => {
          if (data.exists) {
            msgError.style.display = 'block';
            msgError.innerHTML = '<strong>Barbeiro já cadastrado com esses dados</strong>';
            msgSuccess.style.display = 'none';
          } else {
            enviarFormulario(barbeiroData);
          }
        })
        .catch(error => {
          msgError.style.display = 'block';
          msgError.innerHTML = '<strong>Erro ao verificar duplicidade</strong>';
          msgSuccess.style.display = 'none';
        });
  }

  function enviarFormulario(barbeiroData) {
    barbeiroData.senha = senha.value;

    fetch('/barbeiros', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(barbeiroData)
    })
        .then(response => response.json())
        .then(data => {
          msgSuccess.style.display = 'block';
          msgSuccess.innerHTML = '<strong>Barbeiro cadastrado com sucesso!</strong>';
          msgError.style.display = 'none';
        })
        .catch(error => {
          msgError.style.display = 'block';
          msgError.innerHTML = '<strong>Erro ao cadastrar barbeiro</strong>';
          msgSuccess.style.display = 'none';
        });
  }

  function fetchBarbearias() {
    fetch('/barbearias')
        .then(response => response.json())
        .then(data => {
          data.forEach(barbearia => {
            const option = document.createElement('option');
            option.value = barbearia.id;
            option.text = barbearia.nome;
            barbeariaSelect.add(option);
          });
        })
        .catch(error => {
          console.error('Erro ao buscar barbearias:', error);
        });
  }

  fetchBarbearias();
});
