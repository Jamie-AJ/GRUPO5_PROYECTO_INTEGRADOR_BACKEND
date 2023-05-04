package com.proyecto.integrador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.cuentaBancaria;

public interface cuentaBancariaRepository extends JpaRepository<cuentaBancaria, Integer> {

}
