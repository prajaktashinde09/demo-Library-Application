package com.example.demoApplication.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demoApplication.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    @Transactional
    @Query(value = "Select * from Books book where book.cat_id  = :cat_id", nativeQuery = true)
    List<Book> findByCategoryId(Long cat_id);
}
