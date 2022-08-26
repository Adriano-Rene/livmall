package com.livmall.servicos;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.livmall.modelos.Categoria;
import com.livmall.repositorios.CategoriaRepositorio;

@Service
public class CategoriaServico {

    final CategoriaRepositorio CategoriaRepositorio;

    public CategoriaServico(CategoriaRepositorio CategoriaRepositorio) {
        this.CategoriaRepositorio = CategoriaRepositorio;
    }

    @Transactional
    public Object salvar(Categoria modelo) {
        return CategoriaRepositorio.save(modelo);
    }

    public Object encontreTudo(Pageable pageable) {
        return CategoriaRepositorio.findAll(pageable);
    }

    public Optional<Categoria> encontrePeloId(UUID id) {
        return CategoriaRepositorio.findById(id);
    }

    @Transactional
    public void deletar(Categoria Categoria) {
        CategoriaRepositorio.delete(Categoria);
    }



    
}