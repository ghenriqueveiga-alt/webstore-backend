package com.hvs.webstore.back.app.output.webstore.endereco;

import com.hvs.webstore.back.domain.entity.webstore.endereco.Endereco;

public record PatchEnderecoOutput(Long aId,
                                  String aUuid,
                                  String aMessage) {

    public static PatchEnderecoOutput from(Endereco aEndereco) {

        return new PatchEnderecoOutput(
                aEndereco.getId().getValue(),
                aEndereco.getUuid().getValue(),
                "The Endereco with id: " + aEndereco.getUuid().getValue() + " has been successfully patched.");
    }
}
