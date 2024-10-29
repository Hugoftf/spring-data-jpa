package com.exampleJPA.libraryapi.repository;

import com.exampleJPA.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void salvarTeste(){

        Autor autor = new Autor();

        autor.setName("Maria");
        autor.setDataNascimento(LocalDate.of(1966, 2, 21));
        autor.setNascionalidade("Brasileiro");

        var autorSalvo = autorRepository.save(autor);

        System.out.println("Autor " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("9141cb1f-a417-4d54-b7ac-6b1aa7fdfdf5");

        Optional<Autor> possivelAutor =  autorRepository.findById(id);

        if (possivelAutor.isPresent()){
            Autor autorEncontrado = possivelAutor.get();

            System.out.println("Dados do autor: " + autorEncontrado);

            autorEncontrado.setNascionalidade("Argentino");

            autorRepository.save(autorEncontrado);


        }
    }
}
