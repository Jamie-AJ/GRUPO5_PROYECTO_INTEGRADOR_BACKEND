package com.proyecto.integrador.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.proyecto.integrador.entidades.cuentaBancaria;
import com.proyecto.integrador.servicios.cuentaBancariaService;

@RestController
@RequestMapping("Rest/cuentaBancaria")
@CrossOrigin(origins = "http://localhost:4200")
public class cuentaBancariaController {
	
	@Autowired
	private cuentaBancariaService cuentabancaria;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<cuentaBancaria>> listaCuentaBancaria(){
	List<cuentaBancaria> lista=cuentabancaria.listaCuentaBancariaTodos();	
	return  ResponseEntity.ok(lista);
		}
	@GetMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaCuentaBancaria(@RequestBody cuentaBancaria obj){
	Map<String, Object> salida= new  HashMap<>();
	try {
		cuentaBancaria objsalida=cuentabancaria.insertaActualizaCuentaBancaria(obj);
		if (objsalida==null) {
			salida.put("mensaje", "No se registro");
			
		} else {
			salida.put("mensaje", " se registro la cuenta:   "+ obj.getIdCuentaBancaria());

		}
		
	} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registro");
	}
		
		return ResponseEntity.ok(salida);	
	}
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaCuentaBancaria(@RequestBody cuentaBancaria obj){
	Map<String, Object> salida= new  HashMap<>();
	try {
		Optional<cuentaBancaria> optional=cuentabancaria.listaCuentaBancariaxId(obj.getIdCuentaBancaria());
		if (optional.isPresent()) {
			cuentaBancaria objsalida=cuentabancaria.insertaActualizaCuentaBancaria(obj);
			if (objsalida==null) {
				salida.put("mensaje", "No se actualizo");
				
			} else {
				salida.put("mensaje", " se actualizo la cuenta:   "+ obj.getIdCuentaBancaria());

			}
			
		} else {
			salida.put("mensaje", "el id no existe");

		}
		
	} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se actualizo");
	}
		
		return ResponseEntity.ok(salida);	
	}
	
	

}
