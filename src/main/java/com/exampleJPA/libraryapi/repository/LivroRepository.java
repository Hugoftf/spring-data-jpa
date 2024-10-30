package com.exampleJPA.libraryapi.repository;

import com.exampleJPA.libraryapi.model.Autor;
import com.exampleJPA.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    List<Livro> findByAutor(Autor autor);
}
