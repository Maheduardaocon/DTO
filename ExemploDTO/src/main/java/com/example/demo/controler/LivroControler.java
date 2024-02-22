package com.example.demo.controler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LivroDTO;
import com.example.demo.entities.Livro;
import com.example.demo.service.LivroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Livro")
public class LivroControler {
	
	private final LivroService livroService;
	
	public LivroControler (LivroService livroService) {
	this.livroService = livroService;
	}
	
	public ResponseEntity<Livro> buscarLivroId(@PathVariable Long id) {
	Livro livro = livroService.buscaPorId(id);
	if (livro != null) {
	return ResponseEntity.ok(livro);
	} else {
	return ResponseEntity.notFound().build();
	}
	}
	
	public ResponseEntity<List<Livro>> buscaTodosLivros () {
	List<Livro> livro = livroService.buscaTodos();
	return ResponseEntity.ok (livro);
}
	
	
	@PostMapping
	public ResponseEntity<LivroDTO> criar (@RequestBody @Valid Livro livro) {
	LivroDTO salvarLivro = livroService.salvar (livro);
	return ResponseEntity.status(HttpStatus.CREATED). body (salvarLivro);
	}
	


	
}
	
	
	
