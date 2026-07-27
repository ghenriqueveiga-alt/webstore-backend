package com.hvs.webstore.back.app.output.webstore.logauditoria;

import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoria;

public record CreateLogAuditoriaOutput(Long aId,
                                       String aUuid,
                                       String aMessage) {

    public static CreateLogAuditoriaOutput from(LogAuditoria aLogAuditoria) {

        return new CreateLogAuditoriaOutput(
                aLogAuditoria.getId().getValue(),
                aLogAuditoria.getUuid().getValue(),
                "The LogAuditoria with id: " + aLogAuditoria.getUuid().getValue() + " has been successfully created.");
    }
}
