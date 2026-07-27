package com.hvs.webstore.back.app.output.webstore.endereco;

import com.hvs.webstore.back.domain.entity.webstore.endereco.Endereco;

public record UpdateEnderecoOutput(Long aId,
                                   String aUuid,
                                   String aMessage) {

    public static UpdateEnderecoOutput from(Endereco aEndereco) {

        return new UpdateEnderecoOutput(
                aEndereco.getId().getValue(),
                aEndereco.getUuid().getValue(),
                "The Endereco with id: " + aEndereco.getUuid().getValue() + " has been successfully updated.");
    }
}
