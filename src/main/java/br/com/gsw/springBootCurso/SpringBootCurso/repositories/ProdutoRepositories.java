package br.com.gsw.springBootCurso.SpringBootCurso.repositories;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Categoria;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProdutoRepositories  extends JpaRepository<Produto, Integer>{

    @Query("Select ")
    Page<Produto> search(String nome, List<Categoria> categorias, Pageable pageRequest);
}
