package com.example.kinoteatr.repo;


import com.example.kinoteatr.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, Long> {

}


