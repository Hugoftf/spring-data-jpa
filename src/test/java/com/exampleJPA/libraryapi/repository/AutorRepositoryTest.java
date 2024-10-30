package com.exampleJPA.libraryapi.repository;

import com.exampleJPA.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

    @Test
    public void salvarTeste(){

        Autor autor = new Autor();

        autor.setName("Jose");
        autor.setDataNascimento(LocalDate.of(1967, 3, 13));
        autor.setNascionalidade("Argentino");

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

    @Test
    public void listarTeste(){
        List<Autor> listaAutor = autorRepository.findAll();
        for (Autor autor:listaAutor){
            System.out.println(autor);
        }
    }

    @Test
    public void contagemTeste(){
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletarPorIdTeste(){
        var id = UUID.fromString("c90187b6-9b54-4304-b25b-7b386dfa4f79");
        if (id != null){
            autorRepository.deleteById(id);
        }
        else{
            throw new IllegalArgumentException("Id nullo");
        }
    }

    @Test
    public void deleterObjeto(){
        var id = UUID.fromString("9141cb1f-a417-4d54-b7ac-6b1aa7fdfdf5");
        var autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            var autorEncontrado = autor.get();
            autorRepository.delete(autorEncontrado);
        }
    }
}
