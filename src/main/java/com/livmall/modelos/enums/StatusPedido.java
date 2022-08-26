package com.livmall.modelos.enums;

public enum StatusPedido {
    
    ESPERANDO_PAGAMENTO(1),
    PAGO(2),
    ENVIADO(3),
    ENTREGUE(4),
    CANCELADO(5);

    private int codigo;

    private StatusPedido(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo(){
        return codigo;   
    }


    public static StatusPedido valorDe(int cod){
        for(StatusPedido valor : StatusPedido.values()){
            if(valor.getCodigo() == cod){
                return valor;
            }
        }

        throw new IllegalArgumentException("Status do pedido invalido");
    }

}
