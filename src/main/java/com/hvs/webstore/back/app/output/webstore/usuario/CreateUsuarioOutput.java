package com.hvs.webstore.back.app.output.webstore.usuario;

import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;

public record CreateUsuarioOutput(Long aId,
                                  String aUuid,
                                  String aMessage) {

    public static CreateUsuarioOutput from(Usuario aUsuario) {

        return new CreateUsuarioOutput(
                aUsuario.getId().getValue(),
                aUsuario.getUuid().getValue(),
                "The Usuario with id: " + aUsuario.getUuid().getValue() + " has been successfully created.");
    }
}
