package com.spring.demo.controller;

import com.spring.demo.model.Book;
import com.spring.demo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    private Book book;

    @BeforeEach
    void setUp() {
        // Initialize the Book object for testing
        book = new Book("Spring Boot Basics", "John Doe", "1234567890", 19.99);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void testGetAllBooks() throws Exception {
        // Mock the repository method
        when(bookRepository.findAll()).thenReturn(List.of(book));

        // Test the GET /api/books endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Spring Boot Basics"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value("John Doe"));
    }

    @Test
    void testGetBookById() throws Exception {
        // Mock the repository method
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Test the GET /api/books/{id} endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Spring Boot Basics"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("John Doe"));
    }

    @Test
    void testCreateBook() throws Exception {
        // Mock the repository method to save a book
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Test the POST /api/books endpoint
        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                        .contentType("application/json")
                        .content("{\"title\":\"Spring Boot Basics\", \"author\":\"John Doe\", \"isbn\":\"1234567890\", \"price\":19.99}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Spring Boot Basics"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("John Doe"));
    }

    @Test
    void testUpdateBook() throws Exception {
        // Mock the repository method to find and save a book
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Test the PUT /api/books/{id} endpoint
        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/1")
                        .contentType("application/json")
                        .content("{\"title\":\"Updated Title\", \"author\":\"Jane Doe\", \"isbn\":\"0987654321\", \"price\":24.99}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Jane Doe"));
    }

    @Test
    void testDeleteBook() throws Exception {
        // Mock the repository method to check if the book exists
        when(bookRepository.existsById(1L)).thenReturn(true);

        // Test the DELETE /api/books/{id} endpoint
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1"))
                .andExpect(status().isNoContent());

        // Verify that the delete method was called once
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetBookByIdNotFound() throws Exception {
        // Mock the repository to return an empty Optional
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        // Test the GET /api/books/{id} endpoint when the book is not found
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/999"))
                .andExpect(status().isNotFound());
    }
}
