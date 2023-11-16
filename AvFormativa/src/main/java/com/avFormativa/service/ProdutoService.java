package com.avFormativa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avFormativa.entities.Produto;
import com.avFormativa.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoR;

    @Autowired
    public ProdutoService(ProdutoRepository produtoR) {
        this.produtoR = produtoR;
    }

    public List<Produto> buscaTodosProduto(){
        return produtoR.findAll();
    }

    public Produto buscaProdutoId(Long id) {
        Optional <Produto> Produto = produtoR.findById(id);
        return Produto.orElse(null);
    }

    public Produto salvaProduto (Produto produto) {
        return produtoR.save(produto);
    }

    public Produto alterarProduto(Long id, Produto alterarProduto) {
        Optional <Produto> existeProduto = produtoR.findById(id);
        if (existeProduto.isPresent()) {
            alterarProduto.setId(id);
            return produtoR.save(alterarProduto);
        }
        return null;
    }

        public boolean apagarProduto (Long id) {
            Optional <Produto> existeProduto = produtoR.findById(id);
            if (existeProduto.isPresent()) {
            	produtoR.deleteById(id);
                return true;
            }
            return false;
        }

    }