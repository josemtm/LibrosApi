package com.sondeos.codechallenge.controller.libro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.sondeos.codechallenge.entity.Libro;
import com.sondeos.codechallenge.exception.ObjectNotFoundException;
import com.sondeos.codechallenge.service.libro.LibroServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class LibroControllerImplTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private LibroControllerImpl LibroControllerImpl;


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(LibroControllerImpl).build();

    }



    @Test
    public void getLibros() throws Exception
    {
        Libro libro = new Libro("prueba", "jose", 1000.0,
                new Date());

        MvcResult result = mvc.perform( MockMvcRequestBuilders
                .post("/sondeos/api/challenge/v1/libros")
                .content(asJsonString(libro))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        mvc.perform( MockMvcRequestBuilders
                        .get("/sondeos/api/challenge/v1/libros")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].titulo").isNotEmpty());
    }

    @Test
    public void getLibroById() throws Exception
    {
        Libro libro = new Libro("prueba", "jose", 1000.0,
                new Date());

        MvcResult result = mvc.perform( MockMvcRequestBuilders
                .post("/sondeos/api/challenge/v1/libros")
                .content(asJsonString(libro))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        String id = JsonPath.read(content, "id");

        mvc.perform( MockMvcRequestBuilders
                        .get("/sondeos/api/challenge/v1/libros/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    public void postLibro() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .post("/sondeos/api/challenge/v1/libros")
                        .content(asJsonString(new Libro("prueba", "jose", 1000.0,
                                new Date())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateLibro() throws Exception
    {
        Libro libro = new Libro("prueba", "jose", 1000.0,
                new Date());

        MvcResult result = mvc.perform( MockMvcRequestBuilders
                .post("/sondeos/api/challenge/v1/libros")
                .content(asJsonString(libro))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        String id = JsonPath.read(content, "id");
        mvc.perform( MockMvcRequestBuilders
                        .put("/sondeos/api/challenge/v1/libros/{id}", id)
                        .content(asJsonString(libro))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteLibro() throws Exception
    {
        Libro libro = new Libro("prueba", "jose", 1000.0,
                new Date());

        MvcResult result = mvc.perform( MockMvcRequestBuilders
                .post("/sondeos/api/challenge/v1/libros")
                .content(asJsonString(libro))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        String id = JsonPath.read(content, "id");

        mvc.perform( MockMvcRequestBuilders.delete("/sondeos/api/challenge/v1/libros/{id}", id) )
                .andExpect(status().isNoContent());
    }

}