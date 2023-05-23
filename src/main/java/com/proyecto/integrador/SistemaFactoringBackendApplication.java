package com.proyecto.integrador;



//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import com.proyecto.integrador.entidades.Rol;
//import com.proyecto.integrador.entidades.Usuario;
//import com.proyecto.integrador.servicios.UsuarioService;

@SpringBootApplication
public class SistemaFactoringBackendApplication implements CommandLineRunner{
	//@Autowired
	//private UsuarioService usuarioService;
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
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
			usuario.setPassword(bCryptPasswordEncoder.encode("12345"));
			usuario.setFoto("foto.png");
			usuario.setFecha(new Date());
			usuario.setDni("77454558");
			usuario.setEnable("Activo");
			usuario.setIdTipoUsu(2);

			Set<Rol> roles = new HashSet<>();
			Rol rol = new Rol();
			rol.setIdTipoUsu(2L);
			rol.setTipo("ADMIN");
			roles.add(rol);
			
			Usuario usuarioGuardado = usuarioService.insertaActualizaUsuario(usuario);
			System.out.println(usuarioGuardado.getUsername());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}

}
