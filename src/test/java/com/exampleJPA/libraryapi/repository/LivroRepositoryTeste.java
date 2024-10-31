package com.exampleJPA.libraryapi.repository;

import com.exampleJPA.libraryapi.model.Autor;
import com.exampleJPA.libraryapi.model.GeneroLivro;
import com.exampleJPA.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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

    @Test
    public void buscarLivroTeste(){
        var id = UUID.fromString("ca64f229-ceff-4706-ac12-51887bf9ab97");
        var livro = livroRepository.findById(id).orElse(null);

        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor().getName());
    }

    @Test
    public void pesquisarPorIsnb(){
        List<Livro> isbn = livroRepository.findByIsbn("11111111");
        for (Livro livro:isbn){
            System.out.println(livro);
        }
    }

    @Test
    public void pesquisarPorTitulo(){
        List<Livro> titulo = livroRepository.findByTitulo("Melancia");
        for (Livro livro:titulo){
            System.out.println(livro);
        }
    }

    @Test
    public void pesquisarPorTituloEPreco(){
        List<Livro> tituloEPreco = livroRepository.findByTituloAndPreco
                ("Melancia", BigDecimal.valueOf(200.00));
        for (Livro livro:tituloEPreco){
            System.out.println(livro);
        }
    }

    @Test
    public void pesquisarPorTituloOuIsbn(){
        List<Livro> tituloOuIsbn = livroRepository.findByTituloOrIsbn
                ("Melancia", "11111111");
        for (Livro livro:tituloOuIsbn){
            System.out.println(livro);
        }
    }

    @Test
    public void listarLivrosComQueryJPQL(){
        List<Livro> list = livroRepository.listarTodos();
        for (Livro livro:list){
            System.out.println(livro);
        }
    }

    @Test
    public void listarAutorComQueryJPQL(){
        List<Autor> list = livroRepository.listarAutor();
        for (Autor autor:list){
            System.out.println(autor);
        }
    }

    @Test
    public void listarAutoresBrasileirosComQueryJPQL(){
        var resultado = livroRepository.listarGeneroAutoresBrasileiros();
       resultado.forEach(System.out::println);
    }

    @Test
    public void listarPorGeneroQueryParamTest(){
        var resultado = livroRepository.encontrandoPorgeneroOrdenado
                (GeneroLivro.CIENCIA, "dataPublicacao");
        resultado.forEach(System.out::println);

    }

    @Test
    public void deletePorGeneroTest(){
        livroRepository.deletandoPorGenero(GeneroLivro.CIENCIA);
    }

    @Test
    public void atualizandoDataTeste(){
        livroRepository.atualizandoDataPublicacao(LocalDate.of(2001,1,1));
    }

}


