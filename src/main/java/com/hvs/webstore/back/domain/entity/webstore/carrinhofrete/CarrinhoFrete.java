package com.hvs.webstore.back.domain.entity.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.carrinho.Carrinho;
import com.hvs.webstore.back.domain.entity.webstore.frete.Frete;
import com.hvs.webstore.back.domain.entity.webstore.transportadora.Transportadora;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.util.Objects;

public class CarrinhoFrete extends Entity<CarrinhoFreteId> {

    private final CarrinhoFreteUuid uuid;
    private final CarrinhoFreteStatus statusCode;
    private final Carrinho carrinho;
    private final Frete frete;
    private final Long valor;
    private final Integer prazo;
    private final Transportadora transportadora;

    private CarrinhoFrete(final CarrinhoFreteId id,
                          final CarrinhoFreteUuid uuid,
                          final CarrinhoFreteStatus statusCode,
                          final Carrinho carrinho,
                          final Frete frete,
                          final Long valor,
                          final Integer prazo,
                          final Transportadora transportadora) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.carrinho = carrinho;
        this.frete = frete;
        this.valor = valor;
        this.prazo = prazo;
        this.transportadora = transportadora;
    }

    public static CarrinhoFrete create(final Long aCarrinhoId,
                                        final Long aFreteId,
                                        final Long aValor,
                                        final Integer aPrazo,
                                        final Long aTransportadoraId) {

        return new CarrinhoFrete(
                CarrinhoFreteId.from(-1L),
                CarrinhoFreteUuid.unique(),
                CarrinhoFreteStatus.ACTIVE,
                aCarrinhoId != null ? Carrinho.from(aCarrinhoId) : null,
                aFreteId != null ? Frete.from(aFreteId) : null,
                aValor,
                aPrazo,
                aTransportadoraId != null ? Transportadora.from(aTransportadoraId) : null);
    }

    public static CarrinhoFrete update(final Long aId,
                                        final String aUuid,
                                        final String aStatusCode,
                                        final Long aCarrinhoId,
                                        final Long aFreteId,
                                        final Long aValor,
                                        final Integer aPrazo,
                                        final Long aTransportadoraId) {

        return new CarrinhoFrete(
                aId != null ? CarrinhoFreteId.from(aId) : null,
                aUuid != null ? CarrinhoFreteUuid.from(aUuid) : null,
                aStatusCode != null ? CarrinhoFreteStatus.findByCode(aStatusCode) : null,
                aCarrinhoId != null ? Carrinho.from(aCarrinhoId) : null,
                aFreteId != null ? Frete.from(aFreteId) : null,
                aValor,
                aPrazo,
                aTransportadoraId != null ? Transportadora.from(aTransportadoraId) : null);
    }

    public static CarrinhoFrete patch(final String aStatusCode,
                                       final Long aCarrinhoId,
                                       final Long aFreteId,
                                       final Long aValor,
                                       final Integer aPrazo,
                                       final Long aTransportadoraId,
                                       final CarrinhoFrete aExisting) {

        return new CarrinhoFrete(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? CarrinhoFreteStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aCarrinhoId != null ? Carrinho.from(aCarrinhoId) : aExisting.getCarrinho(),
                aFreteId != null ? Frete.from(aFreteId) : aExisting.getFrete(),
                aValor != null ? aValor : aExisting.getValor(),
                aPrazo != null ? aPrazo : aExisting.getPrazo(),
                aTransportadoraId != null ? Transportadora.from(aTransportadoraId) : aExisting.getTransportadora());
    }

    public static CarrinhoFrete from(final Long aId,
                                      final String aUuid,
                                      final String aStatusDesc,
                                      final Carrinho aCarrinho,
                                      final Frete aFrete,
                                      final Long aValor,
                                      final Integer aPrazo,
                                      final Transportadora aTransportadora) {

        return new CarrinhoFrete(
                aId != null ? CarrinhoFreteId.from(aId) : null,
                aUuid != null ? CarrinhoFreteUuid.from(aUuid) : null,
                aStatusDesc != null ? CarrinhoFreteStatus.findByDesc(aStatusDesc) : null,
                aCarrinho,
                aFrete,
                aValor,
                aPrazo,
                aTransportadora);
    }

    public static CarrinhoFrete from(final Long aId) {

        return new CarrinhoFrete(
                aId != null ? CarrinhoFreteId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static CarrinhoFrete from(final String aUuid) {

        return new CarrinhoFrete(
                null,
                aUuid != null ? CarrinhoFreteUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new CarrinhoFreteValidator(aHandler, this).validate();
    }

    public CarrinhoFreteUuid getUuid() {
        return uuid;
    }
    public CarrinhoFreteStatus getStatusCode() {
        return statusCode;
    }
    public Carrinho getCarrinho() {
        return carrinho;
    }
    public Frete getFrete() {
        return frete;
    }
    public Long getValor() {
        return valor;
    }
    public Integer getPrazo() {
        return prazo;
    }
    public Transportadora getTransportadora() {
        return transportadora;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        CarrinhoFrete that = (CarrinhoFrete) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(carrinho, that.carrinho) &&
                Objects.equals(frete, that.frete) &&
                Objects.equals(valor, that.valor) &&
                Objects.equals(prazo, that.prazo) &&
                Objects.equals(transportadora, that.transportadora);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                carrinho,
                frete,
                valor,
                prazo,
                transportadora);
    }
}
