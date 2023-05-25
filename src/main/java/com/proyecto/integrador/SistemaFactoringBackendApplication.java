package com.proyecto.integrador;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.proyecto.integrador.entidades.Cartera;
import com.proyecto.integrador.entidades.Rol;
import com.proyecto.integrador.entidades.TipoTransaccion;
import com.proyecto.integrador.entidades.Usuario;
import com.proyecto.integrador.servicios.CarteraService;
import com.proyecto.integrador.servicios.RolService;
import com.proyecto.integrador.servicios.TipoTransaccionService;
import com.proyecto.integrador.servicios.UsuarioService;

@SpringBootApplication
public class SistemaFactoringBackendApplication implements CommandLineRunner {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private CarteraService carteraService;
	@Autowired
	private RolService rolService;
	@Autowired
	private TipoTransaccionService tipoTransService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SistemaFactoringBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Rol rolExiste = rolService.buscarporId(1L);
			if (rolExiste == null) {
				// inversionista
				Rol rolInv = new Rol();
				rolInv.setTipo("INVERSIONISTA");
				rolService.insertarRol(rolInv);
				// admin
				Rol rolAdmin = new Rol();
				rolAdmin.setTipo("ADMIN");
				rolService.insertarRol(rolAdmin);
				System.out.println("Roles registrado con exito!");
			}
			Usuario usuExiste = usuarioService.buscarUsuarioPorId(1);
			if (usuExiste == null) {
				Usuario usuario = new Usuario();
				usuario.setNombre("Jeimy");
				usuario.setApellidoPa("Apolaya");
				usuario.setApellidoMa("Jurado");
				usuario.setTelefono("938311721");
				usuario.setCorreo("apolaya@gmail.com");
				usuario.setUsername("jamie");
				usuario.setPassword(bCryptPasswordEncoder.encode("12345"));
				usuario.setFoto("foto.png");
				usuario.setFecha(new Date());
				usuario.setDni("77454558");
				usuario.setEnable("Activo");
				usuario.setIdTipoUsu(2L);
				Usuario usuReg = usuarioService.insertaActualizaUsuario(usuario);
				Cartera cartera = new Cartera();
				cartera.setSaldo(10000000);
				cartera.setIdUsu(usuReg.getId());
				carteraService.insertaActualizaCartera(cartera);
				System.out.println("USUARIO registrado con exito!");
			}
			Optional<TipoTransaccion> tipoTransExiste = tipoTransService.buscarxId(1);
			if (tipoTransExiste.isEmpty()) {
				// deposito
				TipoTransaccion tipoDepo = new TipoTransaccion();
				tipoDepo.setTipo("Deposito");
				tipoTransService.insertarTipoTransaccion(tipoDepo);
				// retiro
				TipoTransaccion tipoReti = new TipoTransaccion();
				tipoReti.setTipo("Retiro");
				tipoTransService.insertarTipoTransaccion(tipoReti);
				System.out.println("Tipos registrado con exito!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
