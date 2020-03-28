package com.example.demo.service;

import com.example.demo.Book;
import com.example.demo.BookRepository;
import com.example.demo.dto.BookDto;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookRepository bookRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        return ((List<Book>)bookRepository.findAll())
                .stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    public void addBook(BookDto bookDto) {
        bookRepository.save(modelMapper.map(bookDto, Book.class));
    }

    public void updateBookTitle(Long id, String title) {
        bookRepository.updateTitle(id, title);

        /*Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            book.get().setTitle(title);
            bookRepository.save(book.get());
        }*/

    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
