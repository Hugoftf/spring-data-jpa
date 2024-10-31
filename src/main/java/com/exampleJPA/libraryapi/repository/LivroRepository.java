package com.exampleJPA.libraryapi.repository;

import com.exampleJPA.libraryapi.model.Autor;
import com.exampleJPA.libraryapi.model.GeneroLivro;
import com.exampleJPA.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface    LivroRepository extends JpaRepository<Livro, UUID> {

    List<Livro> findByAutor(Autor autor);
    List<Livro> findByIsbn(String isbn);
    List<Livro> findByTitulo(String titulo);
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    //Fazendo Query com o JPQL

    @Query("select l from Livro as l order by l.titulo, l.preco")
    List<Livro> listarTodos();

    @Query("select a from Livro l join l.autor a")
    List<Autor> listarAutor();

    @Query("""
            select l.generoLivro
            from Livro l
            join l.autor a
            where a.nascionalidade = 'Brasileiro'
            order by l.generoLivro
            """)
    List<String> listarGeneroAutoresBrasileiros();

    @Query("select l from Livro l where l.generoLivro = :generoLivro order by :paramOrdenacao")
    List<Livro> encontrandoPorgeneroOrdenado(@Param("generoLivro") GeneroLivro generoLivro,
                                             @Param("paramOrdenacao") String nomePropriedade);


    @Modifying
    @Transactional
    @Query("delete from Livro where generoLivro = ?1")
    void deletandoPorGenero(GeneroLivro generoLivro);

    @Modifying
    @Transactional
    @Query("update from Livro set dataPublicacao = ?1")
    void atualizandoDataPublicacao(LocalDate novaData);
}
