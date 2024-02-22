package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LivroDTO;
import com.example.demo.entities.Livro;
import com.example.demo.repositories.LivroRepository;

@Service
public class LivroService{
	private final LivroRepository livrorepository;

	
public LivroService(LivroRepository livroRepository){
	this.livrorepository = livroRepository;

}

public List<Livro> buscaTodos(){
	return livrorepository.findAll();
}

public Livro buscaPorId(Long id) {
	return livrorepository.findById(id).orElse(null);
}

public LivroDTO salvar(Livro livro) {
	Livro salvarLivro = livrorepository.save(livro);
	return new LivroDTO(salvarLivro.getId(),salvarLivro.getTitulo(),salvarLivro.getAutor());
			
}

public LivroDTO atualizar(Long id, Livro livro) {
	Livro exiteLivro = livrorepository.findById(id)
			.orElseThrow(() -> new RuntimeException("livro" + "não encontrado"));

	exiteLivro.setTitulo(livro.getTitulo());
	exiteLivro.setAutor(livro.getAutor());
	Livro updateLivro = livrorepository.save(exiteLivro);
	return new LivroDTO(updateLivro.getId(), updateLivro.getTitulo(),updateLivro.getAutor());
			
}
public boolean deletar (Long id) {
	Optional<Livro> exiteLivro = livrorepository.findById(id);
	if (exiteLivro.isPresent()) {
		livrorepository.deleteById(id);
		return true;
		
	}else {
		return false;
	}
}




}