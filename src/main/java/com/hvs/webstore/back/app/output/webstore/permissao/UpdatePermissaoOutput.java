package com.hvs.webstore.back.app.output.webstore.permissao;

import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;

public record UpdatePermissaoOutput(Long aId,
                                    String aUuid,
                                    String aMessage) {

    public static UpdatePermissaoOutput from(Permissao aPermissao) {

        return new UpdatePermissaoOutput(
                aPermissao.getId().getValue(),
                aPermissao.getUuid().getValue(),
                "Permissao updated: " + aPermissao.getUuid().getValue());
    }
}
