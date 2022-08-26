package com.livmall.controles;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.livmall.modelos.Produto;
import com.livmall.servicos.ProdutoServico;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/Produto")
public class ProdutoControle {
    

final ProdutoServico ProdutoServico;

public ProdutoControle(ProdutoServico ProdutoServico) {
    this.ProdutoServico = ProdutoServico;
}

@PostMapping
public ResponseEntity<Object> inserirProduto(@RequestBody @Valid Produto prod){

    var modelo = new Produto();
    BeanUtils.copyProperties(prod, modelo);
    return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoServico.salvar(modelo));
}

@GetMapping
public ResponseEntity<Object> pegueTodosProdutos(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) org.springframework.data.domain.Pageable pageable){
    return ResponseEntity.status(HttpStatus.OK).body(ProdutoServico.encontreTudo(pageable));
}


@GetMapping("/{id}")
public ResponseEntity<Object> pegueProdutoId(@PathVariable(name = "id") UUID id){
    Optional<Produto> modelo = ProdutoServico.encontrePeloId(id);
    if(!modelo.isPresent()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado");
    }
    return ResponseEntity.status(HttpStatus.OK).body(modelo.get());
}



@DeleteMapping("/{id}")
public ResponseEntity<Object> deleteProdutoId(@PathVariable(name = "id") UUID id){
    Optional<Produto> modelo = ProdutoServico.encontrePeloId(id);
    if(!modelo.isPresent()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado");
    }
    ProdutoServico.deletar(modelo.get());
    return ResponseEntity.status(HttpStatus.OK).body("Produto deletado");

}

@PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable(value = "id") UUID id, @RequestBody @Valid Produto Produto){
        Optional<Produto> modelOptional = ProdutoServico.encontrePeloId(id);
        if(!modelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nnao encontrado.. nao e possivel atualizar");
        }

    var modelo = new Produto();
    BeanUtils.copyProperties(Produto, modelo);
        modelo.setId(modelOptional.get().getId());

    return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoServico.salvar(modelo));
    }
}
