package com.example.BookRental.controller;
import com.example.BookRental.model.Book;
import com.example.BookRental.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public String addBook(@RequestBody Book book){
        bookService.addBook(book);
        return "Book added successfully";
    }

    @PutMapping("/status/{isbn}")
    public String updateStatus(@PathVariable String isbn, @RequestParam String status){
        bookService.updateBookStatus(isbn, status);
        return "Book status updated successfully";
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String query){
        return bookService.searchBooks(query);
    }

    @GetMapping("/available")
    public List<Book> getAvailableBooks(){
        return bookService.getAvailableBooks();
    }

    @GetMapping("/rented")
    public List<Book> getRentedBooks(){
        return bookService.getRentedBooks();
    }

    @PutMapping("/rent/{isbn}")
    public String rentBook(@PathVariable String isbn){
        bookService.rentBook(isbn);
        return "Book rented successfully";
    }

    @PutMapping("/return/{isbn}")
    public String returnBook(@PathVariable String isbn){
        bookService.returnBook(isbn);
        return "Book returned successfully";
    }


}
