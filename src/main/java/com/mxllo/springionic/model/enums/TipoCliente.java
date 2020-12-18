package com.mxllo.springionic.model.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum TipoCliente {
    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int codigo;
    private String descricao;

    public static TipoCliente toEnum(Integer codigo) {
        if(codigo == null)
            return null;

        for (TipoCliente cliente : TipoCliente.values()) {
            if (cliente.equals(cliente.getCodigo()))
                return cliente;
        }
        throw new IllegalStateException("Id inválido: " + codigo);
    }

}
