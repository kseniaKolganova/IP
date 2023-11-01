package com.example.kinoteatr.repo;

import com.example.kinoteatr.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {


}
