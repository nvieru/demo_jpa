package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
//localhost:8080/book
@RequestMapping("/book")
@Validated
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public void addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
    }

    /*
    localhost:8080/book/1?title=Titlu
     */
    @PutMapping("/{id}")
    public void updateBookTitle(@PathVariable @Valid @Min(0) Long id, @RequestParam String title) {
        bookService.updateBookTitle(id, title);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity handleException() {
        return ResponseEntity.badRequest().body("Bad request");
    }
}
