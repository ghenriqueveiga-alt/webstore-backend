package com.hvs.webstore.back.app.output.webstore.permissao;

import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;

public record PatchPermissaoOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static PatchPermissaoOutput from(Permissao aPermissao) {

        return new PatchPermissaoOutput(
                aPermissao.getId().getValue(),
                aPermissao.getUuid().getValue(),
                "Permissao patched: " + aPermissao.getUuid().getValue());
    }
}
