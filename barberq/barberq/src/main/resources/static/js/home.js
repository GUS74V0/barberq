document.addEventListener("DOMContentLoaded", async function () {
    try {
        const response = await fetch("/barbearias");
        const jsonData = await response.json();

        const searchButton = document.getElementById('searchButton');
        const pesquisar = document.getElementById('pesquisar');
        const pesquisaContainer = document.getElementById('pesquisaContainer');

        renderServicoProfissional(jsonData, '', pesquisaContainer);

        const handleSearch = () => {
            const searchText = pesquisar.value.toLowerCase();
            renderServicoProfissional(jsonData, searchText, pesquisaContainer);
        };

        searchButton.addEventListener('click', handleSearch);
        pesquisar.addEventListener('keypress', function (e) {
            if (e.key === 'Enter') {
                e.preventDefault();
                handleSearch();
            }
        });
    } catch (error) {
        console.error("Erro ao carregar informações:", error);
    }
});

function renderServicoProfissional(barbearias, searchText, container) {
    container.innerHTML = '';
    let resultsFound = false;

    barbearias.forEach(barbearia => {
        const barbeariaMatchesSearch = barbearia.nome.toLowerCase().includes(searchText);

        if (barbeariaMatchesSearch) {
            resultsFound = true;

            const card = document.createElement("div");
            card.className = "col-sm-6 col-md-4 col-lg-3 mb-4";
            card.innerHTML = `
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-nome">${barbearia.nome}</h5>
                        <a href="PerfilUsuario.html?id=${encodeURIComponent(barbearia.id)}" class="btn btn-primary btn-card">Perfil</a>
                    </div>
                </div>
            `;
            container.appendChild(card);
        }
    });

    if (!resultsFound) {
        const noResultsMessage = document.createElement("div");
        noResultsMessage.className = "alert alert-warning mt-3";
        noResultsMessage.role = "alert";
        noResultsMessage.innerHTML = `
            <strong>Ops!</strong> Nenhum resultado encontrado para a pesquisa realizada.
        `;
        container.appendChild(noResultsMessage);
    }
}