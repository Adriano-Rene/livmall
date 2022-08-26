package com.livmall.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livmall.modelos.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, UUID>{

    
    
}
