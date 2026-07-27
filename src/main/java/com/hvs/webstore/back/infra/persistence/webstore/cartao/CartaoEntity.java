package com.hvs.webstore.back.infra.persistence.webstore.cartao;

import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.usuario.UsuarioEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "cartao")
public class CartaoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nomeTitular;
    private String numero;
    private String bandeira;
    private String tipo;
    private Integer mesVencimento;
    private Integer anoVencimento;
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public CartaoEntity() {

    }

    public CartaoEntity(final Long id,
                        final String uuid,
                        final String statusDesc,
                        final String nomeTitular,
                        final String numero,
                        final String bandeira,
                        final String tipo,
                        final Integer mesVencimento,
                        final Integer anoVencimento,
                        final String cvv,
                        final UsuarioEntity usuario) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nomeTitular = nomeTitular;
        this.numero = numero;
        this.bandeira = bandeira;
        this.tipo = tipo;
        this.mesVencimento = mesVencimento;
        this.anoVencimento = anoVencimento;
        this.cvv = cvv;
        this.usuario = usuario;
    }

    public static CartaoEntity from(final Cartao aCartao) {

        return new CartaoEntity(
                aCartao.getId().getValue() < 0 ? null : aCartao.getId().getValue(),
                aCartao.getUuid().getValue(),
                aCartao.getStatusCode().getDesc(),
                aCartao.getNomeTitular(),
                aCartao.getNumero(),
                aCartao.getBandeira(),
                aCartao.getTipo(),
                aCartao.getMesVencimento(),
                aCartao.getAnoVencimento(),
                aCartao.getCvv(),
                aCartao.getUsuario() != null ? UsuarioEntity.from(aCartao.getUsuario()) : null
        );
    }

    public static CartaoEntity from(final Long aCartaoID) {

        final var cartao = new CartaoEntity();
        cartao.setId(aCartaoID);

        return cartao;
    }

    public Cartao toDomain() {

        return Cartao.from(
                getId(),
                uuid,
                statusDesc,
                nomeTitular,
                numero,
                bandeira,
                tipo,
                mesVencimento,
                anoVencimento,
                cvv,
                usuario != null ? usuario.toDomainChildren() : null
        );
    }

    public Cartao toDomainChildren() {

        return Cartao.from(
                getId(),
                uuid,
                statusDesc,
                nomeTitular,
                numero,
                bandeira,
                tipo,
                mesVencimento,
                anoVencimento,
                cvv,
                usuario != null ? usuario.toDomainSimple() : null
        );
    }

    public Cartao toDomainSimple() {

        return Cartao.from(
                getId(),
                uuid,
                null,
                nomeTitular,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
