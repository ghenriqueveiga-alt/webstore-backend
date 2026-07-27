package com.hvs.webstore.back.app.output.webstore.permissao;

import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;

public record ReadPermissaoOutput(Long aId,
                                  String aUuid,
                                  String aStatusDesc,
                                  String aNome,
                                  String aChave,
                                  String aDescricao) {

    public static ReadPermissaoOutput from(Permissao aPermissao) {

        return new ReadPermissaoOutput(
                aPermissao.getId().getValue(),
                aPermissao.getUuid().getValue(),
                aPermissao.getStatusCode().getDesc(),
                aPermissao.getNome(),
                aPermissao.getChave(),
                aPermissao.getDescricao());
    }

    public static ReadPermissaoOutput fromSimple(Permissao aPermissao) {

        return new ReadPermissaoOutput(
                aPermissao.getId().getValue(),
                aPermissao.getUuid().getValue(),
                null,
                null,
                null,
                null);
    }
}
