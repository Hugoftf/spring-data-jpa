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

        livro.setIsbn("254549");
        livro.setDataPublicacao(LocalDate.of(2024,11, 12));
        livro.setTitulo("O sol");
        livro.setPreco(BigDecimal.valueOf(20));
        livro.setGeneroLivro(GeneroLivro.FICCAO);

        Autor autor = autorRepository.findById(UUID.fromString(
                "1644419a-94f9-40d0-aa78-11c47d7706f6")).orElse(null);
        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void atualizarAutorDoLivro(){
        var livroParaAtualizar = livroRepository.findById(UUID.fromString
                ("3a85bf8e-da20-4a15-aeed-5a63dcb8a8b2")).orElse(null);
        var novoAutor = autorRepository.findById
                (UUID.fromString("279547e4-93b4-4c0a-9a94-6b4afad030cc")).orElse(null);

        livroParaAtualizar.setAutor(novoAutor);

        livroRepository.save(livroParaAtualizar);
    }
}


