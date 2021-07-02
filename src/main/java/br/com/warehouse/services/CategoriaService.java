package br.com.warehouse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.warehouse.models.Categoria;
import br.com.warehouse.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	@Autowired
	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}
	
	public Iterable<Categoria> all() {
		return categoriaRepository.findAll();
		
	}
	
	public Optional<Categoria> findById(Integer id) {
		return categoriaRepository.findById(id);
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
}
