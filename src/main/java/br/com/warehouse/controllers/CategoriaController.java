package br.com.warehouse.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.warehouse.exceptions.ResourceNotFoundException;
import br.com.warehouse.models.Categoria;
import br.com.warehouse.services.CategoriaService;

@RestController
@RequestMapping(path = "categories")
public class CategoriaController {
	
	private CategoriaService categoriaService;
	
	@Autowired
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Categoria>> getCategorias() {
		Iterable<Categoria> cat = categoriaService.all();
		return ResponseEntity.status(HttpStatus.OK.value()).body(cat);
	}
	
	@GetMapping(value="{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> getCategoriasById(@PathVariable Integer id) {
		Categoria cat = categoriaService.findById(id)
				.orElseThrow(() -> 
				new ResourceNotFoundException("Category not found for id: "+id));
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(cat);
	}

	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> saveCategorias(@Valid @RequestBody Categoria categoria) {
		Categoria cat = categoriaService.save(categoria);
		return ResponseEntity.status(201).body(cat);
	}
}
