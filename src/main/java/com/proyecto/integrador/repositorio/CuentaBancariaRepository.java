package com.proyecto.integrador.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.integrador.entidades.CuentaBancaria;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Integer> {

}
