package com.proyecto.integrador;



import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.proyecto.integrador.entidades.Rol;
import com.proyecto.integrador.entidades.Usuario;
import com.proyecto.integrador.entidades.UsuarioRol;
import com.proyecto.integrador.servicios.UsuarioService;

@SpringBootApplication
public class SistemaFactoringBackendApplication implements CommandLineRunner{
	@Autowired
	private UsuarioService usuarioService;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaFactoringBackendApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		/* try {
			Usuario usuario = new Usuario();
			
			usuario.setNombre("Jeimy");
			usuario.setApellidoPa("Apolaya");
			usuario.setApellidoMa("Jurado");
			usuario.setTelefono("938311721");
			usuario.setCorreo("apolaya@gmail.com");
			usuario.setUsername("jamie");
			usuario.setPassword("12345");
			usuario.setFoto("foto.png");
			usuario.setIdTipoUsu(1);
			usuario.setIdCuentaBancaria(1);
			usuario.setFecha(null);
			usuario.setIdHisInver(1);
			usuario.setDni(73831172);
			usuario.setRuc(1);
			usuario.setRazonSocial("a");
			usuario.setDescripcion("a");
			usuario.setIdSubasta(1);
				
			Rol rol = new Rol();
			rol.setIdTipoUsu(1L);
			rol.setTipo("ADMIN");
			
			
			Set<UsuarioRol> usuarioRoles = new HashSet<>();
			UsuarioRol usuarioRol = new UsuarioRol();
			usuarioRol.setRol(rol);
			usuarioRol.setUsuario(usuario);
			usuarioRoles.add(usuarioRol);
			
			Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles);
			System.out.println(usuarioGuardado.getUsername());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}

}
