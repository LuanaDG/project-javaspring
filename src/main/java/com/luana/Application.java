package com.luana;

import com.luana.entities.Categoria;
import com.luana.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Application {

		public static void main(String[] args) throws Exception{

		SpringApplication.run(Application.class, args);
		System.out.println("Olá");
	}

}
