package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public BookService(BookRepository bookRepository) {
        this.bookRepository= bookRepository;
    }


    public List<BookDto> getAllBooks(){
        return ((List<Book>) bookRepository.findAll()).stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    public List<BookDto> getBookById(Long id){
        Optional<Book> book = bookRepository.findById(id);
        List<BookDto> bookDtos = new ArrayList<>();
        if(book.isPresent()){
            BookDto bookDto = modelMapper.map(book.get(), BookDto.class);
            bookDtos.add(bookDto);
        }
        return bookDtos;
    }

    public List<BookDto> findByTitle(String title) {
        List<Book> books = bookRepository.findByTitleIgnoreCaseContaining(title);
        return  books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    public List<BookDto> findByAutor(String autor){
        List<Book> books = bookRepository.findByAutorIgnoreCaseContaining(autor);
        return  books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    public ResponseEntity addBook(BookDto bookDto){
        if(bookDto.getId()!=null){
            if(bookRepository.existsById(bookDto.getId())){
                return  ResponseEntity.ok("Book with id: "+bookDto.getId()+ " exists in DB!");
            }else{
                bookRepository.save(modelMapper.map(bookDto, Book.class));
                return ResponseEntity.ok("Book: "+bookDto.toString()+" was added!");
            }
        }
        bookRepository.save(modelMapper.map(bookDto, Book.class));
        return ResponseEntity.ok("Book: "+bookDto.toString()+" was added!");
    }

    public ResponseEntity updateTitle(Long id, String title){
        if(bookRepository.existsById(id)){
            bookRepository.updateTitle(id, title);
            return ResponseEntity.ok("Book with id: "+id+" has now title: "+title);
        }
        return  ResponseEntity.ok("Book with id: "+id+" doesn't exist in DB!");
    }

    public ResponseEntity deleteBook(Long id) {
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return ResponseEntity.ok("Book with id: "+id+" was deleted!");
        }
        return  ResponseEntity.ok("Book with id: "+id+" not found!");
    }
}
