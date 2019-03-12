package com.example.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	 @RequestMapping(value="/login")
	 public String login() {	
	    return "login";
	}
	
	@RequestMapping(value="/booklist")
	public String bookList(Model model)	{
		model.addAttribute("books", brepository.findAll());
		return "booklist";
	}
	
	//REST get all books
	@GetMapping("/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) brepository.findAll();
	}
	
	//REST get book by id
	@GetMapping("/book/{id}")
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId)	{
		return brepository.findById(bookId);
	}
	
	//Add book
	@RequestMapping(value = "/addbook")
	public String addBook(Model model)	{
		model.addAttribute("book", new Book());
		model.addAttribute("category", crepository.findAll());
		return "addbook";
	}
	
	//Save book
	@PostMapping("/save")
	public String save(Book book)	{
		brepository.save(book);
		return"redirect:booklist";
	}
	
	//Delete book
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		brepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	//Edit book
	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model)	{
	model.addAttribute("book", brepository.findById(bookId));
	model.addAttribute("category", crepository.findAll());
	return "edit";
	}
}
