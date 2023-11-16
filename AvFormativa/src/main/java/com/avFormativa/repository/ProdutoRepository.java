package com.avFormativa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avFormativa.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
