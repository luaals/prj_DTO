package com.examploDTO.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examploDTO.controller.LivroRepository;
import com.examploDTO.dto.LivroDTO;
import com.examploDTO.entities.Livro;


@Service
public class LivroService {
		
	private final LivroRepository livroRepository;

	@Autowired
	public LivroService (LivroRepository livroRepository) {
			this.livroRepository= livroRepository;
	}
	//Método modificado para utilizar o DTO public LivroDTO salvar (Livro livro) (
	public LivroDTO salvar(Livro livro) {
		Livro salvarLivro = livroRepository.save(livro);
		return new LivroDTO (salvarLivro.getId(), salvarLivro.getTitulo(), salvarLivro.getAutor());
	}
		
	//Método modificado para utilizar ODTO
	public LivroDTO atualizar (Long id, Livro livro) {
		Livro existeLivro= livroRepository.findById(id).orElseThrow(()-> new RuntimeException("Livro não encontrado"));
		existeLivro.setTitulo (livro.getTitulo());
		existeLivro.setAutor (livro.getAutor());
		Livro updatedLivro = livroRepository.save(existeLivro);
		return new LivroDTO (updatedLivro.getId(), updatedLivro.getTitulo(), updatedLivro.getAutor());
	}
	
	public boolean deletar (Long id) {
		Optional <Livro> existeCliente = livroRepository.findById(id);
		if (existeCliente.isPresent()) {
			livroRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public List<Livro> buscarTodos () {
		return livroRepository.findAll();
	}
	
	public Livro buscarPorld (Long id) {
		Optional <Livro> Livro = livroRepository.findById(id);
		return Livro.orElse(null);
	}
	
}