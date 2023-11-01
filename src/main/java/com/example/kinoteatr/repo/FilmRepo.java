package com.example.kinoteatr.repo;

import com.example.kinoteatr.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepo extends JpaRepository<Film, Long> {

    @Query("SELECT g FROM Film g WHERE g.name_film = :name_film")
    Film findByName(@Param("name_film") String name_film);


}
