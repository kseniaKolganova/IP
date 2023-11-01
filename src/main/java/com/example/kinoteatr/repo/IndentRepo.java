package com.example.kinoteatr.repo;

import com.example.kinoteatr.model.Indent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IndentRepo extends JpaRepository<Indent, Long> {

    @Query("SELECT g FROM Indent g WHERE g.id_indent = :id")
    Indent findByName(@Param("id") long id);


}