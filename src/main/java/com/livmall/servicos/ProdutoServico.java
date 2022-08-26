package com.livmall.servicos;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.livmall.modelos.Produto;
import com.livmall.repositorios.ProdutoRepositorio;

@Service
public class ProdutoServico {

    final ProdutoRepositorio ProdutoRepositorio;

    public ProdutoServico(ProdutoRepositorio ProdutoRepositorio) {
        this.ProdutoRepositorio = ProdutoRepositorio;
    }

    @Transactional
    public Object salvar(Produto modelo) {
        return ProdutoRepositorio.save(modelo);
    }

    public Object encontreTudo(Pageable pageable) {
        return ProdutoRepositorio.findAll(pageable);
    }

    public Optional<Produto> encontrePeloId(UUID id) {
        return ProdutoRepositorio.findById(id);
    }

    @Transactional
    public void deletar(Produto Produto) {
        ProdutoRepositorio.delete(Produto);
    }



    
}