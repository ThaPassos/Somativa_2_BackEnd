package com.example.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitie.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}