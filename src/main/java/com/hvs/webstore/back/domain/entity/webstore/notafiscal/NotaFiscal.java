package com.hvs.webstore.back.domain.entity.webstore.notafiscal;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.pedido.Pedido;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.LocalDate;
import java.util.Objects;

public class NotaFiscal extends Entity<NotaFiscalId> {

    private final NotaFiscalUuid uuid;
    private final NotaFiscalStatus statusCode;
    private final Pedido pedido;
    private final String chaveAcesso;
    private final Long numero;
    private final Long serie;
    private final TipoAmbiente tipoAmbiente;
    private final String xml;
    private final String danfeUrl;
    private final LocalDate dataEmissao;

    private NotaFiscal(final NotaFiscalId id,
                       final NotaFiscalUuid uuid,
                       final NotaFiscalStatus statusCode,
                       final Pedido pedido,
                       final String chaveAcesso,
                       final Long numero,
                       final Long serie,
                       final TipoAmbiente tipoAmbiente,
                       final String xml,
                       final String danfeUrl,
                       final LocalDate dataEmissao) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.pedido = pedido;
        this.chaveAcesso = chaveAcesso;
        this.numero = numero;
        this.serie = serie;
        this.tipoAmbiente = tipoAmbiente;
        this.xml = xml;
        this.danfeUrl = danfeUrl;
        this.dataEmissao = dataEmissao;
    }

    public static NotaFiscal create(final Long aPedidoId,
                                    final String aChaveAcesso,
                                    final Long aNumero,
                                    final Long aSerie,
                                    final String aTipoAmbienteDesc,
                                    final String aXml,
                                    final String aDanfeUrl) {

        return new NotaFiscal(
                NotaFiscalId.from(-1L),
                NotaFiscalUuid.unique(),
                NotaFiscalStatus.ACTIVE,
                aPedidoId != null ? Pedido.from(aPedidoId) : null,
                aChaveAcesso,
                aNumero,
                aSerie,
                aTipoAmbienteDesc != null ? TipoAmbiente.findByDesc(aTipoAmbienteDesc) : null,
                aXml,
                aDanfeUrl,
                null);
    }

    public static NotaFiscal update(final Long aId,
                                    final String aUuid,
                                    final String aStatusCode,
                                    final Long aPedidoId,
                                    final String aChaveAcesso,
                                    final Long aNumero,
                                    final Long aSerie,
                                    final String aTipoAmbienteCode,
                                    final String aXml,
                                    final String aDanfeUrl,
                                    final LocalDate aDataEmissao) {

        return new NotaFiscal(
                aId != null ? NotaFiscalId.from(aId) : null,
                aUuid != null ? NotaFiscalUuid.from(aUuid) : null,
                aStatusCode != null ? NotaFiscalStatus.findByCode(aStatusCode) : null,
                aPedidoId != null ? Pedido.from(aPedidoId) : null,
                aChaveAcesso,
                aNumero,
                aSerie,
                aTipoAmbienteCode != null ? TipoAmbiente.findByCode(aTipoAmbienteCode) : null,
                aXml,
                aDanfeUrl,
                aDataEmissao);
    }

    public static NotaFiscal patch(final String aStatusCode,
                                   final Long aPedidoId,
                                   final String aChaveAcesso,
                                   final Long aNumero,
                                   final Long aSerie,
                                   final String aTipoAmbienteCode,
                                   final String aXml,
                                   final String aDanfeUrl,
                                   final NotaFiscal aExisting) {

        return new NotaFiscal(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? NotaFiscalStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aPedidoId != null ? Pedido.from(aPedidoId) : aExisting.getPedido(),
                aChaveAcesso != null ? aChaveAcesso : aExisting.getChaveAcesso(),
                aNumero != null ? aNumero : aExisting.getNumero(),
                aSerie != null ? aSerie : aExisting.getSerie(),
                aTipoAmbienteCode != null ? TipoAmbiente.findByCode(aTipoAmbienteCode) : aExisting.getTipoAmbiente(),
                aXml != null ? aXml : aExisting.getXml(),
                aDanfeUrl != null ? aDanfeUrl : aExisting.getDanfeUrl(),
                aExisting.getDataEmissao());
    }

    public static NotaFiscal from(final Long aId,
                                  final String aUuid,
                                  final String aStatusDesc,
                                  final Pedido aPedido,
                                  final String aChaveAcesso,
                                  final Long aNumero,
                                  final Long aSerie,
                                  final String aTipoAmbienteDesc,
                                  final String aXml,
                                  final String aDanfeUrl,
                                  final LocalDate aDataEmissao) {

        return new NotaFiscal(
                aId != null ? NotaFiscalId.from(aId) : null,
                aUuid != null ? NotaFiscalUuid.from(aUuid) : null,
                aStatusDesc != null ? NotaFiscalStatus.findByDesc(aStatusDesc) : null,
                aPedido,
                aChaveAcesso,
                aNumero,
                aSerie,
                aTipoAmbienteDesc != null ? TipoAmbiente.findByDesc(aTipoAmbienteDesc) : null,
                aXml,
                aDanfeUrl,
                aDataEmissao);
    }

    public static NotaFiscal from(final Long aId) {

        return new NotaFiscal(
                aId != null ? NotaFiscalId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static NotaFiscal from(final String aUuid) {

        return new NotaFiscal(
                null,
                aUuid != null ? NotaFiscalUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new NotaFiscalValidator(aHandler, this).validate();
    }

    public NotaFiscalUuid getUuid() {
        return uuid;
    }
    public NotaFiscalStatus getStatusCode() {
        return statusCode;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public String getChaveAcesso() {
        return chaveAcesso;
    }
    public Long getNumero() {
        return numero;
    }
    public Long getSerie() {
        return serie;
    }
    public TipoAmbiente getTipoAmbiente() {
        return tipoAmbiente;
    }
    public String getXml() {
        return xml;
    }
    public String getDanfeUrl() {
        return danfeUrl;
    }
    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        NotaFiscal that = (NotaFiscal) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(pedido, that.pedido) &&
                Objects.equals(chaveAcesso, that.chaveAcesso) &&
                Objects.equals(numero, that.numero) &&
                Objects.equals(serie, that.serie) &&
                tipoAmbiente == that.tipoAmbiente &&
                Objects.equals(xml, that.xml) &&
                Objects.equals(danfeUrl, that.danfeUrl) &&
                Objects.equals(dataEmissao, that.dataEmissao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                pedido,
                chaveAcesso,
                numero,
                serie,
                tipoAmbiente,
                xml,
                danfeUrl,
                dataEmissao);
    }
}
