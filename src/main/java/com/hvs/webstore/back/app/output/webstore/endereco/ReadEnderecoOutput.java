package com.hvs.webstore.back.app.output.webstore.endereco;

import com.hvs.webstore.back.domain.entity.webstore.endereco.Endereco;

public record ReadEnderecoOutput(Long aId,
                                 String aUuid,
                                 String aStatusDesc,
                                 Long aUsuarioId,
                                 String aLogradouro,
                                 String aNumero,
                                 String aComplemento,
                                 String aBairro,
                                 String aCidade,
                                 String aEstado,
                                 String aCep,
                                 Boolean aPrincipal) {

    public static ReadEnderecoOutput from(Endereco aEndereco) {

        return new ReadEnderecoOutput(
                aEndereco.getId().getValue(),
                aEndereco.getUuid().getValue(),
                aEndereco.getStatusCode().getDesc(),
                aEndereco.getUsuario() != null ? aEndereco.getUsuario().getId().getValue() : null,
                aEndereco.getLogradouro(),
                aEndereco.getNumero(),
                aEndereco.getComplemento(),
                aEndereco.getBairro(),
                aEndereco.getCidade(),
                aEndereco.getEstado(),
                aEndereco.getCep(),
                aEndereco.getPrincipal());
    }

    public static ReadEnderecoOutput fromSimple(Endereco aEndereco) {

        return new ReadEnderecoOutput(
                aEndereco.getId().getValue(),
                aEndereco.getUuid().getValue(),
                null,
                null,
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
