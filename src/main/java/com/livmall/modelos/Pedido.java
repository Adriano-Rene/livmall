package com.livmall.modelos;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.livmall.modelos.enums.StatusPedido;


// se tiver duvida olha o enum que foi alterado o get e o set... 

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable{
    private static final long serialVersionUID = 1L;


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private UUID id;
    
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
private Instant momento;

private Integer statusPedido;



public UUID getId() {
    return id;
}

public void setId(UUID id) {
    this.id = id;
}

public Instant getMomento() {
    return momento;
}

public void setMomento(Instant momento) {
    this.momento = momento;
}

public StatusPedido getStatusPedido() {
    return StatusPedido.valorDe(statusPedido);
}

public void setStatusPedido(StatusPedido statusPedido) {
    if(statusPedido != null){
        this.statusPedido = statusPedido.getCodigo();
    }
    
}

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
}

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Pedido other = (Pedido) obj;
    if (id == null) {
        if (other.id != null)
            return false;
    } else if (!id.equals(other.id))
        return false;
    return true;
}


}