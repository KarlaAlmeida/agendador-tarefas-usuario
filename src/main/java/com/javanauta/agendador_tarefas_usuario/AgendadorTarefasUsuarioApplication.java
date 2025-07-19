package com.javanauta.agendador_tarefas_usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgendadorTarefasUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendadorTarefasUsuarioApplication.class, args);
	}

}
