package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitie.Pet;


public interface PetRepository extends JpaRepository<Pet, Long> {
}