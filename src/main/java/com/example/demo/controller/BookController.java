package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public List<BookDto> getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @GetMapping("/title")
    public List<BookDto> getBooksByTitle(@RequestParam String title){
        return bookService.findByTitle(title);
    }

    @GetMapping("/autor")
    public List<BookDto> getBooksByAutor(@RequestParam String autor){
        return bookService.findByAutor(autor);
    }

    @PostMapping
    public ResponseEntity addBook(@RequestBody BookDto bookDto){
       return bookService.addBook(bookDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBookTitle(@PathVariable @Valid @Min(0) Long id, @RequestParam String title){
       return bookService.updateTitle(id, title);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable @Valid @Min(0) Long id){
       return bookService.deleteBook(id);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity handleException(){
        return ResponseEntity.badRequest().body("Bad request!");
    }



/*
    @GetMapping("/book/search")
    public ResponseEntity<List<Book>> getBooks(@RequestParam String term){
        List<Book> bookList = (List<Book>) bookRepository.findAll();
        List<Book> bookResult = new ArrayList<>();
        for (Book book: bookList) {
            if(book.getTitle().toLowerCase().contains(term.toLowerCase())){
                bookResult.add(book);
            }
        }
        //return new ResponseEntity<>(bookResult, HttpStatus.OK);
        return ResponseEntity.ok().body(bookResult);
    }
*/
}


