package br.com.gsw.springBootCurso.SpringBootCurso.repositories;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Categoria;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ProdutoRepositories  extends JpaRepository<Produto, Integer>{

    @Transactional(readOnly = true)
    Page<Produto> findByNomeContainingAndAndCategoriasIn(@Param( "nome" ) String nome,@Param( "categorias" ) List<Categoria> categorias, Pageable pageRequest);

}
