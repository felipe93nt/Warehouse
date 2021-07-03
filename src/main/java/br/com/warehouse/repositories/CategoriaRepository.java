package br.com.warehouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.warehouse.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
