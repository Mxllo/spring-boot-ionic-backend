package com.mxllo.springionic.model;

import com.mxllo.springionic.model.enums.TipoCliente;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cliente  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;
    @OneToMany(mappedBy="cliente")
    private List<Endereco> enderecos = new ArrayList<>();
    @ElementCollection @CollectionTable(name = "telefone")
    private Set<String> telefones = new HashSet<>(); //conjunto de strings

    public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo.getCodigo();
    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo.getCodigo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
