package com.jeffcampos.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jeffcampos.workshopmongo.domain.Post;
import com.jeffcampos.workshopmongo.domain.User;
import com.jeffcampos.workshopmongo.dto.AuthorDTO;
import com.jeffcampos.workshopmongo.dto.CommentDTO;
import com.jeffcampos.workshopmongo.repository.PostRepository;
import com.jeffcampos.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("10/08/2021"), "Partiu viagem", "Vou viajar para São Rock",
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("11/08/2021"), "Bundinha", "Acordei em São Rock", new AuthorDTO(maria));

		CommentDTO comment1 = new CommentDTO("Boa viagem mana", sdf.parse("10/08/2021"), new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO("Bom dia pra vc TBM", sdf.parse("11/08/2021"), new AuthorDTO(bob));
		CommentDTO comment3 = new CommentDTO("Bom dia pra vc também", sdf.parse("11/08/2021"), new AuthorDTO(alex));
		CommentDTO comment4 = new CommentDTO("Vá om Deus", sdf.parse("11/08/2021"), new AuthorDTO(bob));

		post1.getComments().add(comment1);
		post1.getComments().add(comment4);
		post2.getComments().add(comment2);
		post2.getComments().add(comment3);

		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));

		userRepository.save(maria);

	}

}