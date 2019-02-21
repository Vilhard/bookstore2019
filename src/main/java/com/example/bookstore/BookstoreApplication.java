package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			
			crepository.save(new Category("Novel"));
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Drama"));
			
			brepository.save(new Book("A Farewell to Arms", "Ernest Hemingway","1232323-2", 0, null, crepository.findByName("Novel").get(0)));
			brepository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 0, null, crepository.findByName("Novel").get(0)));
	
		};
	}
}
