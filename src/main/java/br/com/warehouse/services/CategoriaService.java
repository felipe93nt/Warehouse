package br.com.warehouse.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.warehouse.exceptions.ResourceNotFoundException;
import br.com.warehouse.models.Categoria;
import br.com.warehouse.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	private final CategoriaRepository categoriaRepository;

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
	
	@Transactional
	public Categoria update(Integer id,String name) {
		Categoria categoria = findById(id);
		categoria.setName(name);
		return categoria;
	}

	public void delete(Integer id) {
			this.exists(id);
			categoriaRepository.deleteById(id);
	}
	
	private boolean exists(Integer id) {
		if(categoriaRepository.existsById(id)) {
			return true;
		}
		throw new ResourceNotFoundException("Category not found for id: " + id);
	}
}
