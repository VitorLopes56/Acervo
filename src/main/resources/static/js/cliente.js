document.addEventListener('DOMContentLoaded', function () {
    const tabelaBusca = document.getElementById('tabela-livros-busca');
    const searchInput = document.getElementById('busca-livro-input');

    function carregarLivros(query = '') {
        // Agora, a query é enviada diretamente para a API
        const url = `/api/cliente/livros?q=${encodeURIComponent(query)}`;

        fetch(url)
            .then(response => response.json())
            .then(livros => {
                tabelaBusca.innerHTML = ''; // Limpa a tabela
                if (livros.length === 0 && query) {
                    tabelaBusca.innerHTML = '<tr><td colspan="4">Nenhum livro encontrado para sua busca.</td></tr>';
                } else {
                    livros.forEach(livro => {
                        const tr = document.createElement('tr');
                        tr.innerHTML = `
                            <td>${livro.titulo}</td>
                            <td>${livro.autor}</td>
                            <td>${livro.categoria}</td>
                            <td>
                                ${livro.disponivel ? `<button class="btn-emprestar" data-livro-id="${livro.id}">Solicitar</button>` : '<span class="status-indisponivel">Indisponível</span>'}
                            </td>
                        `;
                        tabelaBusca.appendChild(tr);
                    });
                }
            })
            .catch(error => {
                console.error('Erro ao carregar livros:', error);
                tabelaBusca.innerHTML = '<tr><td colspan="4">Erro ao carregar os livros.</td></tr>';
            });
    }

    // Listener para o botão de emprestar
    tabelaBusca.addEventListener('click', function(event) {
        if (event.target.classList.contains('btn-emprestar')) {
            const livroId = event.target.getAttribute('data-livro-id');
            
            fetch(`/api/cliente/emprestimos`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ livroId: livroId })
            })
            .then(response => {
                if (response.ok) {
                    alert('Empréstimo solicitado com sucesso! A página será atualizada.');
                    window.location.reload();
                } else {
                    alert('Erro ao solicitar o empréstimo.');
                }
            })
            .catch(error => console.error('Erro:', error));
        }
    });

    // Debounce para evitar buscas a cada tecla pressionada
    let debounceTimer;
    searchInput.addEventListener('keyup', () => {
        clearTimeout(debounceTimer);
        debounceTimer = setTimeout(() => {
            carregarLivros(searchInput.value);
        }, 300); // Atraso de 300ms
    });
    
    // Carrega a lista inicial de livros
    carregarLivros();
});
