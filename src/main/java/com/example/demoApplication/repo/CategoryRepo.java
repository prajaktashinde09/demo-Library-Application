package com.example.demoApplication.repo;

import com.example.demoApplication.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    List<Category> findAll();

    @Transactional
    @Query(value = "Select * from Category category where category.name  = :name", nativeQuery = true)
    Optional<Category> findByCategoryByName(String name);


}
