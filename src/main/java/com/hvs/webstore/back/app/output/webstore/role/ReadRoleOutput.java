package com.hvs.webstore.back.app.output.webstore.role;

import com.hvs.webstore.back.domain.entity.webstore.role.Role;
import java.util.List;

public record ReadRoleOutput(Long aId,
                              String aUuid,
                              String aStatusDesc,
                              String aNome,
                              String aDescricao,
                               List<Long> aPermissaoIds) {

    public static ReadRoleOutput from(Role aRole) {

        return new ReadRoleOutput(
                aRole.getId().getValue(),
                aRole.getUuid().getValue(),
                aRole.getStatusCode().getDesc(),
                aRole.getNome(),
                aRole.getDescricao(),
                aRole.getPermissoes() != null ? aRole.getPermissoes().stream().map(p -> p.getId().getValue()).toList() : null);
    }

    public static ReadRoleOutput fromSimple(Role aRole) {

        return new ReadRoleOutput(
                aRole.getId().getValue(),
                aRole.getUuid().getValue(),
                null,
                null,
                null,
                null);
    }
}
