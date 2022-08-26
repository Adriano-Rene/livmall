package com.livmall.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livmall.modelos.Produto;

public interface ProdutoRepositorio  extends JpaRepository<Produto, UUID>{

    
    
}
