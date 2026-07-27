package com.hvs.webstore.back.app.output.webstore.permissao;

import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;

public record DeletePermissaoOutput(Long aId,
                                    String aUuid,
                                    String aMessage) {

    public static DeletePermissaoOutput from(Permissao aPermissao) {

        return new DeletePermissaoOutput(
                aPermissao.getId().getValue(),
                aPermissao.getUuid().getValue(),
                "Permissao deleted: " + aPermissao.getUuid().getValue());
    }
}
