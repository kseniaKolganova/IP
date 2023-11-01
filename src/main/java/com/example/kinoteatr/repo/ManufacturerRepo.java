package com.example.kinoteatr.repo;

import com.example.kinoteatr.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepo extends JpaRepository<Manufacturer, Long> {

}