package com.example.library.controllers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.example.library.model.Book;
import com.example.library.model.Author;
import com.example.library.repository.BookRepository;
import com.example.library.repository.AuthorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.library.exception.AuthorNotFoundException;

@CrossOrigin
@RestController
public class BooksController {

	@Autowired
	 private BookRepository bookRepository;

	 @Autowired
	 private AuthorRepository authorRepository;

	@GetMapping("/books")
	public List<Book> getBooks() {
		List <Book> books = bookRepository.findAll ();
		return books;
	}

	@GetMapping("/authors/{authorId}/books")
	public List<Book> getBooksByAuthor(@PathVariable Long authorId) {
		List <Book> books = bookRepository.findByAuthorId (authorId);
		return books;
	}

	@GetMapping("/books/{id}")
	Optional <Book> getBook (@PathVariable Long id) {
		Optional <Book> book = bookRepository.findById (id);
		return book;
	}

@PostMapping("/authors/{id}/books")
Book newBook(@RequestBody Book newBook, @PathVariable Long id) throws AuthorNotFoundException
{
		System.out.println ("authorid "+ id);
		Optional <Author> optAuthor = authorRepository.findById (id);
		if (!optAuthor.isPresent ())
		{
				throw new AuthorNotFoundException ("Author not found with id " + id);
		}
	  System.out.println ("Author found");
	  newBook.setAuthor(optAuthor.get());
	  return bookRepository.save(newBook);
}

@DeleteMapping("/books/{id}")
 void deleteBook(@PathVariable Long id) {
	 bookRepository.deleteById(id);
 }
}
