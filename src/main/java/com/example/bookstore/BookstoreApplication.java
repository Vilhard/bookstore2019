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
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			
			crepository.save(new Category("Novel"));
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Drama"));
			
			brepository.save(new Book("A Farewell to Arms", "Ernest Hemingway","1232323-2", 1929, null, crepository.findByName("Novel").get(0)));
			brepository.save(new Book("Animal Farm", "George Orwell", "2212343-5", 1945, null, crepository.findByName("Novel").get(0)));
			
			User user1 = new User("user", "$2a$10$szPIn3pbTKn8QZ3ErILh6O.d80ah5vWr6nLGmWqQ74ymOg3hZkJMe","john@john.com","USER");
			User user2 = new User("admin", "$2a$10$CHliz9wK/Eojhv/iX0XpO.906H3xBKnpCW26kaE6ymt5vW.l890ma","kate@kate.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
	
		};
	}
}
