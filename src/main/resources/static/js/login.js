document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('loginForm');
    const messageDiv = document.getElementById('message');

    form.addEventListener('submit', function (event) {
        event.preventDefault(); // Evita o recarregamento da página

        const formData = new FormData(form);
        const searchParams = new URLSearchParams();
        for (const pair of formData) {
            searchParams.append(pair[0], pair[1]);
        }

        fetch('/login', {
            method: 'POST',
            body: searchParams,
        })
        .then(response => {
            // O Spring Security, por padrão, não retorna um erro 401 em falha de login,
            // mas sim redireciona para uma página de erro (ou a mesma página de login com um parâmetro de erro).
            // A propriedade 'redirected' nos diz se a resposta foi um redirecionamento.
            if (response.redirected) {
                // Se fomos redirecionados, verificamos para onde.
                // Se a URL final contém '/login?error', a autenticação falhou.
                if (response.url.includes('error')) {
                    messageDiv.textContent = 'Usuário ou senha inválidos!';
                    messageDiv.style.color = 'red';
                } else {
                    // Se fomos redirecionados para qualquer outra página, o login foi bem-sucedido.
                    // Forçamos o navegador a ir para a nova URL.
                    window.location.href = response.url;
                }
            } else {
                // Se não houve redirecionamento, algo inesperado aconteceu.
                // Pode ser que o login tenha sido bem-sucedido, mas não houve redirecionamento configurado.
                // Vamos tentar redirecionar para a página inicial como um fallback.
                window.location.href = '/';
            }
        })
        .catch(error => {
            console.error('Erro ao tentar fazer login:', error);
            messageDiv.textContent = 'Ocorreu um erro na comunicação. Tente novamente.';
            messageDiv.style.color = 'red';
        });
    });
});
