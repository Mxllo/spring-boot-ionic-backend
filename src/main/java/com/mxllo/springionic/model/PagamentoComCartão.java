package com.mxllo.springionic.model;
import com.mxllo.springionic.model.enums.EstadoPagamento;
import lombok.*;

import javax.persistence.Entity;

@NoArgsConstructor

@Getter
@Setter
@Entity
public class PagamentoComCartão extends Pagamento{
    private static final long serialVersionUID = 1L;

    private Integer numeroDeParcelas;

    public PagamentoComCartão(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
