package com.hvs.webstore.back.infra.persistence.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFrete;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.carrinho.CarrinhoEntity;
import com.hvs.webstore.back.infra.persistence.webstore.frete.FreteEntity;
import com.hvs.webstore.back.infra.persistence.webstore.transportadora.TransportadoraEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "carrinho_frete")
public class CarrinhoFreteEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private CarrinhoEntity carrinho;

    @ManyToOne
    @JoinColumn(name = "frete_id")
    private FreteEntity frete;
    private Long valor;
    private Integer prazo;

    @ManyToOne
    @JoinColumn(name = "transportadora_id")
    private TransportadoraEntity transportadora;

    public CarrinhoFreteEntity() {

    }

    public CarrinhoFreteEntity(final Long id,
                                final String uuid,
                                final String statusDesc,
                                final CarrinhoEntity carrinho,
                                final FreteEntity frete,
                                final Long valor,
                                final Integer prazo,
                                final TransportadoraEntity transportadora) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.carrinho = carrinho;
        this.frete = frete;
        this.valor = valor;
        this.prazo = prazo;
        this.transportadora = transportadora;
    }

    public static CarrinhoFreteEntity from(final CarrinhoFrete aCarrinhoFrete) {

        return new CarrinhoFreteEntity(
                aCarrinhoFrete.getId().getValue() < 0 ? null : aCarrinhoFrete.getId().getValue(),
                aCarrinhoFrete.getUuid().getValue(),
                aCarrinhoFrete.getStatusCode().getDesc(),
                aCarrinhoFrete.getCarrinho() != null ? CarrinhoEntity.from(aCarrinhoFrete.getCarrinho().getId() != null ? aCarrinhoFrete.getCarrinho().getId().getValue() : null) : null,
                aCarrinhoFrete.getFrete() != null ? FreteEntity.from(aCarrinhoFrete.getFrete().getId() != null ? aCarrinhoFrete.getFrete().getId().getValue() : null) : null,
                aCarrinhoFrete.getValor(),
                aCarrinhoFrete.getPrazo(),
                aCarrinhoFrete.getTransportadora() != null ? TransportadoraEntity.from(aCarrinhoFrete.getTransportadora().getId() != null ? aCarrinhoFrete.getTransportadora().getId().getValue() : null) : null
        );
    }

    public static CarrinhoFreteEntity from(final Long aCarrinhoFreteId) {

        final var carrinhoFrete = new CarrinhoFreteEntity();
        carrinhoFrete.setId(aCarrinhoFreteId);

        return carrinhoFrete;
    }

    public CarrinhoFrete toDomain() {

        return CarrinhoFrete.from(
                getId(),
                uuid,
                statusDesc,
                carrinho != null ? carrinho.toDomainChildren() : null,
                frete != null ? frete.toDomainChildren() : null,
                valor,
                prazo,
                transportadora != null ? transportadora.toDomainChildren() : null
        );
    }

    public CarrinhoFrete toDomainChildren() {

        return CarrinhoFrete.from(
                getId(),
                uuid,
                statusDesc,
                carrinho != null ? carrinho.toDomainSimple() : null,
                frete != null ? frete.toDomainSimple() : null,
                valor,
                prazo,
                transportadora != null ? transportadora.toDomainSimple() : null
        );
    }

    public CarrinhoFrete toDomainSimple() {

        return CarrinhoFrete.from(
                getId(),
                uuid,
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
