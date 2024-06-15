document.addEventListener("DOMContentLoaded", async function () {
  try {
    // Obtém o ID da barbearia da URL
    const urlParams = new URLSearchParams(window.location.search);
    const barbearia_id = urlParams.get('id');

    console.log(barbearia_id);

    // Faz uma solicitação fetch para obter os dados da barbearia com o ID especificado
    const responseBarbearia = await fetch(`/barbearias/${barbearia_id}`);
    const dataBarbearia = await responseBarbearia.json();

    console.log(dataBarbearia);

    // Preenche os campos com os dados da barbearia
    document.getElementById('nomeBarbearia').textContent = dataBarbearia.nome;
    document.getElementById('enderecoBarbearia').textContent = `${dataBarbearia.rua}, ${dataBarbearia.numero}, ${dataBarbearia.bairro}`;
    document.getElementById('emailBarbearia').textContent = dataBarbearia.email;

    // Faz uma solicitação fetch para obter os barbeiros da barbearia
    const dataBarbeiros = dataBarbearia.barbeiros;

    console.log(dataBarbeiros);

    // Cria os cards dos barbeiros
    const barbeirosContainer = document.getElementById('barbeirosContainer');
    dataBarbeiros.forEach(barbeiro => {
      const card = document.createElement("div");
      card.className = "col-sm-6 col-md-4 col-lg-3 mb-4";
      card.innerHTML = `
         <div class="card h-100">
          <div class="card-body">
            <h5 class="card-nome">${barbeiro.nome}</h5>
            <a href="agendaDeHorarios.html?id=${encodeURIComponent(barbeiro.id)}&email=${encodeURIComponent(barbeiro.email)}" class="btn btn-primary btn-card">Agendar</a>
          </div>
        </div>
    `;
      barbeirosContainer.appendChild(card);
    });
  } catch (error) {
    console.error('Erro ao carregar dados da barbearia:', error);
  }
});
