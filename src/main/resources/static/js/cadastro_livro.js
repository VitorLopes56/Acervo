document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('cadastroLivroForm');
    const messageDiv = document.getElementById('message');

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = new FormData(form);
        const data = {
            titulo: formData.get('titulo'),
            autor: formData.get('autor'),
            categoria: formData.get('categoria'),
            disponivel: formData.get('disponivel') === 'true'
        };

        fetch('/api/funcionario/livros', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            }
            throw new Error('Erro ao cadastrar o livro.');
        })
        .then(data => {
            messageDiv.textContent = 'Livro cadastrado com sucesso! Redirecionando...';
            messageDiv.style.color = 'green';
            setTimeout(() => {
                window.location.href = '/funcionario/funcionario.html';
            }, 2000);
        })
        .catch(error => {
            console.error('Erro:', error);
            messageDiv.textContent = error.message;
            messageDiv.style.color = 'red';
        });
    });
});
