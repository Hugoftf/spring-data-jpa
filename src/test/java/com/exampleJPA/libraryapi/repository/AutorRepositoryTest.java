package com.exampleJPA.libraryapi.repository;

import com.exampleJPA.libraryapi.model.Autor;
import com.exampleJPA.libraryapi.model.GeneroLivro;
import com.exampleJPA.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

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

    @Test
    public void salvarAutorComLivroTeste(){
        Autor autor = new Autor();
        autor.setLivros(new ArrayList<>());

        autor.setName("Hugo");
        autor.setDataNascimento(LocalDate.of(1977, 4, 12));
        autor.setNascionalidade("Brasileiro");

        autorRepository.save(autor);

        Livro livro = new Livro();

        livro.setIsbn("11111111");
        livro.setDataPublicacao(LocalDate.of(2010,12, 12));
        livro.setTitulo("A lua");
        livro.setPreco(BigDecimal.valueOf(300));
        livro.setGeneroLivro(GeneroLivro.CIENCIA);
        livro.setAutor(autor);

        autor.getLivros().add(livro);

        Livro livro2 = new Livro();

        livro2.setIsbn("222222");
        livro2.setDataPublicacao(LocalDate.of(2012,01, 25));
        livro2.setTitulo("Melancia");
        livro2.setPreco(BigDecimal.valueOf(200));
        livro2.setGeneroLivro(GeneroLivro.MISTERIO);
        livro2.setAutor(autor);

        autor.getLivros().add(livro2);

        livroRepository.saveAll(autor.getLivros());
    }
}
