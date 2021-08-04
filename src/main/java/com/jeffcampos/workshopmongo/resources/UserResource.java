package com.jeffcampos.workshopmongo.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeffcampos.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		User jeff = new User("1", "Jeff Campos", "jeff@teste.com");
		User livia = new User("1", "Livia Tenorio de Campos", "livia@gmail.com");
		
		return ResponseEntity.ok().body(Arrays.asList(jeff, livia));
	}
}
