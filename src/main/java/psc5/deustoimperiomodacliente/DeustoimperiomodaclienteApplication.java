package psc5.deustoimperiomodacliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import psc5.deustoimperiomodacliente.gui.ventanaAdministrador;

@SpringBootApplication
public class DeustoimperiomodaclienteApplication {

	public static ventanaAdministrador vAdmin;

	public static void main(String[] args) {
		SpringApplication.run(DeustoimperiomodaclienteApplication.class, args);
	}

}
