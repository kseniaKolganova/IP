package com.example.kinoteatr.repo;

import com.example.kinoteatr.model.ModelUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<ModelUser,Long> {
    ModelUser findByUsername(String username);
}

