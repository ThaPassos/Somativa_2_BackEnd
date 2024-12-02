package com.example.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.PetService;
import com.example.demo.entitie.Pet;


@RestController
@RequestMapping("/pet")
public class PetController {

	private final PetService petService;

	@Autowired
	public PetController(PetService petService) {
		this.petService = petService;
	}

	@PostMapping("/inserir")
	public ResponseEntity<Pet> criarPeto(@RequestBody Pet pet) {
		Pet petSalvo = petService.salvarPet(pet);
		if(petSalvo != null) {
			return ResponseEntity.ok(petSalvo);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping
	public List<Pet> buscarTodosPet() {
		return petService.buscarTodosPet();
	}

	@GetMapping("/{id}")
	public Pet buscarPorId(@PathVariable Long id) {
		return petService.buscarPetPorId(id);
	}

	@DeleteMapping("/{id}")
	public void deletarPet(@PathVariable Long id) {
		petService.excluirPet(id);
	}

	@PutMapping
	public ResponseEntity<Pet> atualizarPet(@PathVariable Long id,
			@RequestBody Pet pet) {
		Pet petAtualizado = petService.atualizarPet(id, pet);
		if (petAtualizado != null) {
			return ResponseEntity.ok(petAtualizado);
		} else {
			return null;
		}
	}
}