package br.com.gsw.springBootCurso.SpringBootCurso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Endereco;

public interface EnderecoRepositories extends JpaRepository<Endereco, Integer> {
	
}
