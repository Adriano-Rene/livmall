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

import com.livmall.modelos.Categoria;
import com.livmall.servicos.CategoriaServico;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/Categoria")
public class CategoriaControle {
    

final CategoriaServico CategoriaServico;

public CategoriaControle(CategoriaServico CategoriaServico) {
    this.CategoriaServico = CategoriaServico;
}

@PostMapping
public ResponseEntity<Object> inserirCategoria(@RequestBody @Valid Categoria prod){

    var modelo = new Categoria();
    BeanUtils.copyProperties(prod, modelo);
    return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaServico.salvar(modelo));
}

@GetMapping
public ResponseEntity<Object> pegueTodosCategorias(@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) org.springframework.data.domain.Pageable pageable){
    return ResponseEntity.status(HttpStatus.OK).body(CategoriaServico.encontreTudo(pageable));
}


@GetMapping("/{id}")
public ResponseEntity<Object> pegueCategoriaId(@PathVariable(name = "id") UUID id){
    Optional<Categoria> modelo = CategoriaServico.encontrePeloId(id);
    if(!modelo.isPresent()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria nao encontrado");
    }
    return ResponseEntity.status(HttpStatus.OK).body(modelo.get());
}



@DeleteMapping("/{id}")
public ResponseEntity<Object> deleteCategoriaId(@PathVariable(name = "id") UUID id){
    Optional<Categoria> modelo = CategoriaServico.encontrePeloId(id);
    if(!modelo.isPresent()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria nao encontrado");
    }
    CategoriaServico.deletar(modelo.get());
    return ResponseEntity.status(HttpStatus.OK).body("Categoria deletado");

}

@PutMapping("/{id}")
    public ResponseEntity<Object> atualizarCategoria(@PathVariable(value = "id") UUID id, @RequestBody @Valid Categoria Categoria){
        Optional<Categoria> modelOptional = CategoriaServico.encontrePeloId(id);
        if(!modelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria nnao encontrado.. nao e possivel atualizar");
        }

    var modelo = new Categoria();
    BeanUtils.copyProperties(Categoria, modelo);
        modelo.setId(modelOptional.get().getId());

    return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaServico.salvar(modelo));
    }
}
