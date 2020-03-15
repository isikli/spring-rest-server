package com.example.library.controllers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.example.library.model.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
public class AuthorsController {

	@Autowired
	 private AuthorRepository authorRepository;

	@GetMapping("/authors")
	public List<Author> getAuthors() {
		List <Author> authors = authorRepository.findAll ();
		return authors;
	}

	@GetMapping("/authors/{id}")
	Optional <Author> getAuthor (@PathVariable Long id) {
	Optional <Author> author = authorRepository.findById (id);
	return author;
}

@PostMapping("/authors")
Author newAuthor(@RequestBody Author newAuthor) {
	System.out.println (newAuthor.getFirstName ());
	return authorRepository.save(newAuthor);
}

@DeleteMapping("/authors/{id}")
 void deleteAuthor(@PathVariable Long id) {
	 authorRepository.deleteById(id);
 }
}
