package com.bolsadeideas.springboot.sistema.app;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootSistemaApplication implements CommandLineRunner{
	
	/*
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;*/
	
	public static void main(String[] args) {
                System.out.println("** ----- Server-System-online-OK -----**");
		SpringApplication.run(SpringBootSistemaApplication.class, args);
	}

	
        @Override
	public void run(String... args) throws Exception {
		/*
		String password = "12345";
		
		for (int i = 0; i < 4; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);
		}*/
		System.out.println("** ----- Server-System-online-OK -----**");
		
	}
}