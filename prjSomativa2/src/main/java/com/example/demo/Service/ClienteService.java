package com.example.demo.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entitie.Cliente;
import com.example.demo.repositorys.ClienteRepository;

@Service
public class ClienteService {
	private final ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	public Cliente buscarClientePorId(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}
	public Cliente SalvarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public List<Cliente> buscarTodosCliente() {
		return clienteRepository.findAll();
	}

	public void excluirCliente(Long id) {
		clienteRepository.deleteById(id);
	}

	public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
		Optional<Cliente> clienteExistente = clienteRepository.findById(id);
		if (clienteExistente.isPresent()) {
			Cliente cliente = clienteExistente.get();
			cliente.setNome(clienteAtualizado.getNome());
			cliente.setTelefone(clienteAtualizado.getTelefone());
			cliente.setEmail(clienteAtualizado.getEmail());
			return clienteRepository.save(cliente);
		} else {
			return null;
		}
	}
}

