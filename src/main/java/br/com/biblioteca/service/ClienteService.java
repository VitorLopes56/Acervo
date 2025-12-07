package br.com.biblioteca.service;

import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public List<Cliente> listarTodos() {
        return repo.findAll();
    }

    public Cliente salvar(Cliente cliente) {
        return repo.save(cliente);
    }

    public List<Cliente> searchByNomeOrCpf(String query) {
        return repo.searchByNomeOrCpf(query);
    }
}
