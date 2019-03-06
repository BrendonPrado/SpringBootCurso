package br.com.gsw.springBootCurso.SpringBootCurso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Estado;
import br.com.gsw.springBootCurso.SpringBootCurso.domain.Produto;

@Repository
public interface EstadoRepositories  extends JpaRepository<Estado, Integer>{

}
