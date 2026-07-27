package com.hvs.webstore.back.app.output.webstore.permissao;

import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;

public record CreatePermissaoOutput(Long aId,
                                    String aUuid,
                                    String aMessage) {

    public static CreatePermissaoOutput from(Permissao aPermissao) {

        return new CreatePermissaoOutput(
                aPermissao.getId().getValue(),
                aPermissao.getUuid().getValue(),
                "Permissao created: " + aPermissao.getUuid().getValue());
    }
}
