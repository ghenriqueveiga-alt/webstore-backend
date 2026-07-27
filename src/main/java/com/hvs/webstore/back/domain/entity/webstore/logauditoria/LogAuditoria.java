package com.hvs.webstore.back.domain.entity.webstore.logauditoria;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.preco.Preco;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.validation.ValidationHandler;
import java.time.Instant;
import java.util.Objects;

public class LogAuditoria extends Entity<LogAuditoriaId> {

    private final LogAuditoriaUuid uuid;
    private final LogAuditoriaStatus statusCode;
    private final String entidadeNome;
    private final Long entidadeId;
    private final AcaoLog acao;
    private final Preco valorAntigo;
    private final Preco valorNovo;
    private final Usuario usuario;
    private final Instant dataCriacao;

    private LogAuditoria(final LogAuditoriaId id,
                         final LogAuditoriaUuid uuid,
                         final LogAuditoriaStatus statusCode,
                         final String entidadeNome,
                         final Long entidadeId,
                         final AcaoLog acao,
                         final Preco valorAntigo,
                         final Preco valorNovo,
                         final Usuario usuario,
                         final Instant dataCriacao) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.entidadeNome = entidadeNome;
        this.entidadeId = entidadeId;
        this.acao = acao;
        this.valorAntigo = valorAntigo;
        this.valorNovo = valorNovo;
        this.usuario = usuario;
        this.dataCriacao = dataCriacao;
    }

    public static LogAuditoria create(final String aEntidadeNome,
                                      final Long aEntidadeId,
                                      final String aAcaoDesc,
                                      final Long aValorAntigoId,
                                      final Long aValorNovoId,
                                      final Long aUsuarioId) {

        return new LogAuditoria(
                LogAuditoriaId.from(-1L),
                LogAuditoriaUuid.unique(),
                LogAuditoriaStatus.ACTIVE,
                aEntidadeNome,
                aEntidadeId,
                aAcaoDesc != null ? AcaoLog.findByCode(aAcaoDesc) : null,
                aValorAntigoId != null ? Preco.from(aValorAntigoId) : null,
                aValorNovoId != null ? Preco.from(aValorNovoId) : null,
                aUsuarioId != null ? Usuario.from(aUsuarioId) : null,
                Instant.now());
    }

    public static LogAuditoria update(final Long aId,
                                      final String aUuid,
                                      final String aStatusCode,
                                      final String aEntidadeNome,
                                      final Long aEntidadeId,
                                      final String aAcaoCode,
                                      final Preco aValorAntigo,
                                      final Preco aValorNovo,
                                      final Usuario aUsuario,
                                      final Instant aDataCriacao) {

        return new LogAuditoria(
                aId != null ? LogAuditoriaId.from(aId) : null,
                aUuid != null ? LogAuditoriaUuid.from(aUuid) : null,
                aStatusCode != null ? LogAuditoriaStatus.findByCode(aStatusCode) : null,
                aEntidadeNome,
                aEntidadeId,
                aAcaoCode != null ? AcaoLog.findByCode(aAcaoCode) : null,
                aValorAntigo,
                aValorNovo,
                aUsuario,
                aDataCriacao);
    }

    public static LogAuditoria patch(final String aStatusCode,
                                     final String aEntidadeNome,
                                     final Long aEntidadeId,
                                     final String aAcaoCode,
                                     final Preco aValorAntigo,
                                     final Preco aValorNovo,
                                     final Usuario aUsuario,
                                     final LogAuditoria aExisting) {

        return new LogAuditoria(
                aExisting.getId(),
                aExisting.getUuid(),
                aStatusCode != null ? LogAuditoriaStatus.findByCode(aStatusCode) : aExisting.getStatusCode(),
                aEntidadeNome != null ? aEntidadeNome : aExisting.getEntidadeNome(),
                aEntidadeId != null ? aEntidadeId : aExisting.getEntidadeId(),
                aAcaoCode != null ? AcaoLog.findByCode(aAcaoCode) : aExisting.getAcao(),
                aValorAntigo != null ? aValorAntigo : aExisting.getValorAntigo(),
                aValorNovo != null ? aValorNovo : aExisting.getValorNovo(),
                aUsuario != null ? aUsuario : aExisting.getUsuario(),
                aExisting.getDataCriacao());
    }

    public static LogAuditoria from(final Long aId,
                                    final String aUuid,
                                    final String aStatusDesc,
                                    final String aEntidadeNome,
                                    final Long aEntidadeId,
                                    final String aAcaoDesc,
                                    final Preco aValorAntigo,
                                    final Preco aValorNovo,
                                    final Usuario aUsuario,
                                    final Instant aDataCriacao) {

        return new LogAuditoria(
                aId != null ? LogAuditoriaId.from(aId) : null,
                aUuid != null ? LogAuditoriaUuid.from(aUuid) : null,
                aStatusDesc != null ? LogAuditoriaStatus.findByDesc(aStatusDesc) : null,
                aEntidadeNome,
                aEntidadeId,
                aAcaoDesc != null ? AcaoLog.findByDesc(aAcaoDesc) : null,
                aValorAntigo,
                aValorNovo,
                aUsuario,
                aDataCriacao);
    }

    public static LogAuditoria from(final Long aId) {

        return new LogAuditoria(
                aId != null ? LogAuditoriaId.from(aId) : null,
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

    public static LogAuditoria from(final String aUuid) {

        return new LogAuditoria(
                null,
                aUuid != null ? LogAuditoriaUuid.from(aUuid) : null,
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

        new LogAuditoriaValidator(aHandler, this).validate();
    }

    public LogAuditoriaUuid getUuid() {
        return uuid;
    }
    public LogAuditoriaStatus getStatusCode() {
        return statusCode;
    }
    public String getEntidadeNome() {
        return entidadeNome;
    }
    public Long getEntidadeId() {
        return entidadeId;
    }
    public AcaoLog getAcao() {
        return acao;
    }
    public Preco getValorAntigo() {
        return valorAntigo;
    }
    public Preco getValorNovo() {
        return valorNovo;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Instant getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        LogAuditoria that = (LogAuditoria) o;

        return Objects.equals(uuid, that.uuid) &&
                statusCode == that.statusCode &&
                Objects.equals(entidadeNome, that.entidadeNome) &&
                Objects.equals(entidadeId, that.entidadeId) &&
                acao == that.acao &&
                Objects.equals(valorAntigo, that.valorAntigo) &&
                Objects.equals(valorNovo, that.valorNovo) &&
                Objects.equals(usuario, that.usuario) &&
                Objects.equals(dataCriacao, that.dataCriacao);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                entidadeNome,
                entidadeId,
                acao,
                valorAntigo,
                valorNovo,
                usuario,
                dataCriacao);
    }
}
