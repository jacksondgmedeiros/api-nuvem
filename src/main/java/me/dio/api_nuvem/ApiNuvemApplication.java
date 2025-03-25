package me.dio.api_nuvem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//configuração de swagger para remover o erro de CORS
//@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@SpringBootApplication
public class ApiNuvemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiNuvemApplication.class, args);
	}

}
