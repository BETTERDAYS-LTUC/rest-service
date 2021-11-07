package com.example.restservice.Repository;

import com.example.restservice.Model.Pledge;
import org.springframework.data.repository.CrudRepository;

public interface apiRepository extends CrudRepository<Pledge,String> {
}
