package com.sondeos.codechallenge.controller.libro;


import com.sondeos.codechallenge.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LibroController   {
    Page<Libro> doGetPaginator(Pageable pageable);

}
