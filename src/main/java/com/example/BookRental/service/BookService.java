package com.example.BookRental.service;

import com.example.BookRental.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private static List<Book> bookInventory =new ArrayList<>();

    public void addBook(Book book){
        bookInventory.add(book);
    }

    public void updateBookStatus(String isbn, String newStatus){
        for(Book book: bookInventory){
            if(book.getIsbn().equals(isbn)){
                book.setStatus(newStatus);
                break;
            }
        }
    }

    public List<Book> searchBooks(String query){
        return bookInventory.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase())
                || book.getAuthor().toLowerCase().contains(query.toLowerCase())
                || book.getIsbn().equals(query))
                .collect(Collectors.toList());
    }

    public List<Book> getAvailableBooks(){
        return bookInventory;
    }
    public List<Book> getRentedBooks(){
        return bookInventory;
    }

    public List<Book> getAllBooks(){
        return bookInventory;
    }

    public void rentBook(String isbn){
        for(Book book: bookInventory){
            if(book.getIsbn().equals(isbn)&& book.getStatus().equals("Available")){
                if(book.getCopies()>0){
                    book.setCopies(book.getCopies()-1);
                    if(book.getCopies()==0){
                        book.setStatus("Rented");
                    }
                }
                break;
            }
        }
    }

    public void returnBook(String isbn){
        for (Book book: bookInventory){
            if(book.getIsbn().equals(isbn)&& book.getStatus().equals("Rented")){
                book.setCopies((book.getCopies()+1));
                if(book.getCopies()>0){
                    book.setStatus("Available");
                }
                break;
            }
        }
    }
}
