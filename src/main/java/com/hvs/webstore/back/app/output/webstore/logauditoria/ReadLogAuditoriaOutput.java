package com.hvs.webstore.back.app.output.webstore.logauditoria;

import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoria;

import java.time.Instant;

public record ReadLogAuditoriaOutput(Long aId,
                                     String aUuid,
                                     String aStatusDesc,
                                     String aEntidadeNome,
                                     Long aEntidadeId,
                                     String aAcaoDesc,
                                     Long aValorAntigoId,
                                     Long aValorNovoId,
                                     Long aUsuarioId,
                                     Instant aDataCriacao) {

    public static ReadLogAuditoriaOutput from(LogAuditoria aLogAuditoria) {

        return new ReadLogAuditoriaOutput(
                aLogAuditoria.getId().getValue(),
                aLogAuditoria.getUuid().getValue(),
                aLogAuditoria.getStatusCode().getDesc(),
                aLogAuditoria.getEntidadeNome(),
                aLogAuditoria.getEntidadeId(),
                aLogAuditoria.getAcao().getDesc(),
                aLogAuditoria.getValorAntigo() != null ? aLogAuditoria.getValorAntigo().getId().getValue() : null,
                aLogAuditoria.getValorNovo() != null ? aLogAuditoria.getValorNovo().getId().getValue() : null,
                aLogAuditoria.getUsuario() != null ? aLogAuditoria.getUsuario().getId().getValue() : null,
                aLogAuditoria.getDataCriacao());
    }

    public static ReadLogAuditoriaOutput fromSimple(LogAuditoria aLogAuditoria) {

        return new ReadLogAuditoriaOutput(
                aLogAuditoria.getId().getValue(),
                aLogAuditoria.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
