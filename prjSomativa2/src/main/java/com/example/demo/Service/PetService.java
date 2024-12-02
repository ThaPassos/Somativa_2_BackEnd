package com.example.demo.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entitie.Cliente;
import com.example.demo.entitie.Pet;
import com.example.demo.repositorys.ClienteRepository;
import com.example.demo.repositorys.PetRepository;

@Service
public class PetService {
	private final PetRepository petRepository;
	private final ClienteRepository clienteRepository;

	@Autowired
	public PetService(PetRepository petRepository, ClienteRepository clienteRepository) {
		this.petRepository = petRepository;
		this.clienteRepository = clienteRepository;
	}

	public Pet salvarPet(Pet pet) {
		if(pet.getCliente() != null && pet.getCliente().getId() != null) {
			Optional<Cliente> cliente = clienteRepository.findById(pet.getCliente().getId());
			if(cliente.isPresent()) {
				pet.setCliente(cliente.get());
				return petRepository.save(pet);
			}else {
				throw new RuntimeException("Cliente não encontrado");
			}
		}else {
			throw new RuntimeException("ID do cliente é Obrigatório");
		}
	}

	public Pet buscarPetPorId(Long id) {
		return petRepository.findById(id).orElse(null);
	}

	public List<Pet> buscarTodosPet() {
		return petRepository.findAll();
	}

	public void excluirPet(Long id) {
		petRepository.deleteById(id);
	}

	public Pet atualizarPet(Long id, Pet petAtualizado) {
		Optional<Pet> petExistente = petRepository.findById(id);
		if (petExistente.isPresent()) {
			Pet pet = petExistente.get();
			pet.setNome(petAtualizado.getNome());
			pet.setTipo(petAtualizado.getTipo());
			pet.setRaca(petAtualizado.getRaca());
			pet.setIdade(petAtualizado.getIdade());
			if (petAtualizado.getCliente() != null) {
				pet.setCliente(petAtualizado.getCliente());
			}
			return petRepository.save(pet);
		} else {
			return null;
		}
	}
}