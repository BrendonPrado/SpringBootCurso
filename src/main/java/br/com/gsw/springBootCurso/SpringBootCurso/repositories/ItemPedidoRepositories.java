package br.com.gsw.springBootCurso.SpringBootCurso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gsw.springBootCurso.SpringBootCurso.domain.ItemPedido;
	
@Repository
public interface ItemPedidoRepositories extends JpaRepository<ItemPedido, Integer>{

}

