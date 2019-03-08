package br.com.gsw.springBootCurso.SpringBootCurso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.Cliente;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepositories extends JpaRepository<Cliente, Integer>{

    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
