document.addEventListener('DOMContentLoaded', function () {
    // --- Seletores de Elementos ---
    const tabs = document.querySelectorAll('.tab');
    const tabContents = document.querySelectorAll('.tab-content');
    const modals = {
        livro: document.getElementById('modal-livro'),
        cliente: document.getElementById('modal-cliente')
    };
    const forms = {
        livro: document.getElementById('form-livro'),
        cliente: document.getElementById('form-cliente')
    };
    const searchInputs = {
        emprestimos: document.getElementById('emprestimos-busca-input'),
        clientes: document.getElementById('clientes-busca-input'),
        estoque: document.getElementById('estoque-busca-input')
    };
    const tables = {
        emprestimos: document.getElementById('tabela-emprestimos'),
        clientes: document.getElementById('tabela-clientes'),
        estoque: document.getElementById('tabela-livros-estoque')
    };

    // --- Funções de Renderização ---
    const renderers = {
        emprestimos: (item) => `
            <td>${item.livro.titulo}</td>
            <td>${item.cliente.nome}</td>
            <td>${new Date(item.dataEmprestimo).toLocaleDateString()}</td>
            <td><span class="status-${item.status.toLowerCase()}">${item.status}</span></td>
            <td>${item.status !== 'DEVOLVIDO' ? `<button class="btn-small btn-devolver" data-id="${item.id}">Devolver</button>` : ''}</td>
        `,
        clientes: (item) => `
            <td>${item.nome}</td>
            <td>${item.cpf}</td>
            <td>${item.telefone}</td>
            <td>${item.email}</td>
            <td><button class="btn-small">Editar</button></td>
        `,
        estoque: (item) => `
            <td>${item.titulo}</td>
            <td>${item.autor}</td>
            <td>${item.categoria}</td>
            <td><span class="${item.disponivel ? 'status-ok' : 'status-late'}">${item.disponivel ? 'Disponível' : 'Indisponível'}</span></td>
            <td><button class="btn-small">Editar</button></td>
        `
    };

    // --- Funções de API ---
    const fetchData = (type, query = '') => {
        const url = `/api/funcionario/${type}?q=${encodeURIComponent(query)}`;
        fetch(url)
            .then(response => response.json())
            .then(data => {
                const tableBody = tables[type];
                tableBody.innerHTML = '';
                data.forEach(item => {
                    const tr = document.createElement('tr');
                    tr.innerHTML = renderers[type](item);
                    tableBody.appendChild(tr);
                });
            })
            .catch(error => console.error(`Erro ao buscar ${type}:`, error));
    };

    // --- Event Listeners ---
    // Abas
    tabs.forEach(tab => {
        tab.addEventListener('click', () => {
            tabs.forEach(t => t.classList.remove('active'));
            tabContents.forEach(c => c.classList.remove('active'));
            tab.classList.add('active');
            document.getElementById(tab.dataset.tab).classList.add('active');
        });
    });

    // Buscas (com debounce)
    let debounceTimer;
    for (const type in searchInputs) {
        searchInputs[type].addEventListener('keyup', () => {
            clearTimeout(debounceTimer);
            debounceTimer = setTimeout(() => fetchData(type, searchInputs[type].value), 300);
        });
    }

    // Modais
    document.getElementById('btn-novo-livro').addEventListener('click', () => modals.livro.style.display = 'flex');
    document.getElementById('btn-novo-cliente').addEventListener('click', () => modals.cliente.style.display = 'flex');

    document.querySelectorAll('.btn-cancelar').forEach(btn => {
        btn.addEventListener('click', () => {
            modals.livro.style.display = 'none';
            modals.cliente.style.display = 'none';
        });
    });

    // Submissão de Formulários (Cadastro)
    forms.livro.addEventListener('submit', (e) => {
        e.preventDefault();
        const data = Object.fromEntries(new FormData(e.target).entries());
        fetch('/api/funcionario/livros', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        }).then(() => {
            modals.livro.style.display = 'none';
            fetchData('estoque'); // Atualiza a tabela
        });
    });

    forms.cliente.addEventListener('submit', (e) => {
        e.preventDefault();
        const data = Object.fromEntries(new FormData(e.target).entries());
        fetch('/api/funcionario/clientes', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        }).then(() => {
            modals.cliente.style.display = 'none';
            fetchData('clientes'); // Atualiza a tabela
        });
    });
    
    // Ação de Devolução
    tables.emprestimos.addEventListener('click', (e) => {
        if (e.target.classList.contains('btn-devolver')) {
            const id = e.target.dataset.id;
            if (confirm('Deseja realmente registrar a devolução deste livro?')) {
                fetch(`/api/funcionario/emprestimos/${id}/devolver`, { method: 'POST' })
                    .then(() => fetchData('emprestimos'));
            }
        }
    });

    // --- Carregamento Inicial ---
    fetchData('emprestimos');
    fetchData('clientes');
    fetchData('estoque');
});
