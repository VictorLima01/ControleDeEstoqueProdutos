package com.backend.produtos.produtosestoque.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.produtos.produtosestoque.model.Pessoa;
import com.backend.produtos.produtosestoque.repository.PessoaRepository;

@RestController
@RequestMapping("/api")
public class PessoaController {
	
	@Autowired
    private PessoaRepository pessoaRepository;
	
	@GetMapping("/pessoas")
    public List<Pessoa> list() {
        return pessoaRepository.findAll();
    }
	
	@PostMapping("/pessoas")
	   public ResponseEntity createNote(@Valid @RequestBody Pessoa pessoa) {
		   List<Pessoa> pessoas = pessoaRepository.findAll();
		   if(pessoas.size() == 0) {
			   pessoaRepository.save(pessoa);
		   }else {
			   for (Pessoa pessoaType : pessoas) {
				    if(pessoaType.getEmail().equals(pessoa.getEmail())) {
				    	System.out.println("Email duplicado, digite um email válido! ");
				    	return new ResponseEntity<>(pessoa, HttpStatus.BAD_REQUEST);
				    }else {
				    	pessoaRepository.save(pessoa);
				    }
				}
		   }
		   return new ResponseEntity<>(pessoa, HttpStatus.OK);
	   }
}
