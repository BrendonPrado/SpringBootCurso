package br.com.gsw.springBootCurso.SpringBootCurso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cidade;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Produto;

@Repository
public interface CidadeRepositories  extends JpaRepository<Cidade, Integer>{

}
