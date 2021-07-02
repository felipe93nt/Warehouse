package br.com.warehouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.warehouse.exceptions.ResourceNotFoundException;
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
	
	public Categoria findById(Integer id) {
		return categoriaRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Category not found for id: " + id));
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		if(categoriaRepository.existsById(categoria.getId())) {
			return categoriaRepository.save(categoria);
		}
		throw new ResourceNotFoundException("Category not found for id: "
				+ categoria.getId());
	}
}
