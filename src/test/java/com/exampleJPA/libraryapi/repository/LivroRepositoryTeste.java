package com.exampleJPA.libraryapi.repository;

import com.exampleJPA.libraryapi.model.Autor;
import com.exampleJPA.libraryapi.model.GeneroLivro;
import com.exampleJPA.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class LivroRepositoryTeste {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void salvarLivroTeste(){
        Livro livro = new Livro();

        livro.setIsbn("12120129");
        livro.setDataPublicacao(LocalDate.of(2024,10, 22));
        livro.setTitulo("A forca");
        livro.setPreco(BigDecimal.valueOf(20));
        livro.setGeneroLivro(GeneroLivro.FICCAO);

        Autor autor = autorRepository.findById(UUID.fromString(
                "1644419a-94f9-40d0-aa78-11c47d7706f6")).orElse(null);
        livro.setAutor(autor);

        livroRepository.save(livro);
    }
}
