package com.example.kinoteatr.repo;

import com.example.kinoteatr.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status, Long> {

}