package br.com.gsw.springBootCurso.SpringBootCurso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cliente;

public interface ClienteRepositories extends JpaRepository<Cliente, Integer>{

}
