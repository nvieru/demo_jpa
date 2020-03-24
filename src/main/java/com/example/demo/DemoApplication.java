package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

  private @Autowired
  BookRepository bookRepository;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    bookRepository.save(new Book("Ion Creanga", "Amintiri din copilarie", 1));
    bookRepository.save(new Book("Dolf", "Mein Kampgf", 1));
    bookRepository.save(new Book("Someone", "Spring in action", 1));
    bookRepository.save(new Book("Marc Manson", "Arta subtila a nepasarii", 1));

    List<Book> books = bookRepository.findByTitle("Amintiri din copilarie");

    for (Book book : books) {
      System.out.println(book.toString());
    }

    books = bookRepository.findByTitleAndVolume("Amintiri din copilarie", 1);

		for (Book book : books) {
			System.out.println(book.toString());
		}

		books = bookRepository.findByTitleAndVolume("Amintiri din copilarie", 2);

		for (Book book : books) {
			System.out.println(book.toString());
		}

		books = bookRepository.findByTitle("Spring in action");
		for (Book book : books) {
			book.setAutor("Craig Walls");
		}
		bookRepository.saveAll(books);

		Iterable<Book> allBooks = bookRepository.findAll();

		for (Book book : allBooks) {
			System.out.println(book.toString());
		}

  }
}
