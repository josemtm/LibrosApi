package com.sondeos.codechallenge.service.libro;

import com.sondeos.codechallenge.controller.libro.LibroControllerImpl;
import com.sondeos.codechallenge.entity.Libro;
import com.sondeos.codechallenge.exception.ObjectNotFoundException;
import com.sondeos.codechallenge.repository.libro.LibroRepository;
import java.util.Date;
import java.util.Optional;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LibroServiceImplTest {

    @Mock
    LibroRepository repository;

    @InjectMocks
    LibroServiceImpl service;

    Libro libro;

    @Before
    public  void before(){
        libro = new Libro("prueba", "jose", 1000.0,
                new Date());
    }

    @Test
    public void save() throws Exception {
        libro.setId("idInsertada");
        when(service.save(any(Libro.class))).thenReturn(libro);

        Libro request = new Libro("","",1000.0, libro.getFechaLanzamiento());
        Libro response = service.save(request);

        assertAll(
                () -> assertEquals(response.getId(),libro.getId()),
                () -> assertEquals(response.getTitulo(),libro.getTitulo()),
                () -> assertEquals(response.getAutor(),libro.getAutor()),
                () -> assertEquals(response.getFechaLanzamiento(),libro.getFechaLanzamiento())
        );

    }

    @Test
    public void update() throws ObjectNotFoundException {

        Optional<Libro> libroUpdate = Optional.of(libro);;
        when(repository.findById(anyString())).thenReturn(libroUpdate);
        when(repository.save(any(Libro.class))).thenReturn(libro);
        Libro response = this.service.update(libro,"prueba");
        assertEquals(response.getId(),"prueba");

    }

    @Test
    public void findOne() throws ObjectNotFoundException {
        Optional<Libro> libroUpdate = Optional.of(libro);;
        when(repository.findById(anyString())).thenReturn(libroUpdate);

        Optional<Libro> response = this.service.findOne("prueba");
        assertAll(
                () -> assertTrue(response.isPresent()),
                () -> assertEquals(response.get().getId(),libro.getId()),
                () -> assertEquals(response.get().getTitulo(),libro.getTitulo()),
                () -> assertEquals(response.get().getAutor(),libro.getAutor()),
                () -> assertEquals(response.get().getFechaLanzamiento(),libro.getFechaLanzamiento())
        );
    }


}