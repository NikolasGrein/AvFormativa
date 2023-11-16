package com.avFormativa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avFormativa.entities.Produto;
import com.avFormativa.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produto", description = "API REST DE PRODUTOS")
@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	private final ProdutoService PrService;
	
	@Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.PrService = produtoService;
    }
	
	@GetMapping("/{id}")
	@Operation(summary = "Localiza produto por ID")
    public ResponseEntity<Produto> buscaProdutosControlId(@PathVariable Long id) {
		Produto produto = PrService.buscaProdutoId(id); 
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
        
        @GetMapping("/")
        @Operation(summary = "Apresenta todos os produtos")
        public ResponseEntity<List<Produto>> buscaProdutosControl() { 
            List<Produto> produto = PrService.buscaTodosProduto();
            return ResponseEntity.ok(produto);

    }
        
        @PostMapping("/")
        public ResponseEntity<Produto> salvaProdutosControl(@RequestBody Produto produto) { 
        	Produto salvaProduto = PrService.salvaProduto(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvaProduto);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Altera um produto")
        public ResponseEntity<Produto> alteraProdutosControl(@PathVariable Long id, @RequestBody Produto produto) { 
        	Produto alteraProduto = PrService.alterarProduto(id, produto);
            if (alteraProduto != null) {
                return ResponseEntity.ok(produto);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Exclui um produto")
        public ResponseEntity<String> apagaProdutosControl(@PathVariable Long id) { 
            boolean apagar = PrService.apagarProduto(id);
            if (apagar) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
}