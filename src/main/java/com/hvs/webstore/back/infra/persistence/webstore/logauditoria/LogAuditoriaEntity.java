package com.hvs.webstore.back.infra.persistence.webstore.logauditoria;

import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoria;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.preco.PrecoEntity;
import com.hvs.webstore.back.infra.persistence.webstore.usuario.UsuarioEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "log_auditoria")
public class LogAuditoriaEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "valor_antigo_id")
    private PrecoEntity valorAntigo;

    @ManyToOne
    @JoinColumn(name = "valor_novo_id")
    private PrecoEntity valorNovo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
    private String entidade;
    private Long entidadeId;
    private String acaoDesc;
    private Instant dataCriacao;

    public LogAuditoriaEntity() {

    }

    public LogAuditoriaEntity(final Long id,
                              final String uuid,
                              final String statusDesc,
                              final String entidade,
                              final Long entidadeId,
                              final String acaoDesc,
                              final PrecoEntity valorAntigo,
                              final PrecoEntity valorNovo,
                              final UsuarioEntity usuario,
                              final Instant dataCriacao) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.entidade = entidade;
        this.entidadeId = entidadeId;
        this.acaoDesc = acaoDesc;
        this.valorAntigo = valorAntigo;
        this.valorNovo = valorNovo;
        this.usuario = usuario;
        this.dataCriacao = dataCriacao;
    }

    public static LogAuditoriaEntity from(LogAuditoria aLogAuditoria) {

        return new LogAuditoriaEntity(
                aLogAuditoria.getId().getValue() < 0 ? null : aLogAuditoria.getId().getValue(),
                aLogAuditoria.getUuid().getValue(),
                aLogAuditoria.getStatusCode().getDesc(),
                aLogAuditoria.getEntidadeNome(),
                aLogAuditoria.getEntidadeId(),
                aLogAuditoria.getAcao().getDesc(),
                aLogAuditoria.getValorAntigo() != null ? PrecoEntity.from(aLogAuditoria.getValorAntigo().getId() != null ? aLogAuditoria.getValorAntigo().getId().getValue() : null) : null,
                aLogAuditoria.getValorNovo() != null ? PrecoEntity.from(aLogAuditoria.getValorNovo().getId() != null ? aLogAuditoria.getValorNovo().getId().getValue() : null) : null,
                aLogAuditoria.getUsuario() != null ? UsuarioEntity.from(aLogAuditoria.getUsuario().getId().getValue()) : null,
                aLogAuditoria.getDataCriacao()
        );
    }

    public static LogAuditoriaEntity from(final Long aLogAuditoriaId) {

        final var logAuditoria = new LogAuditoriaEntity();
        logAuditoria.setId(aLogAuditoriaId);

        return logAuditoria;
    }

    public LogAuditoria toDomain() {

        return LogAuditoria.from(
                getId(),
                uuid,
                statusDesc,
                entidade,
                entidadeId,
                acaoDesc,
                valorAntigo != null ? valorAntigo.toDomainChildren(): null,
                valorNovo != null ? valorNovo.toDomainChildren() : null,
                usuario != null ? usuario.toDomainChildren() : null,
                dataCriacao
        );
    }

    public LogAuditoria toDomainChildren() {

        return LogAuditoria.from(
                getId(),
                uuid,
                statusDesc,
                entidade,
                entidadeId,
                acaoDesc,
                valorAntigo != null ? valorAntigo.toDomainSimple() : null,
                valorNovo != null ? valorNovo.toDomainSimple() : null,
                usuario != null ? usuario.toDomainSimple() : null,
                dataCriacao
        );
    }

    public LogAuditoria toDomainSimple() {

        return LogAuditoria.from(
                getId(),
                uuid,
                null,
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
