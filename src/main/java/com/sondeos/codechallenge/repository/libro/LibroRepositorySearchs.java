package com.sondeos.codechallenge.repository.libro;

import com.sondeos.codechallenge.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LibroRepositorySearchs extends JpaRepository<Libro, String>, JpaSpecificationExecutor<Libro> {

}
