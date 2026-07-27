package com.hvs.webstore.back.app.output.webstore.usuario;

import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;

import java.time.Instant;

public record ReadUsuarioOutput(Long aId,
                                String aUuid,
                                String aStatusDesc,
                                String aNome,
                                String aEmail,
                                String aSenha,
                                String aTelefone,
                                Instant aDataCadastro) {

    public static ReadUsuarioOutput from(Usuario aUsuario) {

        return new ReadUsuarioOutput(
                aUsuario.getId().getValue(),
                aUsuario.getUuid().getValue(),
                aUsuario.getStatusCode().getDesc(),
                aUsuario.getNome(),
                aUsuario.getEmail(),
                aUsuario.getSenha(),
                aUsuario.getTelefone(),
                aUsuario.getDataCadastro());
    }

    public static ReadUsuarioOutput fromSimple(Usuario aUsuario) {

        return new ReadUsuarioOutput(
                aUsuario.getId().getValue(),
                aUsuario.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
