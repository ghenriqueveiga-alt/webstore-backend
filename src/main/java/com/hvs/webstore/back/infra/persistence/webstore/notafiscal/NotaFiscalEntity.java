package com.hvs.webstore.back.infra.persistence.webstore.notafiscal;

import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscal;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.pedido.PedidoEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscalEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;
    private String chaveAcesso;
    private Long numero;
    private Long serie;
    private String tipoAmbienteDesc;
    private String xml;
    private String danfeUrl;
    private LocalDate dataEmissao;

    public NotaFiscalEntity() {

    }

    public NotaFiscalEntity(final Long id,
                            final String uuid,
                            final String statusDesc,
                            final PedidoEntity pedido,
                            final String chaveAcesso,
                            final Long numero,
                            final Long serie,
                            final String tipoAmbienteDesc,
                            final String xml,
                            final String danfeUrl,
                            final LocalDate dataEmissao) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.pedido = pedido;
        this.chaveAcesso = chaveAcesso;
        this.numero = numero;
        this.serie = serie;
        this.tipoAmbienteDesc = tipoAmbienteDesc;
        this.xml = xml;
        this.danfeUrl = danfeUrl;
        this.dataEmissao = dataEmissao;
    }

    public static NotaFiscalEntity from(NotaFiscal aNotaFiscal) {

        return new NotaFiscalEntity(
                aNotaFiscal.getId().getValue() < 0 ? null : aNotaFiscal.getId().getValue(),
                aNotaFiscal.getUuid().getValue(),
                aNotaFiscal.getStatusCode().getDesc(),
                aNotaFiscal.getPedido() != null ? PedidoEntity.from(aNotaFiscal.getPedido()) : null,
                aNotaFiscal.getChaveAcesso(),
                aNotaFiscal.getNumero(),
                aNotaFiscal.getSerie(),
                aNotaFiscal.getTipoAmbiente().getDesc(),
                aNotaFiscal.getXml(),
                aNotaFiscal.getDanfeUrl(),
                aNotaFiscal.getDataEmissao()
        );
    }

    public static NotaFiscalEntity from(final Long aNotaFiscalId) {

        final var notaFiscal = new NotaFiscalEntity();
        notaFiscal.setId(aNotaFiscalId);

        return notaFiscal;
    }

    public NotaFiscal toDomain() {

        return NotaFiscal.from(
                getId(),
                uuid,
                statusDesc,
                pedido != null ? pedido.toDomainChildren() : null,
                chaveAcesso,
                numero,
                serie,
                tipoAmbienteDesc,
                xml,
                danfeUrl,
                dataEmissao
        );
    }

    public NotaFiscal toDomainChildren() {

        return NotaFiscal.from(
                getId(),
                uuid,
                statusDesc,
                pedido != null ? pedido.toDomainSimple() : null,
                chaveAcesso,
                numero,
                serie,
                tipoAmbienteDesc,
                xml,
                danfeUrl,
                dataEmissao
        );
    }

    public NotaFiscal toDomainSimple() {

        return NotaFiscal.from(
                getId(),
                uuid,
                null,
                null,
                chaveAcesso,
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
