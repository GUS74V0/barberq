document.addEventListener('DOMContentLoaded', function() {
  const form = document.querySelector('#registrationForm');
  const nome = document.querySelector('#nome');
  const usuario = document.querySelector('#usuario');
  const rua = document.querySelector('#rua');
  const numero = document.querySelector('#numero');
  const bairro = document.querySelector('#bairro');
  const cidade = document.querySelector('#cidade');
  const estado = document.querySelector('#estado');
  const cep = document.querySelector('#cep');
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
      verificarDuplicidade();
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
    if (rua.value.trim() === "") valid = false;
    if (numero.value.trim() === "") valid = false;
    if (bairro.value.trim() === "") valid = false;
    if (cidade.value.trim() === "") valid = false;
    if (estado.value.trim() === "") valid = false;
    if (cep.value.trim() === "") valid = false;
    if (senha.value.trim() === "" || senha.value !== confirmSenha.value) valid = false;

    return valid;
  }

  function verificarDuplicidade() {
    const barbeariaData = {
      nome: nome.value,
      email: usuario.value,
      rua: rua.value,
      numero: numero.value,
      bairro: bairro.value,
      cidade: cidade.value,
      estado: estado.value,
      cep: cep.value
    };
  
    fetch('/barbearias/check', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(barbeariaData)
    })
      .then(response => response.json())
      .then(data => {
        if (data.exists) {
          msgError.style.display = 'block';
          msgError.innerHTML = '<strong>Barbearia já cadastrada com esses dados</strong>';
          msgSuccess.style.display = 'none';
        } else {
          enviarFormulario(barbeariaData);
        }
      })
      .catch(error => {
        msgError.style.display = 'block';
        msgError.innerHTML = '<strong>Erro ao verificar duplicidade</strong>';
        msgSuccess.style.display = 'none';
      });
  }

  function enviarFormulario(barbeariaData) {
    barbeariaData.senha = senha.value;

    fetch('/barbearias', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(barbeariaData)
    })
        .then(response => response.json())
        .then(data => {
          msgSuccess.style.display = 'block';
          msgSuccess.innerHTML = '<strong>Barbearia cadastrada com sucesso!</strong>';
          msgError.style.display = 'none';
        })
        .catch(error => {
          msgError.style.display = 'block';
          msgError.innerHTML = '<strong>Erro ao cadastrar barbearia</strong>';
          msgSuccess.style.display = 'none';
        });
  }
});
