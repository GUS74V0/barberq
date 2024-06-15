const apiUrl = 'http://localhost:8080/api/horarios'; // URL do backend

async function carregarInformacoesBarbeiro(barbeiroId) {
    try {
        console.log('ID do barbeiro:', barbeiroId);

        const response = await fetch(`/barbeiros/${barbeiroId}`);
        const barbeiroDetails = await response.json();

        console.log('Detalhes do barbeiro:', barbeiroDetails);

        const nomeBarbeiroElement = document.getElementById('nomeBarbeiro');
        const emailBarbeiroElement = document.getElementById('emailBarbeiro');

        if (nomeBarbeiroElement && emailBarbeiroElement) {
            nomeBarbeiroElement.textContent = barbeiroDetails.nome;
            emailBarbeiroElement.textContent = barbeiroDetails.email;
        } else {
            console.error('Elementos HTML não encontrados.');
        }

        // Adicionando console.log para verificar o nome e o email do barbeiro
        console.log('Nome do barbeiro:', barbeiroDetails.nome);
        console.log('Email do barbeiro:', barbeiroDetails.email);

    } catch (error) {
        console.error('Erro ao carregar informações do barbeiro:', error);
    }
}

// Inicialização do frontend
document.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const barbeiroId = urlParams.get('id');

    carregarInformacoesBarbeiro(barbeiroId);
});

// Função para obter todos os horários disponíveis
async function getAllHorariosDisponiveis() {
    try {
        const response = await fetch(apiUrl);
        const horarios = await response.json();
        return horarios;
    } catch (error) {
        console.error('Erro ao obter horários disponíveis:', error);
    }
}

// Função para renderizar serviços
async function renderServices(barbeiroId) {
    try {
        // Faça uma solicitação ao backend para obter os serviços disponíveis para o barbeiro
        const response = await fetch(`/barbeiros/${barbeiroId}/servicos`);
        const services = await response.json();

        // Se não houver serviços disponíveis, exiba uma mensagem de erro ou faça algo apropriado
        if (!services || services.length === 0) {
            console.error('Nenhum serviço disponível.');
            return;
        }

        // Encontre o contêiner onde os cards serão renderizados
        const container = document.getElementById('service-list');

        // Limpe o conteúdo existente dentro do contêiner
        container.innerHTML = '';

        // Renderize os cards com base nos serviços obtidos
        services.forEach(service => {
            const cardDiv = document.createElement('div');
            cardDiv.className = 'col-sm-6 col-md-3 mb-3';
            cardDiv.innerHTML = `
                <div class="card mb-4 box-shadow">
                    <div class="card-body">
                        <h5 class="card-title">${service.descricao}</h5>
                        <p class="card-text">R$ ${service.preco}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <button class="btn btn-primary" onclick="selecionarServico(${service.id})">Selecionar</button>
                            </div>
                        </div>
                    </div>
                </div>
            `;
            container.appendChild(cardDiv);
        });
    } catch (error) {
        console.error('Erro ao carregar serviços:', error);
    }
}


// Função para renderizar itens do carrossel
function generateCarouselItems() {
    const carouselItems = document.getElementById('carouselItems');
    const totalDays = 14;
    const daysOfWeek = ['DOM', 'SEG', 'TER', 'QUA', 'QUI', 'SEX', 'SÁB'];

    for (let i = 0; i < totalDays; i += 7) {
        const carouselItem = document.createElement('div');
        carouselItem.classList.add('carousel-item');
        if (i === 0) carouselItem.classList.add('active');

        const row = document.createElement('div');
        row.classList.add('row');

        for (let j = i; j < i + 7; j++) {
            const col = document.createElement('div');
            col.classList.add('col');
            const day = new Date();
            day.setDate(day.getDate() + j);
            const formattedDayOfMonth = day.getDate();
            const formattedDayOfWeek = daysOfWeek[day.getDay()];

            const circle = document.createElement('button');
            circle.classList.add('circle');
            circle.innerHTML = `<span class="day-of-month">${formattedDayOfMonth}</span>`;
            circle.addEventListener('click', () => {
                document.querySelectorAll('.carousel-item').forEach(item => item.classList.remove('active'));
                carouselItem.classList.add('active');
                const formattedDate = `${day.getFullYear()}-${('0' + (day.getMonth() + 1)).slice(-2)}-${('0' + day.getDate()).slice(-2)}`;
                renderHorarios(formattedDate);
            });

            const dayOfWeek = document.createElement('div');
            dayOfWeek.classList.add('day-of-week');
            dayOfWeek.textContent = formattedDayOfWeek;

            col.appendChild(circle);
            col.appendChild(dayOfWeek);
            row.appendChild(col);
        }

        carouselItem.appendChild(row);
        carouselItems.appendChild(carouselItem);
    }

    const today = new Date();
    const formattedToday = `${today.getFullYear()}-${('0' + (today.getMonth() + 1)).slice(-2)}-${('0' + today.getDate()).slice(-2)}`;
    renderHorarios(formattedToday);
}

// Função para renderizar horários
function renderHorarios(dia) {
    const horariosContainer = document.getElementById('tabelaHorarios');
    horariosContainer.innerHTML = '';

    // Horários estáticos
    const horariosEstaticos = [
        { id: 1, horario: '2024-06-07T09:00:00', disponivel: true },
        { id: 2, horario: '2024-06-07T10:00:00', disponivel: false },
        { id: 3, horario: '2024-06-07T11:00:00', disponivel: true },
        { id: 4, horario: '2024-06-07T13:00:00', disponivel: true },
        { id: 5, horario: '2024-06-07T14:00:00', disponivel: false },
        { id: 6, horario: '2024-06-07T15:00:00', disponivel: true },
        // Adicione mais horários conforme necessário
    ];

    // Filtrar os horários do dia especificado
    const horariosDoDia = horariosEstaticos.filter(horario => {
        const dataHora = new Date(horario.horario);
        return dataHora.toISOString().startsWith(dia);
    });

    // Renderizar os horários na página
    horariosDoDia.forEach(horario => {
        const horarioAtual = new Date(horario.horario).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

        const cardDiv = document.createElement('div');
        cardDiv.className = 'col-sm-6 col-md-3 mb-3';
        cardDiv.innerHTML = `
            <div class="card mb-4 box-shadow">
                <div class="card-body">
                    <h5 class="card-title">${horarioAtual}</h5>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="btn-group">
                            <button class="btn ${horario.disponivel ? 'btn-primary' : 'btn-success'}" onclick="reservarHorario(${horario.id})">${horario.disponivel ? 'Reservar' : 'Reservado'}</button>
                        </div>
                    </div>
                </div>
            </div>
        `;
        horariosContainer.appendChild(cardDiv);
    });
}

// Exemplo de chamada da função para renderizar horários
renderHorarios('2024-06-07');


// Função para reservar horário
async function reservarHorario(id) {
    try {
        const response = await fetch(`${apiUrl}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        const horarioReservado = await response.json();
        alert('Horário reservado com sucesso!');
        const dia = new Date(horarioReservado.horario).toISOString().split('T')[0];
        renderHorarios(dia);
    } catch (error) {
        console.error('Erro ao reservar horário:', error);
        alert('Erro ao reservar horário. Por favor, tente novamente.');
    }
}

// Inicializa o frontend
document.addEventListener('DOMContentLoaded', () => {
    renderServices();
    generateCarouselItems();
});
