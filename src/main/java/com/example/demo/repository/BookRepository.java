package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitleIgnoreCaseContaining(String title);
    List<Book> findByAutorIgnoreCaseContaining(String autor);

    @Modifying
    @Transactional
    @Query("update Book b set b.title=:title where b.id =:id")
    void updateTitle(@Param("id")Long id, @Param("title") String title);

}
