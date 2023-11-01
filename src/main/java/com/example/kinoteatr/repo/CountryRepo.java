package com.example.kinoteatr.repo;

import com.example.kinoteatr.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, Long> {


}