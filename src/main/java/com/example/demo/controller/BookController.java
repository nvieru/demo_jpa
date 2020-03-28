package com.example.demo.controller;

import com.example.demo.Book;
import com.example.demo.BookRepository;
import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
//import java.net.http.HttpResponse;
import java.util.List;

@RestController
//@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping("/book")
    public void addBook(@RequestBody BookDto bookDto){
        bookService.addBook(bookDto);
    }

    /*
    localhost:8080/book/1?title=Titlu
     */
    @PutMapping("/book/{id}")
    public void updateBookTitle(@PathVariable Long id, @RequestParam String title){
        bookService.updateBookTile(id, title);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity handleException(){
        return ResponseEntity.badRequest().body("Bad Request");
    }
}


//Controller-Service-Repository