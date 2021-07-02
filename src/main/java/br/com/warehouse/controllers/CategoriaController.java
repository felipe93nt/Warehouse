package br.com.warehouse.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		Categoria cat = categoriaService.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK.value()).body(cat);
	}

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveCategorias(@Valid @RequestBody Categoria categoria) {
		Categoria cat = categoriaService.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(cat.getId()).toUri();
		
		return ResponseEntity.status(HttpStatus.CREATED.value()).location(uri).build();
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> alterCategorias(@Valid @RequestBody Categoria categoria) {
		Categoria cat = categoriaService.update(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cat.getId()).toUri();
		
		return ResponseEntity.status(HttpStatus.CREATED.value()).location(uri).build();
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<?> deleteCategorias(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT.value()).build();
	}
}
