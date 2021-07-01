package br.com.warehouse.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.warehouse.models.Categoria;
import br.com.warehouse.services.CategoriaService;

@RestController
@RequestMapping(path = "categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping( method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Iterable<Categoria>> getCategorias() {
		Iterable<Categoria> c = categoriaService.all();
		return ResponseEntity.status(200)
				.body(c);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Categoria>> getCategoriasById(@PathVariable Integer id) {
		Optional<Categoria> c = categoriaService.findById(id);
		return ResponseEntity.status(200)
			.body(c);
	}

	
	@RequestMapping( method=RequestMethod.POST,
					consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> saveCategorias(@Valid @RequestBody Categoria categoria) {
		Categoria c = categoriaService.save(categoria);
		return ResponseEntity.status(201)
				.body(c);
	}
}
