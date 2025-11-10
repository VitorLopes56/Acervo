package br.com.biblioteca.service;

import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository repo;
    public ClienteService(ClienteRepository repo) { this.repo = repo; }

    public Cliente salvar(Cliente c) {
        if (c.getDataCadastro() == null) c.setDataCadastro(LocalDateTime.now());
        return repo.save(c);
    }
    public List<Cliente> listar() { return repo.findAll(); }
    public Optional<Cliente> porId(Long id) { return repo.findById(id); }
    public void excluir(Long id) { repo.deleteById(id); }
}
