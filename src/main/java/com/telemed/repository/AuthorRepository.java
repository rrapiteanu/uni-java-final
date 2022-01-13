package com.telemed.repository;

import com.telemed.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorRepository extends  JpaRepository<Author, Integer> {
    Author findAuthorById(Long id);
    List<Author> findAll();
}


